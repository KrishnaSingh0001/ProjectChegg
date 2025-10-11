package com.example.irctc.repo;

import com.example.irctc.domain.TrainRun;
import com.example.irctc.domain.Train;
import org.springframework.data.jpa.repository.Lock;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDate;
import java.util.*;

public interface TrainRunRepository extends JpaRepository<TrainRun, Long> {
  Optional<TrainRun> findByTrainAndRunDate(Train train, LocalDate runDate);

  @Lock(LockModeType.PESSIMISTIC_WRITE)
  Optional<TrainRun> findWithLockByTrainAndRunDate(Train train, LocalDate runDate);
  List<TrainRun> findByRunDate(LocalDate runDate);
}
