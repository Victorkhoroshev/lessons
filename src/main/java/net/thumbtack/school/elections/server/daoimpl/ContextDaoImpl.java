package net.thumbtack.school.elections.server.daoimpl;

import net.thumbtack.school.elections.server.dao.ContextDao;
import net.thumbtack.school.elections.server.database.Database;
import net.thumbtack.school.elections.server.model.Context;
import java.util.ArrayList;
import java.util.HashSet;

public class ContextDaoImpl implements ContextDao<Context> {

    public ContextDaoImpl() {
        Database.setCandidateSet(new HashSet<>());
        Database.setVoterSet(new HashSet<>());
        Database.setLogins(new ArrayList<>());
    }

    public ContextDaoImpl(Context context) {
        Database.setCandidateSet(context.getCandidateSet());
        Database.setVoterSet(context.getVoterSet());
        Database.setLogins(context.getLogins());
    }

    @Override
    public void sync(Context context) {
        context.setCandidateSet(Database.getCandidateSet());
        context.setVoterSet(Database.getVoterSet());
        context.setLogins(Database.getLogins());
    }
}
