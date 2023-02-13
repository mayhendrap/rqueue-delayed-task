package id.noir.rqueue;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;

@SpringBootApplication
@EnableRedisRepositories
public class RqueueDelayedTaskApplication {

  public static void main(String[] args) {
    SpringApplication.run(RqueueDelayedTaskApplication.class, args);
  }

}
