package com.example.demo.model;

import lombok.*;

import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Table(name = "quadratic")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Quadratic {

    @EmbeddedId
    private QuadraticKey quadraticKey;
    @NonNull
    private boolean isSolvable;
    private BigDecimal discriminant;
    private BigDecimal x1;
    private BigDecimal x2;

    @Embeddable
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class QuadraticKey implements Serializable {
        @NonNull
        private BigDecimal a;
        @NonNull
        private BigDecimal b;
        @NonNull
        private BigDecimal c;
    }

}
