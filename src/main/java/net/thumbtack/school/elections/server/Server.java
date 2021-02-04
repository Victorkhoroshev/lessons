package net.thumbtack.school.elections.server;
import com.google.gson.Gson;
import net.thumbtack.school.elections.server.dto.request.*;
import net.thumbtack.school.elections.server.dto.response.*;
import net.thumbtack.school.elections.server.model.Context;
import net.thumbtack.school.elections.server.service.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;


//Server - класс, принимающий запросы (вызовы методов данного класса). В данном классе определены все сервисы.
// Запросы приходят в методы класса Server в виде json- строки. Сервер возвращает ответ также в виде json-строки.

public class Server {
    //модификаторы для тестов - package
    private static final String EMPTY_JSON = "";
    private static final String NULL_VALUE = "Некорректный запрос";
    private static final String ELECTION_START = "Выборы уже проходят, действие невозможно.";
    Gson gson;
    ContextService contextService;
    SessionService sessionService;
    IdeaService ideaService;
    CandidateService candidateService;
    VoterService voterService;
    CommissionerService commissionerService;
    ElectionService electionService;
    // Производит всю необходимую инициализацию и запускает сервер.
//savedDataFileName - имя файла, в котором было сохранено состояние сервера.
// Если savedDataFileName == null, восстановление состояния не производится, сервер стартует “с нуля”.
//все сервисы инициализируются здесь
    public void startServer(String savedDataFileName) throws IOException, ClassNotFoundException {
        gson = new Gson();
        sessionService = new SessionService();
        voterService = new VoterService(sessionService);
        if (savedDataFileName != null) {
            try (ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(savedDataFileName))) {
                Context context = (Context) objectInputStream.readObject();
                ideaService = context.getIdeaService();
                candidateService = context.getCandidateService();
                electionService = context.getElectionService();
                commissionerService = new CommissionerService(sessionService, electionService);
                contextService = new ContextService(context);
            }
        } else {
            ideaService = new IdeaService();
            candidateService = new CandidateService();
            electionService = new ElectionService();
            commissionerService = new CommissionerService(sessionService, electionService);
            contextService = new ContextService();
        }
    }

//Останавливает сервер и записывает все его содержимое в файл сохранения с именем savedDataFileName.
// Если saveDataFileName == null, запись содержимого не производится.
    public void stopServer(String saveDataFileName) throws IOException {
        gson = null;
        sessionService = null;
        voterService = null;
        if (saveDataFileName != null) {
            try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(saveDataFileName))) {
                Context context = contextService.getContext();
                contextService.sync();
                context.setCandidateService(candidateService);
                context.setIdeaService(ideaService);
                context.setElectionService(electionService);
                objectOutputStream.writeObject(context);
            }
        }
        ideaService = null;
        candidateService = null;
        contextService = null;
        electionService = null;
        commissionerService = null;
    }

//Регистрирует избирателя на сервере. requestJsonString содержит данные об избирателе , необходимые для регистрации.
// Метод при успешном выполнении возвращает json с единственным элементом “token”.
// Если же команду по какой-то причине выполнить нельзя, возвращает json с элементом “error”
//контроллер проверяет валидность входа
    public String register(String requestJsonString) {
        String response = "";
        RegisterDtoRequest request = gson.fromJson(requestJsonString, RegisterDtoRequest.class);
        if (electionService.isElectionStart().equals(true)) {
            return gson.toJson(new ErrorDtoResponse(ELECTION_START));
        }
        if (requestJsonString == null || !request.requiredFieldsIsNotNull()) {
            return gson.toJson(new ErrorDtoResponse("Пожалуйста, заполните все данные."));
        }
        if (!request.isFirstNameValid()) {
            response += "Имя должно быть на кириллице, без пробелов, спец. символов и цифр.\n";
        }
        if (!request.isLastNameValid()) {
            response += "Фамилия должна быть на кириллице, без пробелов, спец. символов и цифр.\n";
        }
        if (!request.isPatronymicValid()) {
            response += "Отчество должно быть на кириллице, без пробелов, спец. символов и цифр.\n";
        }
        if (!request.isStreetValid()) {
            response += "Название улицы должно быть на кириллице, без пробелов, спец. символов и цифр.\n";
        }
        if (!request.isHouseValid()) {
            response += "Номер дома не может быть меньше единицы.\n";
        }
        if (!request.isApartmentValid()) {
            response += "Номер квартиры не может быть меньше нуля.\n";
        }
        if (!request.isLoginValid()) {
            response += "Длинна логина должна быть не меньше 9 символо.\n";
        }
        if (!request.isPasswordValid()) {
            response += "Пароль должен содержать хотя бы одну заглавную букву, одну строчную букву," +
            " цифру и один спец. символ, а его длинна не менее 9 символов, без пробелов.\n";
        }
        if (response.length() <  1){
            try {
                return gson.toJson(new RegisterDtoResponse(voterService.register(request.newVoter())));
            } catch (ServerException ex) {
                return gson.toJson(new ErrorDtoResponse(ex.getLocalizedMessage()));
            }
        }
        return gson.toJson(new ErrorDtoResponse(response));
    }

    public String login(String requestJsonString) {
        String response = "";
        LoginDtoRequest request = gson.fromJson(requestJsonString, LoginDtoRequest.class);
        if (request == null || !request.requiredFieldsIsNotNull()) {
            response += "Пожалуйста, введите логин и пароль.";
            return gson.toJson(new ErrorDtoResponse(response));
        }
        if(!request.isLoginValid()) {
            response += "Длинна логина должна быть не меньше 9 символо.\n";
        }
        if(!request.isPasswordValid()) {
            response += "Пароль должен содержать хотя бы одну заглавную букву, одну строчную букву," +
                    " цифру и один спец. символ, а его длинна не менее 9 символов, без пробелов.\n";
        }
        if (response.length() < 1) {
            try {
                if (commissionerService.getLogins().contains(request.getLogin())) {
                    return gson.toJson(new LoginDtoResponse(commissionerService.login(request.getLogin(), request.getPassword())));
                }
                return gson.toJson(new LoginDtoResponse(voterService.login(request.getLogin(),
                        request.getPassword())));
            } catch (ServerException ex) {
                return gson.toJson(new ErrorDtoResponse(ex.getLocalizedMessage()));
            }
        }
        return gson.toJson(new ErrorDtoResponse(response));
    }
//Если избиратель выполняет операцию выхода (метод logout) с сервера,
// его токен впредь считается недействительным (невалидным).
// При попытке выполнить любой метод с предъявлением этого токена должен возвращаться json с ошибкой.
    public String logout(String requestJsonString) {
        LogoutDtoRequest request = gson.fromJson(requestJsonString, LogoutDtoRequest.class);
        if (request == null || !request.requiredFieldsIsNotNull()) {
            return gson.toJson(new ErrorDtoResponse(NULL_VALUE));
        }
        try {
            if (commissionerService.isCommissioner(request.getToken())){
                commissionerService.logout(request.getToken());
            }
            else if (candidateService.isCandidate(sessionService.getVoter(request.getToken()))) {
                return gson.toJson(new ErrorDtoResponse("Невозможно разлогиниться, для начала," +
                        " снимите свою кандидатуру с выборов."));
            } else {
                ideaService.setIdeaCommunity(sessionService.getVoter(request.getToken()));
                ideaService.removeAllRating(sessionService.getVoter(request.getToken()));
                voterService.logout(request.getToken());
            }
            return EMPTY_JSON;
        } catch (ServerException ex) {
            return gson.toJson(new ErrorDtoResponse(ex.getLocalizedMessage()));
        }
    }

    //запросить список всех избирателей
    public String getVoterList(String requestJsonString) {
        GetVoterListDtoRequest request = gson.fromJson(requestJsonString, GetVoterListDtoRequest.class);
        if (request != null && request.requiredFieldsIsNotNull()) {
            if (!sessionService.isLogin(request.getToken())) {
                return gson.toJson(new ErrorDtoResponse(ExceptionErrorCode.LOGOUT.getMessage()));
            }
            return gson.toJson(new GetVotersListDtoResponse(voterService.getAll()));
        }
        return gson.toJson(new ErrorDtoResponse(NULL_VALUE));
    }

//Избиратель добавляет кандидата в список кандидатов.
// requestJsonString содержит информацию о кандидате и token,
// полученный как результат выполнения команды регистрации избирателя.
// Метод при успешном выполнении возвращает пустой json .
// Если же команду почему-то выполнить нельзя, возвращает json с элементом “error”

//Зарегистрированный житель города может выставить на пост мэра свою кандидатуру
// или кандидатуру любого другого зарегистрированного избирателя. Если избиратель выставляет себя в качестве кандидата,
// то тем самым он дает свое согласие на выдвижение, если же он выдвигает иную кандидатуру, то выдвигаемый должен
// в дальнейшем дать свое согласие на выдвижение, в противном случае он не считается кандидатом на пост мэра.
// Кандидат в мэры может впоследствии снять свою кандидатуру.
// Если кандидат в мэры, давший свое согласие на участие в выборах, хочет покинуть сервер,
// он должен предварительно снять свою кандидатуру.
    public String addCandidate(String requestJsonString) {
        if (electionService.isElectionStart().equals(true)) {
            return gson.toJson(new ErrorDtoResponse(ELECTION_START));
        }
        AddCandidateDtoRequest request = gson.fromJson(requestJsonString, AddCandidateDtoRequest.class);
        if (request != null && request.requiredFieldsIsNotNull()) {
            try {
                if (!sessionService.getVoter(request.getToken()).isHasOwnCandidate()) {
                    candidateService.addCandidate(voterService.get(request.getCandidateLogin()));
                    sessionService.getVoter(request.getToken()).setHasOwnCandidate(true);
                    return EMPTY_JSON;
                }
            } catch (ServerException ex) {
                return gson.toJson(new ErrorDtoResponse(ex.getLocalizedMessage()));
            }
        }
        return gson.toJson(new ErrorDtoResponse(NULL_VALUE));
    }
//request содержит токен кандидата и его идеи
    public String confirmationCandidacy(String requestJsonString) {
        if (electionService.isElectionStart().equals(true)) {
            return gson.toJson(new ErrorDtoResponse(ELECTION_START));
        }
        ConfirmationCandidacyDtoRequest request = gson.fromJson(requestJsonString,
                ConfirmationCandidacyDtoRequest.class);
        if (request != null && request.requiredFieldsIsNotNull()) {
            try {
                ideaService.addAllIdeas(sessionService.getVoter(request.getToken()), request.getCandidateIdeas());
                List<String> list = new ArrayList<>();
                list.add(sessionService.getVoter(request.getToken()).getLogin());
                candidateService.confirmationCandidacy(sessionService.getVoter(request.getToken()), ideaService.getAllVotersIdeas(list));
                return EMPTY_JSON;
            } catch (ServerException ex) {
                return gson.toJson(new ErrorDtoResponse(ex.getLocalizedMessage()));
            }
        }
        return gson.toJson(new ErrorDtoResponse(NULL_VALUE));
    }

    //кандидат хочет снять свою кандидатуру
    public String withdrawCandidacy(String requestJsonString) {
        if (electionService.isElectionStart().equals(true)) {
            return gson.toJson(new ErrorDtoResponse(ELECTION_START));
        }
        WithdrawCandidacyRequest request = gson.fromJson(requestJsonString, WithdrawCandidacyRequest.class);
        if (request != null && request.requiredFieldsIsNotNull()) {
            try {
                candidateService.withdrawCandidacy(sessionService.getVoter(request.getToken()));
                return EMPTY_JSON;
            } catch (ServerException ex) {
                return gson.toJson(new ErrorDtoResponse(ex.getLocalizedMessage()));
            }
        }
        return gson.toJson(new ErrorDtoResponse(NULL_VALUE));
    }
    //высказать своё предложение
    public String addIdea(String requestJsonString) {
        if (electionService.isElectionStart().equals(true)) {
            return gson.toJson(new ErrorDtoResponse(ELECTION_START));
        }
        AddIdeaDtoRequest request = gson.fromJson(requestJsonString, AddIdeaDtoRequest.class);
        if (request != null && request.requiredFieldsIsNotNull()) {
            try {
                if (candidateService.isCandidate(sessionService.getVoter(request.getToken()))) {
                    ideaService.addIdea(sessionService.getVoter(request.getToken()), request.getIdea());
                    candidateService.addIdea(sessionService.getVoter(request.getToken()), ideaService.getIdea(ideaService.getKey(sessionService.getVoter(request.getToken()),request.getIdea())));
                    return EMPTY_JSON;
                } else {
                    ideaService.addIdea(sessionService.getVoter(request.getToken()), request.getIdea());
                    return EMPTY_JSON;
                }
            } catch (ServerException ex) {
                return gson.toJson(new ErrorDtoResponse(ex.getLocalizedMessage()));
            }
        }
        return gson.toJson(new ErrorDtoResponse(NULL_VALUE));
    }
    // поставить оценку предложению
    public String estimateIdea(String requestJsonString) {
        if (electionService.isElectionStart().equals(true)) {
            return gson.toJson(new ErrorDtoResponse(ELECTION_START));
        }
        EstimateIdeaDtoRequest request = gson.fromJson(requestJsonString, EstimateIdeaDtoRequest.class);
        if (request != null && request.requiredFieldsIsNotNull()) {
            try {
                ideaService.estimate(request.getIdeaKey(), request.getRating(), sessionService.getVoter(request.getToken()));
                return EMPTY_JSON;
            } catch (ServerException ex) {
                return gson.toJson(new ErrorDtoResponse(ex.getLocalizedMessage()));
            }
        }
        return gson.toJson(new ErrorDtoResponse(NULL_VALUE));
    }
    //изменить свою оценку
    //содержит свой токен, токен предложения и новый рейтинг
    public String changeRating(String requestJsonString) {
        if (electionService.isElectionStart().equals(true)) {
            return gson.toJson(new ErrorDtoResponse(ELECTION_START));
        }
        ChangeRatingDtoRequest request = gson.fromJson(requestJsonString, ChangeRatingDtoRequest.class);
        if (request != null && request.requiredFieldsIsNotNull()) {
            try {
                ideaService.changeRating(sessionService.getVoter(request.getToken()), request.getIdeaKey(), request.getRating());
                return EMPTY_JSON;
            } catch (ServerException ex) {
                return gson.toJson(new ErrorDtoResponse(ex.getLocalizedMessage()));
            }
        }
        return gson.toJson(new ErrorDtoResponse(NULL_VALUE));
    }
    //удалить свою оценку
    public String removeRating(String requestJsonString) {
        if (electionService.isElectionStart().equals(true)) {
            return gson.toJson(new ErrorDtoResponse(ELECTION_START));
        }
        RemoveRatingDtoRequest request = gson.fromJson(requestJsonString, RemoveRatingDtoRequest.class);
        if (request != null && request.requiredFieldsIsNotNull()) {
            try {
                ideaService.removeRating(sessionService.getVoter(request.getToken()), request.getIdeaKey());
                return EMPTY_JSON;
            } catch (ServerException ex) {
                return gson.toJson(new ErrorDtoResponse(ex.getLocalizedMessage()));
            }
        }
        return gson.toJson(new ErrorDtoResponse(NULL_VALUE));
    }
    //кандидат добавляет себе новые идеи ругих пользователей
    public String takeIdea(String requestJsonString) {
        if (electionService.isElectionStart().equals(true)) {
            return gson.toJson(new ErrorDtoResponse(ELECTION_START));
        }
        TakeIdeaDtoRequest request = gson.fromJson(requestJsonString, TakeIdeaDtoRequest.class);
        if (request != null && request.requiredFieldsIsNotNull()) {
            try {
                candidateService.addIdea(sessionService.getVoter(request.getToken()), ideaService.getIdea(request.getIdeaKey()));
                return EMPTY_JSON;
            } catch (ServerException ex) {
                return gson.toJson(new ErrorDtoResponse(ex.getLocalizedMessage()));
            }
        }
        return gson.toJson(new ErrorDtoResponse(NULL_VALUE));
    }
    //кандидат удаляет предложения
    public String removeIdea(String requestJsonString) {
        if (electionService.isElectionStart().equals(true)) {
            return gson.toJson(new ErrorDtoResponse(ELECTION_START));
        }
        RemoveIdeaDtoRequest request = gson.fromJson(requestJsonString, RemoveIdeaDtoRequest.class);
        if (request != null && request.requiredFieldsIsNotNull()) {
            try {
                candidateService.removeIdea(sessionService.getVoter(request.getToken()), ideaService.getIdea(request.getIdeaKey()));
                return EMPTY_JSON;
            } catch (ServerException ex) {
                return gson.toJson(new ErrorDtoResponse(ex.getLocalizedMessage()));
            }
        }
        return gson.toJson(new ErrorDtoResponse(NULL_VALUE));
    }
    //избиратель получает список кандидатов с их программой
    public String getCandidateMap(String requestJsonString) {
        GetCandidateMapDtoRequest request = gson.fromJson(requestJsonString, GetCandidateMapDtoRequest.class);
        if (request != null && request.requiredFieldsIsNotNull()) {
            if (!sessionService.isLogin(request.getToken())) {
                return gson.toJson(new ErrorDtoResponse(ExceptionErrorCode.LOGOUT.getMessage()));
            }
            return gson.toJson(new GetCandidateMapDtoResponse(candidateService.getCandidateMap()));
        }
        return gson.toJson(new ErrorDtoResponse(NULL_VALUE));
    }
//    получить список всех предложений с их средней оценкой, отсортированный по оценке
    public String getAllIdeas(String requestJsonString) {
        GetAllIdeasDtoRequest request = gson.fromJson(requestJsonString, GetAllIdeasDtoRequest.class);
        if (request != null && request.requiredFieldsIsNotNull()) {
            if (!sessionService.isLogin(request.getToken())) {
                return gson.toJson(new ErrorDtoResponse(ExceptionErrorCode.LOGOUT.getMessage()));
            }
            return gson.toJson(new GetAllIdeasDtoResponse(ideaService.getIdeas()));
        }
        return gson.toJson(new ErrorDtoResponse(NULL_VALUE));// можно и просто пустой конструктор, который нул велью возвращает
    }
    //получить список предложений, сделанных тем или иным избирателем или несколькими избирателями
    public String getAllVotersIdeas(String requestJsonString) {
        GetAllVotersIdeasDtoRequest request = gson.fromJson(requestJsonString, GetAllVotersIdeasDtoRequest.class);
        if (request != null && request.requiredFieldsIsNotNull()) {
            if (!sessionService.isLogin(request.getToken())) {
                return gson.toJson(new ErrorDtoResponse(ExceptionErrorCode.LOGOUT.getMessage()));
            }
            return gson.toJson(new GetAllVotersIdeasDtoResponse(ideaService.getAllVotersIdeas(request.getLogins())));
        }
        return gson.toJson(new ErrorDtoResponse(NULL_VALUE));
    }
    //объявляется голосование
    public String startElection(String requestJsonString) {
        StartElectionDtoRequest request = gson.fromJson(requestJsonString, StartElectionDtoRequest.class);
        if (request != null && request.requiredFieldsIsNotNull()) {
            try {
                commissionerService.startElection(request.getToken(), candidateService.getCandidateSet());
                return EMPTY_JSON;
            } catch (ServerException ex) {
                return gson.toJson(new ErrorDtoResponse(ex.getLocalizedMessage()));
            }
        }
        return gson.toJson(new ErrorDtoResponse(NULL_VALUE));
    }
    //проголосовать
    public String vote(String requestJsonStrong) {
        if (electionService.isElectionStart().equals(false)) {
            return gson.toJson(new ErrorDtoResponse("Голосование не началось."));
        }
        if (electionService.isElectionStop().equals(true)) {
            return gson.toJson(new ErrorDtoResponse("Голосование закончилось."));
        }
            VoteDtoRequest request = gson.fromJson(requestJsonStrong, VoteDtoRequest.class);
            if (request != null && request.requiredFieldsIsNotNull()) {
                try {
                    electionService.vote(sessionService.getVoter(request.getToken()), candidateService.getCandidate(request.getCandidateLogin()));
                    return EMPTY_JSON;
                } catch (ServerException ex) {
                    return gson.toJson(new ErrorDtoResponse(ex.getLocalizedMessage()));
                }
            }
            return gson.toJson(new ErrorDtoResponse(NULL_VALUE));
    }
    //остановить голосование и получить результаты
    public String getElectionResult(String requestJsonString) {
        GetElectionResultDtoRequest request = gson.fromJson(requestJsonString, GetElectionResultDtoRequest.class);
        if (request != null && request.requiredFieldsIsNotNull()) {
            try {
                return gson.toJson(new GetElectionResultDtoResponse(commissionerService.getElectionResult(request.getToken())));
            } catch (ServerException ex) {
                return gson.toJson(new ErrorDtoResponse(ex.getLocalizedMessage()));
            }
        }
        return gson.toJson(new ErrorDtoResponse(NULL_VALUE));
    }
}
