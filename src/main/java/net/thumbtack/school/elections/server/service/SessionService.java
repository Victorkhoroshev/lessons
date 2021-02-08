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

    /**
     * Get voter session.
     * @param voter voter, from whom we want to get a session.
     * @return Voter session.
     * @throws ServerException if voter logout.
     */
    public Session getSession(Voter voter) throws ServerException {
        if (voterSessions.containsKey(voter)) {
            return voterSessions.get(voter);
        } else {
            throw new ServerException(ExceptionErrorCode.LOGOUT);
        }
    }

    /**
     * Get commissioner session.
     * @param commissioner commissioner, from whom we want to get a session.
     * @return Commissioner's session.
     * @throws ServerException if commissioner logout.
     */
    public Session getSession(Commissioner commissioner) throws ServerException {
        if (commissionerSessions.containsKey(commissioner)) {
            return commissionerSessions.get(commissioner);
        } else {
            throw new ServerException(ExceptionErrorCode.LOGOUT);
        }
    }

    /**
     * Get voter by token.
     * @param token unique voter's id.
     * @return Voter who owns this token.
     * @throws ServerException if voter logout.
     */
    public Voter getVoter(String token) throws ServerException {
        for (Map.Entry<Voter, Session> entry : voterSessions.entrySet()) {
            if (entry.getValue().getToken().equals(token)) {
                return entry.getKey();
            }
        }
        throw new ServerException(ExceptionErrorCode.LOGOUT);
    }

    /**
     * Get commissioner by token.
     * @param token unique commissioner's id.
     * @return If commissioner login: commissioner who owns this token.
     * If commissioner logout: null.
     */
    public Commissioner getCommissioner(String token) {
        for (Map.Entry<Commissioner, Session> entry : commissionerSessions.entrySet()) {
            if (entry.getValue().getToken().equals(token)) {
                return entry.getKey();
            }
        }
        return null;
    }

    /**
     * Checks: login voter or commissioner.
     * @param token unique user id.
     * @return If voter or commissioner login: true.
     * If voter or commissioner logout: false.
     */
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

    /**
     * Put voter in voter's map and his generated unique id.
     * @param voter voter who hasn't logged in yet.
     * @return generated unique id.
     */
    public String login(Voter voter) {
        String token = UUID.randomUUID().toString();
        voterSessions.put(voter, new Session(token));
        return token;
    }

    /**
     * Put commissioner in commissioner's map and his generated unique id.
     * @param commissioner commissioner who hasn't logged in yet.
     * @return Generated unique id.
     */
    public String login(Commissioner commissioner) {
        String token = UUID.randomUUID().toString();
        commissionerSessions.put(commissioner, new Session(token));
        return token;
    }

    /**
     * Remove from voter's map voter.
     * @param token unique voter's id.
     * @throws ServerException if voter logout.
     */
    public void logoutVoter(String token) throws ServerException {
        voterSessions.remove(getVoter(token));
    }

    /**
     * Remove commissioner from commissioner's map.
     * @param token unique commissioner's id.
     */
    public void logoutCommissioner(String token) {
        commissionerSessions.remove(getCommissioner(token));
    }
}
