package com.example.portfolioservice.model;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserDto {
    private Long userId;
    private String name;
    private int age;
    private Long salary;
}
