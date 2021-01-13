package net.thumbtack.school.elections.server.service;

import net.thumbtack.school.elections.server.dto.request.Session;
import net.thumbtack.school.elections.server.model.Voter;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class SessionService {
    static final Map<Voter, Session> sessions = new ConcurrentHashMap<>();

    public Session getSession(Voter voter) throws VoterException {
        if (sessions.containsKey(voter)) {
            return sessions.get(voter);
        } else {
            throw new VoterException(VoterExceptionErrorCode.VOTER_LOGOUT);
        }
    }

    public Voter getVoter(String token) throws VoterException {
        for (Map.Entry<Voter, Session> entry : sessions.entrySet()) {
            if (entry.getValue().getToken().equals(token)) {
                return entry.getKey();
            }
        }
        throw new VoterException(VoterExceptionErrorCode.VOTER_LOGOUT);
    }

    public String loginVoter(Voter voter) {
        sessions.put(voter, new Session(UUID.randomUUID().toString()));
        return sessions.get(voter).getToken();
    }

    public void logoutVoter(String token) throws VoterException {
        sessions.remove(getVoter(token));
    }

}
