package com.solution.atomic;

import lombok.Data;
import lombok.Getter;
import org.springframework.aop.aspectj.InstantiationModelAwarePointcutAdvisor;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

public class StreamCalc {

    @Data
    static class Wrap<T> {
        private T value;
    }

    public static void main(String[] args) {
        List<Integer> arr = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            arr.add(i);
        }

        Wrap<Integer> sum = new Wrap<>();
        arr.forEach(i -> {
            sum.setValue(sum.value + 1);
            // sum.updateAndGet(v -> v + i);
        });
    }
}
