package kafta.onyx.kafkaproducerwikimedia.event;

import com.launchdarkly.eventsource.EventSource;
import com.launchdarkly.eventsource.background.BackgroundEventHandler;
import com.launchdarkly.eventsource.background.BackgroundEventSource;
import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.net.URI;

@Service
@Slf4j
public class WikimediaChangesProducer {

    @Value("${wikimedia.changes.url}")
    private String wikimediaChangesUrl;

    @Value("${spring.kafka.topic.name}")
    private String topic;

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;
    public void sendMessage() {
        BackgroundEventHandler handler = new WikimediaChangesHandler(kafkaTemplate, topic);
        BackgroundEventSource eventSource = new BackgroundEventSource.Builder(
                handler,
                new EventSource.Builder(URI.create(wikimediaChangesUrl))
        ).build();
        log.info("Starting event source");
       eventSource.start();
    }

}
