package com.aldisued.iot.monitoring.repository;

import com.aldisued.iot.monitoring.entity.SensorReading;
import org.springframework.data.jpa.repository.JpaRepository;
import com.aldisued.iot.monitoring.entity.SensorType;
import java.time.LocalDateTime;
import java.util.Optional;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface SensorReadingRepository extends JpaRepository<SensorReading, String> {
    @Query("""
    select avg(reading.value)
    from SensorReading reading
    where reading.sensor.type = :sensorType
      and reading.timestamp between :from and :to
    """)
    Optional<Double> findAverageValueBySensorTypeAndTimeRange(
            @Param("sensorType") SensorType sensorType,
            @Param("from") LocalDateTime from,
            @Param("to") LocalDateTime to);
}
