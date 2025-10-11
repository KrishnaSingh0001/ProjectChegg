package com.example.irctc.api;

import com.example.irctc.api.dto.PNRResponse;
import com.example.irctc.domain.Booking;
import com.example.irctc.service.PNRService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/pnr")
public class PNRController {
  private final PNRService pnrService;

  public PNRController(PNRService pnrService) {
    this.pnrService = pnrService;
  }

  @GetMapping("/{pnr}")
  public ResponseEntity<PNRResponse> get(@PathVariable String pnr) {
    Booking b = pnrService.getPNR(pnr).orElseThrow();
    PNRResponse resp = new PNRResponse();
    resp.setPnr(b.getPnr());
    resp.setTrainNumber(b.getTrainRun().getTrain().getNumber());
    resp.setFrom(b.getFromStop().getStation().getCode());
    resp.setTo(b.getToStop().getStation().getCode());
    resp.setDate(b.getTrainRun().getRunDate().toString());
    resp.setCoachClass(b.getCoachClass().name());
    resp.setStatus(b.getStatus().name());
    resp.setPassengerNames(b.getPassengers().stream().map(p -> p.getName()).collect(Collectors.toList()));
    return ResponseEntity.ok(resp);
  }
}
