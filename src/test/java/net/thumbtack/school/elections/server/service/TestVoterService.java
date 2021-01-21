package net.thumbtack.school.elections.server.service;
import net.thumbtack.school.elections.server.daoimpl.VoterDaoImpl;
import net.thumbtack.school.elections.server.model.Voter;
import org.junit.jupiter.api.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

public class TestVoterService {
//    private final SessionService sessionService = new SessionService();
//    private ObjectInputStream outputStream = new ObjectInputStream(new FileInputStream("C:\\Thumbtack\\thumbtack_online_school_2020_2__viktor_khoroshev\\saveDataFile"));
//    private final VoterService voterService = new VoterService(new VoterDaoImpl(outputStream), sessionService);
//
//    public TestVoterService() throws IOException, ClassNotFoundException {
//    }
//
//    @Test
//    public void registerVoterTest() throws ServerException {
//        Voter voter1 = new Voter("Виткор", "Хорошев",
//                null,"Пригородная", 1, 188, "1111111111111", "111%111Aa");
//        Voter voter2 = new Voter("виткорр", "Хорошев",
//                null, "Пригородная", 1, 188, "222222222222222", "111%111Aa");
//        Voter voter3 = new Voter("ВИТКОРРР", "Хорошев",
//                null, "Пригородная", 1, 188, "333333333333333", "111%111Aa");
//        assertAll(
//                () -> assertEquals(voterService.registerVoter(voter1), sessionService.getSession(voter1).getToken()),
//                () -> assertEquals(voterService.registerVoter(voter2), sessionService.getSession(voter2).getToken()),
//                () -> assertEquals(voterService.registerVoter(voter3), sessionService.getSession(voter3).getToken())
//        );
//    }
//
//    @Test
//    public void logoutVoterTest() throws ServerException {
//        Voter voter1 = new Voter(randomString(), randomString(),
//                null,randomString(), 1, 188, randomString(), "111%111Aa");
//        voterService.logout(voterService.registerVoter(voter1));
//        try {
//            sessionService.getSession(voter1);
//            fail();
//        } catch (ServerException ex) {
//            assertEquals(ExceptionErrorCode.VOTER_LOGOUT, ex.getErrorCode());
//        }
//    }
//
//    @Test
//    public void loginVoterTest() throws ServerException {
//        Voter voter1 = new Voter(randomString(), randomString(),
//                null,randomString(), 1, 188, randomString(), "111%111Aa");
//        voterService.registerVoter(voter1);
//        assertEquals(voterService.loginVoter(voter1.getLogin(), voter1.getPassword()), sessionService.getSession(voter1).getToken());
//        try {
//            voterService.loginVoter(voter1.getLogin(), voter1.getPassword() + "12");
//        } catch (ServerException ex) {
//            assertEquals(ExceptionErrorCode.VOTER_WRONG_PASSWORD, ex.getErrorCode());
//        }
//    }
//
//    private String randomString() {
//        Random random = new Random();
//        char[] sAlphabet = "АБВГДЕЖЗИКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯабвгдежзиклмнопрстуфхцчшщъыьэюя".toCharArray();
//        StringBuilder stringBuilder = new StringBuilder();
//        for (int i = 0; i < 60; i++) {
//            stringBuilder.append(sAlphabet[random.nextInt(sAlphabet.length)]);
//        }
//        return stringBuilder.toString();
//    }
}
