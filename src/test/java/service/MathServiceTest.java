package service;

import com.example.demo.dto.request.QuadraticRequestDto;
import com.example.demo.dto.response.QuadraticResponseDto;
import com.example.demo.model.Quadratic;
import com.example.demo.repository.QuadraticRepo;
import com.example.demo.service.MathService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

import java.math.BigDecimal;
import java.util.Optional;

public class MathServiceTest {

    private static QuadraticRepo quadraticRepo;
    private static MathService mathService;

    @BeforeAll
    public static void setUp() {
        quadraticRepo = Mockito.mock(QuadraticRepo.class);
        mathService = new MathService(quadraticRepo);
    }

    @Test
    public void testQuadraticTwoDecisions() {
        BigDecimal x1Expected = new BigDecimal("0.0000");
        BigDecimal x2Expected = new BigDecimal("-6.0000");
        QuadraticResponseDto actual = mathService.solveQuadraticEquation(
                new QuadraticRequestDto(new BigDecimal(1), new BigDecimal(6), new BigDecimal(0)));

        Assertions.assertEquals(x1Expected, actual.getX1());
        Assertions.assertEquals(x2Expected, actual.getX2());
    }

    @Test
    public void testQuadraticOneDecision() {
        BigDecimal xExpected = new BigDecimal("0.2500");
        QuadraticResponseDto actual = mathService.solveQuadraticEquation(
                new QuadraticRequestDto(new BigDecimal(16), new BigDecimal(-8), new BigDecimal(1)));

        Assertions.assertEquals(xExpected, actual.getX1());
        Assertions.assertEquals(xExpected, actual.getX2());
    }

    @Test
    public void testQuadraticNoDecision() {
        QuadraticResponseDto actual = mathService.solveQuadraticEquation(
                new QuadraticRequestDto(new BigDecimal(1), new BigDecimal(2), new BigDecimal(3)));

        Assertions.assertFalse(actual.isSolvable());
    }

    @Test
    public void testQuadraticSaveToDB() {
        mathService.solveQuadraticEquation(
                new QuadraticRequestDto(new BigDecimal(1), new BigDecimal(2), new BigDecimal(3)));

        ArgumentCaptor<Quadratic> argument = ArgumentCaptor.forClass(Quadratic.class);
        Mockito.verify(quadraticRepo).save(argument.capture());
    }

    @Test
    public void testQuadraticTakeCashFromDB() {
        mathService.solveQuadraticEquation(
                new QuadraticRequestDto(new BigDecimal(1), new BigDecimal(2), new BigDecimal(3)));

        ArgumentCaptor<Quadratic.QuadraticKey> argument1 = ArgumentCaptor.forClass(Quadratic.QuadraticKey.class);
        ArgumentCaptor<Quadratic> argument2 = ArgumentCaptor.forClass(Quadratic.class);

        Mockito.when(quadraticRepo.findById(argument1.capture())).thenReturn(Optional.of(new Quadratic()));

        mathService.solveQuadraticEquation(
                new QuadraticRequestDto(new BigDecimal(1), new BigDecimal(2), new BigDecimal(3)));

        Mockito.verify(quadraticRepo, Mockito.times(1)).save(argument2.capture());
    }

}
