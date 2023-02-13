package id.noir.rqueue.controller;

import com.github.sonus21.rqueue.core.RqueueMessageEnqueuer;
import id.noir.rqueue.dto.TxRequest;
import id.noir.rqueue.entity.Transaction;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class TxController {

  private final RqueueMessageEnqueuer messageEnqueuer;

  @PostMapping(path = "/set-cancellation")
  public ResponseEntity<Object> setCancellation(@RequestBody TxRequest request) {
    log.info(String.format("Set cancellation for TX-%s", request.getId()));
    Instant instant = Instant.now().plus(10, ChronoUnit.SECONDS);

    messageEnqueuer.enqueueAt("tx-cancellation",
        new Transaction(request.getId(), request.getStatus()), instant);

    return ResponseEntity.ok("Success");
  }

}
