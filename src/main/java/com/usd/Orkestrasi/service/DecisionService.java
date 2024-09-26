package com.usd.Orkestrasi.service;

import com.usd.Orkestrasi.dto.request.DecisionRequest;
import com.usd.Orkestrasi.dto.response.DecisionResponse;

import java.util.List;

public interface DecisionService {
    List<DecisionResponse> decision(List<DecisionRequest> decisionRequest);
}
