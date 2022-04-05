package com.kverchi.like.events.listeners;

import com.kverchi.like.events.ModifyEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionalEventListener;

@Slf4j
@Component
public class MediaModificationDoneEventListener {
    //default is AFTER_COMMIT
    @TransactionalEventListener
    public void processMovieModifyEvent(ModifyEvent<?> event) {
        log.info("Modification done with event {} for media: {}",
                event.getModificationType().name(), event.getMedia());
    }
}
