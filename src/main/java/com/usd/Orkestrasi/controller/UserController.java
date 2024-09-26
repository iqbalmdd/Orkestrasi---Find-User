package com.usd.Orkestrasi.controller;

import com.usd.Orkestrasi.constant.APIUrl;
import com.usd.Orkestrasi.dto.request.SearchUserRequest;
import com.usd.Orkestrasi.dto.response.CommonResponse;
import com.usd.Orkestrasi.dto.response.SearchUserResponse;
import com.usd.Orkestrasi.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(APIUrl.USER_API)
public class UserController {
    private final UserService userService;

    @GetMapping
    public ResponseEntity<CommonResponse<List<SearchUserResponse>>> getAll (
            @RequestParam(name = "nameContain", required = false) String nameContain
    ) {
        SearchUserRequest request = SearchUserRequest.builder()
                .nameRequest(nameContain)
                .build();

        List<SearchUserResponse> allUser = userService.getAll(request);
        CommonResponse<List<SearchUserResponse>> response = CommonResponse.<List<SearchUserResponse>>builder()
                .statusCode(HttpStatus.OK.value())
                .message("Success Get All User")
                .data(allUser)
                .build();
        return ResponseEntity.ok(response);
    }
}
