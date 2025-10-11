package com.example.irctc.repo;

import com.example.irctc.domain.Station;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.*;

public interface StationRepository extends JpaRepository<Station, Long> {
  Optional<Station> findByCode(String code);
}
