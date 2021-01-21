package net.thumbtack.school.elections.server.service;

import net.thumbtack.school.elections.server.dao.CandidateDao;
import net.thumbtack.school.elections.server.daoimpl.CandidateDaoImpl;
import net.thumbtack.school.elections.server.model.Candidate;
import net.thumbtack.school.elections.server.model.Context;
import net.thumbtack.school.elections.server.model.Idea;
import net.thumbtack.school.elections.server.model.Voter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CandidateService {
    private final CandidateDao<Candidate> dao;
    private final Map<Candidate, List<Idea>> ideas;

    public CandidateService() {
        dao = new CandidateDaoImpl();
        ideas = new HashMap<>();
    }

    //выдвижение кандидата
    public void addCandidate(Voter candidate) throws ServerException {
        if (!dao.contains(candidate.getLogin())) {
            dao.save(new Candidate(candidate));
        }
    }
    //подтверждение своей кандидатуры
    public void confirmationCandidacy (Voter voter, List<Idea> candidateIdeas) throws ServerException {
        if (dao.contains(voter.getLogin())) {
            ideas.put(dao.get(voter.getLogin()), candidateIdeas);
        } else {
            Candidate candidate = new Candidate(voter);
            dao.save(candidate);
            ideas.put(candidate, candidateIdeas);
        }
    }
    //снятие своей кандидатуры
    public void withdrawCandidacy (Voter voter) throws ServerException {
        ideas.remove(dao.get(voter.getLogin()));
        dao.delete(dao.get(voter.getLogin()));
    }

    public boolean isCandidate(Voter voter) throws ServerException {
        return dao.contains(voter.getLogin());
    }

    public void logout(Voter voter) throws ServerException {
        if (dao.contains(voter.getLogin())) {
            throw new ServerException(ExceptionErrorCode.CANDIDATE_NOT_WITHDRAW_CANDIDACY);
        }
    }
    public void addIdea(Voter voter, Idea idea) throws ServerException {
        ideas.get(dao.get(voter.getLogin())).add(idea);
    }
    public void removeIdea(Voter voter, Idea idea) throws ServerException {
        if (!idea.getAuthor().equals(voter)) {
            ideas.get(dao.get(voter.getLogin())).remove(idea);
        }
    }
    public Map<Candidate, List<Idea>> getCandidateMap() {
        return ideas;
    }

}
