package net.thumbtack.school.elections.server.service;

import net.thumbtack.school.elections.server.dao.ContextDao;
import net.thumbtack.school.elections.server.daoimpl.ContextDaoImpl;
import net.thumbtack.school.elections.server.model.Context;

public class ContextService {
    private final Context context;
    private final ContextDao<Context> contextDao;

    public ContextService() {
        context = Context.getInstance();
        contextDao = new ContextDaoImpl();
    }

    public ContextService(Context context) {
        this.context = context;
        contextDao = new ContextDaoImpl(context);
    }

    public void sync() {
        contextDao.sync(context);
    }

    public Context getContext() {
        return context;
    }
}
