package net.thumbtack.school.elections.server.daoimpl;

import net.thumbtack.school.elections.server.dao.Dao;
import net.thumbtack.school.elections.server.database.Database;
import net.thumbtack.school.elections.server.dto.request.RegisterVoterDtoRequest;
import net.thumbtack.school.elections.server.dto.response.RegisterVoterDtoResponse;
import net.thumbtack.school.elections.server.model.Voter;
import net.thumbtack.school.elections.server.service.VoterException;
import net.thumbtack.school.elections.server.service.VoterExceptionErrorCode;

import java.util.Map;
import java.util.Optional;
import java.util.UUID;

//dao - пакет (см. https://www.codeflow.site/ru/article/java-dao-pattern  только 1 и 2 пункты).
// Связь с базой данных (классом Database) осуществляется именно dao классами.
public class DaoImpl implements Dao<Voter> {

    /**тут изменить, чтобы принимало значение токена*/
    @Override
    public Voter get(String token) throws VoterException {
        for (Voter voter: Database.getVoterMap().keySet()) {
            if (token.equals(voter.getToken())) {
                return voter;
            }
        }
        throw new VoterException(VoterExceptionErrorCode.VOTER_NOT_FOUND);
    }

    @Override
    public Map<Voter, UUID> getAll() {
        return Database.getVoterMap();
    }

    @Override
    public String save(Voter voter) throws VoterException {
        if (Database.getVoterMap().containsKey(voter)){
            throw new VoterException(VoterExceptionErrorCode.VOTER_ALREADY_EXISTS);
        }
        for (String s : Database.getLogins()) {
            if (voter.getLogin().equals(s)) {
                throw new VoterException(VoterExceptionErrorCode.VOTER_LOGIN_ALREADY_EXISTS);
            }
        }
        Database.getVoterMap().put(voter, UUID.fromString(voter.getToken()));
        Database.getLogins().add(voter.getLogin());
        return Database.getVoterMap().get(voter).toString();
    }

    /**апдейтит токен при возващении на сервер */
    @Override
    public String update(Voter voter, String token) {
        Database.getVoterMap().replace(voter, UUID.fromString(token));
        return Database.getVoterMap().get(voter).toString();
    }

    @Override
    public void delete(Voter voter) {
        Database.getVoterMap().remove(voter);
    }
}
