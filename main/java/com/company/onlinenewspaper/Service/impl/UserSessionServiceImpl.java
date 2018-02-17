package com.company.onlinenewspaper.Service.impl;

import com.company.onlinenewspaper.Service.UserSessionService;
import com.company.onlinenewspaper.model.User;
import com.company.onlinenewspaper.model.UserSession;
import com.company.onlinenewspaper.repository.impl.UserSessionRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserSessionServiceImpl implements UserSessionService {

    @Autowired
    UserSessionRepositoryImpl sessionRepository;

    @Override
    public UserSession create(String id, User user) {
        UserSession session = UserSession.builder()
                .sessionId(id)
                .user(user)
                .isValid(true)
                .build();
        return sessionRepository.create(session);
    }

    @Override
    public UserSession getBySessionId(String sessionId) {
        return sessionRepository.getBySessionId(sessionId);
    }

    @Override
    public void invalidateSession(String sessionId) {
        UserSession session = sessionRepository.getBySessionId(sessionId);
        if (session == null) {
            return;
        }
        session.setIsValid(false);
    }
}
