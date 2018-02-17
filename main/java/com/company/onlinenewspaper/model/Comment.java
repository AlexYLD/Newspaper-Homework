package com.company.onlinenewspaper.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Comment {
    Integer id;
    String body;
    User createdBy;
    Date createdOn;

}
