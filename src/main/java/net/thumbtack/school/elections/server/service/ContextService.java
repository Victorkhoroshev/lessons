package net.thumbtack.school.elections.server.service;

import net.thumbtack.school.elections.server.dao.ContextDao;
import net.thumbtack.school.elections.server.daoimpl.ContextDaoImpl;
import net.thumbtack.school.elections.server.model.Context;

public class ContextService {
    private final Context context;
    private final ContextDao<Context> contextDao;
    private final CandidateService candidateService;
    private final IdeaService ideaService;

    public ContextService(CandidateService candidateService, IdeaService ideaService) {
        context = Context.getInstance();
        contextDao = new ContextDaoImpl();
        this.candidateService = candidateService;
        this.ideaService = ideaService;
    }

    public ContextService(Context context) {
        this.context = context;
        contextDao = new ContextDaoImpl(context);
        candidateService = context.getCandidateService();
        ideaService = context.getIdeaService();
    }

    public void sync() {
        contextDao.sync(context);
    }

    public Context getContext() {
        return context;
    }
}
