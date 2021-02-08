package net.thumbtack.school.elections.server.service;

import net.thumbtack.school.elections.server.dto.request.Session;
import net.thumbtack.school.elections.server.model.Commissioner;
import net.thumbtack.school.elections.server.model.Voter;
import org.junit.jupiter.api.Test;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.jupiter.api.Assertions.*;

public class TestSessionService {
    private final SessionService sessionService = new SessionService();
    private final Commissioner commissioner = new Commissioner("victor.net", "25345Qw&&", true);
    @Test
    public void getSessionTest() throws ServerException {
        Voter voter = new Voter(randomString(), randomString(),
                randomString(),1, randomString(),"12qW1233&");
        Voter voter2 = new Voter(randomString(), randomString(),
                randomString(),1, randomString(),"12qW1233&");
        sessionService.voterSessions.put(voter, new Session(randomString()));
        assertEquals(sessionService.voterSessions.get(voter), sessionService.getSession(voter));
        try {
            sessionService.getSession(voter2);
            fail();
        } catch (ServerException ex) {
            assertEquals(ExceptionErrorCode.LOGOUT, ex.getErrorCode());
        }
    }

    @Test
    public void getTest() throws ServerException {
        Voter voter = new Voter(randomString(), randomString(),
                randomString(),1, randomString(),"12qW1233&");
        sessionService.voterSessions.put(voter, new Session("token"));
        assertEquals(voter, sessionService.getVoter("token"));
        try {
            sessionService.getVoter(randomString());
            fail();
        } catch (ServerException ex) {
            assertEquals(ExceptionErrorCode.LOGOUT, ex.getErrorCode());
        }
    }

    @Test
    public void loginTest() {
        Voter voter = new Voter(randomString(), randomString(), randomString(),
                1, randomString(),"12qW1233&");
        AtomicInteger i = new AtomicInteger(sessionService.voterSessions.size());
        AtomicInteger b = new AtomicInteger(sessionService.commissionerSessions.size());
        assertAll(
                () -> assertEquals(sessionService.login(voter), sessionService.getSession(voter).getToken()),
                () -> assertEquals(i.incrementAndGet(), sessionService.voterSessions.size()),
                () -> assertEquals(commissioner, sessionService.getCommissioner(sessionService.login(commissioner))),
                () -> assertEquals(b.incrementAndGet(), sessionService.commissionerSessions.size())
        );
    }

    @Test
    public void logoutTest() throws ServerException {
        Voter voter = new Voter(randomString(), randomString(), randomString(),
                1, randomString(),"12qW1233&");
        sessionService.voterSessions.put(voter, new Session("34"));
        sessionService.commissionerSessions.put(commissioner, new Session("35"));
        AtomicInteger i = new AtomicInteger(sessionService.voterSessions.size());
        AtomicInteger b = new AtomicInteger(sessionService.commissionerSessions.size());
        sessionService.logoutVoter("34");
        sessionService.logoutCommissioner("35");
        assertAll(
                () -> assertEquals(i.decrementAndGet(), sessionService.voterSessions.size()),
                () -> assertEquals(b.decrementAndGet(), sessionService.commissionerSessions.size())
        );
        try {
            sessionService.logoutVoter(randomString());
        } catch (ServerException ex) {
            assertEquals(ExceptionErrorCode.LOGOUT, ex.getErrorCode());
        }
    }

    @Test
    public void isLoginTest() {
        sessionService.commissionerSessions.put(commissioner, new Session("1"));
        assertTrue(sessionService.isLogin("1"));
        assertFalse(sessionService.isLogin("2"));
    }

    @Test
    public void getCommissionerTest() {
        sessionService.commissionerSessions.put(commissioner , new Session("token"));
        assertEquals(commissioner , sessionService.getCommissioner("token"));
        try {
            sessionService.getVoter(randomString());
            fail();
        } catch (ServerException ex) {
            assertEquals(ExceptionErrorCode.LOGOUT, ex.getErrorCode());
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
