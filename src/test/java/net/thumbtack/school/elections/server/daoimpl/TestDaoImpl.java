package net.thumbtack.school.elections.server.daoimpl;
import net.thumbtack.school.elections.server.model.Voter;
import net.thumbtack.school.elections.server.service.ServerException;
import net.thumbtack.school.elections.server.service.ExceptionErrorCode;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

public class TestDaoImpl {
//    private ObjectInputStream outputStream = new ObjectInputStream(new FileInputStream("C:\\Thumbtack\\thumbtack_online_school_2020_2__viktor_khoroshev\\saveDataFile"));
//    private VoterDaoImpl dao = new VoterDaoImpl(outputStream);
//
//    public TestDaoImpl() throws IOException, ClassNotFoundException {
//    }
//
//    @Test
//    public void saveTest() throws ServerException {
//        Voter voter = new Voter("Виктор", "Хорошев",
//                "Пригородная", 21, 188, "victor@khoroshev.net"," 1111");
//        Voter voter1 = new Voter("Виктор", "Хорошев",
//                "Пригородная", 21, 188, "victor.net","121231");
//        Voter voter2 = new Voter("Андрей", "Хорошев",
//                "Пригородная", 21, 188, "victor@khoroshev.net"," 1111");
//        dao.saveVoter(voter);
//        try {
//            dao.saveVoter(voter1);
//            fail();
//        } catch (ServerException ex) {
//            assertEquals(ExceptionErrorCode.VOTER_ALREADY_EXISTS, ex.getErrorCode());
//        }
//        try {
//            dao.saveVoter(voter2);
//            fail();
//        } catch (ServerException ex) {
//            assertEquals(ExceptionErrorCode.VOTER_LOGIN_ALREADY_EXISTS, ex.getErrorCode());
//        }
//    }
//
//    @Test
//    public void getTest() throws ServerException {
//        Voter voter = new Voter(randomString(), randomString(),
//                randomString(), 21, 188, randomString(),"qQ%34231111");
//        Voter voter1 = new Voter(randomString(), randomString(),
//                randomString(), 21, 188, "victor.net","qQ%34231111");
//        dao.saveVoter(voter);
//        dao.saveVoter(voter1);
//        try {
//            dao.getVoter("1");
//            fail();
//        } catch (ServerException ex) {
//            assertEquals(ExceptionErrorCode.VOTER_NOT_FOUND, ex.getErrorCode());
//        }
//        assertEquals(voter1, dao.getVoter("victor.net"));
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
