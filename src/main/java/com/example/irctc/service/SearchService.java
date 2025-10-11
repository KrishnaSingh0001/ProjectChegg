package com.example.irctc.service;

import com.example.irctc.domain.*;
import com.example.irctc.repo.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class SearchService {
  private final StationRepository stationRepository;
  private final TrainRepository trainRepository;
  private final TrainRouteStopRepository routeStopRepository;
  private final TrainRunRepository trainRunRepository;
  private final CoachRepository coachRepository;
  private final BookingRepository bookingRepository;

  public SearchService(StationRepository stationRepository,
                       TrainRepository trainRepository,
                       TrainRouteStopRepository routeStopRepository,
                       TrainRunRepository trainRunRepository,
                       CoachRepository coachRepository,
                       BookingRepository bookingRepository) {
    this.stationRepository = stationRepository;
    this.trainRepository = trainRepository;
    this.routeStopRepository = routeStopRepository;
    this.trainRunRepository = trainRunRepository;
    this.coachRepository = coachRepository;
    this.bookingRepository = bookingRepository;
  }

  @Transactional(readOnly = true)
  public List<SearchResult> search(String fromCode, String toCode, LocalDate date, CoachClass coachClass) {
    Station from = stationRepository.findByCode(fromCode).orElseThrow();
    Station to = stationRepository.findByCode(toCode).orElseThrow();

    List<TrainRun> runs = trainRunRepository.findByRunDate(date);

    List<SearchResult> results = new ArrayList<>();
    for (TrainRun run : runs) {
      List<TrainRouteStop> stops = routeStopRepository.findByTrainOrderBySequenceIndexAsc(run.getTrain());
      Optional<TrainRouteStop> fromStopOpt = stops.stream().filter(s -> s.getStation().getId().equals(from.getId())).findFirst();
      Optional<TrainRouteStop> toStopOpt = stops.stream().filter(s -> s.getStation().getId().equals(to.getId())).findFirst();
      if (fromStopOpt.isEmpty() || toStopOpt.isEmpty()) continue;
      TrainRouteStop fromStop = fromStopOpt.get();
      TrainRouteStop toStop = toStopOpt.get();
      if (fromStop.getSequenceIndex() >= toStop.getSequenceIndex()) continue;

      int capacity = coachRepository.findByTrainAndCoachClass(run.getTrain(), coachClass)
          .stream().mapToInt(c -> c.getTotalSeats()).sum();

      int booked = bookingRepository.findActiveOverlappingBookings(run, fromStop.getSequenceIndex(), toStop.getSequenceIndex())
          .stream().filter(b -> b.getCoachClass() == coachClass)
          .mapToInt(Booking::getSeatCount).sum();

      int available = Math.max(0, capacity - booked);
      if (available > 0) {
        results.add(new SearchResult(run, fromStop, toStop, coachClass, available));
      }
    }
    return results;
  }

  public static class SearchResult {
    private final TrainRun run;
    private final TrainRouteStop fromStop;
    private final TrainRouteStop toStop;
    private final CoachClass coachClass;
    private final int availableSeats;

    public SearchResult(TrainRun run, TrainRouteStop fromStop, TrainRouteStop toStop, CoachClass coachClass, int availableSeats) {
      this.run = run;
      this.fromStop = fromStop;
      this.toStop = toStop;
      this.coachClass = coachClass;
      this.availableSeats = availableSeats;
    }

    public TrainRun getRun() { return run; }
    public TrainRouteStop getFromStop() { return fromStop; }
    public TrainRouteStop getToStop() { return toStop; }
    public CoachClass getCoachClass() { return coachClass; }
    public int getAvailableSeats() { return availableSeats; }
  }
}
