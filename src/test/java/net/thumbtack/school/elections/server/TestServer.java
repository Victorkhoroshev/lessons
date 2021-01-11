package net.thumbtack.school.elections.server;

import com.google.gson.Gson;
import net.thumbtack.school.elections.server.database.Database;
import net.thumbtack.school.elections.server.dto.request.LoginDtoRequest;
import net.thumbtack.school.elections.server.dto.request.LogoutDtoRequest;
import net.thumbtack.school.elections.server.dto.request.RegisterVoterDtoRequest;
import net.thumbtack.school.elections.server.dto.response.LoginDtoResponse;
import net.thumbtack.school.elections.server.dto.response.LogoutDtoResponse;
import net.thumbtack.school.elections.server.dto.response.RegisterVoterDtoResponse;
import net.thumbtack.school.elections.server.service.SessionService;
import net.thumbtack.school.elections.server.service.VoterException;
import net.thumbtack.school.elections.server.service.VoterExceptionErrorCode;
import org.jetbrains.annotations.Nullable;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

public class TestServer {
    private final Gson gson = new Gson();
    private final Server server = new Server();
    private final Database database = Database.getInstance();
    private final SessionService session = SessionService.getInstance();
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
    public void testRegisterVoter()
    {
        //firstName
        RegisterVoterDtoRequest voter1 = new RegisterVoterDtoRequest("Виктор", "Хорошев",
                null,"Пригородная", 1, 188, randomString(), "111%111Aa");
        RegisterVoterDtoRequest voter2 = new RegisterVoterDtoRequest("викторр", "Хорошев",
                null, "Пригородная", 1, 188, randomString(), "111%111Aa");
        RegisterVoterDtoRequest voter3 = new RegisterVoterDtoRequest("ВИКТОРРР", "Хорошев",
                null, "Пригородная", 1, 188, randomString(), "111%111Aa");
        RegisterVoterDtoRequest voter4 = new RegisterVoterDtoRequest("victor", "Хорошев",
                null,  "Пригородная", 1, 188, randomString(), "111%111Aa");
        RegisterVoterDtoRequest voter5 = new RegisterVoterDtoRequest("Вик тор", "Хорошев",
                null, "Пригородная", 1, 188, randomString(), "111%111Aa");
        RegisterVoterDtoRequest voter6 = new RegisterVoterDtoRequest("Вик/тор", "Хорошев",
                null,  "Пригородная", 1, 188, randomString(), "111%111Aa");
        RegisterVoterDtoRequest voter66 = new RegisterVoterDtoRequest("Вик1тор", "Хорошев",
                null,  "Пригородная", 1, 188, randomString(), "111%111Aa");
        //lastname
        RegisterVoterDtoRequest voter7 = new RegisterVoterDtoRequest("Андрей", "Хорошев",
                null, "Пригородная", 1, 188, randomString(), "111%111Aa");
        RegisterVoterDtoRequest voter8 = new RegisterVoterDtoRequest("Андрей", "хорошевв",
                null,  "Пригородная", 1, 188, randomString(), "111%111Aa");
        RegisterVoterDtoRequest voter9 = new RegisterVoterDtoRequest("Аддрей", "ХОРОШЕВВВ",
                null,  "Пригородная", 1, 188, randomString(), "111%111Aa");
        RegisterVoterDtoRequest voter10 = new RegisterVoterDtoRequest("Аддрей", "Khoroshev",
                null,  "Пригородная", 1, 188, randomString(), "111%111Aa");
        RegisterVoterDtoRequest voter11 = new RegisterVoterDtoRequest("Аддрей", "ХОРО ШЕВ",
                null,   "Пригородная", 1, 188, randomString(), "111%111Aa");
        RegisterVoterDtoRequest voter12 = new RegisterVoterDtoRequest("Аддрей", "ХОРО.ЕВВВ",
                null,  "Пригородная", 1, 188, randomString(), "111%111Aa");
        RegisterVoterDtoRequest voter122 = new RegisterVoterDtoRequest("Аддрей", "ХОРО&ЕВВВ",
                null,  "Пригородная", 1, 188, randomString(), "111%111Aa");
        //patronymic
        RegisterVoterDtoRequest voter13 = new RegisterVoterDtoRequest(randomString(), "Хорошев","Игоревич",
                "Пригородная", 1, 188, randomString(), "111%111Aa");
        RegisterVoterDtoRequest voter14 = new RegisterVoterDtoRequest(randomString(), "Хорошев","игоревич",
                "Пригородная", 1, 188, randomString(), "111%111Aa");
        RegisterVoterDtoRequest voter15 = new RegisterVoterDtoRequest(randomString(), "Хорошев","ИГОРЕВИЧ",
                "Пригородная", 1, 188, randomString(), "111%111Aa");
        RegisterVoterDtoRequest voter16 = new RegisterVoterDtoRequest(randomString(), "Хорошев","Igorevich",
                "Пригородная", 1, 188, randomString(), "111%111Aa");
        RegisterVoterDtoRequest voter17 = new RegisterVoterDtoRequest(randomString(), "Хорошев","Игоревич1",
                "Пригородная", 1, 188, randomString(), "111%111Aa");
        RegisterVoterDtoRequest voter18 = new RegisterVoterDtoRequest(randomString(), "Хорошев","Игорев/ич",
                "Пригородная", 1, 188, randomString(), "111%111Aa");
        RegisterVoterDtoRequest voter188 = new RegisterVoterDtoRequest(randomString(), "Хорошев","Игорев ич",
                "Пригородная", 1, 188, randomString(), "111%111Aa");
        //street
        RegisterVoterDtoRequest voter19 = new RegisterVoterDtoRequest(randomString(), "Хорошев","Игоревич",
                "Пригородная", 1, 188, randomString(), "111%111Aa");
        RegisterVoterDtoRequest voter20 = new RegisterVoterDtoRequest(randomString(), "Хорошев","Игоревич",
                "пригородная", 1, 188, randomString(), "111%111Aa");
        RegisterVoterDtoRequest voter21 = new RegisterVoterDtoRequest(randomString(), "Хорошев","Игоревич",
                "ПРИГОРОДНАЯ", 1, 188, randomString(), "111%111Aa");
        RegisterVoterDtoRequest voter22 = new RegisterVoterDtoRequest(randomString(), "Хорошев","Игоревич",
                "Prigiridnaya", 1, 188, randomString(), "111%111Aa");
        RegisterVoterDtoRequest voter23 = new RegisterVoterDtoRequest(randomString(), "Хорошев","Игоревич",
                "Пригор1одная", 1, 188, randomString(), "111%111Aa");
        RegisterVoterDtoRequest voter24 = new RegisterVoterDtoRequest(randomString(), "Хорошев","Игоревич",
                "Приг ородная", 1, 188, randomString(), "111%111Aa");
        RegisterVoterDtoRequest voter25 = new RegisterVoterDtoRequest(randomString(), "Хорошев","Игоревич",
                "Пригор*одная", 1, 188, randomString(), "111%111Aa");
        //house
        RegisterVoterDtoRequest voter26 = new RegisterVoterDtoRequest(randomString(), "Хорошев","Игоревич",
                "Пригородная", 1, 188, randomString(), "111%111Aa");
        RegisterVoterDtoRequest voter27 = new RegisterVoterDtoRequest(randomString(), "Хорошев","Игоревич",
                "Пригородная", 0, 188, randomString(), "111%111Aa");
        RegisterVoterDtoRequest voter28 = new RegisterVoterDtoRequest(randomString(), "Хорошев","Игоревич",
                "Пригородная", -13, 188, randomString(), "111%111Aa");
        //apartment
        RegisterVoterDtoRequest voter29 = new RegisterVoterDtoRequest(randomString(), "Хорошев","Игоревич",
                "Пригородная", 1, 188, randomString(), "111%111Aa");
        RegisterVoterDtoRequest voter30 = new RegisterVoterDtoRequest(randomString(), "Хорошев",
                "Игоревич", "Пригородная", 1, 1, randomString(), "111%111Aa");
        RegisterVoterDtoRequest voter31 = new RegisterVoterDtoRequest(randomString(), "Хорошев","Игоревич",
                "Пригородная", 1, -1, randomString(), "111%111Aa");
        //login
        RegisterVoterDtoRequest voter33 = new RegisterVoterDtoRequest(randomString(), "Хорошев","Игоревич",
                "Пригородная", 1, 188, "vic", "111%111Aa");
        //password
        RegisterVoterDtoRequest voter35 = new RegisterVoterDtoRequest(randomString(), "Хорошев","Игоревич",
                "Пригородная", 1, 188, randomString(), "111%111qQ");
        RegisterVoterDtoRequest voter36 = new RegisterVoterDtoRequest(randomString(), "Хорошев","Игоревич",
                "Пригородная", 1, 188, randomString(), "111%111*гЖ");
        RegisterVoterDtoRequest voter37 = new RegisterVoterDtoRequest(randomString(), "Хорошев","Игоревич",
                "Пригородная", 1, 188, randomString(), "1г11%111Q");
        RegisterVoterDtoRequest voter38 = new RegisterVoterDtoRequest(randomString(), "Хорошев","Игоревич",
                "Пригородная", 1, 188, randomString(), "111q%11Г1");
        RegisterVoterDtoRequest voter39 = new RegisterVoterDtoRequest(randomString(), "Хорошев","Игоревич",
                "Пригородная", 1, 188, randomString(), "111111Aa");
        RegisterVoterDtoRequest voter40 = new RegisterVoterDtoRequest(randomString(), "Хорошев","Игоревич",
                "Пригородная", 1, 188, randomString(), "фффф%ффффAa");
        RegisterVoterDtoRequest voter41 = new RegisterVoterDtoRequest(randomString(), "Хорошев","Игоревич",
                "Пригородная", 1, 188, randomString(), "111%111фa");
        RegisterVoterDtoRequest voter42 = new RegisterVoterDtoRequest(randomString(), "Хорошев","Игоревич",
                "Пригородная", 1, 188, randomString(), "111%111AФ");
        RegisterVoterDtoRequest voter43 = new RegisterVoterDtoRequest(randomString(), "Хорошев","Игоревич",
                "Пригородная", 1, 188, randomString(), "111%111 Aa");
        RegisterVoterDtoRequest voter44 = new RegisterVoterDtoRequest(randomString(), "Хорошев","Игоревич",
                "Пригородная", 1, 188, randomString(), "1");
        RegisterVoterDtoRequest voter45 = new RegisterVoterDtoRequest(randomString(), "Хорошев","Игоревич",
                "Пригородная", 1, 188, randomString(), "11%?AaфQ");
        //NULL parameter
        RegisterVoterDtoRequest voter46 = new RegisterVoterDtoRequest(null, "Хорошев","Игоревич",
                "Пригородная", 1, 188, randomString(), "11%?AaфQ");
        RegisterVoterDtoRequest voter47 = new RegisterVoterDtoRequest(randomString(), null,"Игоревич",
                "Пригородная", 1, 188, randomString(), "11%?AaфQ");
        RegisterVoterDtoRequest voter48 = new RegisterVoterDtoRequest(randomString(), "Хорошев","Игоревич",
                null, 1, 188, randomString(), "11%?AaфQ");
        RegisterVoterDtoRequest voter50 = new RegisterVoterDtoRequest(randomString(), "Хорошев","Игоревич",
                "Пригородная", 1, 188, null, "11%?AaфQ");
        RegisterVoterDtoRequest voter51 = new RegisterVoterDtoRequest(randomString(), "Хорошев","Игоревич",
                "Пригородная", null, 188, randomString(), null);
        RegisterVoterDtoRequest voter52 = new RegisterVoterDtoRequest(null, null,null,
                null, null, null, null, null);

        RegisterVoterDtoRequest voter53 = new RegisterVoterDtoRequest("V", "CD","POL",
                "-ап", -1, -234, "null", "1");

        assertAll(
                () -> assertEquals(server.registerVoter(requestToJson(voter1)), responseToJson(new RegisterVoterDtoResponse(session.getSession(voter1.newVoter()).getToken()))),
                () -> assertEquals(server.registerVoter(requestToJson(voter2)), responseToJson(new RegisterVoterDtoResponse(session.getSession(voter2.newVoter()).getToken()))),
                () -> assertEquals(server.registerVoter(requestToJson(voter3)), responseToJson(new RegisterVoterDtoResponse(session.getSession(voter3.newVoter()).getToken()))),
                () -> assertEquals(server.registerVoter(requestToJson(voter4)), responseToJson(new RegisterVoterDtoResponse(WRONG_FIRSTNAME))),
                () -> assertEquals(server.registerVoter(requestToJson(voter5)), responseToJson(new RegisterVoterDtoResponse(WRONG_FIRSTNAME))),
                () -> assertEquals(server.registerVoter(requestToJson(voter6)), responseToJson(new RegisterVoterDtoResponse(WRONG_FIRSTNAME))),
                () -> assertEquals(server.registerVoter(requestToJson(voter66)), responseToJson(new RegisterVoterDtoResponse(WRONG_FIRSTNAME))),
                () -> assertEquals(server.registerVoter(requestToJson(voter7)), responseToJson(new RegisterVoterDtoResponse(session.getSession(voter7.newVoter()).getToken()))),
                () -> assertEquals(server.registerVoter(requestToJson(voter8)), responseToJson(new RegisterVoterDtoResponse(session.getSession(voter8.newVoter()).getToken()))),
                () -> assertEquals(server.registerVoter(requestToJson(voter9)), responseToJson(new RegisterVoterDtoResponse(session.getSession(voter9.newVoter()).getToken()))),
                () -> assertEquals(server.registerVoter(requestToJson(voter10)), responseToJson(new RegisterVoterDtoResponse(WRONG_LASTNAME))),
                () -> assertEquals(server.registerVoter(requestToJson(voter11)), responseToJson(new RegisterVoterDtoResponse(WRONG_LASTNAME))),
                () -> assertEquals(server.registerVoter(requestToJson(voter12)), responseToJson(new RegisterVoterDtoResponse(WRONG_LASTNAME))),
                () -> assertEquals(server.registerVoter(requestToJson(voter122)), responseToJson(new RegisterVoterDtoResponse(WRONG_LASTNAME))),
                () -> assertEquals(server.registerVoter(requestToJson(voter13)), responseToJson(new RegisterVoterDtoResponse(session.getSession(voter13.newVoter()).getToken()))),
                () -> assertEquals(server.registerVoter(requestToJson(voter14)), responseToJson(new RegisterVoterDtoResponse(session.getSession(voter14.newVoter()).getToken()))),
                () -> assertEquals(server.registerVoter(requestToJson(voter15)), responseToJson(new RegisterVoterDtoResponse(session.getSession(voter15.newVoter()).getToken()))),
                () -> assertEquals(server.registerVoter(requestToJson(voter16)), responseToJson(new RegisterVoterDtoResponse(WRONG_PATRONYMIC))),
                () -> assertEquals(server.registerVoter(requestToJson(voter17)), responseToJson(new RegisterVoterDtoResponse(WRONG_PATRONYMIC))),
                () -> assertEquals(server.registerVoter(requestToJson(voter18)), responseToJson(new RegisterVoterDtoResponse(WRONG_PATRONYMIC))),
                () -> assertEquals(server.registerVoter(requestToJson(voter188)), responseToJson(new RegisterVoterDtoResponse(WRONG_PATRONYMIC))),
                () -> assertEquals(server.registerVoter(requestToJson(voter19)), responseToJson(new RegisterVoterDtoResponse(session.getSession(voter19.newVoter()).getToken()))),
                () -> assertEquals(server.registerVoter(requestToJson(voter20)), responseToJson(new RegisterVoterDtoResponse(session.getSession(voter20.newVoter()).getToken()))),
                () -> assertEquals(server.registerVoter(requestToJson(voter21)), responseToJson(new RegisterVoterDtoResponse(session.getSession(voter21.newVoter()).getToken()))),
                () -> assertEquals(server.registerVoter(requestToJson(voter22)), responseToJson(new RegisterVoterDtoResponse(WRONG_STREET))),
                () -> assertEquals(server.registerVoter(requestToJson(voter23)), responseToJson(new RegisterVoterDtoResponse(WRONG_STREET))),
                () -> assertEquals(server.registerVoter(requestToJson(voter24)), responseToJson(new RegisterVoterDtoResponse(WRONG_STREET))),
                () -> assertEquals(server.registerVoter(requestToJson(voter25)), responseToJson(new RegisterVoterDtoResponse(WRONG_STREET))),
                () -> assertEquals(server.registerVoter(requestToJson(voter26)), responseToJson(new RegisterVoterDtoResponse(session.getSession(voter26.newVoter()).getToken()))),
                () -> assertEquals(server.registerVoter(requestToJson(voter27)), responseToJson(new RegisterVoterDtoResponse(WRONG_HOUSE))),
                () -> assertEquals(server.registerVoter(requestToJson(voter28)), responseToJson(new RegisterVoterDtoResponse(WRONG_HOUSE))),
                () -> assertEquals(server.registerVoter(requestToJson(voter29)), responseToJson(new RegisterVoterDtoResponse(session.getSession(voter29.newVoter()).getToken()))),
                () -> assertEquals(server.registerVoter(requestToJson(voter30)), responseToJson(new RegisterVoterDtoResponse(session.getSession(voter30.newVoter()).getToken()))),
                () -> assertEquals(server.registerVoter(requestToJson(voter31)), responseToJson(new RegisterVoterDtoResponse(WRONG_APARTMENT))),
                () -> assertEquals(server.registerVoter(requestToJson(voter33)), responseToJson(new RegisterVoterDtoResponse(WRONG_LOGIN))),
                () -> assertEquals(server.registerVoter(requestToJson(voter35)), responseToJson(new RegisterVoterDtoResponse(session.getSession(voter35.newVoter()).getToken()))),
                () -> assertEquals(server.registerVoter(requestToJson(voter36)), responseToJson(new RegisterVoterDtoResponse(session.getSession(voter36.newVoter()).getToken()))),
                () -> assertEquals(server.registerVoter(requestToJson(voter37)), responseToJson(new RegisterVoterDtoResponse(session.getSession(voter37.newVoter()).getToken()))),
                () -> assertEquals(server.registerVoter(requestToJson(voter38)), responseToJson(new RegisterVoterDtoResponse(session.getSession(voter38.newVoter()).getToken()))),
                () -> assertEquals(server.registerVoter(requestToJson(voter39)), responseToJson(new RegisterVoterDtoResponse(WRONG_PASSWORD))),
                () -> assertEquals(server.registerVoter(requestToJson(voter40)), responseToJson(new RegisterVoterDtoResponse(WRONG_PASSWORD))),
                () -> assertEquals(server.registerVoter(requestToJson(voter41)), responseToJson(new RegisterVoterDtoResponse(WRONG_PASSWORD))),
                () -> assertEquals(server.registerVoter(requestToJson(voter42)), responseToJson(new RegisterVoterDtoResponse(WRONG_PASSWORD))),
                () -> assertEquals(server.registerVoter(requestToJson(voter43)), responseToJson(new RegisterVoterDtoResponse(WRONG_PASSWORD))),
                () -> assertEquals(server.registerVoter(requestToJson(voter44)), responseToJson(new RegisterVoterDtoResponse(WRONG_PASSWORD))),
                () -> assertEquals(server.registerVoter(requestToJson(voter45)), responseToJson(new RegisterVoterDtoResponse(WRONG_PASSWORD))),
                () -> assertEquals(server.registerVoter(requestToJson(voter46)), responseToJson(new RegisterVoterDtoResponse(NULL_REGISTERVOTERDTOREQUEST))),
                () -> assertEquals(server.registerVoter(requestToJson(voter47)), responseToJson(new RegisterVoterDtoResponse(NULL_REGISTERVOTERDTOREQUEST))),
                () -> assertEquals(server.registerVoter(requestToJson(voter48)), responseToJson(new RegisterVoterDtoResponse(NULL_REGISTERVOTERDTOREQUEST))),
                () -> assertEquals(server.registerVoter(requestToJson(voter50)), responseToJson(new RegisterVoterDtoResponse(NULL_REGISTERVOTERDTOREQUEST))),
                () -> assertEquals(server.registerVoter(requestToJson(voter51)), responseToJson(new RegisterVoterDtoResponse(NULL_REGISTERVOTERDTOREQUEST))),
                () -> assertEquals(server.registerVoter(requestToJson(voter52)), responseToJson(new RegisterVoterDtoResponse(NULL_REGISTERVOTERDTOREQUEST))),
                () -> assertEquals(server.registerVoter(requestToJson(voter53)), responseToJson(new RegisterVoterDtoResponse(WRONG_FIRSTNAME + WRONG_LASTNAME + WRONG_PATRONYMIC + WRONG_STREET + WRONG_HOUSE + WRONG_APARTMENT + WRONG_LOGIN + WRONG_PASSWORD)))
        );
    }

    @Test
    public void testLogoutVoter() throws VoterException {
        RegisterVoterDtoRequest voter1 = new RegisterVoterDtoRequest(randomString(), randomString(),
                null,randomString(), 1, 188, randomString(), "111%111Aa");
        server.registerVoter(gson.toJson(voter1));
        LogoutDtoRequest request = new LogoutDtoRequest(session.getSession(voter1.newVoter()).getToken());
        LogoutDtoResponse response = new LogoutDtoResponse(server.logoutVoter(gson.toJson(request)));
    }
    @Test
    public void testLoginVoter() {
        RegisterVoterDtoRequest voter1 = new RegisterVoterDtoRequest(randomString(), randomString(),
                null,randomString(), 1, 188, "login1111111", "111%111Aa");
        RegisterVoterDtoRequest voter2 = new RegisterVoterDtoRequest(randomString(), randomString(),
                null, randomString(), 1, 188, "login222222", "111%111Aa");
        RegisterVoterDtoRequest voter3 = new RegisterVoterDtoRequest(randomString(), randomString(),
                null, randomString(), 1, 188, "login333333", "111%111Aa");
        server.registerVoter(gson.toJson(voter1));
        server.registerVoter(gson.toJson(voter2));
        server.registerVoter(gson.toJson(voter3));
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
                () -> assertEquals(server.loginVoter(gson.toJson(loginDtoRequest1)), gson.toJson(new LoginDtoResponse(session.getSession(voter1.newVoter()).getToken()))),
                () -> assertEquals(server.loginVoter(gson.toJson(loginDtoRequest2)), gson.toJson(new LoginDtoResponse(session.getSession(voter2.newVoter()).getToken()))),
                () -> assertEquals(server.loginVoter(gson.toJson(loginDtoRequest3)), gson.toJson(new LoginDtoResponse(session.getSession(voter3.newVoter()).getToken()))),
                () -> assertEquals(server.loginVoter(gson.toJson(loginDtoRequest4)), gson.toJson(new LoginDtoResponse(VoterExceptionErrorCode.VOTER_WRONG_PASSWORD.getMessage()))),
                () -> assertEquals(server.loginVoter(gson.toJson(loginDtoRequest5)), gson.toJson(new LoginDtoResponse(WRONG_PASSWORD))),
                () -> assertEquals(server.loginVoter(gson.toJson(loginDtoRequest6)), gson.toJson(new LoginDtoResponse(VoterExceptionErrorCode.VOTER_NOT_FOUND.getMessage()))),
                () -> assertEquals(server.loginVoter(gson.toJson(loginDtoRequest7)), gson.toJson(new LoginDtoResponse(NULL_LOGINDTOREQUEST))),
                () -> assertEquals(server.loginVoter(gson.toJson(loginDtoRequest8)), gson.toJson(new LoginDtoResponse(NULL_LOGINDTOREQUEST))),
                () -> assertEquals(server.loginVoter(gson.toJson(loginDtoRequest9)), gson.toJson(new LoginDtoResponse(WRONG_LOGIN)))
        );
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

    private String requestToJson(RegisterVoterDtoRequest request) {
        return gson.toJson(request);
    }
    private String responseToJson(RegisterVoterDtoResponse response) {
        return gson.toJson(response);
    }

}
