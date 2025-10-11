package com.example.irctc.repo;

import com.example.irctc.domain.TrainRouteStop;
import com.example.irctc.domain.Train;
import com.example.irctc.domain.Station;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.*;

public interface TrainRouteStopRepository extends JpaRepository<TrainRouteStop, Long> {
  List<TrainRouteStop> findByTrainOrderBySequenceIndexAsc(Train train);
  List<TrainRouteStop> findByTrainAndStation(Train train, Station station);
}
