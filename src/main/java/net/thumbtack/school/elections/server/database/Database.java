package net.thumbtack.school.elections.server.database;

import net.thumbtack.school.elections.server.model.Voter;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectStreamException;
import java.io.Serializable;
import java.util.*;


//Database - класс, содержит в себе все необходимые структуры данных ,
// то есть различные коллекции (Map, Set, List и т.д.) классов моделей.
// Для реализации данного класса следует использовать паттерн Singleton
// (https://refactoring.guru/ru/design-patterns/singleton/java/example)
public final class Database {
    private static Set<Voter> voterSet;
    private static List<String> logins;
    private static volatile Database database;


    public static Database getInstance() {
        Database result = database;
        if (result != null) {
            return result;
        }
        synchronized (Database.class) {
            if (database == null) {
                database = new Database();
            }
            return database;
        }
    }

    public static Set<Voter> getVoterSet() {
        return voterSet;
    }

    public static List<String> getLogins() {
        return logins;
    }

    public static void setVoterSet(Set<Voter> voterSet) {
        Database.voterSet = voterSet;
    }

    public static void setLogins(List<String> logins) {
        Database.logins = logins;
    }
}
