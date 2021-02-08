package net.thumbtack.school.elections.server.daoimpl;

import net.thumbtack.school.elections.server.Server;
import net.thumbtack.school.elections.server.dao.ContextDao;
import net.thumbtack.school.elections.server.database.Database;
import net.thumbtack.school.elections.server.model.Candidate;
import net.thumbtack.school.elections.server.model.Context;
import net.thumbtack.school.elections.server.model.Voter;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class TestContextDaoImpl {
    private final Server server = new Server();
    private final ContextDao<Context> dao = new ContextDaoImpl();
    @Test
    public void syncTest() throws IOException, ClassNotFoundException {
        server.startServer(null);
        assertAll(
                () -> assertEquals(3, Database.getLogins().size()),
                () -> assertEquals(0, Database.getVoterSet().size()),
                () -> assertEquals(0, Database.getCandidateSet().size())
        );
        Context context = new Context();
        Voter voter1 = new Voter("Виктор", "Хорошев",
                "Пригородная", 21, 188, "victor@khoroshev.net"," 1111");
        Voter voter2 = new Voter("Андрей", "Хорышев",
                "Пригородная", 21, 188, "victooroshev.net"," 1111");
        Candidate candidate1 = new Candidate(voter1);
        Candidate candidate2 = new Candidate(voter2);
        List<String> logins = new ArrayList<>(Arrays.asList("victor@khoroshev.net", "victooroshev.net"));
        Set<Voter> voterSet = new HashSet<>(Arrays.asList(voter1, voter2));
        Set<Candidate> candidateSet = new HashSet<>(Arrays.asList(candidate1, candidate2));
        Database.setLogins(logins);
        Database.setVoterSet(voterSet);
        Database.setCandidateSet(candidateSet);
        dao.sync(context);
        assertAll(
                () -> assertEquals(voterSet, Database.getVoterSet()),
                () -> assertEquals(logins, Database.getLogins()),
                () -> assertEquals(candidateSet, Database.getCandidateSet())
        );

        List<String> logins1 = new ArrayList<>();
        Set<Voter> voterSet1 = new HashSet<>();
        Set<Candidate> candidateSet1 = new HashSet<>();
        context.setLogins(logins1);
        context.setVoterSet(voterSet1);
        context.setCandidateSet(candidateSet1);
        ContextDao<Context> daoWithContext = new ContextDaoImpl(context);
        assertAll(
                () -> assertEquals(logins1, Database.getLogins()),
                () -> assertEquals(voterSet1, Database.getVoterSet()),
                () -> assertEquals(candidateSet1, Database.getCandidateSet())
        );
        server.stopServer(null);
    }
}
