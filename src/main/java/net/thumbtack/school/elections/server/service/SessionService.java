package net.thumbtack.school.elections.server.service;

public class SessionService {
    private static volatile SessionService session;

    public String token;

    private SessionService(String token) {
        this.token = token;
    }

    public static SessionService getInstance(String token) {
        SessionService result = session;
        if (result != null) {
            return result;
        }
        synchronized(SessionService.class) {
            if (session == null) {
                session = new SessionService(token);
            }
            return session;
        }
    }
}
