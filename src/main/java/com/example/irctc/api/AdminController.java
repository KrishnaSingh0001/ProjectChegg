package com.example.irctc.api;

import com.example.irctc.domain.*;
import com.example.irctc.repo.*;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalTime;

@RestController
@RequestMapping("/api/admin")
public class AdminController {
  private final StationRepository stationRepository;
  private final TrainRepository trainRepository;
  private final TrainRouteStopRepository routeStopRepository;
  private final TrainRunRepository trainRunRepository;
  private final CoachRepository coachRepository;

  public AdminController(StationRepository stationRepository, TrainRepository trainRepository,
                         TrainRouteStopRepository routeStopRepository, TrainRunRepository trainRunRepository,
                         CoachRepository coachRepository) {
    this.stationRepository = stationRepository;
    this.trainRepository = trainRepository;
    this.routeStopRepository = routeStopRepository;
    this.trainRunRepository = trainRunRepository;
    this.coachRepository = coachRepository;
  }

  @PostMapping("/seed")
  @Transactional
  public ResponseEntity<String> seed() {
    Station ndls = stationRepository.save(new Station("NDLS", "New Delhi"));
    Station agc = stationRepository.save(new Station("AGC", "Agra Cantt"));
    Station bpl = stationRepository.save(new Station("BPL", "Bhopal Jn"));
    Station bct = stationRepository.save(new Station("BCT", "Mumbai Central"));

    Train t = trainRepository.save(new Train("12952", "Rajdhani Express"));

    routeStopRepository.save(new TrainRouteStop(t, ndls, 0, LocalTime.of(0, 0), LocalTime.of(16, 55)));
    routeStopRepository.save(new TrainRouteStop(t, agc, 1, LocalTime.of(19, 40), LocalTime.of(19, 45)));
    routeStopRepository.save(new TrainRouteStop(t, bpl, 2, LocalTime.of(0, 15), LocalTime.of(0, 20)));
    routeStopRepository.save(new TrainRouteStop(t, bct, 3, LocalTime.of(8, 15), LocalTime.of(0, 0)));

    coachRepository.save(new Coach(t, CoachClass.THIRD_AC, "B1", 64));
    coachRepository.save(new Coach(t, CoachClass.THIRD_AC, "B2", 64));
    coachRepository.save(new Coach(t, CoachClass.SECOND_AC, "A1", 48));
    coachRepository.save(new Coach(t, CoachClass.SLEEPER, "S1", 72));

    trainRunRepository.save(new TrainRun(t, LocalDate.now().plusDays(1)));
    trainRunRepository.save(new TrainRun(t, LocalDate.now().plusDays(2)));

    return ResponseEntity.ok("Seeded");
  }
}
