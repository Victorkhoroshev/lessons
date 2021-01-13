package net.thumbtack.school.elections.server.daoimpl;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import net.thumbtack.school.elections.server.dao.Dao;
import net.thumbtack.school.elections.server.database.Database;
import net.thumbtack.school.elections.server.model.Voter;
import net.thumbtack.school.elections.server.service.VoterException;
import net.thumbtack.school.elections.server.service.VoterExceptionErrorCode;

import java.beans.Transient;
import java.io.*;
import java.util.*;

//dao - пакет (см. https://www.codeflow.site/ru/article/java-dao-pattern  только 1 и 2 пункты).
// Связь с базой данных (классом Database) осуществляется именно dao классами.
public class DaoImpl implements Dao<Voter> {

    public DaoImpl(String savedDataFileName) {
        if (savedDataFileName != null) {
            try (ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(savedDataFileName))) {
                Database.getInstance();
                Database.setVoterSet((Set<Voter>) objectInputStream.readObject());
                Database.setLogins((ArrayList<String>)objectInputStream.readObject());
            } catch (IOException | ClassNotFoundException ex) {
                ex.printStackTrace();
            }
        } else {
           Database.getInstance();
           Database.setVoterSet(new HashSet<>());
           Database.setLogins(new ArrayList<>());
        }
    }

    @Override
    public Voter get(String login) throws VoterException {
        Voter voter;
        for (Voter value : Database.getVoterSet()) {
            voter = value;
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
//sync - это стоп сервер
    public void stopServer(String saveDataFileName, Gson gson) {
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(saveDataFileName))) {
            outputStream.writeObject(Database.getVoterSet());
            outputStream.writeObject(Database.getLogins());
        } catch (IOException ex) {
            ex.printStackTrace();
        }
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
//
//    /**апдейтит токен при возващении на сервер */
//    @Override
    //initialize(fileName)
//    public void initialize(String savedDataFileName) {
//        Gson gson = new Gson();
//        JsonObject object = (JsonObject) new JsonParser().parse(gson.toJson(savedDataFileName));
//        Database.getInstance((Set<Voter>)object.get("voterSet"), (List<String>)object.get("logins"));
//    }

//    @Override
//    public void delete(Voter voter) {
//        Database.getVoterMap().remove(voter);
//    }
}
