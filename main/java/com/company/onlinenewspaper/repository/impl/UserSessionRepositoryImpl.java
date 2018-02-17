package com.company.onlinenewspaper.repository.impl;

import com.company.onlinenewspaper.model.User;
import com.company.onlinenewspaper.model.UserSession;
import com.company.onlinenewspaper.repository.UserSessionRepository;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class UserSessionRepositoryImpl implements UserSessionRepository {
    private static Map<String, UserSession> sessions = new HashMap<>();

    @Override
    public UserSession create(UserSession session) {
        sessions.putIfAbsent(session.getSessionId(), session);
        return session;
    }

    @Override
    public UserSession getBySessionId(String sessionId) {
        UserSession session = sessions.get(sessionId);
        if (session == null || !session.getIsValid()) {
            return null;
        }
        return session;
    }


}
