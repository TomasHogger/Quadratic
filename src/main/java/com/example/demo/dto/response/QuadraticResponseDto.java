package com.example.demo.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class QuadraticResponseDto {
    private boolean isSolvable;
    private BigDecimal discriminant;
    private BigDecimal x1;
    private BigDecimal x2;
}
