package net.thumbtack.school.elections.server.daoimpl;

import net.thumbtack.school.elections.server.Server;
import net.thumbtack.school.elections.server.dao.CommissionerDao;
import net.thumbtack.school.elections.server.model.Commissioner;
import net.thumbtack.school.elections.server.service.ExceptionErrorCode;
import net.thumbtack.school.elections.server.service.ServerException;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class TestCommissionerDaoImpl {
    private final Server server = new Server();
    private final CommissionerDao<Commissioner> dao = new CommissionerDaoImpl();
    @Test
    public void getTest() throws IOException, ClassNotFoundException {
        server.startServer(null);
        Commissioner commissioner1 = new Commissioner("victor.net", "25345Qw&&", true);
        Commissioner commissioner2 = new Commissioner("egor.net", "3456eR&21", false);
        assertAll(
                () -> assertEquals(commissioner1, dao.get(commissioner1.getLogin())),
                () -> assertEquals(commissioner2, dao.get(commissioner2.getLogin()))
        );
        try {
            dao.get("1");
            fail();
        } catch (ServerException ex) {
            assertEquals(ExceptionErrorCode.NOT_FOUND, ex.getErrorCode());
        }
        server.stopServer(null);
    }

}
