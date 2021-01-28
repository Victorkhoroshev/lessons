package net.thumbtack.school.elections.server;

import com.google.gson.Gson;
import net.thumbtack.school.elections.server.database.Database;
import net.thumbtack.school.elections.server.dto.request.*;
import net.thumbtack.school.elections.server.dto.response.ErrorDtoResponse;
import net.thumbtack.school.elections.server.dto.response.GetVotersListDtoResponse;
import net.thumbtack.school.elections.server.dto.response.LoginDtoResponse;
import net.thumbtack.school.elections.server.dto.response.RegisterDtoResponse;
import net.thumbtack.school.elections.server.model.Candidate;
import net.thumbtack.school.elections.server.model.Idea;
import net.thumbtack.school.elections.server.model.Voter;
import net.thumbtack.school.elections.server.service.*;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class TestServer {
    private final Gson gson = new Gson();
    private final Server server = new Server();
    private static final String EMPTY_JSON = "";
    private static final String NULL_VALUE = "Некорректный запрос";
    private static final String SESSION_NOT_FOUND = "Сессия пользователя не найдена.";
    private static final String NULL_REGISTERVOTERDTOREQUEST = "Пожалуйста, заполните все данные.";
    private static final String NULL_LOGINDTOREQUEST = "Пожалуйста, введите логин и пароль.";
    private static final String WRONG_FIRSTNAME = "Имя должно быть на кириллице, без пробелов, спец. символов и цифр.\n";
    private static final String WRONG_LASTNAME = "Фамилия должна быть на кириллице, без пробелов, спец. символов и цифр.\n";
    private static final String WRONG_PATRONYMIC = "Отчество должно быть на кириллице, без пробелов, спец. символов и цифр.\n";
    private static final String WRONG_STREET = "Название улицы должно быть на кириллице, без пробелов, спец. символов и цифр.\n";
    private static final String WRONG_HOUSE = "Номер дома не может быть меньше единицы.\n";
    private static final String WRONG_APARTMENT = "Номер квартиры не может быть меньше нуля.\n";
    private static final String WRONG_LOGIN = "Длинна логина должна быть не меньше 9 символо.\n";
    private static final String WRONG_PASSWORD = "Пароль должен содержать хотя бы одну заглавную букву, одну строчную букву," +
            " цифру и один спец. символ, а его длинна не менее 9 символов, без пробелов.\n";
    @Test
    public void startStopServerTest() throws IOException, ClassNotFoundException {
        server.startServer(null);
        assertAll(
                () -> assertNotNull(server.gson),
                () -> assertNotNull(server.contextService),
                () -> assertNotNull(server.sessionService),
                () -> assertNotNull(server.ideaService),
                () -> assertNotNull(server.candidateService),
                () -> assertNotNull(server.voterService)
        );
        server.stopServer(null);
        assertAll(
                () -> assertNull(server.gson),
                () -> assertNull(server.contextService),
                () -> assertNull(server.sessionService),
                () -> assertNull(server.ideaService),
                () -> assertNull(server.candidateService),
                () -> assertNull(server.voterService)
        );
        server.startServer(null);
        Set<Candidate> candidateSet = Database.getCandidateSet();
        Set<Voter> voterSet = Database.getVoterSet();
        List<String> logins = Database.getLogins();
        CandidateService candidateService = server.candidateService;
        IdeaService ideaService = server.ideaService;
        server.stopServer("C:\\Thumbtack\\thumbtack_online_school_2020_2__viktor_khoroshev\\saveDataFile");
        server.startServer("C:\\Thumbtack\\thumbtack_online_school_2020_2__viktor_khoroshev\\saveDataFile");
        assertAll(
                () -> assertEquals(candidateSet, Database.getCandidateSet()),
                () -> assertEquals(voterSet, Database.getVoterSet()),
                () -> assertEquals(logins, Database.getLogins()),
                () -> assertEquals(candidateService, server.candidateService),
                () -> assertEquals(ideaService, server.ideaService)
        );
    }

    @Test
    public void registerTest() throws IOException, ClassNotFoundException {
        server.startServer(null);
        SessionService session = server.sessionService;
        //firstName
        RegisterDtoRequest voter1 = new RegisterDtoRequest("Виктор", "Хорошев",
                null,"Пригородная", 1, 188, randomString(), "111%111Aa");
        RegisterDtoRequest voter2 = new RegisterDtoRequest("викторр", "Хорошев",
                null, "Пригородная", 1, 188, randomString(), "111%111Aa");
        RegisterDtoRequest voter3 = new RegisterDtoRequest("ВИКТОРРР", "Хорошев",
                null, "Пригородная", 1, 188, randomString(), "111%111Aa");
        RegisterDtoRequest voter4 = new RegisterDtoRequest("victor", "Хорошев",
                null,  "Пригородная", 1, 188, randomString(), "111%111Aa");
        RegisterDtoRequest voter5 = new RegisterDtoRequest("Вик тор", "Хорошев",
                null, "Пригородная", 1, 188, randomString(), "111%111Aa");
        RegisterDtoRequest voter6 = new RegisterDtoRequest("Вик/тор", "Хорошев",
                null,  "Пригородная", 1, 188, randomString(), "111%111Aa");
        RegisterDtoRequest voter66 = new RegisterDtoRequest("Вик1тор", "Хорошев",
                null,  "Пригородная", 1, 188, randomString(), "111%111Aa");
        //lastname
        RegisterDtoRequest voter7 = new RegisterDtoRequest("Андрей", "Хорошев",
                null, "Пригородная", 1, 188, randomString(), "111%111Aa");
        RegisterDtoRequest voter8 = new RegisterDtoRequest("Андрей", "хорошевв",
                null,  "Пригородная", 1, 188, randomString(), "111%111Aa");
        RegisterDtoRequest voter9 = new RegisterDtoRequest("Аддрей", "ХОРОШЕВВВ",
                null,  "Пригородная", 1, 188, randomString(), "111%111Aa");
        RegisterDtoRequest voter10 = new RegisterDtoRequest("Аддрей", "Khoroshev",
                null,  "Пригородная", 1, 188, randomString(), "111%111Aa");
        RegisterDtoRequest voter11 = new RegisterDtoRequest("Аддрей", "ХОРО ШЕВ",
                null,   "Пригородная", 1, 188, randomString(), "111%111Aa");
        RegisterDtoRequest voter12 = new RegisterDtoRequest("Аддрей", "ХОРО.ЕВВВ",
                null,  "Пригородная", 1, 188, randomString(), "111%111Aa");
        RegisterDtoRequest voter122 = new RegisterDtoRequest("Аддрей", "ХОРО&ЕВВВ",
                null,  "Пригородная", 1, 188, randomString(), "111%111Aa");
        //patronymic
        RegisterDtoRequest voter13 = new RegisterDtoRequest(randomString(), "Хорошев","Игоревич",
                "Пригородная", 1, 188, randomString(), "111%111Aa");
        RegisterDtoRequest voter14 = new RegisterDtoRequest(randomString(), "Хорошев","игоревич",
                "Пригородная", 1, 188, randomString(), "111%111Aa");
        RegisterDtoRequest voter15 = new RegisterDtoRequest(randomString(), "Хорошев","ИГОРЕВИЧ",
                "Пригородная", 1, 188, randomString(), "111%111Aa");
        RegisterDtoRequest voter16 = new RegisterDtoRequest(randomString(), "Хорошев","Igorevich",
                "Пригородная", 1, 188, randomString(), "111%111Aa");
        RegisterDtoRequest voter17 = new RegisterDtoRequest(randomString(), "Хорошев","Игоревич1",
                "Пригородная", 1, 188, randomString(), "111%111Aa");
        RegisterDtoRequest voter18 = new RegisterDtoRequest(randomString(), "Хорошев","Игорев/ич",
                "Пригородная", 1, 188, randomString(), "111%111Aa");
        RegisterDtoRequest voter188 = new RegisterDtoRequest(randomString(), "Хорошев","Игорев ич",
                "Пригородная", 1, 188, randomString(), "111%111Aa");
        //street
        RegisterDtoRequest voter19 = new RegisterDtoRequest(randomString(), "Хорошев","Игоревич",
                "Пригородная", 1, 188, randomString(), "111%111Aa");
        RegisterDtoRequest voter20 = new RegisterDtoRequest(randomString(), "Хорошев","Игоревич",
                "пригородная", 1, 188, randomString(), "111%111Aa");
        RegisterDtoRequest voter21 = new RegisterDtoRequest(randomString(), "Хорошев","Игоревич",
                "ПРИГОРОДНАЯ", 1, 188, randomString(), "111%111Aa");
        RegisterDtoRequest voter22 = new RegisterDtoRequest(randomString(), "Хорошев","Игоревич",
                "Prigiridnaya", 1, 188, randomString(), "111%111Aa");
        RegisterDtoRequest voter23 = new RegisterDtoRequest(randomString(), "Хорошев","Игоревич",
                "Пригор1одная", 1, 188, randomString(), "111%111Aa");
        RegisterDtoRequest voter24 = new RegisterDtoRequest(randomString(), "Хорошев","Игоревич",
                "Приг ородная", 1, 188, randomString(), "111%111Aa");
        RegisterDtoRequest voter25 = new RegisterDtoRequest(randomString(), "Хорошев","Игоревич",
                "Пригор*одная", 1, 188, randomString(), "111%111Aa");
        //house
        RegisterDtoRequest voter26 = new RegisterDtoRequest(randomString(), "Хорошев","Игоревич",
                "Пригородная", 1, 188, randomString(), "111%111Aa");
        RegisterDtoRequest voter27 = new RegisterDtoRequest(randomString(), "Хорошев","Игоревич",
                "Пригородная", 0, 188, randomString(), "111%111Aa");
        RegisterDtoRequest voter28 = new RegisterDtoRequest(randomString(), "Хорошев","Игоревич",
                "Пригородная", -13, 188, randomString(), "111%111Aa");
        //apartment
        RegisterDtoRequest voter29 = new RegisterDtoRequest(randomString(), "Хорошев","Игоревич",
                "Пригородная", 1, 188, randomString(), "111%111Aa");
        RegisterDtoRequest voter30 = new RegisterDtoRequest(randomString(), "Хорошев",
                "Игоревич", "Пригородная", 1, 1, randomString(), "111%111Aa");
        RegisterDtoRequest voter31 = new RegisterDtoRequest(randomString(), "Хорошев","Игоревич",
                "Пригородная", 1, -1, randomString(), "111%111Aa");
        //login
        RegisterDtoRequest voter33 = new RegisterDtoRequest(randomString(), "Хорошев","Игоревич",
                "Пригородная", 1, 188, "vic", "111%111Aa");
        //password
        RegisterDtoRequest voter35 = new RegisterDtoRequest(randomString(), "Хорошев","Игоревич",
                "Пригородная", 1, 188, randomString(), "111%111qQ");
        RegisterDtoRequest voter36 = new RegisterDtoRequest(randomString(), "Хорошев","Игоревич",
                "Пригородная", 1, 188, randomString(), "111%111*гЖ");
        RegisterDtoRequest voter37 = new RegisterDtoRequest(randomString(), "Хорошев","Игоревич",
                "Пригородная", 1, 188, randomString(), "1г11%111Q");
        RegisterDtoRequest voter38 = new RegisterDtoRequest(randomString(), "Хорошев","Игоревич",
                "Пригородная", 1, 188, randomString(), "111q%11Г1");
        RegisterDtoRequest voter39 = new RegisterDtoRequest(randomString(), "Хорошев","Игоревич",
                "Пригородная", 1, 188, randomString(), "111111Aa");
        RegisterDtoRequest voter40 = new RegisterDtoRequest(randomString(), "Хорошев","Игоревич",
                "Пригородная", 1, 188, randomString(), "фффф%ффффAa");
        RegisterDtoRequest voter41 = new RegisterDtoRequest(randomString(), "Хорошев","Игоревич",
                "Пригородная", 1, 188, randomString(), "111%111фa");
        RegisterDtoRequest voter42 = new RegisterDtoRequest(randomString(), "Хорошев","Игоревич",
                "Пригородная", 1, 188, randomString(), "111%111AФ");
        RegisterDtoRequest voter43 = new RegisterDtoRequest(randomString(), "Хорошев","Игоревич",
                "Пригородная", 1, 188, randomString(), "111%111 Aa");
        RegisterDtoRequest voter44 = new RegisterDtoRequest(randomString(), "Хорошев","Игоревич",
                "Пригородная", 1, 188, randomString(), "1");
        RegisterDtoRequest voter45 = new RegisterDtoRequest(randomString(), "Хорошев","Игоревич",
                "Пригородная", 1, 188, randomString(), "11%?AaфQ");
        //NULL parameter
        RegisterDtoRequest voter46 = new RegisterDtoRequest(null, "Хорошев","Игоревич",
                "Пригородная", 1, 188, randomString(), "11%?AaфQ");
        RegisterDtoRequest voter47 = new RegisterDtoRequest(randomString(), null,"Игоревич",
                "Пригородная", 1, 188, randomString(), "11%?AaфQ");
        RegisterDtoRequest voter48 = new RegisterDtoRequest(randomString(), "Хорошев","Игоревич",
                null, 1, 188, randomString(), "11%?AaфQ");
        RegisterDtoRequest voter50 = new RegisterDtoRequest(randomString(), "Хорошев","Игоревич",
                "Пригородная", 1, 188, null, "11%?AaфQ");
        RegisterDtoRequest voter51 = new RegisterDtoRequest(randomString(), "Хорошев","Игоревич",
                "Пригородная", null, 188, randomString(), null);
        RegisterDtoRequest voter52 = new RegisterDtoRequest(null, null,null,
                null, null, null, null, null);

        RegisterDtoRequest voter53 = new RegisterDtoRequest("V", "CD","POL",
                "-ап", -1, -234, "null", "1");

        assertAll(
                () -> assertEquals(server.register(gson.toJson(voter1)), gson.toJson(new RegisterDtoResponse(session.getSession(voter1.newVoter()).getToken()))),
                () -> assertEquals(server.register(gson.toJson(voter2)), gson.toJson(new RegisterDtoResponse(session.getSession(voter2.newVoter()).getToken()))),
                () -> assertEquals(server.register(gson.toJson(voter3)), gson.toJson(new RegisterDtoResponse(session.getSession(voter3.newVoter()).getToken()))),
                () -> assertEquals(server.register( gson.toJson(voter4)), gson.toJson(new ErrorDtoResponse(WRONG_FIRSTNAME))),
                () -> assertEquals(server.register(gson.toJson(voter5)), gson.toJson(new ErrorDtoResponse(WRONG_FIRSTNAME))),
                () -> assertEquals(server.register(gson.toJson(voter6)), gson.toJson(new ErrorDtoResponse(WRONG_FIRSTNAME))),
                () -> assertEquals(server.register(gson.toJson(voter66)), gson.toJson(new ErrorDtoResponse(WRONG_FIRSTNAME))),
                () -> assertEquals(server.register(gson.toJson(voter7)), gson.toJson(new RegisterDtoResponse(session.getSession(voter7.newVoter()).getToken()))),
                () -> assertEquals(server.register(gson.toJson(voter8)), gson.toJson(new RegisterDtoResponse(session.getSession(voter8.newVoter()).getToken()))),
                () -> assertEquals(server.register(gson.toJson(voter9)), gson.toJson(new RegisterDtoResponse(session.getSession(voter9.newVoter()).getToken()))),
                () -> assertEquals(server.register(gson.toJson(voter10)), gson.toJson(new ErrorDtoResponse(WRONG_LASTNAME))),
                () -> assertEquals(server.register(gson.toJson(voter11)), gson.toJson(new ErrorDtoResponse(WRONG_LASTNAME))),
                () -> assertEquals(server.register(gson.toJson(voter12)), gson.toJson(new ErrorDtoResponse(WRONG_LASTNAME))),
                () -> assertEquals(server.register(gson.toJson(voter122)), gson.toJson(new ErrorDtoResponse(WRONG_LASTNAME))),
                () -> assertEquals(server.register(gson.toJson(voter13)), gson.toJson(new RegisterDtoResponse(session.getSession(voter13.newVoter()).getToken()))),
                () -> assertEquals(server.register(gson.toJson(voter14)), gson.toJson(new RegisterDtoResponse(session.getSession(voter14.newVoter()).getToken()))),
                () -> assertEquals(server.register(gson.toJson(voter15)), gson.toJson(new RegisterDtoResponse(session.getSession(voter15.newVoter()).getToken()))),
                () -> assertEquals(server.register(gson.toJson(voter16)), gson.toJson(new ErrorDtoResponse(WRONG_PATRONYMIC))),
                () -> assertEquals(server.register(gson.toJson(voter17)), gson.toJson(new ErrorDtoResponse(WRONG_PATRONYMIC))),
                () -> assertEquals(server.register(gson.toJson(voter18)), gson.toJson(new ErrorDtoResponse(WRONG_PATRONYMIC))),
                () -> assertEquals(server.register(gson.toJson(voter188)), gson.toJson(new ErrorDtoResponse(WRONG_PATRONYMIC))),
                () -> assertEquals(server.register(gson.toJson(voter19)), gson.toJson(new RegisterDtoResponse(session.getSession(voter19.newVoter()).getToken()))),
                () -> assertEquals(server.register(gson.toJson(voter20)), gson.toJson(new RegisterDtoResponse(session.getSession(voter20.newVoter()).getToken()))),
                () -> assertEquals(server.register(gson.toJson(voter21)), gson.toJson(new RegisterDtoResponse(session.getSession(voter21.newVoter()).getToken()))),
                () -> assertEquals(server.register(gson.toJson(voter22)), gson.toJson(new ErrorDtoResponse(WRONG_STREET))),
                () -> assertEquals(server.register(gson.toJson(voter23)), gson.toJson(new ErrorDtoResponse(WRONG_STREET))),
                () -> assertEquals(server.register(gson.toJson(voter24)), gson.toJson(new ErrorDtoResponse(WRONG_STREET))),
                () -> assertEquals(server.register(gson.toJson(voter25)), gson.toJson(new ErrorDtoResponse(WRONG_STREET))),
                () -> assertEquals(server.register(gson.toJson(voter26)), gson.toJson(new RegisterDtoResponse(session.getSession(voter26.newVoter()).getToken()))),
                () -> assertEquals(server.register(gson.toJson(voter27)), gson.toJson(new ErrorDtoResponse(WRONG_HOUSE))),
                () -> assertEquals(server.register(gson.toJson(voter28)), gson.toJson(new ErrorDtoResponse(WRONG_HOUSE))),
                () -> assertEquals(server.register(gson.toJson(voter29)), gson.toJson(new RegisterDtoResponse(session.getSession(voter29.newVoter()).getToken()))),
                () -> assertEquals(server.register(gson.toJson(voter30)), gson.toJson(new RegisterDtoResponse(session.getSession(voter30.newVoter()).getToken()))),
                () -> assertEquals(server.register(gson.toJson(voter31)), gson.toJson(new ErrorDtoResponse(WRONG_APARTMENT))),
                () -> assertEquals(server.register(gson.toJson(voter33)), gson.toJson(new ErrorDtoResponse(WRONG_LOGIN))),
                () -> assertEquals(server.register(gson.toJson(voter35)), gson.toJson(new RegisterDtoResponse(session.getSession(voter35.newVoter()).getToken()))),
                () -> assertEquals(server.register(gson.toJson(voter35)), gson.toJson(new ErrorDtoResponse("Вы уже зарегестрированны."))),
                () -> assertEquals(server.register(gson.toJson(voter36)), gson.toJson(new RegisterDtoResponse(session.getSession(voter36.newVoter()).getToken()))),
                () -> assertEquals(server.register(gson.toJson(voter37)), gson.toJson(new RegisterDtoResponse(session.getSession(voter37.newVoter()).getToken()))),
                () -> assertEquals(server.register(gson.toJson(voter38)), gson.toJson(new RegisterDtoResponse(session.getSession(voter38.newVoter()).getToken()))),
                () -> assertEquals(server.register(gson.toJson(voter39)), gson.toJson(new ErrorDtoResponse(WRONG_PASSWORD))),
                () -> assertEquals(server.register(gson.toJson(voter40)), gson.toJson(new ErrorDtoResponse(WRONG_PASSWORD))),
                () -> assertEquals(server.register(gson.toJson(voter41)), gson.toJson(new ErrorDtoResponse(WRONG_PASSWORD))),
                () -> assertEquals(server.register(gson.toJson(voter42)), gson.toJson(new ErrorDtoResponse(WRONG_PASSWORD))),
                () -> assertEquals(server.register(gson.toJson(voter43)), gson.toJson(new ErrorDtoResponse(WRONG_PASSWORD))),
                () -> assertEquals(server.register(gson.toJson(voter44)), gson.toJson(new ErrorDtoResponse(WRONG_PASSWORD))),
                () -> assertEquals(server.register(gson.toJson(voter45)), gson.toJson(new ErrorDtoResponse(WRONG_PASSWORD))),
                () -> assertEquals(server.register(gson.toJson(voter46)), gson.toJson(new ErrorDtoResponse(NULL_REGISTERVOTERDTOREQUEST))),
                () -> assertEquals(server.register(gson.toJson(voter47)), gson.toJson(new ErrorDtoResponse(NULL_REGISTERVOTERDTOREQUEST))),
                () -> assertEquals(server.register(gson.toJson(voter48)), gson.toJson(new ErrorDtoResponse(NULL_REGISTERVOTERDTOREQUEST))),
                () -> assertEquals(server.register(gson.toJson(voter50)), gson.toJson(new ErrorDtoResponse(NULL_REGISTERVOTERDTOREQUEST))),
                () -> assertEquals(server.register(gson.toJson(voter51)), gson.toJson(new ErrorDtoResponse(NULL_REGISTERVOTERDTOREQUEST))),
                () -> assertEquals(server.register(gson.toJson(voter52)), gson.toJson(new ErrorDtoResponse(NULL_REGISTERVOTERDTOREQUEST))),
                () -> assertEquals(server.register(null), gson.toJson(new ErrorDtoResponse(NULL_REGISTERVOTERDTOREQUEST))),
                () -> assertEquals(server.register(gson.toJson(voter53)), gson.toJson(new ErrorDtoResponse(WRONG_FIRSTNAME + WRONG_LASTNAME + WRONG_PATRONYMIC + WRONG_STREET + WRONG_HOUSE + WRONG_APARTMENT + WRONG_LOGIN + WRONG_PASSWORD)))
        );
        server.startServer(null);
    }
    //TODO: МОККИТО
    @Test
    public void logoutTest() throws ServerException, IOException, ClassNotFoundException {
        server.startServer(null);
        SessionService session = server.sessionService;
        RegisterDtoRequest voter1 = new RegisterDtoRequest(randomString(), randomString(),
                null,randomString(), 1, 188, randomString(), "111%111Aa");
        RegisterDtoRequest voter2 = new RegisterDtoRequest(randomString(), randomString(),
                null,randomString(), 1, 188, randomString(), "111%111Aa");
        server.register(gson.toJson(voter1));
        server.register(gson.toJson(voter2));
        server.candidateService.addCandidate(voter2.newVoter());
        server.candidateService.confirmationCandidacy(voter2.newVoter(),new ArrayList<>());
        LogoutDtoRequest request = new LogoutDtoRequest(session.getSession(voter1.newVoter()).getToken());
        LogoutDtoRequest request1 = null;
        LogoutDtoRequest request2 = new LogoutDtoRequest(null);
        LogoutDtoRequest request3 = new LogoutDtoRequest(randomString());
        LogoutDtoRequest request4 = new LogoutDtoRequest(session.getSession(voter2.newVoter()).getToken());
        assertAll(
                () -> assertEquals(EMPTY_JSON,server.logout(gson.toJson(request))),
                () -> assertEquals(gson.toJson(new ErrorDtoResponse("Невозможно разлогиниться, для начала, снимите свою кандидатуру с выборов.")),server.logout(gson.toJson(request4))),
                () -> assertEquals(gson.toJson(new ErrorDtoResponse(NULL_VALUE)),server.logout(gson.toJson(request1))),
                () -> assertEquals(gson.toJson(new ErrorDtoResponse(NULL_VALUE)),server.logout(gson.toJson(request2))),
                () -> assertEquals(gson.toJson(new ErrorDtoResponse(SESSION_NOT_FOUND)), server.logout(gson.toJson(request3)))
        );
        server.stopServer(null);
    }
    @Test
    public void loginTest() throws IOException, ClassNotFoundException {
        server.startServer(null);
        SessionService session = server.sessionService;
        RegisterDtoRequest voter1 = new RegisterDtoRequest(randomString(), randomString(),
                null,randomString(), 1, 188, "login1111111", "111%111Aa");
        RegisterDtoRequest voter2 = new RegisterDtoRequest(randomString(), randomString(),
                null, randomString(), 1, 188, "login222222", "111%111Aa");
        RegisterDtoRequest voter3 = new RegisterDtoRequest(randomString(), randomString(),
                null, randomString(), 1, 188, "login333333", "111%111Aa");
        server.register(gson.toJson(voter1));
        server.register(gson.toJson(voter2));
        server.register(gson.toJson(voter3));
        LoginDtoRequest loginDtoRequest1 = new LoginDtoRequest("login1111111", "111%111Aa");
        LoginDtoRequest loginDtoRequest2 = new LoginDtoRequest("login222222", "111%111Aa");
        LoginDtoRequest loginDtoRequest3 = new LoginDtoRequest("login333333", "111%111Aa");
        //неверный пароль
        LoginDtoRequest loginDtoRequest4 = new LoginDtoRequest("login333333", "111%111Aa1");
        //ошибка в пароле
        LoginDtoRequest loginDtoRequest5 = new LoginDtoRequest("login333333", "11Aa1");
        //нет такого пользователя
        LoginDtoRequest loginDtoRequest6 = new LoginDtoRequest("login44444444444", "111%111Aa");
        //null
        LoginDtoRequest loginDtoRequest7 = null;
        LoginDtoRequest loginDtoRequest8 = new LoginDtoRequest(null, null);
        //ошибка в логине
        LoginDtoRequest loginDtoRequest9 = new LoginDtoRequest("log", "111%111Aa");

        assertAll(
                () -> assertEquals(server.login(gson.toJson(loginDtoRequest1)), gson.toJson(new LoginDtoResponse(session.getSession(voter1.newVoter()).getToken()))),
                () -> assertEquals(server.login(gson.toJson(loginDtoRequest2)), gson.toJson(new LoginDtoResponse(session.getSession(voter2.newVoter()).getToken()))),
                () -> assertEquals(server.login(gson.toJson(loginDtoRequest3)), gson.toJson(new LoginDtoResponse(session.getSession(voter3.newVoter()).getToken()))),
                () -> assertEquals(server.login(gson.toJson(loginDtoRequest4)), gson.toJson(new ErrorDtoResponse(ExceptionErrorCode.VOTER_WRONG_PASSWORD.getMessage()))),
                () -> assertEquals(server.login(gson.toJson(loginDtoRequest5)), gson.toJson(new ErrorDtoResponse(WRONG_PASSWORD))),
                () -> assertEquals(server.login(gson.toJson(loginDtoRequest6)), gson.toJson(new ErrorDtoResponse(ExceptionErrorCode.VOTER_NOT_FOUND.getMessage()))),
                () -> assertEquals(server.login(gson.toJson(loginDtoRequest7)), gson.toJson(new ErrorDtoResponse(NULL_LOGINDTOREQUEST))),
                () -> assertEquals(server.login(gson.toJson(loginDtoRequest8)), gson.toJson(new ErrorDtoResponse(NULL_LOGINDTOREQUEST))),
                () -> assertEquals(server.login(gson.toJson(loginDtoRequest9)), gson.toJson(new ErrorDtoResponse(WRONG_LOGIN)))
        );
        server.stopServer(null);
    }
    @Test
    public void getVoterListTest() throws IOException, ClassNotFoundException, ServerException {
        server.startServer(null);
        RegisterDtoRequest request1 = new RegisterDtoRequest(randomString(), randomString(), null, randomString(), 1, 1, "loginNumber1", "111^wW1234");
        RegisterDtoRequest request2 = new RegisterDtoRequest(randomString(), randomString(), null, randomString(), 1, 1, "loginNumber2", "111^wW1234");
        RegisterDtoRequest request3 = new RegisterDtoRequest(randomString(), randomString(), null, randomString(), 1, 1, "loginNumber3", "111^wW1234");
        server.register(gson.toJson(request1));
        server.register(gson.toJson(request2));
        server.register(gson.toJson(request3));
        Set<String> voterSet = new HashSet<>();
        voterSet.add(request1.newVoter().getLastName() + " " + request1.newVoter().getFirstName());
        voterSet.add(request2.newVoter().getLastName() + " " + request2.newVoter().getFirstName());
        voterSet.add(request3.newVoter().getLastName() + " " + request3.newVoter().getFirstName());
        assertEquals(gson.toJson(new GetVotersListDtoResponse(voterSet)), server.getVoterList(gson.toJson(new GetVoterListDtoRequest(server.sessionService.getSession(server.voterService.get("loginNumber1")).getToken()))));
        assertEquals(gson.toJson(new ErrorDtoResponse(NULL_VALUE)), server.getVoterList(null));
        assertEquals(gson.toJson(new ErrorDtoResponse(NULL_VALUE)), server.getVoterList(gson.toJson(new GetVoterListDtoRequest(null))));
        assertEquals(gson.toJson(new ErrorDtoResponse(SESSION_NOT_FOUND)), server.getVoterList(gson.toJson(new GetVoterListDtoRequest("1"))));
        server.stopServer(null);
    }
    @Test
    public void addCandidateTest() throws IOException, ClassNotFoundException, ServerException {
        server.startServer(null);
        RegisterDtoRequest request1 = new RegisterDtoRequest(randomString(), randomString(), null, randomString(), 1, 1, "loginNumber1", "111^wW1234");
        RegisterDtoRequest request2 = new RegisterDtoRequest(randomString(), randomString(), null, randomString(), 1, 1, "loginNumber2", "111^wW1234");
        RegisterDtoRequest request3 = new RegisterDtoRequest(randomString(), randomString(), null, randomString(), 1, 1, "loginNumber3", "111^wW1234");
        server.register(gson.toJson(request1));
        server.register(gson.toJson(request2));
        server.register(gson.toJson(request3));
        assertAll(
                () -> assertEquals(EMPTY_JSON, server.addCandidate(gson.toJson(new AddCandidateDtoRequest(request2.newVoter().getLogin(), server.sessionService.getSession(request1.newVoter()).getToken())))),
                () -> assertTrue(server.voterService.get("loginNumber1").isHasOwnCandidate()),
                () -> assertEquals(gson.toJson(new ErrorDtoResponse(NULL_VALUE)), server.addCandidate(gson.toJson(new AddCandidateDtoRequest(request3.newVoter().getLogin(), server.sessionService.getSession(request1.newVoter()).getToken())))),
                () -> assertEquals(gson.toJson(new ErrorDtoResponse(SESSION_NOT_FOUND)), server.addCandidate(gson.toJson(new AddCandidateDtoRequest(request3.newVoter().getLogin(), "1")))),
                () -> assertEquals(gson.toJson(new ErrorDtoResponse(NULL_VALUE)), server.addCandidate(null)),
                () -> assertEquals(gson.toJson(new ErrorDtoResponse(NULL_VALUE)), server.addCandidate(gson.toJson(new AddCandidateDtoRequest(null, server.sessionService.getSession(request1.newVoter()).getToken())))),
                () -> assertEquals(gson.toJson(new ErrorDtoResponse(NULL_VALUE)), server.addCandidate(gson.toJson(new AddCandidateDtoRequest("loginNumber3" , null))))
        );
        server.stopServer(null);
    }
    @Test
    public void confirmationCandidacyTest() throws IOException, ClassNotFoundException, ServerException {
        server.startServer(null);
        RegisterDtoRequest request1 = new RegisterDtoRequest(randomString(), randomString(), null, randomString(), 1, 1, "loginNumber1", "111^wW1234");
        server.register(gson.toJson(request1));
        List<String> text = new ArrayList<>();
        text.add("idea №1");
        text.add("idea №2");
        text.add("idea №3");
        assertAll(
                () -> assertEquals(EMPTY_JSON, server.confirmationCandidacy(gson.toJson(new ConfirmationCandidacyDtoRequest(server.sessionService.getSession(request1.newVoter()).getToken(), text)))),
                () -> assertEquals(gson.toJson(new ErrorDtoResponse(SESSION_NOT_FOUND)), server.confirmationCandidacy(gson.toJson(new ConfirmationCandidacyDtoRequest("1", text)))),
                () -> assertEquals(gson.toJson(new ErrorDtoResponse(NULL_VALUE)), server.confirmationCandidacy(null)),
                () -> assertEquals(gson.toJson(new ErrorDtoResponse(NULL_VALUE)), server.confirmationCandidacy(gson.toJson(new ConfirmationCandidacyDtoRequest(null, text)))),
                () -> assertEquals(gson.toJson(new ErrorDtoResponse(NULL_VALUE)), server.confirmationCandidacy(gson.toJson(new ConfirmationCandidacyDtoRequest("1", null))))
        );
        server.stopServer(null);
    }
    @Test
    public void withdrawCandidacyTest() throws IOException, ClassNotFoundException, ServerException {
        server.startServer(null);
        RegisterDtoRequest request1 = new RegisterDtoRequest(randomString(), randomString(), null, randomString(), 1, 1, "loginNumber1", "111^wW1234");
        server.register(gson.toJson(request1));
        List<String> text = new ArrayList<>();
        text.add("idea №1");
        server.confirmationCandidacy(gson.toJson(new ConfirmationCandidacyDtoRequest(server.sessionService.getSession(request1.newVoter()).getToken(), text)));
        assertAll(
                () -> assertEquals(EMPTY_JSON, server.withdrawCandidacy(gson.toJson(new WithdrawCandidacyRequest(server.sessionService.getSession(request1.newVoter()).getToken())))),
                () -> assertEquals(gson.toJson(new ErrorDtoResponse(SESSION_NOT_FOUND)), server.withdrawCandidacy(gson.toJson(new WithdrawCandidacyRequest("1")))),
                () -> assertEquals(gson.toJson(new ErrorDtoResponse(NULL_VALUE)), server.withdrawCandidacy(null)),
                () -> assertEquals(gson.toJson(new ErrorDtoResponse(NULL_VALUE)), server.withdrawCandidacy(gson.toJson(new WithdrawCandidacyRequest(null))))
        );
        server.stopServer(null);
    }
    //TODO: МОККИТО
    @Test
    public void addIdeaTest() throws IOException, ServerException, ClassNotFoundException {
        server.startServer(null);
        RegisterDtoRequest request1 = new RegisterDtoRequest(randomString(), randomString(), null, randomString(), 1, 1, "loginNumber1", "111^wW1234");
        RegisterDtoRequest request2 = new RegisterDtoRequest(randomString(), randomString(), null, randomString(), 1, 1, "loginNumber2", "111^wW1234");
        server.register(gson.toJson(request1));
        server.register(gson.toJson(request2));
        List<String> text = new ArrayList<>();
        text.add("idea №1");
        server.confirmationCandidacy(gson.toJson(new ConfirmationCandidacyDtoRequest(server.sessionService.getSession(request2.newVoter()).getToken(), text)));
        assertAll(
                () -> assertEquals(EMPTY_JSON ,server.addIdea(gson.toJson(new AddIdeaDtoRequest("idea №2", server.sessionService.getSession(request1.newVoter()).getToken())))),
                () -> assertEquals(EMPTY_JSON ,server.addIdea(gson.toJson(new AddIdeaDtoRequest("idea №3", server.sessionService.getSession(request2.newVoter()).getToken())))),
                () -> assertEquals(gson.toJson(new ErrorDtoResponse(NULL_VALUE)) , server.addIdea(gson.toJson(null))),
                () -> assertEquals(gson.toJson(new ErrorDtoResponse(NULL_VALUE)) , server.addIdea(gson.toJson(new AddIdeaDtoRequest(null, server.sessionService.getSession(request1.newVoter()).getToken())))),
                () -> assertEquals(gson.toJson(new ErrorDtoResponse(NULL_VALUE)) , server.addIdea(gson.toJson(new AddIdeaDtoRequest("idea №4", null)))),
                () -> assertEquals(gson.toJson(new ErrorDtoResponse(SESSION_NOT_FOUND)) , server.addIdea(gson.toJson(new AddIdeaDtoRequest("idea №5", "1"))))
        );
        server.stopServer(null);
    }
    @Test
    public void estimateIdeaTest() throws IOException, ClassNotFoundException, ServerException {
        server.startServer(null);
        RegisterDtoRequest request1 = new RegisterDtoRequest(randomString(), randomString(), null, randomString(), 1, 1, "loginNumber1", "111^wW1234");
        RegisterDtoRequest request2 = new RegisterDtoRequest(randomString(), randomString(), null, randomString(), 1, 1, "loginNumber2", "111^wW1234");
        server.register(gson.toJson(request1));
        server.register(gson.toJson(request2));
        server.addIdea(gson.toJson(new AddIdeaDtoRequest("idea №1", server.sessionService.getSession(request1.newVoter()).getToken())));
        assertAll(
                () -> assertEquals(EMPTY_JSON, server.estimateIdea(gson.toJson(new EstimateIdeaDtoRequest(server.voterService.get("loginNumber1").getLogin(), 1, server.sessionService.getSession(request2.newVoter()).getToken())))),
                () -> assertEquals(gson.toJson(new ErrorDtoResponse("Оценка должна быть от 1 до 5.")), server.estimateIdea(gson.toJson(new EstimateIdeaDtoRequest(server.voterService.get("loginNumber1").getLogin(), 0, server.sessionService.getSession(request2.newVoter()).getToken())))),
                () -> assertEquals(gson.toJson(new ErrorDtoResponse(NULL_VALUE)), server.estimateIdea(gson.toJson(null))),
                () -> assertEquals(gson.toJson(new ErrorDtoResponse(NULL_VALUE)), server.estimateIdea(gson.toJson(new EstimateIdeaDtoRequest(null, 1, server.sessionService.getSession(request2.newVoter()).getToken())))),
                () -> assertEquals(gson.toJson(new ErrorDtoResponse(NULL_VALUE)), server.estimateIdea(gson.toJson(new EstimateIdeaDtoRequest(server.voterService.get("loginNumber1").getLogin(), 1, null))))
        );
        server.stopServer(null);
    }
    @Test
    public void changeRatingTest() throws IOException, ClassNotFoundException, ServerException {
        server.startServer(null);
        RegisterDtoRequest request1 = new RegisterDtoRequest(randomString(), randomString(), null, randomString(), 1, 1, "loginNumber1", "111^wW1234");
        RegisterDtoRequest request2 = new RegisterDtoRequest(randomString(), randomString(), null, randomString(), 1, 1, "loginNumber2", "111^wW1234");
        server.register(gson.toJson(request1));
        server.register(gson.toJson(request2));
        String token1 = server.sessionService.getSession(request1.newVoter()).getToken();
        String token2 = server.sessionService.getSession(request2.newVoter()).getToken();
        server.addIdea(gson.toJson(new AddIdeaDtoRequest("idea №1", token1)));
        server.estimateIdea(gson.toJson(new EstimateIdeaDtoRequest(server.voterService.get("loginNumber1").getLogin(), 1, token2)));
        assertAll(
                () -> assertEquals(EMPTY_JSON, server.changeRating(gson.toJson(new ChangeRatingDtoRequest(token2, server.ideaService.getKey(server.sessionService.getVoter(token1), "idea №1"), 5)))),
                () -> assertEquals(gson.toJson(new ErrorDtoResponse("Оценка должна быть от 1 до 5.")), server.changeRating(gson.toJson(new ChangeRatingDtoRequest(token2, server.ideaService.getKey(server.sessionService.getVoter(token1), "idea №1"), 0)))),
                () -> assertEquals(gson.toJson(new ErrorDtoResponse(NULL_VALUE)), server.changeRating(gson.toJson(null))),
                () -> assertEquals(gson.toJson(new ErrorDtoResponse(NULL_VALUE)), server.changeRating(gson.toJson(new ChangeRatingDtoRequest(null, server.ideaService.getKey(server.sessionService.getVoter(token1), "idea №1"), 5)))),
                () -> assertEquals(gson.toJson(new ErrorDtoResponse(NULL_VALUE)), server.changeRating(gson.toJson(new ChangeRatingDtoRequest(token2, null, 5))))
        );
        server.stopServer(null);
    }

    private Voter getNewVoter() {
        Random random = new Random();
        return new Voter(randomString(), randomString(),
                randomString(), random.nextInt(100), random.nextInt(100), randomString(),randomString());
    }

    private String randomString() {
        Random random = new Random();
        char[] sAlphabet = "АБВГДЕЖЗИКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯабвгдежзиклмнопрстуфхцчшщъыьэюя".toCharArray();
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < 60; i++) {
            stringBuilder.append(sAlphabet[random.nextInt(sAlphabet.length)]);
        }
        return stringBuilder.toString();
    }
}
