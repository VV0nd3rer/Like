package com.kverchi.like.events.listeners;

import com.kverchi.like.events.MovieUpdatedEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class MovieUpdatedEventListener {
    @EventListener
    public void processMovieUpdatedEvent(MovieUpdatedEvent event) {
        System.out.println("Hello from processing event...");
        log.info("Event received: {}", event);
        log.info("Movie updated event for movie with title: {}", event.getMovie().getTitle());
    }
}
