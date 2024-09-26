package com.usd.Orkestrasi.service.implement;

import com.usd.Orkestrasi.constant.APIUrl;
import com.usd.Orkestrasi.dto.request.DecisionRequest;
import com.usd.Orkestrasi.dto.request.MatchingLogicRequest;
import com.usd.Orkestrasi.dto.request.UserCheckerRequest;
import com.usd.Orkestrasi.dto.response.*;
import com.usd.Orkestrasi.service.UserCheckerService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserCheckerServiceImplement implements UserCheckerService {
    private final WebClient.Builder webClientBuilder;

    @Override
    public UserCheckerResponse checkUser(UserCheckerRequest request) {
        // USER API
        CommonResponse<List<SearchUserResponse>> userResponse = webClientBuilder.build()
                .get()
                .uri(uriBuilder -> uriBuilder
                        .path(APIUrl.USER_API)
                        .queryParam("nameContain", request.getNameRequest())
                        .build())
                .header("Content-Type", "application/json")
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<CommonResponse<List<SearchUserResponse>>>() {})
                .block();

        // Cek status respons
        if (userResponse == null || userResponse.getStatusCode() != 200) {
            throw new RuntimeException("Failed to fetch users: " + (userResponse != null ? userResponse.getMessage() : "No response"));
        }

        List<SearchUserResponse> users = userResponse.getData();

        // MATCHING LOGIC API
        MatchingLogicRequest matchingLogicRequest = MatchingLogicRequest.builder()
                .nameRequest(request.getNameRequest())
                .users(users)
                .build();

        CommonResponse<List<MatchingLogicResponse>> matchingResponse = webClientBuilder.build()
                .post()
                .uri(APIUrl.MATCHING_API)
                .header("Content-Type", "application/json")
                .bodyValue(matchingLogicRequest)
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<CommonResponse<List<MatchingLogicResponse>>>() {})
                .block();

        // Cek status respons
        if (matchingResponse == null || matchingResponse.getStatusCode() != 200) {
            throw new RuntimeException("Failed to fetch matching logic responses: " + (matchingResponse != null ? matchingResponse.getMessage() : "No response"));
        }

        List<MatchingLogicResponse> matchingLogicResponses = matchingResponse.getData();

        // DECISION API
        List<DecisionRequest> decisionRequests = matchingLogicResponses.stream()
                .map(matchingLogic -> DecisionRequest.builder()
                        .fullname(matchingLogic.getFullname())
                        .score(matchingLogic.getScore())
                        .build())
                .collect(Collectors.toList());

        CommonResponse<List<DecisionResponse>> decisionResponse = webClientBuilder.build()
                .post()
                .uri(APIUrl.DECISION_API)
                .header("Content-Type", "application/json")
                .bodyValue(decisionRequests)
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<CommonResponse<List<DecisionResponse>>>() {})
                .block();

        // Cek status respons
        if (decisionResponse == null || decisionResponse.getStatusCode() != 200) {
            throw new RuntimeException("Failed to fetch decision responses: " + (decisionResponse != null ? decisionResponse.getMessage() : "No response"));
        }

        List<DecisionResponse> decisionResponses = decisionResponse.getData();
        String result = determineResult(decisionResponses);

        return UserCheckerResponse.builder()
                .decision(result)
                .build();
    }

    private String determineResult(List<DecisionResponse> decisionResponses) {
        System.out.println("Decision Responses for Result Determination: " + decisionResponses);
        boolean autoMatchFound = false;
        boolean ambiguousMatchFound = false;

        for (DecisionResponse response : decisionResponses) {
            if ("AUTO MATCH".equals(response.getDecision())) {
                autoMatchFound = true;
                break;
            } else if ("AMBIGUOUS".equals(response.getDecision())) {
                ambiguousMatchFound = true;
            }
        }

        if (autoMatchFound) {
            return "USER DETECTED";
        } else if (ambiguousMatchFound) {
            return "CANNOT DETERMINE";
        } else {
            return "USER NOT DETECTED";
        }
    }
}
