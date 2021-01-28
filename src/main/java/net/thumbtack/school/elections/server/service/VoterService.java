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
    public String register(Voter voter) throws ServerException {
        dao.save(voter);
        return sessionService.login(voter);
    }

    public Set<String> getAll() {
        Set<String> voters = new HashSet<>();
        for (Voter voter: dao.getAll()) {
            voters.add(voter.getLastName() + " " + voter.getFirstName());
        }
        return voters;
    }

    public String login(String login, String password) throws ServerException {
        Voter voter = dao.get(login);
        if (!voter.getPassword().equals(password)) {
            throw new ServerException(ExceptionErrorCode.VOTER_WRONG_PASSWORD);
        }
        return sessionService.login(voter);
    }

    public void logout(String token) throws ServerException {
        sessionService.logout(token);
    }

    public Voter get(String login) throws ServerException {
        return dao.get(login);
    }
}
