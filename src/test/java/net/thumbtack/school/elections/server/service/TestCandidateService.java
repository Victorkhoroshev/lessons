package net.thumbtack.school.elections.server.service;
import net.thumbtack.school.elections.server.Server;
import net.thumbtack.school.elections.server.database.Database;
import net.thumbtack.school.elections.server.model.Candidate;
import net.thumbtack.school.elections.server.model.Idea;
import net.thumbtack.school.elections.server.model.Voter;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

public class TestCandidateService {
    private final Server server = new Server();
    private final CandidateService candidateService = new CandidateService();
    @Test
    public void addCandidateTest() throws IOException, ClassNotFoundException, ServerException {
        server.startServer(null);
        Voter voter = getNewVoter();
        candidateService.addCandidate(voter);
        candidateService.addCandidate(voter);
        assertAll(
                () -> assertEquals(1, Database.getCandidateSet().size())
        );
        server.stopServer(null);
    }
    @Test
    public void confirmationCandidacyTest() throws IOException, ClassNotFoundException, ServerException {
        server.startServer(null);
        Voter voter = getNewVoter();
        List<Idea> ideas = new ArrayList<>();
        ideas.add(new Idea("1", voter,randomString()));
        ideas.add(new Idea("2", voter, randomString()));
        candidateService.confirmationCandidacy(voter, ideas);
        assertTrue(candidateService.getCandidateMap().containsValue(ideas));
        assertTrue(Database.getCandidateSet().contains(new Candidate(voter)));
        Voter voter2 = getNewVoter();
        candidateService.addCandidate(voter);
        List<Idea> ideas2 = new ArrayList<>();
        ideas2.add(new Idea("1", voter2,randomString()));
        ideas2.add(new Idea("2", voter2,randomString()));
        candidateService.confirmationCandidacy(voter2, ideas2);
        assertTrue(candidateService.getCandidateMap().containsValue(ideas2));
        server.stopServer(null);
    }
    @Test
    public void withdrawCandidacyTest() throws IOException, ClassNotFoundException, ServerException {
        server.startServer(null);
        Voter voter = getNewVoter();
        List<Idea> ideas = new ArrayList<>();
        ideas.add(new Idea("1", voter,randomString()));
        ideas.add(new Idea("2", voter, randomString()));
        candidateService.confirmationCandidacy(voter, ideas);
        candidateService.withdrawCandidacy(voter);
        assertFalse(candidateService.getCandidateMap().containsValue(ideas));
        assertEquals(0, Database.getCandidateSet().size());
        server.stopServer(null);
    }
    @Test
    public void isCandidateTest() throws IOException, ClassNotFoundException, ServerException {
        server.startServer(null);
        Voter voter = getNewVoter();
        Voter voter2 = getNewVoter();
        List<Idea> ideas = new ArrayList<>();
        ideas.add(new Idea("1", voter,randomString()));
        ideas.add(new Idea("2", voter, randomString()));
        candidateService.confirmationCandidacy(voter, ideas);
        assertTrue(candidateService.isCandidate(voter));
        assertFalse(candidateService.isCandidate(voter2));
        server.stopServer(null);
    }

    @Test
    public void addIdeaTest() throws IOException, ClassNotFoundException, ServerException {
        server.startServer(null);
        Voter voter = getNewVoter();
        List<Idea> ideas = new ArrayList<>();
        ideas.add(new Idea("1", voter,randomString()));
        ideas.add(new Idea("1", voter, randomString()));
        candidateService.confirmationCandidacy(voter, ideas);
        candidateService.addIdea(voter, new Idea("1", voter, randomString()));
        assertAll(
                () ->assertEquals(1, candidateService.getCandidateMap().size()),
                () ->assertEquals(3, candidateService.getCandidateMap().get(new Candidate(voter)).size())
        );
        server.stopServer(null);
    }
    @Test
    public void removeIdeaTest() throws IOException, ClassNotFoundException, ServerException {
        server.startServer(null);
        Voter voter = getNewVoter();
        Voter voter2 = getNewVoter();
        List<Idea> ideas = new ArrayList<>();
        Idea idea = new Idea("1", voter, randomString());
        Idea idea2 = new Idea("1", voter2, randomString());
        ideas.add(idea);
        ideas.add(idea2);
        ideas.add(new Idea("1", voter, randomString()));
        candidateService.confirmationCandidacy(voter, ideas);
        candidateService.removeIdea(voter, idea);
        assertEquals(3, candidateService.getCandidateMap().get(new Candidate(voter)).size());
        candidateService.removeIdea(voter, idea2);
        assertEquals(2, candidateService.getCandidateMap().get(new Candidate(voter)).size());
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
