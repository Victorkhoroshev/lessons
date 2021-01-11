package net.thumbtack.school.elections.server.daoimpl;

import net.thumbtack.school.elections.server.dao.Dao;
import net.thumbtack.school.elections.server.database.Database;
import net.thumbtack.school.elections.server.model.Voter;
import net.thumbtack.school.elections.server.service.VoterException;
import net.thumbtack.school.elections.server.service.VoterExceptionErrorCode;

import java.util.Iterator;
import java.util.Set;
import java.util.UUID;

//dao - пакет (см. https://www.codeflow.site/ru/article/java-dao-pattern  только 1 и 2 пункты).
// Связь с базой данных (классом Database) осуществляется именно dao классами.
public class DaoImpl implements Dao<Voter> {

    @Override
    public Voter get(String login) throws VoterException {
        Voter voter;
        Iterator<Voter> iterator = Database.getVoterSet().iterator();
        while (iterator.hasNext()) {
            voter = iterator.next();
            if (voter.getLogin().equals(login)) {
                return voter;
            }
        }
        throw new VoterException(VoterExceptionErrorCode.VOTER_NOT_FOUND);
    }

    @Override
    public Set<Voter> getAll() {
        return Database.getVoterSet();
    }

    @Override
    public void save(Voter voter) throws VoterException {
        if (Database.getVoterSet().contains(voter)){
            throw new VoterException(VoterExceptionErrorCode.VOTER_ALREADY_EXISTS);
        }
        for (String s : Database.getLogins()) {
            if (voter.getLogin().equals(s)) {
                throw new VoterException(VoterExceptionErrorCode.VOTER_LOGIN_ALREADY_EXISTS);
            }
        }
        Database.getVoterSet().add(voter);
        Database.getLogins().add(voter.getLogin());
    }

    /**апдейтит токен при возващении на сервер */
//    @Override
//    public String update(Voter voter, String token) {
//        Database.getVoterMap().replace(voter, UUID.fromString(token));
//        return Database.getVoterMap().get(voter).toString();
//    }

//    @Override
//    public void delete(Voter voter) {
//        Database.getVoterMap().remove(voter);
//    }
}
