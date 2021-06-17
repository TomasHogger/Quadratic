package com.example.demo.controller.rest;

import com.example.demo.dto.request.QuadraticRequestDto;
import com.example.demo.dto.response.QuadraticResponseDto;
import com.example.demo.service.MathService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class MathController {

    private final MathService mathService;

    public MathController(MathService mathService) {
        this.mathService = mathService;
    }

    @PostMapping("/solve_quadratic_equation/")
    public ResponseEntity<?> solveQuadraticEquation(@RequestBody @Valid QuadraticRequestDto quadraticRequestDto) {
        QuadraticResponseDto quadraticResponseDto = mathService.solveQuadraticEquation(quadraticRequestDto);
        return quadraticResponseDto.isSolvable() ?
                new ResponseEntity<>(quadraticResponseDto, HttpStatus.OK) :
                new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

}
