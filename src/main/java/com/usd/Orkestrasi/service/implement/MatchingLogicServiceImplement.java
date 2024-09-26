package com.usd.Orkestrasi.service.implement;

import com.usd.Orkestrasi.dto.request.MatchingLogicRequest;
import com.usd.Orkestrasi.dto.response.MatchingLogicResponse;
import com.usd.Orkestrasi.dto.response.SearchUserResponse;
import com.usd.Orkestrasi.service.MatchingLogicService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.text.similarity.JaroWinklerSimilarity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MatchingLogicServiceImplement implements MatchingLogicService {

    @Override
    public List<MatchingLogicResponse> userScore(MatchingLogicRequest matchingLogicRequest) {
        List<SearchUserResponse> users = matchingLogicRequest.getUsers();
        String userInput = matchingLogicRequest.getNameRequest();
        List<MatchingLogicResponse> responses = users.stream().map(user -> {
            String fullname = user.getFirstName() + " " + user.getLastName();
            float fuzzyScore = calculateFuzzyScore(fullname, userInput);
            return MatchingLogicResponse.builder()
                    .fullname(fullname)
                    .score(fuzzyScore)
                    .build();
        }).toList();
         return responses;
    }

    // Menghitung skor fuzzy menggunakan Jaro-Winkler Similarity
    private float calculateFuzzyScore(String fullname, String userInput) {
        JaroWinklerSimilarity jaroWinkler = new JaroWinklerSimilarity();
        double similarityScore = jaroWinkler.apply(fullname.toLowerCase(), userInput.toLowerCase());
        return (float) (similarityScore * 100);
    }

}
