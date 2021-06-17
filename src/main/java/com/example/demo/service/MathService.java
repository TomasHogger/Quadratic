package com.example.demo.service;

import com.example.demo.constant.MathConstants;
import com.example.demo.dto.request.QuadraticRequestDto;
import com.example.demo.dto.response.QuadraticResponseDto;
import com.example.demo.mapper.QuadraticMapper;
import com.example.demo.model.Quadratic;
import com.example.demo.repository.QuadraticRepo;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Optional;

import static com.example.demo.constant.MathConstants.DISCRIMINANT_MULTIPLIER;

@Service
public class MathService {

    private final QuadraticRepo quadraticRepo;

    public MathService(QuadraticRepo quadraticRepo) {
        this.quadraticRepo = quadraticRepo;
    }

    public QuadraticResponseDto solveQuadraticEquation(QuadraticRequestDto quadraticRequestDto) {
        Quadratic.QuadraticKey quadraticKey = QuadraticMapper.QuadraticRequestDto2quadraticKey(quadraticRequestDto);
        Optional<Quadratic> quadraticOptional = quadraticRepo.findById(quadraticKey);

        // Предположим, что решается не квадратное уравнение, а что-то более сложное,
        // и достать значение из БД быстрее, чем его рассчитать.
        if (quadraticOptional.isPresent()) {
            return QuadraticMapper.quadratic2QQuadraticResponseDto(quadraticOptional.get());
        }

        // Не используем формулу сокращённого дискриминанта, т.к будет всего на 2 операцию меньше при вычислении
        BigDecimal discriminant = quadraticRequestDto.getB().pow(2).subtract(
                quadraticRequestDto.getA().multiply(quadraticRequestDto.getC()).multiply(DISCRIMINANT_MULTIPLIER)
        );

        Quadratic quadratic;

        if (discriminant.compareTo(BigDecimal.ZERO) < 0) {
            quadratic = new Quadratic(quadraticKey, false, discriminant, null, null);
        } else if (discriminant.equals(BigDecimal.ZERO)) {
            BigDecimal divider = quadraticRequestDto.getA().multiply(MathConstants.QUADRATIC_A_MULTIPLIER);
            BigDecimal x = quadraticRequestDto.getB().negate().divide(divider, MathConstants.QUADRATIC_X_SCALE, RoundingMode.HALF_UP);
            quadratic = new Quadratic(quadraticKey, true, BigDecimal.ZERO, x, x);
        } else {
            BigDecimal divider = quadraticRequestDto.getA().multiply(MathConstants.QUADRATIC_A_MULTIPLIER);
            BigDecimal discriminantSqrt = BigDecimal.valueOf(Math.sqrt(discriminant.doubleValue()));
            BigDecimal x1 = quadraticRequestDto.getB().negate().add(discriminantSqrt).divide(divider, MathConstants.QUADRATIC_X_SCALE, RoundingMode.HALF_UP);
            BigDecimal x2 = quadraticRequestDto.getB().negate().subtract(discriminantSqrt).divide(divider, MathConstants.QUADRATIC_X_SCALE, RoundingMode.HALF_UP);
            quadratic = new Quadratic(quadraticKey, true, BigDecimal.ZERO, x1, x2);
        }

        quadraticRepo.save(quadratic);
        return QuadraticMapper.quadratic2QQuadraticResponseDto(quadratic);
    }
}
