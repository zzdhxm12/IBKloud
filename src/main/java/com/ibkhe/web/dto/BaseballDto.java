package com.ibkhe.web.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor

public class BaseballDto {
    private final int[] given;
    private final int[] input;
}
