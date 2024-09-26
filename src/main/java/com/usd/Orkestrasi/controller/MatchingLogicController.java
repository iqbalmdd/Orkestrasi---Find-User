package com.usd.Orkestrasi.controller;

import com.usd.Orkestrasi.constant.APIUrl;
import com.usd.Orkestrasi.dto.request.MatchingLogicRequest;
import com.usd.Orkestrasi.dto.response.CommonResponse;
import com.usd.Orkestrasi.dto.response.MatchingLogicResponse;
import com.usd.Orkestrasi.service.MatchingLogicService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(APIUrl.MATCHING_API)
public class MatchingLogicController {
    private final MatchingLogicService matchingLogicService;

    @PostMapping
    public ResponseEntity<CommonResponse<List<MatchingLogicResponse>>> getScore(@RequestBody MatchingLogicRequest matchingLogicRequest) {
        List<MatchingLogicResponse> matchingLogicResponses = matchingLogicService.userScore(matchingLogicRequest);
        CommonResponse<List<MatchingLogicResponse>> response = CommonResponse.<List<MatchingLogicResponse>>builder()
                .statusCode(HttpStatus.OK.value())
                .message("Success Scoring All Users")
                .data(matchingLogicResponses)
                .build();
        return ResponseEntity.ok(response);
    }
}
