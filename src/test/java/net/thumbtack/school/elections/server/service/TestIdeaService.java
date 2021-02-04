package net.thumbtack.school.elections.server.service;

import net.thumbtack.school.elections.server.Server;
import net.thumbtack.school.elections.server.model.Idea;
import net.thumbtack.school.elections.server.model.Voter;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

public class TestIdeaService {
    private final Server server = new Server();
    private final IdeaService service = new IdeaService();

    @Test
    public void getIdeasTest() throws IOException, ClassNotFoundException, ServerException {
        server.startServer(null);
        Voter voter = getNewVoter();
        List<String> ideaList = new ArrayList<>();
        ideaList.add("1");
        ideaList.add("2");
        ideaList.add("3");
        ideaList.add("4");
        ideaList.add("5");
        service.addAllIdeas(voter, ideaList);
        Idea idea5 = service.getIdea(service.getKey(voter, "5"));
        Idea idea4 = service.getIdea(service.getKey(voter, "4"));
        Idea idea3 = service.getIdea(service.getKey(voter, "3"));
        Idea idea2 = service.getIdea(service.getKey(voter, "2"));
        Idea idea1 = service.getIdea(service.getKey(voter, "1"));
        Map<Idea, Float> sortedMap = service.getIdeas();
        assertAll(
                () ->assertEquals(5f, sortedMap.get(idea5)),
                () ->assertEquals(5f, sortedMap.get(idea4)),
                () ->assertEquals(5f, sortedMap.get(idea3)),
                () ->assertEquals(5f, sortedMap.get(idea2)),
                () ->assertEquals(5f, sortedMap.get(idea1))
        );
        service.estimate(idea5.getKey(), 5, getNewVoter());
        service.estimate(idea4.getKey(), 4, getNewVoter());
        service.estimate(idea3.getKey(), 3, getNewVoter());
        service.estimate(idea2.getKey(), 2, getNewVoter());
        service.estimate(idea1.getKey(), 1, getNewVoter());
        Map<Idea, Float> sortedMap2 = service.getIdeas();
        assertAll(
                () -> assertEquals(5f, sortedMap2.get(idea5)),
                () -> assertEquals(4.5f, sortedMap2.get(idea4)),
                () -> assertEquals(4f, sortedMap2.get(idea3)),
                () -> assertEquals(3.5f, sortedMap2.get(idea2)),
                () -> assertEquals(3f, sortedMap2.get(idea1))
        );
        server.stopServer(null);
    }

    @Test
    public void addIdeaTest() throws IOException, ClassNotFoundException {
        server.startServer(null);
        Voter voter = getNewVoter();
        service.addIdea(voter, randomString());
        assertEquals(1, service.getIdeas().size());
        server.stopServer(null);
    }

    @Test
    public void setIdeaCommunityTest() throws IOException, ClassNotFoundException {
        server.startServer(null);
        Voter voter = getNewVoter();
        Voter voter2 = getNewVoter();
        Voter voter3 = getNewVoter();
        service.addIdea(voter, randomString());
        service.addIdea(voter2, randomString());
        service.addIdea(voter3, randomString());
        List<String> voterList = new ArrayList<>();
        List<String> voterList2 = new ArrayList<>();
        List<String> voterList3 = new ArrayList<>();
        voterList.add(voter.getLogin());
        voterList2.add(voter2.getLogin());
        voterList3.add(voter3.getLogin());
        service.setIdeaCommunity(voter);
        service.setIdeaCommunity(voter3);
        assertEquals(0, service.getAllVotersIdeas(voterList).size());
        assertEquals(1, service.getAllVotersIdeas(voterList2).size());
        assertEquals(0, service.getAllVotersIdeas(voterList3).size());
        server.stopServer(null);
    }
    @Test
    public void estimateTest() throws IOException, ClassNotFoundException, ServerException {
        server.startServer(null);
        Voter voter = getNewVoter();
        Voter voter2 = getNewVoter();
        service.addIdea(voter, "1");
        service.estimate(service.getKey(voter, "1"), 1, voter);
        Idea idea = service.getIdea(service.getKey(voter, "1"));
        assertEquals(5f, idea.getRating());
        try {
            service.estimate(service.getKey(voter, "1"), 0, voter2);
        } catch (ServerException ex) {
            assertEquals(ExceptionErrorCode.RATING_INCORRECT, ex.getErrorCode());
        }
        try {
            service.estimate(service.getKey(voter, "1"), 6, voter2);
        } catch (ServerException ex) {
            assertEquals(ExceptionErrorCode.RATING_INCORRECT, ex.getErrorCode());
        }
        server.stopServer(null);
    }
    @Test
    public void changeRatingTest() throws IOException, ClassNotFoundException, ServerException {
        server.startServer(null);
        Voter voter = getNewVoter();
        Voter voter1 = getNewVoter();
        Voter voter2 = getNewVoter();
        service.addIdea(voter, "1");
        service.addIdea(voter1, "1");
        try {
            service.changeRating(voter1, service.getKey(voter, "1"), 0);
        } catch (ServerException ex) {
            assertEquals(ExceptionErrorCode.RATING_INCORRECT, ex.getErrorCode());
        }
        try {
            service.changeRating(voter1, service.getKey(voter, "1"), 6);
        } catch (ServerException ex) {
            assertEquals(ExceptionErrorCode.RATING_INCORRECT, ex.getErrorCode());
        }
        service.estimate(service.getKey(voter, "1"), 1, voter1);
        service.changeRating(voter, service.getKey(voter, "1"), 5);
        service.changeRating(voter1, service.getKey(voter, "1"), 5);
        service.changeRating(voter2, service.getKey(voter, "1"), 5);
        assertEquals(5f, service.getIdea(service.getKey(voter, "1")).getRating());
        service.setIdeaCommunity(voter);
        service.changeRating(voter1, service.getKey(null, "1"), 1);
        assertEquals(3f, service.getIdea(service.getKey(null, "1")).getRating());
        server.stopServer(null);
    }
    @Test
    public void removeRatingTest() throws IOException, ClassNotFoundException, ServerException {
        server.startServer(null);
        Voter voter = getNewVoter();
        Voter voter1 = getNewVoter();
        Voter voter2 = getNewVoter();
        Voter voter3 = getNewVoter();
        service.addIdea(voter, "1");
        service.estimate(service.getKey(voter, "1"), 2, voter1);
        service.estimate(service.getKey(voter, "1"), 5, voter2);
        service.removeRating(voter1, service.getKey(voter, "1"));
        service.removeRating(voter3, service.getKey(voter, "1"));
        service.removeRating(voter, service.getKey(voter, "1"));
        service.removeRating(voter2, "ss");
        assertEquals(5f, service.getIdea(service.getKey(voter, "1")).getRating());
        server.stopServer(null);
    }

    @Test
    public void removeAllRatingTest() throws IOException, ClassNotFoundException, ServerException {
        server.startServer(null);
        Voter voter = getNewVoter();
        Voter voter1 = getNewVoter();
        Voter voter2 = getNewVoter();
        Voter voter3 = getNewVoter();
        service.addIdea(voter, "1");
        service.addIdea(voter1, "1");
        service.estimate(service.getKey(voter, "1"), 5, voter2);
        service.estimate(service.getKey(voter, "1"), 5, voter2);
        service.estimate(service.getKey(voter, "1"), 1, voter3);
        service.removeAllRating(voter2);
        service.removeAllRating(voter);
        assertEquals(3f, service.getIdea(service.getKey(voter, "1")).getRating());
        server.stopServer(null);
    }
    @Test
    public void getIdeaTest() throws IOException, ClassNotFoundException {
        server.startServer(null);
        Voter voter = getNewVoter();
        service.addIdea(voter, randomString());
        service.addIdea(voter, randomString());
        try {
            service.getIdea("1");
        } catch (ServerException ex) {
            assertEquals(ExceptionErrorCode.IDEA_NOT_FOUND, ex.getErrorCode());
        }
        server.stopServer(null);
    }
    @Test
    public void getAllVotersIdeasTest() throws IOException, ClassNotFoundException {
        server.startServer(null);
        Voter voter = getNewVoter();
        Voter voter2 = getNewVoter();
        service.addIdea(voter, "1");
        service.addIdea(voter, "2");
        service.addIdea(voter2, "3");
        service.addIdea(voter2, "4");
        service.addIdea(voter2, "5");
        service.setIdeaCommunity(voter);
        List<String> logins = new ArrayList<>();
        logins.add(null);
        assertEquals(2, service.getAllVotersIdeas(logins).size());
        server.stopServer(null);
    }
    @Test
    public void getKeyTest() throws IOException, ClassNotFoundException {
        server.startServer(null);
        Voter voter = getNewVoter();
        Voter voter1 = getNewVoter();
        Voter voter2 = getNewVoter();
        service.addIdea(voter, "1");
        service.addIdea(voter1, "2");
        try {
            service.getKey(voter2, "1");
        } catch (ServerException ex) {
            assertEquals(ExceptionErrorCode.IDEA_NOT_FOUND, ex.getErrorCode());
        }
        server.stopServer(null);
    }
    @Test
    public void equalsTest() {
        IdeaService service2 = service;
        assertTrue(service2.equals(service) && service.equals(service2));
        assertFalse(service2.equals(null));
        assertFalse(service2.equals(getNewVoter()));
    }

    @Test
    public void hashCodeTest() {
        IdeaService service2 = service;
        assertTrue(service2.hashCode() == service.hashCode());
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
