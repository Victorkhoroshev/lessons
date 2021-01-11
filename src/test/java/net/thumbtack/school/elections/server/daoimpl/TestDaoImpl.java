package net.thumbtack.school.elections.server.daoimpl;
import net.thumbtack.school.elections.server.model.Voter;
import net.thumbtack.school.elections.server.service.SessionService;
import net.thumbtack.school.elections.server.service.VoterException;
import net.thumbtack.school.elections.server.service.VoterExceptionErrorCode;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

public class TestDaoImpl {
    private DaoImpl dao = new DaoImpl();
    @Test
    public void saveTest() throws VoterException {
        Voter voter = new Voter("Виктор", "Хорошев",
                "Пригородная", 21, 188, "victor@khoroshev.net"," 1111");
        Voter voter1 = new Voter("Виктор", "Хорошев",
                "Пригородная", 21, 188, "victor.net","121231");
        Voter voter2 = new Voter("Андрей", "Хорошев",
                "Пригородная", 21, 188, "victor@khoroshev.net"," 1111");
        dao.save(voter);
        try {
            dao.save(voter1);
            fail();
        } catch (VoterException ex) {
            assertEquals(VoterExceptionErrorCode.VOTER_ALREADY_EXISTS, ex.getErrorCode());
        }
        try {
            dao.save(voter2);
            fail();
        } catch (VoterException ex) {
            assertEquals(VoterExceptionErrorCode.VOTER_LOGIN_ALREADY_EXISTS, ex.getErrorCode());
        }
    }

    @Test
    public void getTest() throws VoterException {
        Voter voter = new Voter(randomString(), randomString(),
                randomString(), 21, 188, randomString(),"qQ%34231111");
        Voter voter1 = new Voter(randomString(), randomString(),
                randomString(), 21, 188, "victor.net","qQ%34231111");
        dao.save(voter);
        dao.save(voter1);
        try {
            dao.get("1");
            fail();
        } catch (VoterException ex) {
            assertEquals(VoterExceptionErrorCode.VOTER_NOT_FOUND, ex.getErrorCode());
        }
        assertEquals(voter1, dao.get("victor.net"));
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
