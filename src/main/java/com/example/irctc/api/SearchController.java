package com.example.irctc.api;

import com.example.irctc.api.dto.*;
import com.example.irctc.domain.*;
import com.example.irctc.service.SearchService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/search")
public class SearchController {
  private final SearchService searchService;

  public SearchController(SearchService searchService) {
    this.searchService = searchService;
  }

  @PostMapping
  public ResponseEntity<List<SearchResponse>> search(@Validated @RequestBody SearchRequest req) {
    List<SearchService.SearchResult> results = searchService.search(
        req.getFrom(), req.getTo(), req.getDate(), req.getCoachClass());
    List<SearchResponse> resp = results.stream().map(r -> {
      SearchResponse sr = new SearchResponse();
      sr.setTrainNumber(r.getRun().getTrain().getNumber());
      sr.setTrainName(r.getRun().getTrain().getName());
      sr.setFrom(r.getFromStop().getStation().getCode());
      sr.setTo(r.getToStop().getStation().getCode());
      sr.setDate(r.getRun().getRunDate().toString());
      sr.setCoachClass(r.getCoachClass().name());
      sr.setAvailable(r.getAvailableSeats());
      return sr;
    }).collect(Collectors.toList());
    return ResponseEntity.ok(resp);
  }
}
