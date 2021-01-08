package net.thumbtack.school.elections.server.daoimpl;
import net.thumbtack.school.elections.server.database.Database;
import net.thumbtack.school.elections.server.model.Voter;
import net.thumbtack.school.elections.server.service.VoterException;
import net.thumbtack.school.elections.server.service.VoterExceptionErrorCode;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestDaoImpl {
    private DaoImpl dao = new DaoImpl();
    @Test
    public void saveNewVoter() throws VoterException {
        Voter voter = new Voter("Виктор", "Хорошев",
                "Пригородная", 21, 188, "victor@khoroshev.net"," 1111");
        Voter voter1 = new Voter("Виктор", "Хорошев",
                "Пригородная", 21, 188, "victor.net","121231");
        Voter voter2 = new Voter("Андрей", "Хорошев",
                "Пригородная", 21, 188, "victor@khoroshev.net"," 1111");
        assertEquals(voter.getToken(), dao.save(voter));
        try {
            dao.save(voter1);
            fail();
        } catch (VoterException ex) {
            assertEquals(VoterExceptionErrorCode.VOTER_ALREADY_EXISTS, ex.getErrorCode());
        }
        try {
            dao.save(voter2);
            fail();
        } catch (VoterException ex) {
            assertEquals(VoterExceptionErrorCode.VOTER_LOGIN_ALREADY_EXISTS, ex.getErrorCode());
        }
    }

}
