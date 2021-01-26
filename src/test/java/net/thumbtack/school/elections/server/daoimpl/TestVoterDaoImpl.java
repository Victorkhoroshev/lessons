package net.thumbtack.school.elections.server.daoimpl;
import net.thumbtack.school.elections.server.Server;
import net.thumbtack.school.elections.server.dao.VoterDao;
import net.thumbtack.school.elections.server.model.Voter;
import net.thumbtack.school.elections.server.service.ServerException;
import net.thumbtack.school.elections.server.service.ExceptionErrorCode;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class TestVoterDaoImpl {
    private final Server server = new Server();
    private final VoterDao<Voter> dao = new VoterDaoImpl();

    @Test
    public void saveTest() throws ServerException, IOException, ClassNotFoundException {
        server.startServer(null);
        Voter voter = new Voter("Виктор", "Хорошев",
                "Пригородная", 21, 188, "victor@khoroshev.net"," 1111");
        Voter voter1 = new Voter("Виктор", "Хорошев",
                "Пригородная", 21, 188, "victor.net","121231");
        Voter voter2 = new Voter("Андрей", "Хорошев",
                "Пригородная", 21, 188, "victor@khoroshev.net"," 1111");
        Voter voter3 = new Voter("Андрей", "Хорышев",
                "Пригородная", 21, 188, "victooroshev.net"," 1111");
        dao.save(voter);
        try {
            dao.save(voter1);
            fail();
        } catch (ServerException ex) {
            assertEquals(ExceptionErrorCode.VOTER_ALREADY_EXISTS, ex.getErrorCode());
        }
        try {
            dao.save(voter2);
            fail();
        } catch (ServerException ex) {
            assertEquals(ExceptionErrorCode.VOTER_LOGIN_ALREADY_EXISTS, ex.getErrorCode());
        }
        dao.save(voter3);
        assertAll(
                () -> assertTrue(dao.getAll().contains(voter)),
                () -> assertTrue(dao.getAll().contains(voter3)),
                () -> assertFalse(dao.getAll().contains(voter2)),
                () -> assertEquals(2, dao.getAll().size())
        );
        server.stopServer(null);
    }

    @Test
    public void getTest() throws ServerException, IOException, ClassNotFoundException {
        server.startServer(null);
        Voter voter = new Voter("Виктор", "Хорошев",
                "Пригородная", 21, 188, "victor@khoroshev.net"," 1111");
        Voter voter1 = new Voter(randomString(), randomString(),
                randomString(), 21, 188, "victor.net","qQ%34231111");
        dao.save(voter);
        dao.save(voter1);
        try {
            dao.get("1");
            fail();
        } catch (ServerException ex) {
            assertEquals(ExceptionErrorCode.VOTER_NOT_FOUND, ex.getErrorCode());
        }
        assertEquals(voter1, dao.get("victor.net"));
        server.stopServer(null);
    }

    @Test
    public void getAllTest() throws IOException, ClassNotFoundException, ServerException {
        server.startServer(null);
        Voter voter = new Voter("Виктор", "Хорошев",
                "Пригородная", 21, 188, "victor@khoroshev.net"," 1111");
        dao.save(voter);
        Set<Voter> voterSet = new HashSet<>(){{
            add(voter);
        }};
        assertEquals(voterSet, dao.getAll());
        server.stopServer(null);
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
