package com.example;

import com.example.entity.Media;
import com.example.repository.IWikimediaRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

// Service annotation will make it Spring Bean
@Service
@Slf4j
public class ConsumerDB {

    private IWikimediaRepository wikimediaRepository;
    public ConsumerDB(IWikimediaRepository wikimediaRepository) {
        this.wikimediaRepository = wikimediaRepository;
    }


    @KafkaListener(topics = "wikimedia_topic", groupId = "wikiGroup")
    public void consumeEvents(String eventMessage){
        //log.info(String.format(" Event Message is : %s", eventMessage));

        Media media = new Media();
        //if(eventMessage != null) {
        //System.out.println(" Event MEssage String length is : " + eventMessage.length());
        //eventMessage = "Hello World!!!";
            media.setWikimediaData(eventMessage);
            wikimediaRepository.save(media);
        //}

    }
}
