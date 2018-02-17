package com.company.onlinenewspaper.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Article {
    Integer id;
    String title;
    String body;
    User createdBy;
    Date createdOn;
    List<Category> categories;
    List<Comment> comments;
}
