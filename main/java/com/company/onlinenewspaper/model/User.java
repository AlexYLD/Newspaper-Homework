package com.company.onlinenewspaper.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class User {
    Integer id;
    private String username;
    @JsonIgnore
    private String password;
    private String firstName;
    private String lastName;
    List<Role> roles;

}
