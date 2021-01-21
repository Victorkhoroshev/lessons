package net.thumbtack.school.elections.server;

import com.google.gson.Gson;
import net.thumbtack.school.elections.server.database.Database;
import net.thumbtack.school.elections.server.dto.request.LoginDtoRequest;
import net.thumbtack.school.elections.server.dto.request.LogoutDtoRequest;
import net.thumbtack.school.elections.server.dto.request.RegisterVoterDtoRequest;
import net.thumbtack.school.elections.server.dto.response.ErrorDtoResponse;
import net.thumbtack.school.elections.server.dto.response.LoginDtoResponse;
import net.thumbtack.school.elections.server.dto.response.RegisterVoterDtoResponse;
import net.thumbtack.school.elections.server.service.SessionService;
import net.thumbtack.school.elections.server.service.ServerException;
import net.thumbtack.school.elections.server.service.ExceptionErrorCode;
import org.junit.Ignore;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

public class TestServer {
//    private final Gson gson = new Gson();
//    private final Server server = new Server();
//    private static final String NULL_REGISTERVOTERDTOREQUEST = "Пожалуйста, заполните все данные.";
//    private static final String NULL_LOGINDTOREQUEST = "Пожалуйста, введите логин и пароль.";
//    private static final String WRONG_FIRSTNAME = "Имя должно быть на кириллице, без пробелов, спец. символов и цифр.\n";
//    private static final String WRONG_LASTNAME = "Фамилия должна быть на кириллице, без пробелов, спец. символов и цифр.\n";
//    private static final String WRONG_PATRONYMIC = "Отчество должно быть на кириллице, без пробелов, спец. символов и цифр.\n";
//    private static final String WRONG_STREET = "Название улицы должно быть на кириллице, без пробелов, спец. символов и цифр.\n";
//    private static final String WRONG_HOUSE = "Номер дома не может быть меньше единицы.\n";
//    private static final String WRONG_APARTMENT = "Номер квартиры не может быть меньше нуля.\n";
//    private static final String WRONG_LOGIN = "Длинна логина должна быть не меньше 9 символо.\n";
//    private static final String WRONG_PASSWORD = "Пароль должен содержать хотя бы одну заглавную букву, одну строчную букву," +
//            " цифру и один спец. символ, а его длинна не менее 9 символов, без пробелов.\n";
//    @Test
//    public void testStartStopServer() {
//        server.startServer(null);
//        RegisterVoterDtoRequest registerVoterDto1 = new RegisterVoterDtoRequest("йцу", "йцу",
//                null,"йцу", 1, 188, randomString(), "111%111Aa");
//        assertAll(
//                () -> assertEquals(server.registerVoter(gson.toJson(registerVoterDto1)),gson.toJson(new RegisterVoterDtoResponse(server.sessionService.getSession(registerVoterDto1.newVoter()).getToken()))),
//                () -> assertEquals(gson.toJson(new LoginDtoResponse("Вы успешно разлогинились.")), server.logout(gson.toJson(new LogoutDtoRequest(server.sessionService.getSession(registerVoterDto1.newVoter()).getToken())))),
//                () -> assertEquals(1, server.voterDao.getAllVoters().size())
//        );
//        server.stopServer("C:\\Thumbtack\\thumbtack_online_school_2020_2__viktor_khoroshev\\saveDataFile");
//        assertAll(
//                () -> assertNull(server.gson),
//                () -> assertNull(server.voterService),
//                () -> assertNull(server.voterDao),
//                () -> assertNull(server.sessionService),
//                () -> assertNull(server.candidateService)
//        );
//        assertAll(
//                () -> assertThrows(NullPointerException.class, () -> {
//                    server.registerVoter(gson.toJson(registerVoterDto1));
//                }),
//                () -> assertThrows(NullPointerException.class, () -> {
//                    server.loginVoter(gson.toJson(new LogoutDtoRequest(server.sessionService.getSession(registerVoterDto1.newVoter()).getToken())));
//                })
//        );
//        server.startServer("C:\\Thumbtack\\thumbtack_online_school_2020_2__viktor_khoroshev\\saveDataFile");
//        assertAll(
//                () -> assertEquals(gson.toJson(new ErrorDtoResponse("Вы уже зарегестрированны.")), server.registerVoter(gson.toJson(registerVoterDto1))),
//                () -> assertEquals(1,Database.getVoterSet().size())
//        );
//    }
//
//    @Test
//    public void testRegisterVoter(){
//        server.startServer(null);
//        SessionService session = server.sessionService;
//        //firstName
//        RegisterVoterDtoRequest voter1 = new RegisterVoterDtoRequest("Виктор", "Хорошев",
//                null,"Пригородная", 1, 188, randomString(), "111%111Aa");
//        RegisterVoterDtoRequest voter2 = new RegisterVoterDtoRequest("викторр", "Хорошев",
//                null, "Пригородная", 1, 188, randomString(), "111%111Aa");
//        RegisterVoterDtoRequest voter3 = new RegisterVoterDtoRequest("ВИКТОРРР", "Хорошев",
//                null, "Пригородная", 1, 188, randomString(), "111%111Aa");
//        RegisterVoterDtoRequest voter4 = new RegisterVoterDtoRequest("victor", "Хорошев",
//                null,  "Пригородная", 1, 188, randomString(), "111%111Aa");
//        RegisterVoterDtoRequest voter5 = new RegisterVoterDtoRequest("Вик тор", "Хорошев",
//                null, "Пригородная", 1, 188, randomString(), "111%111Aa");
//        RegisterVoterDtoRequest voter6 = new RegisterVoterDtoRequest("Вик/тор", "Хорошев",
//                null,  "Пригородная", 1, 188, randomString(), "111%111Aa");
//        RegisterVoterDtoRequest voter66 = new RegisterVoterDtoRequest("Вик1тор", "Хорошев",
//                null,  "Пригородная", 1, 188, randomString(), "111%111Aa");
//        //lastname
//        RegisterVoterDtoRequest voter7 = new RegisterVoterDtoRequest("Андрей", "Хорошев",
//                null, "Пригородная", 1, 188, randomString(), "111%111Aa");
//        RegisterVoterDtoRequest voter8 = new RegisterVoterDtoRequest("Андрей", "хорошевв",
//                null,  "Пригородная", 1, 188, randomString(), "111%111Aa");
//        RegisterVoterDtoRequest voter9 = new RegisterVoterDtoRequest("Аддрей", "ХОРОШЕВВВ",
//                null,  "Пригородная", 1, 188, randomString(), "111%111Aa");
//        RegisterVoterDtoRequest voter10 = new RegisterVoterDtoRequest("Аддрей", "Khoroshev",
//                null,  "Пригородная", 1, 188, randomString(), "111%111Aa");
//        RegisterVoterDtoRequest voter11 = new RegisterVoterDtoRequest("Аддрей", "ХОРО ШЕВ",
//                null,   "Пригородная", 1, 188, randomString(), "111%111Aa");
//        RegisterVoterDtoRequest voter12 = new RegisterVoterDtoRequest("Аддрей", "ХОРО.ЕВВВ",
//                null,  "Пригородная", 1, 188, randomString(), "111%111Aa");
//        RegisterVoterDtoRequest voter122 = new RegisterVoterDtoRequest("Аддрей", "ХОРО&ЕВВВ",
//                null,  "Пригородная", 1, 188, randomString(), "111%111Aa");
//        //patronymic
//        RegisterVoterDtoRequest voter13 = new RegisterVoterDtoRequest(randomString(), "Хорошев","Игоревич",
//                "Пригородная", 1, 188, randomString(), "111%111Aa");
//        RegisterVoterDtoRequest voter14 = new RegisterVoterDtoRequest(randomString(), "Хорошев","игоревич",
//                "Пригородная", 1, 188, randomString(), "111%111Aa");
//        RegisterVoterDtoRequest voter15 = new RegisterVoterDtoRequest(randomString(), "Хорошев","ИГОРЕВИЧ",
//                "Пригородная", 1, 188, randomString(), "111%111Aa");
//        RegisterVoterDtoRequest voter16 = new RegisterVoterDtoRequest(randomString(), "Хорошев","Igorevich",
//                "Пригородная", 1, 188, randomString(), "111%111Aa");
//        RegisterVoterDtoRequest voter17 = new RegisterVoterDtoRequest(randomString(), "Хорошев","Игоревич1",
//                "Пригородная", 1, 188, randomString(), "111%111Aa");
//        RegisterVoterDtoRequest voter18 = new RegisterVoterDtoRequest(randomString(), "Хорошев","Игорев/ич",
//                "Пригородная", 1, 188, randomString(), "111%111Aa");
//        RegisterVoterDtoRequest voter188 = new RegisterVoterDtoRequest(randomString(), "Хорошев","Игорев ич",
//                "Пригородная", 1, 188, randomString(), "111%111Aa");
//        //street
//        RegisterVoterDtoRequest voter19 = new RegisterVoterDtoRequest(randomString(), "Хорошев","Игоревич",
//                "Пригородная", 1, 188, randomString(), "111%111Aa");
//        RegisterVoterDtoRequest voter20 = new RegisterVoterDtoRequest(randomString(), "Хорошев","Игоревич",
//                "пригородная", 1, 188, randomString(), "111%111Aa");
//        RegisterVoterDtoRequest voter21 = new RegisterVoterDtoRequest(randomString(), "Хорошев","Игоревич",
//                "ПРИГОРОДНАЯ", 1, 188, randomString(), "111%111Aa");
//        RegisterVoterDtoRequest voter22 = new RegisterVoterDtoRequest(randomString(), "Хорошев","Игоревич",
//                "Prigiridnaya", 1, 188, randomString(), "111%111Aa");
//        RegisterVoterDtoRequest voter23 = new RegisterVoterDtoRequest(randomString(), "Хорошев","Игоревич",
//                "Пригор1одная", 1, 188, randomString(), "111%111Aa");
//        RegisterVoterDtoRequest voter24 = new RegisterVoterDtoRequest(randomString(), "Хорошев","Игоревич",
//                "Приг ородная", 1, 188, randomString(), "111%111Aa");
//        RegisterVoterDtoRequest voter25 = new RegisterVoterDtoRequest(randomString(), "Хорошев","Игоревич",
//                "Пригор*одная", 1, 188, randomString(), "111%111Aa");
//        //house
//        RegisterVoterDtoRequest voter26 = new RegisterVoterDtoRequest(randomString(), "Хорошев","Игоревич",
//                "Пригородная", 1, 188, randomString(), "111%111Aa");
//        RegisterVoterDtoRequest voter27 = new RegisterVoterDtoRequest(randomString(), "Хорошев","Игоревич",
//                "Пригородная", 0, 188, randomString(), "111%111Aa");
//        RegisterVoterDtoRequest voter28 = new RegisterVoterDtoRequest(randomString(), "Хорошев","Игоревич",
//                "Пригородная", -13, 188, randomString(), "111%111Aa");
//        //apartment
//        RegisterVoterDtoRequest voter29 = new RegisterVoterDtoRequest(randomString(), "Хорошев","Игоревич",
//                "Пригородная", 1, 188, randomString(), "111%111Aa");
//        RegisterVoterDtoRequest voter30 = new RegisterVoterDtoRequest(randomString(), "Хорошев",
//                "Игоревич", "Пригородная", 1, 1, randomString(), "111%111Aa");
//        RegisterVoterDtoRequest voter31 = new RegisterVoterDtoRequest(randomString(), "Хорошев","Игоревич",
//                "Пригородная", 1, -1, randomString(), "111%111Aa");
//        //login
//        RegisterVoterDtoRequest voter33 = new RegisterVoterDtoRequest(randomString(), "Хорошев","Игоревич",
//                "Пригородная", 1, 188, "vic", "111%111Aa");
//        //password
//        RegisterVoterDtoRequest voter35 = new RegisterVoterDtoRequest(randomString(), "Хорошев","Игоревич",
//                "Пригородная", 1, 188, randomString(), "111%111qQ");
//        RegisterVoterDtoRequest voter36 = new RegisterVoterDtoRequest(randomString(), "Хорошев","Игоревич",
//                "Пригородная", 1, 188, randomString(), "111%111*гЖ");
//        RegisterVoterDtoRequest voter37 = new RegisterVoterDtoRequest(randomString(), "Хорошев","Игоревич",
//                "Пригородная", 1, 188, randomString(), "1г11%111Q");
//        RegisterVoterDtoRequest voter38 = new RegisterVoterDtoRequest(randomString(), "Хорошев","Игоревич",
//                "Пригородная", 1, 188, randomString(), "111q%11Г1");
//        RegisterVoterDtoRequest voter39 = new RegisterVoterDtoRequest(randomString(), "Хорошев","Игоревич",
//                "Пригородная", 1, 188, randomString(), "111111Aa");
//        RegisterVoterDtoRequest voter40 = new RegisterVoterDtoRequest(randomString(), "Хорошев","Игоревич",
//                "Пригородная", 1, 188, randomString(), "фффф%ффффAa");
//        RegisterVoterDtoRequest voter41 = new RegisterVoterDtoRequest(randomString(), "Хорошев","Игоревич",
//                "Пригородная", 1, 188, randomString(), "111%111фa");
//        RegisterVoterDtoRequest voter42 = new RegisterVoterDtoRequest(randomString(), "Хорошев","Игоревич",
//                "Пригородная", 1, 188, randomString(), "111%111AФ");
//        RegisterVoterDtoRequest voter43 = new RegisterVoterDtoRequest(randomString(), "Хорошев","Игоревич",
//                "Пригородная", 1, 188, randomString(), "111%111 Aa");
//        RegisterVoterDtoRequest voter44 = new RegisterVoterDtoRequest(randomString(), "Хорошев","Игоревич",
//                "Пригородная", 1, 188, randomString(), "1");
//        RegisterVoterDtoRequest voter45 = new RegisterVoterDtoRequest(randomString(), "Хорошев","Игоревич",
//                "Пригородная", 1, 188, randomString(), "11%?AaфQ");
//        //NULL parameter
//        RegisterVoterDtoRequest voter46 = new RegisterVoterDtoRequest(null, "Хорошев","Игоревич",
//                "Пригородная", 1, 188, randomString(), "11%?AaфQ");
//        RegisterVoterDtoRequest voter47 = new RegisterVoterDtoRequest(randomString(), null,"Игоревич",
//                "Пригородная", 1, 188, randomString(), "11%?AaфQ");
//        RegisterVoterDtoRequest voter48 = new RegisterVoterDtoRequest(randomString(), "Хорошев","Игоревич",
//                null, 1, 188, randomString(), "11%?AaфQ");
//        RegisterVoterDtoRequest voter50 = new RegisterVoterDtoRequest(randomString(), "Хорошев","Игоревич",
//                "Пригородная", 1, 188, null, "11%?AaфQ");
//        RegisterVoterDtoRequest voter51 = new RegisterVoterDtoRequest(randomString(), "Хорошев","Игоревич",
//                "Пригородная", null, 188, randomString(), null);
//        RegisterVoterDtoRequest voter52 = new RegisterVoterDtoRequest(null, null,null,
//                null, null, null, null, null);
//
//        RegisterVoterDtoRequest voter53 = new RegisterVoterDtoRequest("V", "CD","POL",
//                "-ап", -1, -234, "null", "1");
//
//        assertAll(
//                () -> assertEquals(server.registerVoter(gson.toJson(voter1)), gson.toJson(new RegisterVoterDtoResponse(session.getSession(voter1.newVoter()).getToken()))),
//                () -> assertEquals(server.registerVoter(gson.toJson(voter2)), gson.toJson(new RegisterVoterDtoResponse(session.getSession(voter2.newVoter()).getToken()))),
//                () -> assertEquals(server.registerVoter(gson.toJson(voter3)), gson.toJson(new RegisterVoterDtoResponse(session.getSession(voter3.newVoter()).getToken()))),
//                () -> assertEquals(server.registerVoter( gson.toJson(voter4)), gson.toJson(new ErrorDtoResponse(WRONG_FIRSTNAME))),
//                () -> assertEquals(server.registerVoter(gson.toJson(voter5)), gson.toJson(new ErrorDtoResponse(WRONG_FIRSTNAME))),
//                () -> assertEquals(server.registerVoter(gson.toJson(voter6)), gson.toJson(new ErrorDtoResponse(WRONG_FIRSTNAME))),
//                () -> assertEquals(server.registerVoter(gson.toJson(voter66)), gson.toJson(new ErrorDtoResponse(WRONG_FIRSTNAME))),
//                () -> assertEquals(server.registerVoter(gson.toJson(voter7)), gson.toJson(new RegisterVoterDtoResponse(session.getSession(voter7.newVoter()).getToken()))),
//                () -> assertEquals(server.registerVoter(gson.toJson(voter8)), gson.toJson(new RegisterVoterDtoResponse(session.getSession(voter8.newVoter()).getToken()))),
//                () -> assertEquals(server.registerVoter(gson.toJson(voter9)), gson.toJson(new RegisterVoterDtoResponse(session.getSession(voter9.newVoter()).getToken()))),
//                () -> assertEquals(server.registerVoter(gson.toJson(voter10)), gson.toJson(new ErrorDtoResponse(WRONG_LASTNAME))),
//                () -> assertEquals(server.registerVoter(gson.toJson(voter11)), gson.toJson(new ErrorDtoResponse(WRONG_LASTNAME))),
//                () -> assertEquals(server.registerVoter(gson.toJson(voter12)), gson.toJson(new ErrorDtoResponse(WRONG_LASTNAME))),
//                () -> assertEquals(server.registerVoter(gson.toJson(voter122)), gson.toJson(new ErrorDtoResponse(WRONG_LASTNAME))),
//                () -> assertEquals(server.registerVoter(gson.toJson(voter13)), gson.toJson(new RegisterVoterDtoResponse(session.getSession(voter13.newVoter()).getToken()))),
//                () -> assertEquals(server.registerVoter(gson.toJson(voter14)), gson.toJson(new RegisterVoterDtoResponse(session.getSession(voter14.newVoter()).getToken()))),
//                () -> assertEquals(server.registerVoter(gson.toJson(voter15)), gson.toJson(new RegisterVoterDtoResponse(session.getSession(voter15.newVoter()).getToken()))),
//                () -> assertEquals(server.registerVoter(gson.toJson(voter16)), gson.toJson(new ErrorDtoResponse(WRONG_PATRONYMIC))),
//                () -> assertEquals(server.registerVoter(gson.toJson(voter17)), gson.toJson(new ErrorDtoResponse(WRONG_PATRONYMIC))),
//                () -> assertEquals(server.registerVoter(gson.toJson(voter18)), gson.toJson(new ErrorDtoResponse(WRONG_PATRONYMIC))),
//                () -> assertEquals(server.registerVoter(gson.toJson(voter188)), gson.toJson(new ErrorDtoResponse(WRONG_PATRONYMIC))),
//                () -> assertEquals(server.registerVoter(gson.toJson(voter19)), gson.toJson(new RegisterVoterDtoResponse(session.getSession(voter19.newVoter()).getToken()))),
//                () -> assertEquals(server.registerVoter(gson.toJson(voter20)), gson.toJson(new RegisterVoterDtoResponse(session.getSession(voter20.newVoter()).getToken()))),
//                () -> assertEquals(server.registerVoter(gson.toJson(voter21)), gson.toJson(new RegisterVoterDtoResponse(session.getSession(voter21.newVoter()).getToken()))),
//                () -> assertEquals(server.registerVoter(gson.toJson(voter22)), gson.toJson(new ErrorDtoResponse(WRONG_STREET))),
//                () -> assertEquals(server.registerVoter(gson.toJson(voter23)), gson.toJson(new ErrorDtoResponse(WRONG_STREET))),
//                () -> assertEquals(server.registerVoter(gson.toJson(voter24)), gson.toJson(new ErrorDtoResponse(WRONG_STREET))),
//                () -> assertEquals(server.registerVoter(gson.toJson(voter25)), gson.toJson(new ErrorDtoResponse(WRONG_STREET))),
//                () -> assertEquals(server.registerVoter(gson.toJson(voter26)), gson.toJson(new RegisterVoterDtoResponse(session.getSession(voter26.newVoter()).getToken()))),
//                () -> assertEquals(server.registerVoter(gson.toJson(voter27)), gson.toJson(new ErrorDtoResponse(WRONG_HOUSE))),
//                () -> assertEquals(server.registerVoter(gson.toJson(voter28)), gson.toJson(new ErrorDtoResponse(WRONG_HOUSE))),
//                () -> assertEquals(server.registerVoter(gson.toJson(voter29)), gson.toJson(new RegisterVoterDtoResponse(session.getSession(voter29.newVoter()).getToken()))),
//                () -> assertEquals(server.registerVoter(gson.toJson(voter30)), gson.toJson(new RegisterVoterDtoResponse(session.getSession(voter30.newVoter()).getToken()))),
//                () -> assertEquals(server.registerVoter(gson.toJson(voter31)), gson.toJson(new ErrorDtoResponse(WRONG_APARTMENT))),
//                () -> assertEquals(server.registerVoter(gson.toJson(voter33)), gson.toJson(new ErrorDtoResponse(WRONG_LOGIN))),
//                () -> assertEquals(server.registerVoter(gson.toJson(voter35)), gson.toJson(new RegisterVoterDtoResponse(session.getSession(voter35.newVoter()).getToken()))),
//                () -> assertEquals(server.registerVoter(gson.toJson(voter36)), gson.toJson(new RegisterVoterDtoResponse(session.getSession(voter36.newVoter()).getToken()))),
//                () -> assertEquals(server.registerVoter(gson.toJson(voter37)), gson.toJson(new RegisterVoterDtoResponse(session.getSession(voter37.newVoter()).getToken()))),
//                () -> assertEquals(server.registerVoter(gson.toJson(voter38)), gson.toJson(new RegisterVoterDtoResponse(session.getSession(voter38.newVoter()).getToken()))),
//                () -> assertEquals(server.registerVoter(gson.toJson(voter39)), gson.toJson(new ErrorDtoResponse(WRONG_PASSWORD))),
//                () -> assertEquals(server.registerVoter(gson.toJson(voter40)), gson.toJson(new ErrorDtoResponse(WRONG_PASSWORD))),
//                () -> assertEquals(server.registerVoter(gson.toJson(voter41)), gson.toJson(new ErrorDtoResponse(WRONG_PASSWORD))),
//                () -> assertEquals(server.registerVoter(gson.toJson(voter42)), gson.toJson(new ErrorDtoResponse(WRONG_PASSWORD))),
//                () -> assertEquals(server.registerVoter(gson.toJson(voter43)), gson.toJson(new ErrorDtoResponse(WRONG_PASSWORD))),
//                () -> assertEquals(server.registerVoter(gson.toJson(voter44)), gson.toJson(new ErrorDtoResponse(WRONG_PASSWORD))),
//                () -> assertEquals(server.registerVoter(gson.toJson(voter45)), gson.toJson(new ErrorDtoResponse(WRONG_PASSWORD))),
//                () -> assertEquals(server.registerVoter(gson.toJson(voter46)), gson.toJson(new ErrorDtoResponse(NULL_REGISTERVOTERDTOREQUEST))),
//                () -> assertEquals(server.registerVoter(gson.toJson(voter47)), gson.toJson(new ErrorDtoResponse(NULL_REGISTERVOTERDTOREQUEST))),
//                () -> assertEquals(server.registerVoter(gson.toJson(voter48)), gson.toJson(new ErrorDtoResponse(NULL_REGISTERVOTERDTOREQUEST))),
//                () -> assertEquals(server.registerVoter(gson.toJson(voter50)), gson.toJson(new ErrorDtoResponse(NULL_REGISTERVOTERDTOREQUEST))),
//                () -> assertEquals(server.registerVoter(gson.toJson(voter51)), gson.toJson(new ErrorDtoResponse(NULL_REGISTERVOTERDTOREQUEST))),
//                () -> assertEquals(server.registerVoter(gson.toJson(voter52)), gson.toJson(new ErrorDtoResponse(NULL_REGISTERVOTERDTOREQUEST))),
//                () -> assertEquals(server.registerVoter(gson.toJson(voter53)), gson.toJson(new ErrorDtoResponse(WRONG_FIRSTNAME + WRONG_LASTNAME + WRONG_PATRONYMIC + WRONG_STREET + WRONG_HOUSE + WRONG_APARTMENT + WRONG_LOGIN + WRONG_PASSWORD)))
//        );
//        server.startServer(null);
//    }
//
//    @Test
//    public void testLogoutVoter() throws ServerException {
//        server.startServer(null);
//        SessionService session = server.sessionService;
//        RegisterVoterDtoRequest voter1 = new RegisterVoterDtoRequest(randomString(), randomString(),
//                null,randomString(), 1, 188, randomString(), "111%111Aa");
//        server.registerVoter(gson.toJson(voter1));
//        LogoutDtoRequest request = new LogoutDtoRequest(session.getSession(voter1.newVoter()).getToken());
//        LogoutDtoRequest request1 = null;
//        LogoutDtoRequest request2 = new LogoutDtoRequest(null);
//        LogoutDtoRequest request3 = new LogoutDtoRequest(randomString());
//        assertEquals(gson.toJson(new LoginDtoResponse("Вы успешно разлогинились.")),server.logout(gson.toJson(request)));
//        assertEquals(gson.toJson(new ErrorDtoResponse("Что-то пошло не так.")),server.logout(gson.toJson(request1)));
//        assertEquals(gson.toJson(new ErrorDtoResponse("Что-то пошло не так.")),server.logout(gson.toJson(request2)));
//        assertEquals(gson.toJson(new ErrorDtoResponse("Сессия пользователя не найдена.")), server.logout(gson.toJson(request3)));
//        server.stopServer(null);
//    }
//    @Test
//    public void testLoginVoter() {
//        server.startServer(null);
//        SessionService session = server.sessionService;
//        RegisterVoterDtoRequest voter1 = new RegisterVoterDtoRequest(randomString(), randomString(),
//                null,randomString(), 1, 188, "login1111111", "111%111Aa");
//        RegisterVoterDtoRequest voter2 = new RegisterVoterDtoRequest(randomString(), randomString(),
//                null, randomString(), 1, 188, "login222222", "111%111Aa");
//        RegisterVoterDtoRequest voter3 = new RegisterVoterDtoRequest(randomString(), randomString(),
//                null, randomString(), 1, 188, "login333333", "111%111Aa");
//        server.registerVoter(gson.toJson(voter1));
//        server.registerVoter(gson.toJson(voter2));
//        server.registerVoter(gson.toJson(voter3));
//        LoginDtoRequest loginDtoRequest1 = new LoginDtoRequest("login1111111", "111%111Aa");
//        LoginDtoRequest loginDtoRequest2 = new LoginDtoRequest("login222222", "111%111Aa");
//        LoginDtoRequest loginDtoRequest3 = new LoginDtoRequest("login333333", "111%111Aa");
//        //неверный пароль
//        LoginDtoRequest loginDtoRequest4 = new LoginDtoRequest("login333333", "111%111Aa1");
//        //ошибка в пароле
//        LoginDtoRequest loginDtoRequest5 = new LoginDtoRequest("login333333", "11Aa1");
//        //нет такого пользователя
//        LoginDtoRequest loginDtoRequest6 = new LoginDtoRequest("login44444444444", "111%111Aa");
//        //null
//        LoginDtoRequest loginDtoRequest7 = null;
//        LoginDtoRequest loginDtoRequest8 = new LoginDtoRequest(null, null);
//        //ошибка в логине
//        LoginDtoRequest loginDtoRequest9 = new LoginDtoRequest("log", "111%111Aa");
//
//        assertAll(
//                () -> assertEquals(server.loginVoter(gson.toJson(loginDtoRequest1)), gson.toJson(new LoginDtoResponse(session.getSession(voter1.newVoter()).getToken()))),
//                () -> assertEquals(server.loginVoter(gson.toJson(loginDtoRequest2)), gson.toJson(new LoginDtoResponse(session.getSession(voter2.newVoter()).getToken()))),
//                () -> assertEquals(server.loginVoter(gson.toJson(loginDtoRequest3)), gson.toJson(new LoginDtoResponse(session.getSession(voter3.newVoter()).getToken()))),
//                () -> assertEquals(server.loginVoter(gson.toJson(loginDtoRequest4)), gson.toJson(new ErrorDtoResponse(ExceptionErrorCode.VOTER_WRONG_PASSWORD.getMessage()))),
//                () -> assertEquals(server.loginVoter(gson.toJson(loginDtoRequest5)), gson.toJson(new ErrorDtoResponse(WRONG_PASSWORD))),
//                () -> assertEquals(server.loginVoter(gson.toJson(loginDtoRequest6)), gson.toJson(new ErrorDtoResponse(ExceptionErrorCode.VOTER_NOT_FOUND.getMessage()))),
//                () -> assertEquals(server.loginVoter(gson.toJson(loginDtoRequest7)), gson.toJson(new ErrorDtoResponse(NULL_LOGINDTOREQUEST))),
//                () -> assertEquals(server.loginVoter(gson.toJson(loginDtoRequest8)), gson.toJson(new ErrorDtoResponse(NULL_LOGINDTOREQUEST))),
//                () -> assertEquals(server.loginVoter(gson.toJson(loginDtoRequest9)), gson.toJson(new ErrorDtoResponse(WRONG_LOGIN)))
//        );
//        server.stopServer(null);
//    }
//
//    private String randomString() {
//        Random random = new Random();
//        char[] sAlphabet = "АБВГДЕЖЗИКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯабвгдежзиклмнопрстуфхцчшщъыьэюя".toCharArray();
//        StringBuilder stringBuilder = new StringBuilder();
//        for (int i = 0; i < 60; i++) {
//            stringBuilder.append(sAlphabet[random.nextInt(sAlphabet.length)]);
//        }
//        return stringBuilder.toString();
//    }
}
