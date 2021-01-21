package net.thumbtack.school.elections.server.daoimpl;

import net.thumbtack.school.elections.server.dao.CandidateDao;
import net.thumbtack.school.elections.server.database.Database;
import net.thumbtack.school.elections.server.model.Candidate;
import net.thumbtack.school.elections.server.service.ExceptionErrorCode;
import net.thumbtack.school.elections.server.service.ServerException;

import java.util.Set;

public class CandidateDaoImpl implements CandidateDao<Candidate> {

    @Override
    public Candidate get(String login) throws ServerException {
        Candidate candidate;
        for (Candidate value : Database.getCandidateSet()) {
            candidate = value;
            if (candidate.getVoter().getLogin().equals(login)) {
                return candidate;
            }
        }
        throw new ServerException(ExceptionErrorCode.CANDIDATE_NOT_FOUND);
    }

    @Override
    public void save(Candidate candidate) {
        Database.getCandidateSet().add(candidate);
    }

    @Override
    public boolean contains(String login) {
        for (Candidate candidate : Database.getCandidateSet()) {
            if (candidate.getVoter().getLogin().equals(login)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void delete(Candidate candidate) {
        Database.getCandidateSet().remove(candidate);
    }
}
