package net.thumbtack.school.elections.server.dao;

public interface ContextDao<T>{
    void sync(T t);
}
