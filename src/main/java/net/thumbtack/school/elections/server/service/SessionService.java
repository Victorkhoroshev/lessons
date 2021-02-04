package net.thumbtack.school.elections.server.service;

import net.thumbtack.school.elections.server.dto.request.Session;
import net.thumbtack.school.elections.server.model.Commissioner;
import net.thumbtack.school.elections.server.model.Voter;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class SessionService {
    final Map<Voter, Session> voterSessions = new HashMap<>();
    final Map<Commissioner, Session> commissionerSessions = new HashMap<>();

    public Session getSession(Voter voter) throws ServerException {
        if (voterSessions.containsKey(voter)) {
            return voterSessions.get(voter);
        } else {
            throw new ServerException(ExceptionErrorCode.LOGOUT);
        }
    }

    public Session getSession(Commissioner commissioner) throws ServerException {
        if (commissionerSessions.containsKey(commissioner)) {
            return commissionerSessions.get(commissioner);
        } else {
            throw new ServerException(ExceptionErrorCode.LOGOUT);
        }
    }

    public Voter getVoter(String token) throws ServerException {
        for (Map.Entry<Voter, Session> entry : voterSessions.entrySet()) {
            if (entry.getValue().getToken().equals(token)) {
                return entry.getKey();
            }
        }
        throw new ServerException(ExceptionErrorCode.LOGOUT);
    }

    public Commissioner getCommissioner(String token) {
        for (Map.Entry<Commissioner, Session> entry : commissionerSessions.entrySet()) {
            if (entry.getValue().getToken().equals(token)) {
                return entry.getKey();
            }
        }
        return null;
    }

    public boolean isLogin(String token) {
        for (Map.Entry<Voter, Session> entry : voterSessions.entrySet()) {
            if (entry.getValue().getToken().equals(token)) {
                return true;
            }
        }
        for (Map.Entry<Commissioner, Session> entry : commissionerSessions.entrySet()) {
            if (entry.getValue().getToken().equals(token)) {
                return true;
            }
        }
        return false;
    }

    public String login(Voter voter) {
        String token = UUID.randomUUID().toString();
        voterSessions.put(voter, new Session(token));
        return token;
    }

    public String login(Commissioner commissioner) {
        String token = UUID.randomUUID().toString();
        commissionerSessions.put(commissioner, new Session(token));
        return token;
    }

    public void logoutVoter(String token) throws ServerException {
        voterSessions.remove(getVoter(token));
    }

    public void logoutCommissioner(String token) {
        commissionerSessions.remove(getCommissioner(token));
    }
}
