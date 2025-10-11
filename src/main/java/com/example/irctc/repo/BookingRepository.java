package com.example.irctc.repo;

import com.example.irctc.domain.Booking;
import com.example.irctc.domain.TrainRun;
import com.example.irctc.domain.TrainRouteStop;
import com.example.irctc.domain.BookingStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.*;

public interface BookingRepository extends JpaRepository<Booking, Long> {
  Optional<Booking> findByPnr(String pnr);

  @Query("SELECT b FROM Booking b WHERE b.trainRun = :trainRun AND b.status = 'CONFIRMED' " +
         "AND b.toStop.sequenceIndex > :fromSeq AND b.fromStop.sequenceIndex < :toSeq")
  List<Booking> findActiveOverlappingBookings(@Param("trainRun") TrainRun trainRun,
                                              @Param("fromSeq") int fromSeq,
                                              @Param("toSeq") int toSeq);
}
