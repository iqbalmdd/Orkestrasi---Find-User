package com.usd.Orkestrasi.controller;

import com.usd.Orkestrasi.constant.APIUrl;
import com.usd.Orkestrasi.dto.request.DecisionRequest;
import com.usd.Orkestrasi.dto.response.CommonResponse;
import com.usd.Orkestrasi.dto.response.DecisionResponse;
import com.usd.Orkestrasi.service.DecisionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(APIUrl.DECISION_API)
public class DecisionController {
    private final DecisionService decisionService;

    @PostMapping
    public ResponseEntity<CommonResponse<List<DecisionResponse>>> decision (@RequestBody List<DecisionRequest> decisionRequest){
        List<DecisionResponse> allDecision = decisionService.decision(decisionRequest);
        CommonResponse<List<DecisionResponse>> response = CommonResponse.<List<DecisionResponse>>builder()
                .statusCode(HttpStatus.OK.value())
                .message("Success Get Decision")
                .data(allDecision)
                .build();
        return ResponseEntity.ok(response);
    }
}
