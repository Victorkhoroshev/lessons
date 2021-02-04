package net.thumbtack.school.elections.server.service;

import net.thumbtack.school.elections.server.model.Candidate;
import net.thumbtack.school.elections.server.model.Voter;

import java.io.Serializable;
import java.util.*;

public class ElectionService implements Serializable {
    private Boolean isElectionStart;
    private Boolean isElectionStop;
    private Map<Candidate, List<Voter>> candidateMap;
    private List<Voter> vsEveryone;

    public ElectionService() {
        setIsElectionStart(false);
        setIsElectionStop(false);
    }

    public Boolean isElectionStart() {
        return isElectionStart;
    }

    public Map<Candidate, List<Voter>> getCandidateMap() {
        return candidateMap;
    }

    public List<Voter> getVsEveryone() {
        return vsEveryone;
    }

    public void startElection(Set<Candidate> candidateSet) {
        setIsElectionStart(true);
        setCandidateMap(new HashMap<>());
        setVsEveryone(new ArrayList<>());
        for (Candidate candidate : candidateSet) {
            candidateMap.put(candidate, new ArrayList<>());
        }
    }

    public void setIsElectionStart(Boolean isElectionStart) {
        this.isElectionStart = isElectionStart;
    }

    public Boolean isElectionStop() {
        return isElectionStop;
    }

    public void setIsElectionStop(Boolean electionStop) {
        isElectionStop = electionStop;
    }

    public void vote(Voter voter, Candidate candidate) {
        if (candidateMap.values().stream()
                .noneMatch(voters -> voters.contains(voter))) {
            if (candidate == null) {
                vsEveryone.add(voter);
            } else if (!voter.equals(candidate.getVoter())){
                candidateMap.get(candidate).add(voter);
            }
        }
    }

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
                setIsElectionStop(true);
            }
        }
        setIsElectionStop(true);
        return candidateSet;
    }

    public void setCandidateMap(Map<Candidate, List<Voter>> candidateMap) {
        this.candidateMap = candidateMap;
    }

    public void setVsEveryone(List<Voter> vsEveryone) {
        this.vsEveryone = vsEveryone;
    }
}
