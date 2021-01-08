package net.thumbtack.school.elections.server;
import com.google.gson.Gson;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSyntaxException;
import net.thumbtack.school.elections.server.dto.request.LogoutDtoRequest;
import net.thumbtack.school.elections.server.dto.request.RegisterVoterDtoRequest;
import net.thumbtack.school.elections.server.dto.response.RegisterVoterDtoResponse;
import net.thumbtack.school.elections.server.model.Voter;
import net.thumbtack.school.elections.server.service.ServerService;
import net.thumbtack.school.elections.server.service.VoterException;
import net.thumbtack.school.elections.server.service.VoterService;


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
        String s = "Введите данные для регистрации!";
        RegisterVoterDtoRequest request = gson.fromJson(requestJsonString, RegisterVoterDtoRequest.class);
        if (requestJsonString != null && request.isValid()) {
            try {
                s = voterService.registerVoter(new Voter(request));
            } catch (VoterException ex) {
                s = ex.getMessage();
            }
        }
        return gson.toJson(new RegisterVoterDtoResponse(s));
    }
//Если избиратель выполняет операцию выхода (метод logout) с сервера,
// его токен впредь считается недействительным (невалидным).
// При попытке выполнить любой метод с предъявлением этого токена должен возвращаться json с ошибкой.
/*
    public String logout(String logoutJsonString) throws VoterException {
        String s =
        LogoutDtoRequest request = gson.fromJson(logoutJsonString, LogoutDtoRequest.class);
        if (logoutJsonString != null) {
            voterService.logout(request.getToken());
        }
        return gson.toJson(new )
    }
*/

}
