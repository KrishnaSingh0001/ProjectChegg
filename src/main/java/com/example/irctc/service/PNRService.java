package com.example.irctc.service;

import com.example.irctc.domain.Booking;
import com.example.irctc.repo.BookingRepository;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class PNRService {
  private final BookingRepository bookingRepository;

  public PNRService(BookingRepository bookingRepository) {
    this.bookingRepository = bookingRepository;
  }

  public Optional<Booking> getPNR(String pnr) {
    return bookingRepository.findByPnr(pnr);
  }
}
