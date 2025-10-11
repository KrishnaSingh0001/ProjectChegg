package com.example.irctc.api;

import com.example.irctc.api.dto.*;
import com.example.irctc.domain.*;
import com.example.irctc.service.BookingService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/booking")
public class BookingController {
  private final BookingService bookingService;

  public BookingController(BookingService bookingService) {
    this.bookingService = bookingService;
  }

  @PostMapping
  public ResponseEntity<BookingResponse> book(@Validated @RequestBody BookingRequest req) {
    List<Passenger> pax = new ArrayList<>();
    for (BookingRequest.Pax p : req.getPassengers()) {
      pax.add(new Passenger(p.name, p.age, p.gender));
    }
    Booking booking = bookingService.book(req.getTrainNumber(), req.getDate(), req.getFrom(), req.getTo(), req.getCoachClass(), pax);
    return ResponseEntity.ok(new BookingResponse(booking.getPnr(), booking.getStatus().name()));
  }
}
