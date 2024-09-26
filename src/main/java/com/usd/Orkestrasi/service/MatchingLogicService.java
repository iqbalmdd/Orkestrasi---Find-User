package com.usd.Orkestrasi.service;

import com.usd.Orkestrasi.dto.request.MatchingLogicRequest;
import com.usd.Orkestrasi.dto.response.MatchingLogicResponse;

import java.util.List;

public interface MatchingLogicService {
    List<MatchingLogicResponse> userScore(MatchingLogicRequest matchingLogicRequest);
}
