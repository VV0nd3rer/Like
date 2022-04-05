package com.kverchi.like.events.listeners;

import com.kverchi.like.events.ModifyEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ModifyMediaEventListener {

    @EventListener
    public void processMovieModifyEvent(ModifyEvent<?> event) {
        log.info("Media {} event for media: {}",
                event.getModificationType().name(), event.getMedia());
    }
}
