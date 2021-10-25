package com.example.portfolioservice.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserDto {
    private Long userId;
    private String name;
    private int age;
    private Long salary;
    private boolean portfolio;
}
