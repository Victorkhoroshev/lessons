package net.thumbtack.school.elections.server.service;
import net.thumbtack.school.elections.server.daoimpl.DaoImpl;
import net.thumbtack.school.elections.server.model.Voter;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

public class TestVoterService {
    private final SessionService sessionService = new SessionService();
    private final VoterService voterService = new VoterService(new DaoImpl(null), sessionService);
    @Test
    public void registerVoterTest() throws VoterException {
        Voter voter1 = new Voter("Виткор", "Хорошев",
                null,"Пригородная", 1, 188, "1111111111111", "111%111Aa");
        Voter voter2 = new Voter("виткорр", "Хорошев",
                null, "Пригородная", 1, 188, "222222222222222", "111%111Aa");
        Voter voter3 = new Voter("ВИТКОРРР", "Хорошев",
                null, "Пригородная", 1, 188, "333333333333333", "111%111Aa");
        assertAll(
                () -> assertEquals(voterService.registerVoter(voter1), sessionService.getSession(voter1).getToken()),
                () -> assertEquals(voterService.registerVoter(voter2), sessionService.getSession(voter2).getToken()),
                () -> assertEquals(voterService.registerVoter(voter3), sessionService.getSession(voter3).getToken())
        );
    }

    @Test
    public void logoutVoterTest() throws VoterException {
        Voter voter1 = new Voter(randomString(), randomString(),
                null,randomString(), 1, 188, randomString(), "111%111Aa");
        voterService.logoutVoter(voterService.registerVoter(voter1));
        try {
            sessionService.getSession(voter1);
            fail();
        } catch (VoterException ex) {
            assertEquals(VoterExceptionErrorCode.VOTER_LOGOUT, ex.getErrorCode());
        }
    }

    @Test
    public void loginVoterTest() throws VoterException {
        Voter voter1 = new Voter(randomString(), randomString(),
                null,randomString(), 1, 188, randomString(), "111%111Aa");
        voterService.registerVoter(voter1);
        assertEquals(voterService.loginVoter(voter1.getLogin(), voter1.getPassword()), sessionService.getSession(voter1).getToken());
        try {
            voterService.loginVoter(voter1.getLogin(), voter1.getPassword() + "12");
        } catch (VoterException ex) {
            assertEquals(VoterExceptionErrorCode.VOTER_WRONG_PASSWORD, ex.getErrorCode());
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
