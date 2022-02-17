package com.kverchi.like.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class LoggerService {
    public void log(String msg) {
        log.info("{}", msg);
    }
}
