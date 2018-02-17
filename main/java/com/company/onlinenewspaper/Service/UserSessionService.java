package com.company.onlinenewspaper.Service;

import com.company.onlinenewspaper.model.User;
import com.company.onlinenewspaper.model.UserSession;

public interface UserSessionService {
    UserSession create(String id, User user);

    UserSession getBySessionId(String sessionId);

    void invalidateSession(String sessionId);
}
