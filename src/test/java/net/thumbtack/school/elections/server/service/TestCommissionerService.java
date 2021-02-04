package net.thumbtack.school.elections.server.service;

import net.thumbtack.school.elections.server.Server;
import net.thumbtack.school.elections.server.model.Candidate;
import net.thumbtack.school.elections.server.model.Commissioner;
import net.thumbtack.school.elections.server.model.Voter;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class TestCommissionerService {
    private final Server server = new Server();
    private final SessionService sessionService = new SessionService();
    private final ElectionService electionService = new ElectionService();
    private final CommissionerService commissionerService = new CommissionerService(sessionService, electionService);
    @Test
    public void loginTest() throws IOException, ClassNotFoundException, ServerException {
        server.startServer(null);
        Commissioner commissioner = new Commissioner("Виктор", "Хорошев","victor.net", "25345Qw&&", true);
        assertEquals(commissionerService.login(commissioner.getLogin(), commissioner.getPassword()), sessionService.getSession(commissioner).getToken());
        try {
            commissionerService.login(commissioner.getLogin(), commissioner.getPassword() + "12");
            fail();
        } catch (ServerException ex) {
            assertEquals(ExceptionErrorCode.WRONG_PASSWORD, ex.getErrorCode());
        }
        server.stopServer(null);
    }
    @Test
    public void isCommissionerTest() throws IOException, ClassNotFoundException, ServerException {
        server.startServer(null);
        Commissioner commissioner = new Commissioner("Виктор", "Хорошев","victor.net", "25345Qw&&", true);
        assertTrue(commissionerService.isCommissioner(commissionerService.login(commissioner.getLogin(), commissioner.getPassword())));
        server.stopServer(null);
    }
    @Test
    public void logoutTest() throws IOException, ClassNotFoundException, ServerException {
        server.startServer(null);
        Commissioner commissioner = new Commissioner("Виктор", "Хорошев","victor.net", "25345Qw&&", true);
        String token = commissionerService.login(commissioner.getLogin(), commissioner.getPassword());
        commissionerService.logout(token);
        try {
            sessionService.getSession(commissioner);
            fail();
        } catch (ServerException ex) {
            assertEquals(ExceptionErrorCode.LOGOUT, ex.getErrorCode());
        }
        server.stopServer(null);
    }
    @Test
    public void startElectionTest() throws IOException, ClassNotFoundException, ServerException {
        server.startServer(null);
        Commissioner commissioner = new Commissioner("Виктор", "Хорошев","victor.net", "25345Qw&&", true);
        Commissioner commissioner2 = new Commissioner("Егор", "Хорошев","egor.net", "3456eR&21", false);
        commissionerService.login(commissioner.getLogin(), commissioner.getPassword());
        String token = commissionerService.login(commissioner.getLogin(), commissioner.getPassword());
        String token2 = commissionerService.login(commissioner2.getLogin(), commissioner2.getPassword());
        try {
            commissionerService.startElection("1", new HashSet<>());
            fail();
        } catch (ServerException ex) {
            assertEquals(ExceptionErrorCode.NOT_CHAIRMAN, ex.getErrorCode());
        }
        try {
            commissionerService.startElection(token2, new HashSet<>());
            fail();
        } catch (ServerException ex) {
            assertEquals(ExceptionErrorCode.NOT_CHAIRMAN, ex.getErrorCode());
        }
        commissionerService.startElection(token, new HashSet<>());
        assertTrue(electionService.isElectionStart());
        server.stopServer(null);
    }
    @Test
    public void getElectionResult() throws IOException, ClassNotFoundException, ServerException {
        server.startServer(null);
        Commissioner commissioner = new Commissioner("Виктор", "Хорошев","victor.net", "25345Qw&&", true);
        Commissioner commissioner2 = new Commissioner("Егор", "Хорошев","egor.net", "3456eR&21", false);
        Candidate candidate = new Candidate(new Voter("1", "1", "1", 1, "login2222", "1@qWerf324"));
        Candidate candidate2 = new Candidate(new Voter("3", "1", "1", 1, "login222122", "1@qWerf324"));
        Voter voter = new Voter("2", "1", "1", 1, "login21222", "1@qWerf324");
        Set<Candidate> candidateSet = new HashSet<>();
        candidateSet.add(candidate);
        candidateSet.add(candidate2);
        Set<Candidate> result = new HashSet<>();
        result.add(candidate);
        commissionerService.startElection(commissionerService.login(commissioner.getLogin(), commissioner.getPassword()), candidateSet);
        electionService.vote(voter, candidate);
        String token = commissionerService.login(commissioner.getLogin(), commissioner.getPassword());
        String token2 = commissionerService.login(commissioner2.getLogin(), commissioner2.getPassword());
        try {
            commissionerService.getElectionResult("1");
            fail();
        } catch (ServerException ex) {
            assertEquals(ExceptionErrorCode.NOT_CHAIRMAN, ex.getErrorCode());
        }
        try {
            commissionerService.getElectionResult(token2);
            fail();
        } catch (ServerException ex) {
            assertEquals(ExceptionErrorCode.NOT_CHAIRMAN, ex.getErrorCode());
        }
        assertEquals(result ,commissionerService.getElectionResult(token));
        server.stopServer(null);

    }
}
