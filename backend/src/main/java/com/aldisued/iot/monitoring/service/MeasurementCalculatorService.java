package com.aldisued.iot.monitoring.service;


import java.util.List;
import org.springframework.stereotype.Service;
import java.util.ArrayList;

@Service
public class MeasurementCalculatorService {

  public List<Double> filterByAverageDeviation(List<Double> values, double deviation) {
    if (!(deviation >= 0.0 && deviation <= 1.0)) {
      throw new IllegalArgumentException("Deviation must be between 0.0 and 1.0");
    }

    if (values.isEmpty()) {
      return List.of();
    }

    double average = values.stream()
            .mapToDouble(Double::doubleValue)
            .average()
            .orElseThrow();

    double allowedDifference = Math.abs(average) * deviation;
    double lowerBound = average - allowedDifference;
    double upperBound = average + allowedDifference;

    return values.stream()
            .filter(value -> value >= lowerBound && value <= upperBound)
            .toList();
  }

  public List<Double> getMovingAverage(List<Double> values, int windowSize) {
    if (windowSize <= 0 || windowSize > values.size()) {
      throw new IllegalArgumentException(
              "Window size must be between 1 and the number of values");
    }

    int windowCount = values.size() - windowSize + 1;
    List<Double> averages = new ArrayList<>(windowCount);

    for (int start = 0; start < windowCount; start++) {
      double average = values.subList(start, start + windowSize).stream()
              .mapToDouble(Double::doubleValue)
              .average()
              .orElseThrow();

      averages.add(average);
    }

    return averages;
  }

}
