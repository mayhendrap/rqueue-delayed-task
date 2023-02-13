package id.noir.rqueue.seeder;

import id.noir.rqueue.entity.Transaction;
import id.noir.rqueue.repository.TransactionRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class Seeder {

  private final TransactionRepository txRepository;

  @EventListener(ContextRefreshedEvent.class)
  public void seed() {
    txSeeder();
  }

  public void txSeeder() {
    if (txRepository.findAll().isEmpty()) {
      Transaction tx1 = new Transaction(1L, "PENDING");
      Transaction tx2 = new Transaction(2L, "PENDING");
      Transaction tx3 = new Transaction(3L, "PENDING");
      txRepository.saveAll(List.of(tx1, tx2, tx3));
    }
  }

}
