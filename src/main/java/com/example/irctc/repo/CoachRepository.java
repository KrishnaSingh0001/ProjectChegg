package com.example.irctc.repo;

import com.example.irctc.domain.Coach;
import com.example.irctc.domain.Train;
import com.example.irctc.domain.CoachClass;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.*;

public interface CoachRepository extends JpaRepository<Coach, Long> {
  List<Coach> findByTrainAndCoachClass(Train train, CoachClass coachClass);
}
