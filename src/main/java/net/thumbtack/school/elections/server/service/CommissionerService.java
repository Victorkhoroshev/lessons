package net.thumbtack.school.elections.server.service;

import net.thumbtack.school.elections.server.dao.CommissionerDao;
import net.thumbtack.school.elections.server.daoimpl.CommissionerDaoImpl;
import net.thumbtack.school.elections.server.model.Candidate;
import net.thumbtack.school.elections.server.model.Commissioner;
import java.util.List;
import java.util.Set;

public class CommissionerService {
    private final CommissionerDao<Commissioner> dao;
    private final SessionService sessionService;
    private final ElectionService electionService;

    public CommissionerService(SessionService sessionService, ElectionService electionService) {
        dao = new CommissionerDaoImpl();
        this.sessionService = sessionService;
        this.electionService = electionService;
    }

    public String login(String login, String password) throws ServerException {
        Commissioner commissioner = dao.get(login);
        if (!commissioner.getPassword().equals(password)) {
            throw new ServerException(ExceptionErrorCode.WRONG_PASSWORD);
        }
        return sessionService.login(commissioner);
    }

    public boolean isCommissioner(String token) {
        return sessionService.getCommissioner(token) != null;
    }

    public void logout(String token) {
        sessionService.logoutCommissioner(token);
    }

    public void startElection(String token, Set<Candidate> candidateSet) throws ServerException {
        if (sessionService.getCommissioner(token) == null || !sessionService.getCommissioner(token).isChairman()) {
            throw new ServerException(ExceptionErrorCode.NOT_CHAIRMAN);
        }
        electionService.startElection(candidateSet);
    }

    public Set<Candidate> getElectionResult(String token) throws ServerException {
        if (sessionService.getCommissioner(token) != null && sessionService.getCommissioner(token).isChairman()) {
            return electionService.getElectionResult();
        } else {
            throw new ServerException(ExceptionErrorCode.NOT_CHAIRMAN);
        }
    }

    public List<String> getLogins() {
        return dao.getLogins();
    }
}
