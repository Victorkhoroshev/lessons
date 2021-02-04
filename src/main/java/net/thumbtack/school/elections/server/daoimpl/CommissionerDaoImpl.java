package net.thumbtack.school.elections.server.daoimpl;

import net.thumbtack.school.elections.server.dao.CommissionerDao;
import net.thumbtack.school.elections.server.database.Database;
import net.thumbtack.school.elections.server.model.Commissioner;
import net.thumbtack.school.elections.server.service.ExceptionErrorCode;
import net.thumbtack.school.elections.server.service.ServerException;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class CommissionerDaoImpl implements CommissionerDao<Commissioner> {
    public CommissionerDaoImpl() {
    }

    public List<String> getLogins() {
        List<String> logins = new ArrayList<>();
        for (Commissioner commissioner: Database.getCommissionerSet()) {
            logins.add(commissioner.getLogin());
        }
        return logins;
    }

    public Commissioner get(String login) throws ServerException {
        for (Commissioner commissioner : Database.getCommissionerSet()) {
            if (commissioner.getLogin().equals(login)) {
                return commissioner;
            }
        }
        throw new ServerException(ExceptionErrorCode.NOT_FOUND);
    }
}
