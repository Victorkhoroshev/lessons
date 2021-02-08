package net.thumbtack.school.elections.server.service;

import net.thumbtack.school.elections.server.dao.VoterDao;
import net.thumbtack.school.elections.server.daoimpl.VoterDaoImpl;
import net.thumbtack.school.elections.server.model.Voter;
import java.util.Set;

public class VoterService {
    private final VoterDao<Voter> dao;
    private final SessionService sessionService;
    private final ContextService contextService;

    public VoterService(SessionService sessionService, ContextService contextService) {
        dao = new VoterDaoImpl();
        this.sessionService = sessionService;
        this.contextService = contextService;
    }

    /**
     * Register new voter and login him.
     * @param voter new voter for registration.
     * @return Generated voter's id.
     * @throws ServerException if voter already exist or login already exist or election already start.
     */
    public String register(Voter voter) throws ServerException {
        if (!contextService.isElectionStart()) {
            dao.save(voter);
            return sessionService.login(voter);
        }
        throw new ServerException(ExceptionErrorCode.ELECTION_START);
    }

    /**
     * Get all voters, who already register.
     * @return Voter's set.
     */
    public Set<Voter> getAll() {
        return dao.getAll();
    }

    /**
     * Login voter.
     * @param login the login voter, who already logged out from the server.
     * @param password the password voter, who already logged out from the server.
     * @return Generated voter's id.
     * @throws ServerException if login not found or password incorrect for login or election start.
     */
    public String login(String login, String password) throws ServerException {
        if (!contextService.isElectionStart()) {
            Voter voter = dao.get(login);
            if (!voter.getPassword().equals(password)) {
                throw new ServerException(ExceptionErrorCode.WRONG_PASSWORD);
            }
            return sessionService.login(voter);
        }
        throw new ServerException(ExceptionErrorCode.ELECTION_START);
    }

    /**
     * Logout voter.
     * @param token unique voter's id.
     * @throws ServerException if voter already logout.
     */
    public void logout(String token) throws ServerException {
        sessionService.logoutVoter(token);
    }

    /**
     * Get voter by his login.
     * @param login the login voter, who already logged out from the server.
     * @return The voter who owns this login.
     * @throws ServerException if login not found in database.
     */
    public Voter get(String login) throws ServerException {
        return dao.get(login);
    }
}
