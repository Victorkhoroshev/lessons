package net.thumbtack.school.elections.server.daoimpl;

import net.thumbtack.school.elections.server.dao.VoterDao;
import net.thumbtack.school.elections.server.database.Database;
import net.thumbtack.school.elections.server.model.Voter;
import net.thumbtack.school.elections.server.service.ServerException;
import net.thumbtack.school.elections.server.service.ExceptionErrorCode;
import java.util.*;

public class VoterDaoImpl implements VoterDao<Voter> {

    @Override
    public Voter getVoter(String login) throws ServerException {
        Voter voter;
        for (Voter value : Database.getVoterSet()) {
            voter = value;
            if (voter.getLogin().equals(login)) {
                return voter;
            }
        }
        throw new ServerException(ExceptionErrorCode.VOTER_NOT_FOUND);
    }

    @Override
    public Set<Voter> getAllVoters() {
        return Database.getVoterSet();
    }

    @Override
    public void saveVoter(Voter voter) throws ServerException {
        if (Database.getVoterSet().contains(voter)){
            throw new ServerException(ExceptionErrorCode.VOTER_ALREADY_EXISTS);
        }
        for (String s : Database.getLogins()) {
            if (voter.getLogin().equals(s)) {
                throw new ServerException(ExceptionErrorCode.VOTER_LOGIN_ALREADY_EXISTS);
            }
        }
        Database.getVoterSet().add(voter);
        Database.getLogins().add(voter.getLogin());
    }
}
