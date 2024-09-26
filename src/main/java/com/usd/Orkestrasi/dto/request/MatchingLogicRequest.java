package com.usd.Orkestrasi.dto.request;

import com.usd.Orkestrasi.dto.response.SearchUserResponse;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MatchingLogicRequest {
    private String nameRequest;
    private List<SearchUserResponse> users;
}
