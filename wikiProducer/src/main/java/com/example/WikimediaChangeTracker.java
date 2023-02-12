package com.example;

import com.launchdarkly.eventsource.EventHandler;
import com.launchdarkly.eventsource.MessageEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;

// This class will track all the events (changes) in wikimedia. OnMessage event method will
// be invoked when new change in wikimedia.
@Slf4j
public class WikimediaChangeTracker implements EventHandler {

    private KafkaTemplate<String, String> kafkaTemplate;
    private String topic;

    public WikimediaChangeTracker(KafkaTemplate<String, String> kafkaTemplate, String topic) {
        this.kafkaTemplate = kafkaTemplate;
        this.topic = topic;
    }


    @Override
    public void onOpen() throws Exception {

    }

    @Override
    public void onClosed() throws Exception {

    }

    @Override
    public void onMessage(String s, MessageEvent messageEvent) throws Exception {
        log.info(String.format(" New event data is available  %s", messageEvent.getData()));

        //Now send event data to the kafka topic
        kafkaTemplate.send(topic, messageEvent.getData());
    }

    @Override
    public void onComment(String s) throws Exception {

    }

    @Override
    public void onError(Throwable throwable) {

    }
}

