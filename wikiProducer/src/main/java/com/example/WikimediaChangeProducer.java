package com.example;

import com.launchdarkly.eventsource.EventHandler;
import com.launchdarkly.eventsource.EventSource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.net.URL;
import java.util.concurrent.TimeUnit;

@Service
@Slf4j
public class WikimediaChangeProducer {

    private KafkaTemplate<String, String> kafkaTemplate;

    public WikimediaChangeProducer(KafkaTemplate<String, String> kafkaTemplate)
    {
        this.kafkaTemplate = kafkaTemplate;
    }

    // Create a method that will read events stream

    public void sendMessage() throws InterruptedException {
        String topic_name = "wikimedia_topic";

        //We will get the latest events/changes from WikimediaChangeTracker
        EventHandler eventHandler = new WikimediaChangeTracker(kafkaTemplate, topic_name);
        String url = "https://stream.wikimedia.org/v2/stream/recentchange";
        EventSource.Builder builder = new EventSource.Builder(eventHandler, URI.create(url));
        EventSource eventSource = builder.build();

        eventSource.start();

        TimeUnit.MINUTES.sleep(10); // sleep for 10 mins.
    }
}
