package net.thumbtack.school.elections.server.database;

import net.thumbtack.school.elections.server.model.Voter;

import java.util.*;


//Database - класс, содержит в себе все необходимые структуры данных ,
// то есть различные коллекции (Map, Set, List и т.д.) классов моделей.
// Для реализации данного класса следует использовать паттерн Singleton
// (https://refactoring.guru/ru/design-patterns/singleton/java/example)
public final class Database {
    private static final  Map<Voter, UUID> voterMap = new HashMap<>();
    private static final List<String> logins = new ArrayList<>();
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

    public static Map<Voter, UUID> getVoterMap() {
        return voterMap;
    }

    public static List<String> getLogins() {
        return logins;
    }
}
