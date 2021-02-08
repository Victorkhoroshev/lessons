package net.thumbtack.school.elections.server.service;

import net.thumbtack.school.elections.server.model.Candidate;
import net.thumbtack.school.elections.server.model.Voter;
import java.io.Serializable;
import java.util.*;

public class ElectionService implements Serializable {
    private final transient ContextService contextService;
    private Map<Candidate, List<Voter>> candidateMap;
    private List<Voter> vsEveryone;

    public ElectionService(ContextService contextService) {
        this.contextService = contextService;
    }

    public Map<Candidate, List<Voter>> getCandidateMap() {
        return candidateMap;
    }

    public List<Voter> getVsEveryone() {
        return vsEveryone;
    }

    /**
     * Start election, set "is election start" - true, create new candidate's map with candidates and their voters,
     * filling with the passed values, create new empty versus everyone set.
     * @param candidateSet set of candidates who have confirmed their candidacy.
     */
    public void startElection(Set<Candidate> candidateSet) {
        contextService.setIsElectionStart(true);
        setCandidateMap(new HashMap<>());
        setVsEveryone(new ArrayList<>());
        for (Candidate candidate : candidateSet) {
            candidateMap.put(candidate, new ArrayList<>());
        }
    }

    /**
     * If the voter did not contains in candidate's map and list versus everyone and the candidate is not equal null:
     * in the value of candidate from candidate's map put this voter.
     * If the voter did not contains in candidate's map and list versus everyone the candidate is equal null:
     * versus everyone list add this voter.
     * @param voter voter who wand vote.
     * @param candidate candidate to vote for.
     * @throws ServerException if election not start or election already stop.
     */
    public void vote(Voter voter, Candidate candidate) throws ServerException {
        if (contextService.isElectionStart()) {
            if (contextService.isElectionStop()) {
                throw new ServerException(ExceptionErrorCode.ELECTION_STOP);
            }
            if (candidateMap.values().stream()
                    .noneMatch(voters -> voters.contains(voter))) {
                if (candidate == null) {
                    vsEveryone.add(voter);
                } else if (!voter.equals(candidate.getVoter())) {
                    candidateMap.get(candidate).add(voter);
                }
            }
        } else {
            throw new ServerException(ExceptionErrorCode.ELECTION_NOT_START);
        }
    }

    /**
     * Get election's result.
     * If the return value contains 2 or more elements, clear candidates map and versus everyone map,
     * fills the candidates map with new values, conducts the second round.
     * @return If the size of voters of at map one candidate is greater than the size of list versus everyone size:
     * set of candidates with the highest number of votes.
     * Else: null.
     */
    public Set<Candidate> getElectionResult() {
        Set<Candidate> candidateSet = new HashSet<>();
        Candidate candidate = null;
        int i = 0;
        for (Map.Entry<Candidate, List<Voter>> entry: candidateMap.entrySet()) {
            if (entry.getValue().size() > i && entry.getValue().size() > vsEveryone.size()) {
                i = entry.getValue().size();
                candidate = entry.getKey();
            }
        }
        candidateSet.add(candidate);
        if (candidate != null) {
            for (Map.Entry<Candidate, List<Voter>> entry : candidateMap.entrySet()) {
                if (entry.getValue().size() == candidateMap.get(candidate).size() && !entry.getValue().equals(candidateMap.get(candidate))) {
                    candidateSet.add(entry.getKey());
                }
            }
            if (candidateSet.size() > 1) {
                startElection(candidateSet);
            } else {
                contextService.setIsElectionStop(true);
            }
        } else {
            contextService.setIsElectionStop(true);
        }
        return candidateSet;
    }

    public void setCandidateMap(Map<Candidate, List<Voter>> candidateMap) {
        this.candidateMap = candidateMap;
    }

    public void setVsEveryone(List<Voter> vsEveryone) {
        this.vsEveryone = vsEveryone;
    }
}
