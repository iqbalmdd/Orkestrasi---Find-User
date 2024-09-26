package com.usd.Orkestrasi.dto.response;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DecisionResponse {
    private String fullname;
    private float score;
    private String decision;
}
