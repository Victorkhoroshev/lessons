package net.thumbtack.school.elections.server.database;

import net.thumbtack.school.elections.server.model.Candidate;
import net.thumbtack.school.elections.server.model.Commissioner;
import net.thumbtack.school.elections.server.model.Voter;
import java.util.*;

public final class Database {
    private static Set<Candidate> candidateSet;
    private static Set<Voter> voterSet;
    private static List<String> logins;
    private static Set<Commissioner> commissionerSet;

    private Database() {
    }

    public static Set<Commissioner> getCommissionerSet() {
        return commissionerSet;
    }

    public static Set<Candidate> getCandidateSet() {
        return candidateSet;
    }

    public static Set<Voter> getVoterSet() {
        return voterSet;
    }

    public static List<String> getLogins() {
        return logins;
    }

    public static void setCommissionerSet(Set<Commissioner> commissionerSet) {
        Database.commissionerSet = commissionerSet;
    }

    public static void setCandidateSet(Set<Candidate> candidateSet) {
        Database.candidateSet = candidateSet;
    }

    public static void setVoterSet(Set<Voter> voterSet) {
        Database.voterSet = voterSet;
    }

    public static void setLogins(List<String> logins) {
        Database.logins = logins;
    }
}
