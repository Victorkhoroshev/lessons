package net.thumbtack.school.elections.server.service;

import net.thumbtack.school.elections.server.Server;
import net.thumbtack.school.elections.server.model.Candidate;
import net.thumbtack.school.elections.server.model.Voter;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class TestElectionService {
    private final Server server = new Server();
    private final ContextService contextService = new ContextService();
    private final ElectionService electionService = new ElectionService(contextService);

    @Test
    public void voteTest() throws IOException, ClassNotFoundException, ServerException {
        server.startServer(null);
        Voter voter = getNewVoter();
        Candidate candidate = new Candidate(voter);
        Candidate candidate2 = new Candidate(getNewVoter());
        Voter voter1 = getNewVoter();
        Voter voter2 = getNewVoter();
        Set<Candidate> candidateSet = new HashSet<>();
        candidateSet.add(candidate);
        candidateSet.add(candidate2);
        electionService.startElection(candidateSet);
        electionService.vote(voter1, candidate);
        electionService.vote(voter1, candidate);
        electionService.vote(voter1, candidate2);
        electionService.vote(voter1, null);
        electionService.vote(voter2, null);
        electionService.vote(voter, candidate);
        assertEquals(1 ,electionService.getCandidateMap().get(candidate).size());
        assertEquals(0 ,electionService.getCandidateMap().get(candidate2).size());
        assertEquals(1, electionService.getVsEveryone().size());
        server.stopServer(null);
    }

    @Test
    public void getElectionResultTest() throws IOException, ClassNotFoundException, ServerException {
        server.startServer(null);
        Voter voter1 = getNewVoter();
        Voter voter2 = getNewVoter();
        Voter voter3 = getNewVoter();
        Voter voter4 = getNewVoter();
        Voter voter5 = getNewVoter();
        Voter voter6 = getNewVoter();
        Voter voter7 = getNewVoter();
        Voter voter8 = getNewVoter();
        Voter voter9 = getNewVoter();
        Voter voter10 = getNewVoter();
        Voter voter11 = getNewVoter();
        Candidate candidate = new Candidate(getNewVoter());
        Candidate candidate1 = new Candidate(getNewVoter());
        Candidate candidate2 = new Candidate(getNewVoter());
        Candidate candidate3 = new Candidate(getNewVoter());
        Set<Candidate> candidateSet = new HashSet<>();
        candidateSet.add(candidate);
        candidateSet.add(candidate1);
        candidateSet.add(candidate2);
        candidateSet.add(candidate3);
        electionService.startElection(candidateSet);
        Set<Candidate> result = new HashSet<>();
        result.add(candidate1);
        result.add(candidate3);
        electionService.vote(voter10, candidate);
        electionService.vote(voter11, candidate);
        electionService.vote(voter1, candidate1);
        electionService.vote(voter2, candidate1);
        electionService.vote(voter3, candidate1);
        electionService.vote(voter4, candidate2);
        electionService.vote(voter7, candidate3);
        electionService.vote(voter8, candidate3);
        electionService.vote(voter9, candidate3);
        electionService.vote(voter5, null);
        electionService.vote(voter6, null);
        assertEquals(result, electionService.getElectionResult());
        electionService.vote(voter1, candidate1);
        electionService.vote(voter2, candidate3);
        electionService.vote(voter3, null);
        electionService.vote(voter4, null);
        assertTrue(electionService.getElectionResult().contains(null));
        assertTrue(contextService.isElectionStop());
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
