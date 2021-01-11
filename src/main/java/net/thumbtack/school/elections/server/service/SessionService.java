package net.thumbtack.school.elections.server.service;

import net.thumbtack.school.elections.server.dto.request.Session;
import net.thumbtack.school.elections.server.model.Voter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class SessionService {
    private static final Map<Voter, Session> sessions = new ConcurrentHashMap<>();
    private static volatile SessionService sessionService;


    public static SessionService getInstance() {
        SessionService result = sessionService;
        if (result != null) {
            return result;
        }
        synchronized(SessionService.class) {
            if (sessionService == null) {
                sessionService = new SessionService();
            }
            return sessionService;
        }
    }

    public Session getSession(Voter voter) throws VoterException {
        if (sessions.containsKey(voter)) {
            return sessions.get(voter);
        } else {
            throw new VoterException(VoterExceptionErrorCode.VOTER_LOGOUT);
        }
    }

    public Voter getVoter(String token) throws VoterException {
        for (Map.Entry<Voter, Session> map : sessions.entrySet()) {
            if (map.getValue().getToken().equals(token)) {
                return map.getKey();
            }
        }
        throw new VoterException(VoterExceptionErrorCode.VOTER_LOGOUT);
    }

    public String login(Voter voter) {
        sessions.put(voter, new Session(UUID.randomUUID().toString()));
        return sessions.get(voter).getToken();
    }

    public void logout(String token) throws VoterException {
        sessions.remove(getVoter(token));
    }

}
