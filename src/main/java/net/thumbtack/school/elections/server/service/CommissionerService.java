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
    private final ContextService contextService;

    public CommissionerService(SessionService sessionService, ElectionService electionService, ContextService contextService) {
        dao = new CommissionerDaoImpl();
        this.sessionService = sessionService;
        this.electionService = electionService;
        this.contextService = contextService;
    }

    /**
     * Login commissioner.
     * @param login the login voter, who already logged out from the server.
     * @param password the password commissioner, who already logged out from the server.
     * @return Unique commissioner's id.
     * @throws ServerException if login not found or password incorrect for login or election start.
     */
    public String login(String login, String password) throws ServerException {
        if (!contextService.isElectionStart()) {
            Commissioner commissioner = dao.get(login);
            if (!commissioner.getPassword().equals(password)) {
                throw new ServerException(ExceptionErrorCode.WRONG_PASSWORD);
            }
            return sessionService.login(commissioner);
        } else {
            throw new ServerException(ExceptionErrorCode.ELECTION_START);
        }
    }

    /**
     * Commissioner verification.
     * @param token unique commissioner's id.
     * @return If commissioner's session exist: true.
     * If commissioner's session not exist: false.
     */
    public boolean isCommissioner(String token) {
        return sessionService.getCommissioner(token) != null;
    }

    /**
     * Logout commissioner.
     * @param token unique commissioner's id.
     */
    public void logout(String token) {
        sessionService.logoutCommissioner(token);
    }

    /**
     * Start election.
     * @param token commissioner's unique id.
     * @param candidateSet set candidates, who confirmed their candidacy.
     * @throws ServerException if the token does not belong to chairman.
     */
    public void startElection(String token, Set<Candidate> candidateSet) throws ServerException {
        if (sessionService.getCommissioner(token) == null || !sessionService.getCommissioner(token).isChairman()) {
            throw new ServerException(ExceptionErrorCode.NOT_CHAIRMAN);
        }
        electionService.startElection(candidateSet);
    }

    /**
     * Get election result.
     * If result contains only one candidate, than election stop.
     * @param token commissioner's unique id.
     * @return Candidates set.
     * @throws ServerException if the token does not belong to chairman.
     */
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
