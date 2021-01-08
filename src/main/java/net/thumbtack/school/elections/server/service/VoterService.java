package net.thumbtack.school.elections.server.service;

import net.thumbtack.school.elections.server.daoimpl.DaoImpl;
import net.thumbtack.school.elections.server.model.Voter;

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
//здесь генерируется токен????
    public String registerVoter(Voter voter) throws VoterException {
        if(!voter.isFirstNameValid()) {
            throw new VoterException(VoterExceptionErrorCode.VOTER_FIRSTNAME_INVALID);
        }
        if (!voter.isLastNameValid()) {
            throw new VoterException(VoterExceptionErrorCode.VOTER_LASTNAME_INVALID);
        }
        if (!voter.isPatronymicValid()) {
            throw new VoterException(VoterExceptionErrorCode.VOTER_PATRONYMIC_INVALID);
        }
        if (!voter.isStreetValid()) {
            throw new VoterException(VoterExceptionErrorCode.VOTER_STREET_INVALID);
        }
        if (!voter.isHouseValid()) {
            throw new VoterException(VoterExceptionErrorCode.VOTER_HOUSE_INVALID);
        }
        if (!voter.isApartmentValid()) {
            throw new VoterException(VoterExceptionErrorCode.VOTER_APARTMENT_INVALID);
        }
        if (!voter.isLoginValid()) {
            throw new VoterException(VoterExceptionErrorCode.VOTER_LOGIN_INVALID);
        }
        if (!voter.isPasswordValid()) {
            throw new VoterException(VoterExceptionErrorCode.VOTER_PASSWORD_INVALID);
        }
        return dao.save(voter);
    }

    public String logout(String token) throws VoterException {
        return dao.update(dao.get(token), null);
    }

/*
    private String takeVoterByToken(String token) {

    }
*/

}
