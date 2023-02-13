package id.noir.rqueue.listener;

import com.github.sonus21.rqueue.annotation.RqueueListener;
import id.noir.rqueue.entity.Transaction;
import id.noir.rqueue.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class TxListener {

  private final TransactionRepository txRepository;

  @RqueueListener(value = "tx-cancellation")
  public void cancelTx(Transaction tx) {
    log.info(String.format("Cancelling TX-%s", tx.getId()));
    tx = txRepository.findById(tx.getId())
        .orElseThrow(() -> new IllegalArgumentException("TX - NOT FOUND"));
    tx.setStatus("CANCEL");
    txRepository.save(tx);
    log.info("Success cancelling");
  }

}
