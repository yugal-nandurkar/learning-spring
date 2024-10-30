package com.microteam.spring_in_5_steps.basic;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@Primary
public class BubbleSortAlgorithm implements SortAlgorithm {
    public int[] sort (int[] numbers) {
        // Logic for Bubble Sort
        return numbers;
    }
}