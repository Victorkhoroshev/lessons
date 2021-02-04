package net.thumbtack.school.elections.server.daoimpl;

import net.thumbtack.school.elections.server.dao.ContextDao;
import net.thumbtack.school.elections.server.database.Database;
import net.thumbtack.school.elections.server.model.Commissioner;
import net.thumbtack.school.elections.server.model.Context;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ContextDaoImpl implements ContextDao<Context> {

    public ContextDaoImpl() {
        Set<Commissioner> commissionerSet = new HashSet<>();
        List<String> logins = new ArrayList<>();
        commissionerSet.add(new Commissioner("Виктор", "Хорошев","victor.net", "25345Qw&&", true));
        commissionerSet.add(new Commissioner("Егор", "Хорошев","egor.net", "3456eR&21", false));
        commissionerSet.add(new Commissioner("Игорь", "Хорошев", "igor.net", "77??SDSw23", false));
        logins.add("victor@khoroshev.net");
        logins.add("egor@khoroshev.net");
        logins.add("igor@khoroshev.net");
        Database.setCandidateSet(new HashSet<>());
        Database.setVoterSet(new HashSet<>());
        Database.setLogins(logins);
        Database.setCommissionerSet(commissionerSet);
    }

    public ContextDaoImpl(Context context) {
        Database.setCandidateSet(context.getCandidateSet());
        Database.setVoterSet(context.getVoterSet());
        Database.setLogins(context.getLogins());
        Database.setCommissionerSet(context.getCommissionerSet());
    }

    @Override
    public void sync(Context context) {
        context.setCandidateSet(Database.getCandidateSet());
        context.setVoterSet(Database.getVoterSet());
        context.setLogins(Database.getLogins());
        context.setCommissionerSet(Database.getCommissionerSet());
    }
}
