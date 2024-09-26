package com.usd.Orkestrasi.service.implement;

import com.usd.Orkestrasi.dto.request.DecisionRequest;
import com.usd.Orkestrasi.dto.response.DecisionResponse;
import com.usd.Orkestrasi.service.DecisionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DecisionServiceImplement implements DecisionService {
    @Override
    public List<DecisionResponse> decision(List<DecisionRequest> decisionRequest) {
        List<DecisionResponse> responses = decisionRequest.stream().map(des -> {
            return DecisionResponse.builder()
                    .fullname(des.getFullname())
                    .score(des.getScore())
                    .decision(finalDes(des.getScore()))
                    .build();
        }).toList();
        return responses;
    }

    private String finalDes(float score){
        if (score >= 80) {
            return "AUTO MATCH";
        } else if (score >= 60) {
            return "AMBIGUOUS";
        }
        return "AUTO NOT MATCH";
    }
}
