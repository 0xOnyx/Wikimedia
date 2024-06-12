package kafta.onyx.kafkaproducerwikimedia;

import kafta.onyx.kafkaproducerwikimedia.event.WikimediaChangesProducer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@SpringBootApplication
@EnableScheduling
@Slf4j
public class KafkaProducerWikimediaApplication {

	@Autowired
	WikimediaChangesProducer wikimediaChangesProducer;

	public static void main(String[] args) {
		SpringApplication.run(KafkaProducerWikimediaApplication.class, args);
	}


	@Scheduled(fixedDelay = Long.MAX_VALUE)
	public void startEvent(){
		log.info("Start Scheduled Event");
		wikimediaChangesProducer.sendMessage();
	}

}
