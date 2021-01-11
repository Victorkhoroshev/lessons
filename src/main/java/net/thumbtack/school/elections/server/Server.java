package net.thumbtack.school.elections.server;
import com.google.gson.Gson;
import net.thumbtack.school.elections.server.dto.request.LoginDtoRequest;
import net.thumbtack.school.elections.server.dto.request.LogoutDtoRequest;
import net.thumbtack.school.elections.server.dto.request.RegisterVoterDtoRequest;
import net.thumbtack.school.elections.server.dto.request.Session;
import net.thumbtack.school.elections.server.dto.response.LoginDtoResponse;
import net.thumbtack.school.elections.server.dto.response.LogoutDtoResponse;
import net.thumbtack.school.elections.server.dto.response.RegisterVoterDtoResponse;
import net.thumbtack.school.elections.server.model.Voter;
import net.thumbtack.school.elections.server.service.ServerService;
import net.thumbtack.school.elections.server.service.VoterException;
import net.thumbtack.school.elections.server.service.VoterService;
import org.jetbrains.annotations.Nullable;


//Server - класс, принимающий запросы (вызовы методов данного класса). В данном классе определены все сервисы.
// Запросы приходят в методы класса Server в виде json- строки. Сервер возвращает ответ также в виде json-строки.

public class Server {
    private final Gson gson = new Gson();
    private final VoterService voterService = new VoterService();
// Производит всю необходимую инициализацию и запускает сервер.
//savedDataFileName - имя файла, в котором было сохранено состояние сервера.
// Если savedDataFileName == null, восстановление состояния не производится, сервер стартует “с нуля”.
    public void startServer(String savedDataFileName) {
        ServerService.startServer(savedDataFileName);
    }

//Останавливает сервер и записывает все его содержимое в файл сохранения с именем savedDataFileName.
// Если savedDataFileName == null, запись содержимого не производится.
    public void stopServer(String saveDataFileName) {
        ServerService.stopServer(saveDataFileName);
    }

//Регистрирует избирателя на сервере. requestJsonString содержит данные об избирателе , необходимые для регистрации.
// Метод при успешном выполнении возвращает json с единственным элементом “token”.
// Если же команду по какой-то причине выполнить нельзя, возвращает json с элементом “error”
//контроллер проверяет валидность входа
    public String registerVoter(String requestJsonString) {
        String response = "";
        RegisterVoterDtoRequest request = gson.fromJson(requestJsonString, RegisterVoterDtoRequest.class);
        if (!request.requiredFieldsIsNotNull() || requestJsonString == null) {
            response += "Пожалуйста, заполните все данные.";
            return gson.toJson(new RegisterVoterDtoResponse(response));
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
                response = voterService.registerVoter(request.newVoter());
            } catch (VoterException ex) {
                response = ex.getLocalizedMessage();
            }
        }
        return gson.toJson(new RegisterVoterDtoResponse(response));
    }

    public String loginVoter(String loginJsonString) {
        String response = "";
        LoginDtoRequest request = gson.fromJson(loginJsonString, LoginDtoRequest.class);
        if (request == null || !request.requiredFieldsIsNotNull()) {
            response += "Пожалуйста, введите логин и пароль.";
            return gson.toJson(new RegisterVoterDtoResponse(response));
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
                response = voterService.loginVoter(request.getLogin(), request.getPassword());
            } catch (VoterException ex) {
                response = ex.getLocalizedMessage();
            }
        }
        return gson.toJson(new LoginDtoResponse(response));
    }
//Если избиратель выполняет операцию выхода (метод logout) с сервера,
// его токен впредь считается недействительным (невалидным).
// При попытке выполнить любой метод с предъявлением этого токена должен возвращаться json с ошибкой.
    public String logoutVoter(String logoutJsonString) throws VoterException {
        String response = "Что-то пошле не так.";
        LogoutDtoRequest request = gson.fromJson(logoutJsonString, LogoutDtoRequest.class);
        if (request == null || !request.requiredFieldsIsNotNull()) {
            return gson.toJson(new LogoutDtoResponse(response));
        }
        return gson.toJson(new LoginDtoResponse(voterService.logoutVoter(request.getToken())));
    }

}
