package net.thumbtack.school.elections.server.service;
import net.thumbtack.school.elections.server.database.Database;
import net.thumbtack.school.elections.server.dto.request.RegisterVoterDtoRequest;
import net.thumbtack.school.elections.server.model.Voter;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

public class TestVoterService {
    private final Database database = Database.getInstance();
    private final SessionService session = SessionService.getInstance();
    private final VoterService voterService = new VoterService();
    @Test
    public void registerVoterTest() throws VoterException {
        Voter voter1 = new Voter("Виткор", "Хорошев",
                null,"Пригородная", 1, 188, "1111111111111", "111%111Aa");
        Voter voter2 = new Voter("виткорр", "Хорошев",
                null, "Пригородная", 1, 188, "222222222222222", "111%111Aa");
        Voter voter3 = new Voter("ВИТКОРРР", "Хорошев",
                null, "Пригородная", 1, 188, "333333333333333", "111%111Aa");
        assertAll(
                () -> assertEquals(voterService.registerVoter(voter1), session.getSession(voter1).getToken()),
                () -> assertEquals(voterService.registerVoter(voter2), session.getSession(voter2).getToken()),
                () -> assertEquals(voterService.registerVoter(voter3), session.getSession(voter3).getToken())
        );
    }

    @Test
    public void logoutVoterTest() throws VoterException {
        Voter voter1 = new Voter(randomString(), randomString(),
                null,randomString(), 1, 188, randomString(), "111%111Aa");
        voterService.logoutVoter(voterService.registerVoter(voter1));
        try {
            session.getSession(voter1);
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
        assertEquals(voterService.loginVoter(voter1.getLogin(), voter1.getPassword()), session.getSession(voter1).getToken());
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
