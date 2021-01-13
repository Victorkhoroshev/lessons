package net.thumbtack.school.elections.server.service;

import net.thumbtack.school.elections.server.Server;
import net.thumbtack.school.elections.server.dto.request.Session;
import net.thumbtack.school.elections.server.model.Voter;
import org.junit.Before;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;
public class TestSessionService {
    private final SessionService sessionService = new SessionService();
    @Test
    public void getSessionTest() throws VoterException {
        Voter voter = new Voter(randomString(), randomString(), randomString(),1, randomString(),"12qW1233&");
        Voter voter2 = new Voter(randomString(), randomString(), randomString(),1, randomString(),"12qW1233&");
        SessionService.sessions.put(voter, new Session(randomString()));
        assertEquals(SessionService.sessions.get(voter), sessionService.getSession(voter));
        try {
            sessionService.getSession(voter2);
        } catch (VoterException ex) {
            assertEquals(VoterExceptionErrorCode.VOTER_LOGOUT, ex.getErrorCode());
        }
    }

    @Test
    public void getVoterTest() throws VoterException {
        Voter voter = new Voter(randomString(), randomString(), randomString(),1, randomString(),"12qW1233&");
        SessionService.sessions.put(voter, new Session("token"));
        assertEquals(voter, sessionService.getVoter("token"));
        try {
            sessionService.getVoter(randomString());
        } catch (VoterException ex) {
            assertEquals(VoterExceptionErrorCode.VOTER_LOGOUT, ex.getErrorCode());
        }
    }

    @Test
    public void loginVoterTest() throws VoterException {
        Voter voter = new Voter(randomString(), randomString(), randomString(),1, randomString(),"12qW1233&");
        int i = SessionService.sessions.size();
        assertEquals(sessionService.loginVoter(voter), sessionService.getSession(voter).getToken());
        assertEquals(i + 1, SessionService.sessions.size());
    }

    @Test
    public void logoutVoterTest() throws VoterException {
        Voter voter = new Voter(randomString(), randomString(), randomString(),1, randomString(),"12qW1233&");
        SessionService.sessions.put(voter, new Session("34"));
        int i = SessionService.sessions.size();
        sessionService.logoutVoter("34");
        assertEquals(i - 1, SessionService.sessions.size());
        try {
            sessionService.logoutVoter(randomString());
        } catch (VoterException ex) {
            assertEquals(VoterExceptionErrorCode.VOTER_LOGOUT, ex.getErrorCode());
        }
    }

    private String randomString() {
        Random random = new Random();
        char[] sAlphabet = "АБВГДЕЖЗИКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯабвгдежзиклмнопрстуфхцчшщъыьэюя".toCharArray();
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < 60; i++) {
            stringBuilder.append(sAlphabet[random.nextInt(sAlphabet.length)]);
        }
        return stringBuilder.toString();
    }
}
