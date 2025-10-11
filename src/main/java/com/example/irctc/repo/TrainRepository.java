package com.example.irctc.repo;

import com.example.irctc.domain.Train;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.*;

public interface TrainRepository extends JpaRepository<Train, Long> {
  Optional<Train> findByNumber(String number);
}
