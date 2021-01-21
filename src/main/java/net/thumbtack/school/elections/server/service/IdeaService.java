package net.thumbtack.school.elections.server.service;

import net.thumbtack.school.elections.server.model.Idea;
import net.thumbtack.school.elections.server.model.Voter;
import java.util.*;

public class IdeaService {
    private final Map<String, Idea> ideas;

    public IdeaService() {
        ideas = new HashMap<>();
    }

    public List<Idea> getIdeas() {
        List<Idea> sortedList = (List<Idea>) ideas.values();
        sortedList.sort(Comparator.comparing(Idea::getRating));
        return sortedList;
    }

    public String addIdea(Voter voter, String idea) {
        String token = UUID.randomUUID().toString();
        ideas.put(token, new Idea(voter, idea));
        return token;
    }

    public void setIdeaCommunity(Voter voter) {
        for (Idea idea: ideas.values()) {
            if (idea.getAuthor().equals(voter)) {
                idea.setAuthor(null);
            }
        }
    }

    public void estimate(String ideasToken, int rating, Voter voter) throws ServerException {
        if (rating < 1 || rating > 5) {
            throw new ServerException(ExceptionErrorCode.RATING_INCORRECT);
        }
        if (!ideas.get(ideasToken).getVotedVoters().containsKey(voter)) {
            ideas.get(ideasToken).setRating(rating, voter);
        }
    }

    public void changeRating(Voter voter, String ideasToken, int rating) throws ServerException {
        if (rating < 1 || rating > 5) {
            throw new ServerException(ExceptionErrorCode.RATING_INCORRECT);
        }
        if (!ideas.get(ideasToken).getAuthor().equals(voter)) {
            ideas.get(ideasToken).changeRating(voter, rating);
        }
    }

    public void removeRating(Voter voter, String ideasToken) {
        if (!ideas.get(ideasToken).getAuthor().equals(voter)) {
            ideas.get(ideasToken).removeRating(voter);
        }
    }

    public void removeAllRating(Voter voter) {
        for (Idea idea : ideas.values()) {
           if (idea.getVotedVoters().containsKey(voter) && !idea.getAuthor().equals(voter)) {
               idea.removeRating(voter);
           }
        }
    }

    public void addAllIdeas(List<Idea> ideas) {
        for(Idea idea : ideas) {
            this.ideas.put(UUID.randomUUID().toString(), idea);
        }
    }

    public Idea getIdea(String ideaToken) {
        return ideas.get(ideaToken);
    }

    public List<String> getAllVotersIdeas(List<String> voters) {
        List<String> voterIdeas = new ArrayList<>();
        for (Idea idea : ideas.values()) {
            if (voters.contains(idea.getAuthor().getLogin())) {
                voterIdeas.add(idea.getTextOfIdea());
            }
        }
        return voterIdeas;
    }
}
