package net.thumbtack.school.elections.server.model;

import java.util.*;

public class Candidate {
    private final Voter voter;

    public Candidate(Voter candidate) {
        this.voter = candidate;
    }

    public Voter getVoter() {
        return voter;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Candidate candidate = (Candidate) o;
        return Objects.equals(voter, candidate.voter);
    }

    @Override
    public int hashCode() {
        return Objects.hash(voter);
    }
}
