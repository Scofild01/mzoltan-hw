package com.aldisued.iot.monitoring.service;


import java.util.List;
import org.springframework.stereotype.Service;

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

  public List<Double> getMovingAverage(List<Double> data, int windowSize) {
    // TODO: Task 10
    return List.of();
  }

}
