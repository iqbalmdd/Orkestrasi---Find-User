package com.usd.Orkestrasi.dto.response;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SearchUserResponse {
    private String id;
    private String firstName;
    private String lastName;
    private String email;
    private String gender;
}
