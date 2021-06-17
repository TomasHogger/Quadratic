package com.example.demo.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.NotNull;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class QuadraticRequestDto {
    @NotNull
    private BigDecimal a;
    @NotNull
    private BigDecimal b;
    @NotNull
    private BigDecimal c;
}
