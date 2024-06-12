package kafta.onyx.kafkaconsumerdatabases.kafka;

import kafta.onyx.kafkaconsumerdatabases.entity.WikimediaData;
import kafta.onyx.kafkaconsumerdatabases.repository.WikimediaDataRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class kakfaDatabaseConsumer {

    @Autowired
    private WikimediaDataRepository wikimediaDataRepository;

    @KafkaListener(
        topics = "${spring.kafka.topic.name}",
        groupId = "${spring.kafka.consumer.group-id}")
    public void consume(String message) {

        WikimediaData wikimediaData = new WikimediaData();
        wikimediaData.setWikiEventData(message);
        wikimediaDataRepository.save(wikimediaData);
        log.info("WikimediaData saved: {}", wikimediaData);
    }
}
