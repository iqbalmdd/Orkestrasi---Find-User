package com.usd.Orkestrasi.dto.response;

import lombok.*;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MatchingLogicResponse {
    private String fullname;
    private float score;
}
