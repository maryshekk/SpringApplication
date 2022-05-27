package com.example.springapp.model;

import lombok.*;

@Data
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class EmployeeModel {
    private Long id;
    private String position;
}
