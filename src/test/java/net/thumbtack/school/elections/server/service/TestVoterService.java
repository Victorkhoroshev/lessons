package net.thumbtack.school.elections.server.service;
import net.thumbtack.school.elections.server.Server;
import net.thumbtack.school.elections.server.model.Voter;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class TestVoterService {
    private final Server server = new Server();
    private final ContextService contextService = new ContextService();
    private final SessionService sessionService = new SessionService();
    private final VoterService voterService = new VoterService(sessionService, contextService);

    @Test
    public void registerVoterTest() throws ServerException, IOException, ClassNotFoundException {
        server.startServer(null);
        Voter voter1 = getNewVoter();
        Voter voter2 = getNewVoter();
        Voter voter3 = getNewVoter();
        assertAll(
                () -> assertEquals(voterService.register(voter1), sessionService.getSession(voter1).getToken()),
                () -> assertEquals(voterService.register(voter2), sessionService.getSession(voter2).getToken()),
                () -> assertEquals(voterService.register(voter3), sessionService.getSession(voter3).getToken())
        );
        server.stopServer(null);
    }

    @Test
    public void logoutVoterTest() throws ServerException, IOException, ClassNotFoundException {
        server.startServer(null);
        Voter voter1 = getNewVoter();
        voterService.logout(voterService.register(voter1));
        try {
            sessionService.getSession(voter1);
            fail();
        } catch (ServerException ex) {
            assertEquals(ExceptionErrorCode.LOGOUT, ex.getErrorCode());
        }
        server.stopServer(null);
    }

    @Test
    public void loginVoterTest() throws ServerException, IOException, ClassNotFoundException {
        server.startServer(null);
        Voter voter1 = getNewVoter();
        voterService.register(voter1);
        assertEquals(voterService.login(voter1.getLogin(), voter1.getPassword()),
                sessionService.getSession(voter1).getToken());
        try {
            voterService.login(voter1.getLogin(), voter1.getPassword() + "12");
        } catch (ServerException ex) {
            assertEquals(ExceptionErrorCode.WRONG_PASSWORD, ex.getErrorCode());
        }
        server.stopServer(null);
    }

    @Test
    public void getAllVotersTest() throws IOException, ClassNotFoundException, ServerException {
        server.startServer(null);
        Voter voter = getNewVoter();
        Voter voter2 = getNewVoter();
        Set<Voter> set = new HashSet<>();
        set.add(voter);
        set.add(voter2);
        voterService.register(voter);
        voterService.register(voter2);
        assertEquals(set, voterService.getAll());
        server.stopServer(null);
    }

    @Test
    public void getVoterTest() throws IOException, ClassNotFoundException, ServerException {
        server.startServer(null);
        Voter voter = new Voter("имя", "фамилия","отчество", "улица", 1, "login","qwe");
        voterService.register(voter);
        assertEquals(voter, voterService.get("login"));
        server.stopServer(null);
    }

    private Voter getNewVoter() {
        Random random = new Random();
        return new Voter(randomString(), randomString(),
                randomString(), random.nextInt(100), random.nextInt(100), randomString(),randomString());
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
