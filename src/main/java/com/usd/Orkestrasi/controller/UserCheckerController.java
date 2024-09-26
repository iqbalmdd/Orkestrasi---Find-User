package com.usd.Orkestrasi.controller;

import com.usd.Orkestrasi.constant.APIUrl;
import com.usd.Orkestrasi.dto.request.UserCheckerRequest;
import com.usd.Orkestrasi.dto.response.CommonResponse;
import com.usd.Orkestrasi.dto.response.UserCheckerResponse;
import com.usd.Orkestrasi.service.UserCheckerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(APIUrl.USERCHECKER_API)
public class UserCheckerController {
    private final UserCheckerService userCheckerService;

    @PostMapping
    public ResponseEntity<CommonResponse<UserCheckerResponse>> checkUser(@RequestBody UserCheckerRequest request){
        UserCheckerResponse userCheckerResponse = userCheckerService.checkUser(request);

        CommonResponse<UserCheckerResponse> response = CommonResponse.<UserCheckerResponse>builder()
                .statusCode(HttpStatus.OK.value())
                .message("Success Checking User")
                .data(userCheckerResponse)
                .build();
        return ResponseEntity.ok(response);
    }
}
