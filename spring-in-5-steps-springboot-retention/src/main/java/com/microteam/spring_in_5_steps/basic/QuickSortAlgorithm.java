package com.microteam.spring_in_5_steps.basic;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
@Qualifier("quick")
public class QuickSortAlgorithm implements SortAlgorithm {
    public int[] sort (int[] numbers) {
        // Logic for Quick Sort
        return numbers;
    }
}
