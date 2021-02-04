package net.thumbtack.school.elections.server.service;

import net.thumbtack.school.elections.server.dao.CandidateDao;
import net.thumbtack.school.elections.server.daoimpl.CandidateDaoImpl;
import net.thumbtack.school.elections.server.model.Candidate;
import net.thumbtack.school.elections.server.model.Idea;
import net.thumbtack.school.elections.server.model.Voter;

import java.io.Serializable;
import java.util.*;

public class CandidateService implements Serializable {
    private final transient CandidateDao<Candidate> dao;
    private final Map<Candidate, List<Idea>> ideas;

    public CandidateService() {
        dao = new CandidateDaoImpl();
        ideas = new HashMap<>();
    }

    //выдвижение кандидата
    public void addCandidate(Voter candidate) {
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

    public boolean isCandidate(Voter voter) {
        return ideas.containsKey(new Candidate(voter));
    }

    public void addIdea(Voter voter, Idea idea) throws ServerException {
        ideas.get(dao.get(voter.getLogin())).add(idea);
    }
    public void removeIdea(Voter voter, Idea idea) throws ServerException {
        if (idea.getAuthor() != voter) {
            ideas.get(dao.get(voter.getLogin())).remove(idea);
        }
    }
    public Map<Candidate, List<Idea>> getCandidateMap() {
        return ideas;
    }
    public Set<Candidate> getCandidateSet() {
        return ideas.keySet();
    }
    public Candidate getCandidate(String login) throws ServerException {
        if (login != null) {
            for (Candidate candidate : ideas.keySet()) {
                if (candidate.getVoter().getLogin().equals(login)) {
                    return candidate;
                }
            }
            throw new ServerException(ExceptionErrorCode.CANDIDATE_NOT_FOUND);
        }
        return null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CandidateService that = (CandidateService) o;
        return ideas.equals(that.ideas);
    }

    @Override
    public int hashCode() {
        return Objects.hash(dao, ideas);
    }
}
