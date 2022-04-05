package com.kverchi.like.events;

/**
 * Event related to adding, updating, deleting or getting some media.
 * Example of media: Movie, Book, Game, Music.
 * @see ModificationType
 */
public interface ModifyEvent <T> {
    T getMedia();
    ModificationType getModificationType();
}
