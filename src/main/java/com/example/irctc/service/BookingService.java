package com.example.irctc.service;

import com.example.irctc.domain.*;
import com.example.irctc.repo.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class BookingService {
  private final StationRepository stationRepository;
  private final TrainRepository trainRepository;
  private final TrainRouteStopRepository routeStopRepository;
  private final TrainRunRepository trainRunRepository;
  private final CoachRepository coachRepository;
  private final BookingRepository bookingRepository;

  public BookingService(StationRepository stationRepository,
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

  @Transactional
  public Booking book(String trainNumber, LocalDate date, String fromCode, String toCode, CoachClass coachClass,
                      List<Passenger> passengers) {
    Train train = trainRepository.findByNumber(trainNumber).orElseThrow();
    Station from = stationRepository.findByCode(fromCode).orElseThrow();
    Station to = stationRepository.findByCode(toCode).orElseThrow();

    List<TrainRouteStop> stops = routeStopRepository.findByTrainOrderBySequenceIndexAsc(train);
    TrainRouteStop fromStop = stops.stream().filter(s -> s.getStation().getId().equals(from.getId())).findFirst().orElseThrow();
    TrainRouteStop toStop = stops.stream().filter(s -> s.getStation().getId().equals(to.getId())).findFirst().orElseThrow();
    if (fromStop.getSequenceIndex() >= toStop.getSequenceIndex()) {
      throw new IllegalArgumentException("Invalid boarding/alighting sequence");
    }

    TrainRun run = trainRunRepository.findWithLockByTrainAndRunDate(train, date).orElseThrow();

    int capacity = coachRepository.findByTrainAndCoachClass(train, coachClass).stream().mapToInt(Coach::getTotalSeats).sum();
    int booked = bookingRepository.findActiveOverlappingBookings(run, fromStop.getSequenceIndex(), toStop.getSequenceIndex())
        .stream().filter(b -> b.getCoachClass() == coachClass)
        .mapToInt(Booking::getSeatCount).sum();

    int seatsRequested = passengers.size();
    if (booked + seatsRequested > capacity) {
      throw new IllegalStateException("Not enough availability");
    }

    Booking booking = new Booking(generatePNR(), run, fromStop, toStop, coachClass, seatsRequested, passengers);
    return bookingRepository.save(booking);
  }

  public Optional<Booking> getByPNR(String pnr) {
    return bookingRepository.findByPnr(pnr);
  }

  private String generatePNR() {
    long n = ThreadLocalRandom.current().nextLong(1_000_000_000L, 9_999_999_999L);
    return Long.toString(n);
  }
}
