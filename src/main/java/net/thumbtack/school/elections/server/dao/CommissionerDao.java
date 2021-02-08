package net.thumbtack.school.elections.server.dao;

import net.thumbtack.school.elections.server.model.Commissioner;
import net.thumbtack.school.elections.server.service.ServerException;
import java.util.List;

public interface CommissionerDao<T> {

    List<String> getLogins();

    Commissioner get(String login) throws ServerException;
}
