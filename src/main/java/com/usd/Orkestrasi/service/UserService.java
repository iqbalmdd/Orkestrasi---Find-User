package com.usd.Orkestrasi.service;

import com.usd.Orkestrasi.dto.request.SearchUserRequest;
import com.usd.Orkestrasi.dto.response.SearchUserResponse;

import java.util.List;

public interface UserService {
 List<SearchUserResponse> getAll(SearchUserRequest searchUserRequest);
}
