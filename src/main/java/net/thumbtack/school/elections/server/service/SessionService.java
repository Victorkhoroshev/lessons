package net.thumbtack.school.elections.server.service;

import net.thumbtack.school.elections.server.dto.request.Session;
import net.thumbtack.school.elections.server.model.Voter;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class SessionService {
    static final Map<Voter, Session> sessions = new ConcurrentHashMap<>();

    public Session getSession(Voter voter) throws ServerException {
        if (sessions.containsKey(voter)) {
            return sessions.get(voter);
        } else {
            throw new ServerException(ExceptionErrorCode.VOTER_LOGOUT);
        }
    }

    public Voter getVoter(String token) throws ServerException {
        for (Map.Entry<Voter, Session> entry : sessions.entrySet()) {
            if (entry.getValue().getToken().equals(token)) {
                return entry.getKey();
            }
        }
        throw new ServerException(ExceptionErrorCode.VOTER_LOGOUT);
    }

    public String login(Voter voter) {
        String token = UUID.randomUUID().toString();
        sessions.put(voter, new Session(token));
        return token;
    }

    public void logout(String token) throws ServerException {
        sessions.remove(getVoter(token));
    }
}
