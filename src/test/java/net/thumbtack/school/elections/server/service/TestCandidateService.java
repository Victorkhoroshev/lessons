package net.thumbtack.school.elections.server.service;
import net.thumbtack.school.elections.server.Server;
import net.thumbtack.school.elections.server.database.Database;
import net.thumbtack.school.elections.server.model.Candidate;
import net.thumbtack.school.elections.server.model.Idea;
import net.thumbtack.school.elections.server.model.Voter;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class TestCandidateService {
    private final Server server = new Server();
    private final ContextService contextService = new ContextService();
    private final CandidateService candidateService = new CandidateService(contextService);

    @Test
    public void addCandidateTest() throws IOException, ClassNotFoundException, ServerException {
        server.startServer(null);
        Voter voter1 = getNewVoter();
        Voter voter2 = getNewVoter();
        Voter voter3 = getNewVoter();
        Voter voter4 = getNewVoter();
        voter3.setHasOwnCandidate(true);
        candidateService.addCandidate(voter1, voter2);
        candidateService.addCandidate(voter1, voter2);
        candidateService.addCandidate(voter4, voter2);
        candidateService.addCandidate(voter3, voter1);;
        assertAll(
                () -> assertEquals(1, Database.getCandidateSet().size())
        );
        server.stopServer(null);
    }

    @Test
    public void confirmationCandidacyTest() throws IOException, ClassNotFoundException, ServerException {
        server.startServer(null);
        Voter voter = getNewVoter();
        Voter voter2 = getNewVoter();
        Voter voter3 = getNewVoter();
        Voter voter4 = getNewVoter();
        candidateService.addCandidate(voter3, voter2);
        List<Idea> ideas = new ArrayList<>();
        ideas.add(new Idea("1", voter,randomString()));
        ideas.add(new Idea("2", voter, randomString()));
        candidateService.confirmationCandidacy(voter, ideas);
        List<Idea> ideas2 = new ArrayList<>();
        ideas2.add(new Idea("1", voter2,randomString()));
        ideas2.add(new Idea("2", voter2,randomString()));
        candidateService.confirmationCandidacy(voter2, ideas2);
        candidateService.confirmationCandidacy(voter3, new ArrayList<>());
        assertAll(
                () -> assertTrue(candidateService.getCandidateMap().containsValue(ideas)),
                () -> assertTrue(Database.getCandidateSet().contains(new Candidate(voter))),
                () -> assertTrue(candidateService.getCandidateMap().containsKey(new Candidate(voter2))),
                () -> assertFalse(candidateService.getCandidateMap().containsKey(new Candidate(voter3)))
        );
        contextService.setIsElectionStart(true);
        try {
            candidateService.confirmationCandidacy(voter4, new ArrayList<>());
            fail();
        } catch (ServerException ex) {
            assertEquals(ExceptionErrorCode.ELECTION_START, ex.getErrorCode());
        }
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

    @Test
    public void getCandidateSetTest() throws IOException, ClassNotFoundException, ServerException {
        server.startServer(null);
        Voter voter1 = getNewVoter();
        Voter voter2 = getNewVoter();
        candidateService.confirmationCandidacy(voter1, new ArrayList<>());
        candidateService.confirmationCandidacy(voter2, new ArrayList<>());
        Set<Candidate> candidateSet = new HashSet<>();
        candidateSet.add(new Candidate(voter1));
        candidateSet.add(new Candidate(voter2));
        assertEquals(candidateService.getCandidateSet(), candidateSet);
        server.stopServer(null);
    }

    @Test
    public void getCandidateTest() throws IOException, ClassNotFoundException, ServerException {
        server.startServer(null);
        Voter voter1 = new Voter("Виктор", "Хорошев",
                null,"Пригородная", 1, 188, "1", "111%111Aa");
        Voter voter2 = new Voter("Виктор1", "Хорошев1",
                null,"Пригородная1", 1, 188, "2", "111%111Aa");
        candidateService.confirmationCandidacy(voter1, new ArrayList<>());
        candidateService.confirmationCandidacy(voter2, new ArrayList<>());
        assertAll(
                () -> assertEquals(new Candidate(voter1), candidateService.getCandidate("1")),
                () -> assertNull(candidateService.getCandidate(null))
        );
        try {
            candidateService.getCandidate("4");
        } catch (ServerException ex) {
            assertEquals(ExceptionErrorCode.CANDIDATE_NOT_FOUND, ex.getErrorCode());
        }
        server.stopServer(null);
    }

    @Test
    public void equalsTest() {
        CandidateService candidateService2 = candidateService;
        assertTrue(candidateService.equals(candidateService2) && candidateService2.equals(candidateService));
        assertFalse(candidateService2.equals(null));
        assertFalse(candidateService2.equals(getNewVoter()));
    }

    @Test
    public void hashCodeTest() {
        CandidateService candidateService2 = candidateService;
        assertTrue(candidateService2.hashCode() == candidateService.hashCode());
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
