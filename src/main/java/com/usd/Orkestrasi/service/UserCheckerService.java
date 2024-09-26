package com.usd.Orkestrasi.service;

import com.usd.Orkestrasi.dto.request.UserCheckerRequest;
import com.usd.Orkestrasi.dto.response.UserCheckerResponse;

public interface UserCheckerService {
    UserCheckerResponse checkUser(UserCheckerRequest userCheckerRequest);
}
