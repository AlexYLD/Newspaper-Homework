package com.company.onlinenewspaper.model;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class UserSession {
    private String sessionId;
    private User user;
    private Boolean isValid;
}
