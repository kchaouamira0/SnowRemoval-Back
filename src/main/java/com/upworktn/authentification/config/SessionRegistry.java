package com.upworktn.authentification.config;

import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class SessionRegistry {
    private final Map<String, Instant> activeSessions = new ConcurrentHashMap<>();

    public void registerSession(String email) {
        activeSessions.put(email, Instant.now());
    }

    public void removeSession(String email) {
        activeSessions.remove(email);
    }

    public List<String> getActiveSessions() {
        return new ArrayList<>(activeSessions.keySet());
    }

    public void updateLastActivity(String email) {
        activeSessions.put(email, Instant.now());
    }
}
