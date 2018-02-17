package com.company.onlinenewspaper.repository;

import com.company.onlinenewspaper.model.User;
import com.company.onlinenewspaper.model.UserSession;

public interface UserSessionRepository {

    UserSession create(UserSession session);

    UserSession getBySessionId(String sessionId);

}
