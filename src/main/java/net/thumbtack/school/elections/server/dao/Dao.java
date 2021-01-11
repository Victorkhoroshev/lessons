package net.thumbtack.school.elections.server.dao;

import net.thumbtack.school.elections.server.dto.response.RegisterVoterDtoResponse;
import net.thumbtack.school.elections.server.model.Voter;
import net.thumbtack.school.elections.server.service.VoterException;

import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

//daoimpl - пакет с имплементациями dao-интерфейсов.
public interface Dao<T> {
//
    Voter get(String s) throws VoterException;

    Set<T> getAll();

    void save(T t) throws VoterException;

//    String update(T t, String s);
//
//    void delete(T t);
}
