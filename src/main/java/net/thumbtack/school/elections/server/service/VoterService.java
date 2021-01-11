package net.thumbtack.school.elections.server.service;

import net.thumbtack.school.elections.server.daoimpl.DaoImpl;
import net.thumbtack.school.elections.server.model.Voter;
import org.jetbrains.annotations.Nullable;

import java.util.Locale;
import java.util.Objects;

//service - пакет с классами, в которых производятся получение строки запроса от Server,
// преобразование json в соответствующий класс dto-запрос, проверка этого dto (валидация),
// арифметические операции,
//создание экземпляров классов модели на основе этого dto или получение экземпляров классов модели на основе dto-запроса
// и другие действия бизнес-логики.
// Для записи экземпляров модели в базу данных или для получения их из базы данных сервис вызывает dao.
//.. Сервис возвращает ответ серверу в виде текстовой строки в формате json,
//.. создавая для этого экземпляр класса dto-ответ и преобразуя его в json
// работает с бизнес-объектами
public class VoterService {
    private final DaoImpl dao = new DaoImpl();
    private final SessionService session = SessionService.getInstance();
//здесь генерируется токен????

    /**
     *
     * @param voter
     * @return generated token
     * @throws VoterException
     */
    public String registerVoter(Voter voter) throws VoterException {
        dao.save(voter);
        return session.login(voter);
    }

    public String loginVoter(String login, String password) throws VoterException {
        Voter voter = dao.get(login);
        if (!voter.getPassword().equals(password)) {
            throw new VoterException(VoterExceptionErrorCode.VOTER_WRONG_PASSWORD);
        }
        return session.login(voter);
    }

    public String logoutVoter(String token) throws VoterException {
        session.logout(token);
        return "Вы успешно разлогинились.";
    }

/*
    private String takeVoterByToken(String token) {

    }
*/

}
