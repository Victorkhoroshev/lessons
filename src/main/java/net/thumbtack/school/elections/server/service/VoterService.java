package net.thumbtack.school.elections.server.service;

import net.thumbtack.school.elections.server.dao.VoterDao;
import net.thumbtack.school.elections.server.daoimpl.VoterDaoImpl;
import net.thumbtack.school.elections.server.model.Voter;
import java.util.HashSet;
import java.util.Set;

public class VoterService {
    private final VoterDao<Voter> dao;
    private final SessionService sessionService;

    public VoterService(SessionService sessionService) {
        dao = new VoterDaoImpl();
        this.sessionService = sessionService;
    }

    /**
     * @param voter
     * @return generated token
     * @throws ServerException
     */
    public String registerVoter(Voter voter) throws ServerException {
        dao.saveVoter(voter);
        return sessionService.loginVoter(voter);
    }

    public Set<String> getAllVoters() {
        Set<String> voters = new HashSet<>();
        for (Voter voter: dao.getAllVoters()) {
            voters.add(voter.getLastName() + " " + voter.getFirstName());
        }
        return voters;
    }

    public String loginVoter(String login, String password) throws ServerException {
        Voter voter = dao.getVoter(login);
        if (!voter.getPassword().equals(password)) {
            throw new ServerException(ExceptionErrorCode.VOTER_WRONG_PASSWORD);
        }
        return sessionService.loginVoter(voter);
    }

    public void logout(String token) throws ServerException {
        sessionService.logout(token);
    }

    public Voter getVoter(String login) throws ServerException {
        return dao.getVoter(login);
    }
}
