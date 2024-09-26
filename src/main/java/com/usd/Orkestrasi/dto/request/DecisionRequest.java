package com.usd.Orkestrasi.dto.request;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DecisionRequest {
    private String fullname;
    private float score;
}
