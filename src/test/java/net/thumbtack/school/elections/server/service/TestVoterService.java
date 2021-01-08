package net.thumbtack.school.elections.server.service;
import net.thumbtack.school.elections.server.model.Voter;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

public class TestVoterService {
    @Test
    public void registerVoter() throws VoterException {
        VoterService voterService = new VoterService();
        //firstName
        Voter voter1 = new Voter("Виктор", "Хорошев",
                "Пригородная", 1, 188, randomString(), "111%111Aa");
        Voter voter2 = new Voter("викторр", "Хорошев",
                "Пригородная", 1, 188, randomString(), "111%111Aa");
        Voter voter3 = new Voter("ВИКТОРРР", "Хорошев",
                "Пригородная", 1, 188, randomString(), "111%111Aa");
        Voter voter4 = new Voter("victor", "Хорошев",
                "Пригородная", 1, 188, randomString(), "111%111Aa");
        Voter voter5 = new Voter("Вик тор", "Хорошев",
                "Пригородная", 1, 188, randomString(), "111%111Aa");
        Voter voter6 = new Voter("Вик/тор", "Хорошев",
                "Пригородная", 1, 188, randomString(), "111%111Aa");
        Voter voter66 = new Voter("Вик1тор", "Хорошев",
                "Пригородная", 1, 188, randomString(), "111%111Aa");
        //lastname
        Voter voter7 = new Voter("Андрей", "Хорошев",
                "Пригородная", 1, 188, randomString(), "111%111Aa");
        Voter voter8 = new Voter("Андрей", "хорошевв",
                "Пригородная", 1, 188, randomString(), "111%111Aa");
        Voter voter9 = new Voter("Аддрей", "ХОРОШЕВВВ",
                "Пригородная", 1, 188, randomString(), "111%111Aa");
        Voter voter10 = new Voter("Аддрей", "Khoroshev",
                "Пригородная", 1, 188, randomString(), "111%111Aa");
        Voter voter11 = new Voter("Аддрей", "ХОРО ШЕВ",
                "Пригородная", 1, 188, randomString(), "111%111Aa");
        Voter voter12 = new Voter("Аддрей", "ХОРО.ЕВВВ",
                "Пригородная", 1, 188, randomString(), "111%111Aa");
        Voter voter122 = new Voter("Аддрей", "ХОРО&ЕВВВ",
                "Пригородная", 1, 188, randomString(), "111%111Aa");
        //patronymic
        Voter voter13 = new Voter(randomString(), "Хорошев","Игоревич",
                "Пригородная", 1, 188, randomString(), "111%111Aa");
        Voter voter14 = new Voter(randomString(), "Хорошев","игоревич",
                "Пригородная", 1, 188, randomString(), "111%111Aa");
        Voter voter15 = new Voter(randomString(), "Хорошев","ИГОРЕВИЧ",
                "Пригородная", 1, 188, randomString(), "111%111Aa");
        Voter voter16 = new Voter(randomString(), "Хорошев","Igorevich",
                "Пригородная", 1, 188, randomString(), "111%111Aa");
        Voter voter17 = new Voter(randomString(), "Хорошев","Игоревич1",
                "Пригородная", 1, 188, randomString(), "111%111Aa");
        Voter voter18 = new Voter(randomString(), "Хорошев","Игорев/ич",
                "Пригородная", 1, 188, randomString(), "111%111Aa");
        Voter voter188 = new Voter(randomString(), "Хорошев","Игорев ич",
                "Пригородная", 1, 188, randomString(), "111%111Aa");
        //street
        Voter voter19 = new Voter(randomString(), "Хорошев","Игоревич",
                "Пригородная", 1, 188, randomString(), "111%111Aa");
        Voter voter20 = new Voter(randomString(), "Хорошев","Игоревич",
                "пригородная", 1, 188, randomString(), "111%111Aa");
        Voter voter21 = new Voter(randomString(), "Хорошев","Игоревич",
                "ПРИГОРОДНАЯ", 1, 188, randomString(), "111%111Aa");
        Voter voter22 = new Voter(randomString(), "Хорошев","Игоревич",
                "Prigiridnaya", 1, 188, randomString(), "111%111Aa");
        Voter voter23 = new Voter(randomString(), "Хорошев","Игоревич",
                "Пригор1одная", 1, 188, randomString(), "111%111Aa");
        Voter voter24 = new Voter(randomString(), "Хорошев","Игоревич",
                "Приг ородная", 1, 188, randomString(), "111%111Aa");
        Voter voter25 = new Voter(randomString(), "Хорошев","Игоревич",
                "Пригор*одная", 1, 188, randomString(), "111%111Aa");
        //house
        Voter voter26 = new Voter(randomString(), "Хорошев","Игоревич",
                "Пригородная", 1, 188, randomString(), "111%111Aa");
        Voter voter27 = new Voter(randomString(), "Хорошев","Игоревич",
                "Пригородная", 0, 188, randomString(), "111%111Aa");
        Voter voter28 = new Voter(randomString(), "Хорошев","Игоревич",
                "Пригородная", -13, 188, randomString(), "111%111Aa");
        //apartment
        Voter voter29 = new Voter(randomString(), "Хорошев","Игоревич",
                "Пригородная", 1, 188, randomString(), "111%111Aa");
        Voter voter30 = new Voter(randomString(), "Хорошев","Игоревич",
                "Пригородная", 1, randomString(), "111%111Aa");
        Voter voter31 = new Voter(randomString(), "Хорошев","Игоревич",
                "Пригородная", 1, -1, randomString(), "111%111Aa");
        //login
        Voter voter33 = new Voter(randomString(), "Хорошев","Игоревич",
                "Пригородная", 1, 188, "vic", "111%111Aa");
        //password
        Voter voter35 = new Voter(randomString(), "Хорошев","Игоревич",
                "Пригородная", 1, 188, randomString(), "111%111qQ");
        Voter voter36 = new Voter(randomString(), "Хорошев","Игоревич",
                "Пригородная", 1, 188, randomString(), "111%111*гЖ");
        Voter voter37 = new Voter(randomString(), "Хорошев","Игоревич",
                "Пригородная", 1, 188, randomString(), "1г11%111Q");
        Voter voter38 = new Voter(randomString(), "Хорошев","Игоревич",
                "Пригородная", 1, 188, randomString(), "111q%11Г1");
        Voter voter39 = new Voter(randomString(), "Хорошев","Игоревич",
                "Пригородная", 1, 188, randomString(), "111111Aa");
        Voter voter40 = new Voter(randomString(), "Хорошев","Игоревич",
                "Пригородная", 1, 188, randomString(), "фффф%ффффAa");
        Voter voter41 = new Voter(randomString(), "Хорошев","Игоревич",
                "Пригородная", 1, 188, randomString(), "111%111фa");
        Voter voter42 = new Voter(randomString(), "Хорошев","Игоревич",
                "Пригородная", 1, 188, randomString(), "111%111AФ");
        Voter voter43 = new Voter(randomString(), "Хорошев","Игоревич",
                "Пригородная", 1, 188, randomString(), "111%111 Aa");
        Voter voter44 = new Voter(randomString(), "Хорошев","Игоревич",
                "Пригородная", 1, 188, randomString(), "1");
        Voter voter45 = new Voter(randomString(), "Хорошев","Игоревич",
                "Пригородная", 1, 188, randomString(), "11%?AaфQ");

        assertAll(
                () -> assertEquals(voter1.getToken(), voterService.registerVoter(voter1)),
                () -> assertEquals(voter2.getToken(), voterService.registerVoter(voter2)),
                () -> assertEquals(voter3.getToken(), voterService.registerVoter(voter3)),
                () -> assertEquals(voter7.getToken(), voterService.registerVoter(voter7)),
                () -> assertEquals(voter8.getToken(), voterService.registerVoter(voter8)),
                () -> assertEquals(voter9.getToken(), voterService.registerVoter(voter9)),
                () -> assertEquals(voter13.getToken(), voterService.registerVoter(voter13)),
                () -> assertEquals(voter14.getToken(), voterService.registerVoter(voter14)),
                () -> assertEquals(voter15.getToken(), voterService.registerVoter(voter15)),
                () -> assertEquals(voter19.getToken(), voterService.registerVoter(voter19)),
                () -> assertEquals(voter20.getToken(), voterService.registerVoter(voter20)),
                () -> assertEquals(voter21.getToken(), voterService.registerVoter(voter21)),
                () -> assertEquals(voter26.getToken(), voterService.registerVoter(voter26)),
                () -> assertEquals(voter29.getToken(), voterService.registerVoter(voter29)),
                () -> assertEquals(voter30.getToken(), voterService.registerVoter(voter30)),
                () -> assertEquals(voter35.getToken(), voterService.registerVoter(voter35)),
                () -> assertEquals(voter36.getToken(), voterService.registerVoter(voter36)),
                () -> assertEquals(voter37.getToken(), voterService.registerVoter(voter37)),
                () -> assertEquals(voter38.getToken(), voterService.registerVoter(voter38))

        );
        try {
            voterService.registerVoter(voter4);
            fail();
        } catch (VoterException ex) {
            assertEquals(VoterExceptionErrorCode.VOTER_FIRSTNAME_INVALID, ex.getErrorCode());
        }
        try {
            voterService.registerVoter(voter5);
            fail();
        } catch (VoterException ex) {
            assertEquals(VoterExceptionErrorCode.VOTER_FIRSTNAME_INVALID, ex.getErrorCode());
        }
        try {
            voterService.registerVoter(voter6);
            fail();
        } catch (VoterException ex) {
            assertEquals(VoterExceptionErrorCode.VOTER_FIRSTNAME_INVALID, ex.getErrorCode());
        }
        try {
            voterService.registerVoter(voter66);
            fail();
        } catch (VoterException ex) {
            assertEquals(VoterExceptionErrorCode.VOTER_FIRSTNAME_INVALID, ex.getErrorCode());
        }
        try {
            voterService.registerVoter(voter10);
            fail();
        } catch (VoterException ex) {
            assertEquals(VoterExceptionErrorCode.VOTER_LASTNAME_INVALID, ex.getErrorCode());
        }
        try {
            voterService.registerVoter(voter11);
            fail();
        } catch (VoterException ex) {
            assertEquals(VoterExceptionErrorCode.VOTER_LASTNAME_INVALID, ex.getErrorCode());
        }
        try {
            voterService.registerVoter(voter12);
            fail();
        } catch (VoterException ex) {
            assertEquals(VoterExceptionErrorCode.VOTER_LASTNAME_INVALID, ex.getErrorCode());
        }
        try {
            voterService.registerVoter(voter122);
            fail();
        } catch (VoterException ex) {
            assertEquals(VoterExceptionErrorCode.VOTER_LASTNAME_INVALID, ex.getErrorCode());
        }
        try {
            voterService.registerVoter(voter16);
            fail();
        } catch (VoterException ex) {
            assertEquals(VoterExceptionErrorCode.VOTER_PATRONYMIC_INVALID, ex.getErrorCode());
        }
        try {
            voterService.registerVoter(voter17);
            fail();
        } catch (VoterException ex) {
            assertEquals(VoterExceptionErrorCode.VOTER_PATRONYMIC_INVALID, ex.getErrorCode());
        }
        try {
            voterService.registerVoter(voter18);
            fail();
        } catch (VoterException ex) {
            assertEquals(VoterExceptionErrorCode.VOTER_PATRONYMIC_INVALID, ex.getErrorCode());
        }
        try {
            voterService.registerVoter(voter188);
            fail();
        } catch (VoterException ex) {
            assertEquals(VoterExceptionErrorCode.VOTER_PATRONYMIC_INVALID, ex.getErrorCode());
        }
        try {
            voterService.registerVoter(voter22);
            fail();
        } catch (VoterException ex) {
            assertEquals(VoterExceptionErrorCode.VOTER_STREET_INVALID, ex.getErrorCode());
        }
        try {
            voterService.registerVoter(voter23);
            fail();
        } catch (VoterException ex) {
            assertEquals(VoterExceptionErrorCode.VOTER_STREET_INVALID, ex.getErrorCode());
        }
        try {
            voterService.registerVoter(voter24);
            fail();
        } catch (VoterException ex) {
            assertEquals(VoterExceptionErrorCode.VOTER_STREET_INVALID, ex.getErrorCode());
        }
        try {
            voterService.registerVoter(voter25);
            fail();
        } catch (VoterException ex) {
            assertEquals(VoterExceptionErrorCode.VOTER_STREET_INVALID, ex.getErrorCode());
        }
        try {
            voterService.registerVoter(voter27);
            fail();
        } catch (VoterException ex) {
            assertEquals(VoterExceptionErrorCode.VOTER_HOUSE_INVALID, ex.getErrorCode());
        }
        try {
            voterService.registerVoter(voter28);
            fail();
        } catch (VoterException ex) {
            assertEquals(VoterExceptionErrorCode.VOTER_HOUSE_INVALID, ex.getErrorCode());
        }
        try {
            voterService.registerVoter(voter31);
            fail();
        } catch (VoterException ex) {
            assertEquals(VoterExceptionErrorCode.VOTER_APARTMENT_INVALID, ex.getErrorCode());
        }
        try {
            voterService.registerVoter(voter33);
            fail();
        } catch (VoterException ex) {
            assertEquals(VoterExceptionErrorCode.VOTER_LOGIN_INVALID, ex.getErrorCode());
        }
        try {
            voterService.registerVoter(voter39);
            fail();
        } catch (VoterException ex) {
            assertEquals(VoterExceptionErrorCode.VOTER_PASSWORD_INVALID, ex.getErrorCode());
        }
        try {
            voterService.registerVoter(voter40);
            fail();
        } catch (VoterException ex) {
            assertEquals(VoterExceptionErrorCode.VOTER_PASSWORD_INVALID, ex.getErrorCode());
        }
        try {
            voterService.registerVoter(voter41);
            fail();
        } catch (VoterException ex) {
            assertEquals(VoterExceptionErrorCode.VOTER_PASSWORD_INVALID, ex.getErrorCode());
        }
        try {
            voterService.registerVoter(voter42);
            fail();
        } catch (VoterException ex) {
            assertEquals(VoterExceptionErrorCode.VOTER_PASSWORD_INVALID, ex.getErrorCode());
        }
        try {
            voterService.registerVoter(voter43);
            fail();
        } catch (VoterException ex) {
            assertEquals(VoterExceptionErrorCode.VOTER_PASSWORD_INVALID, ex.getErrorCode());
        }
        try {
            voterService.registerVoter(voter44);
            fail();
        } catch (VoterException ex) {
            assertEquals(VoterExceptionErrorCode.VOTER_PASSWORD_INVALID, ex.getErrorCode());
        }
        try {
            voterService.registerVoter(voter45);
            fail();
        } catch (VoterException ex) {
            assertEquals(VoterExceptionErrorCode.VOTER_PASSWORD_INVALID, ex.getErrorCode());
        }
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
