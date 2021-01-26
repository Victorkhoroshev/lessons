package net.thumbtack.school.elections.server.service;

import net.thumbtack.school.elections.server.model.Idea;
import net.thumbtack.school.elections.server.model.Voter;
import java.io.Serializable;
import java.util.*;

public class IdeaService implements Serializable {
    private final List<Idea> ideas;

    public IdeaService() {
        ideas = new ArrayList<>();
    }

    public Map<Idea, Float> getIdeas() {
        Map<Idea, Float> sortedMap = new TreeMap<>();
        for (Idea idea : ideas) {
            sortedMap.put(idea, idea.getRating());
        }
        return sortedMap;
    }

    public void addIdea(Voter voter, String idea) {
        ideas.add(new Idea(UUID.randomUUID().toString(), voter, idea));
    }

    public void setIdeaCommunity(Voter voter) {
        for (Idea idea: ideas) {
            if (idea.getAuthor() != null && idea.getAuthor().equals(voter)) {
                idea.setAuthor(null);
            }
        }
    }

    public void estimate(String ideaKey, int rating, Voter voter) throws ServerException {
        if (rating < 1 || rating > 5) {
            throw new ServerException(ExceptionErrorCode.RATING_INCORRECT);
        }
        for (Idea idea : ideas) {
            if (idea.getKey().equals(ideaKey) && !idea.getVotedVoters().containsKey(voter)) {
                idea.getVotedVoters().put(voter, rating);
                idea.setSum(idea.getSum() + rating);
                float newRating = (float) idea.getSum() / idea.getVotedVoters().size();
                idea.setRating(newRating);
            }
        }
    }

    public void changeRating(Voter voter, String ideaKey, int rating) throws ServerException {
        if (rating < 1 || rating > 5) {
            throw new ServerException(ExceptionErrorCode.RATING_INCORRECT);
        }
        for (Idea idea : ideas) {
            if (idea.getKey().equals(ideaKey) && idea.getVotedVoters().containsKey(voter) && idea.getAuthor() != voter) {
                idea.setSum(idea.getSum() + rating - idea.getVotedVoters().get(voter));
                idea.getVotedVoters().put(voter, rating);
                float newRating = (float) idea.getSum() / idea.getVotedVoters().size();
                idea.setRating(newRating);
                break;
            }
        }
    }

    public void removeRating(Voter voter, String ideaKey) {
        for(Idea idea : ideas) {
            if (idea.getKey().equals(ideaKey) && idea.getVotedVoters().containsKey(voter) && idea.getAuthor() != voter) {
                idea.setSum(idea.getSum() - idea.getVotedVoters().get(voter));
                idea.getVotedVoters().remove(voter);
                float newRating = (float) idea.getSum() / idea.getVotedVoters().size();
                idea.setRating(newRating);
                break;
            }
        }
    }

    public void removeAllRating(Voter voter) {
        for (Idea idea : ideas) {
            if (idea.getVotedVoters().containsKey(voter) && idea.getAuthor() != voter) {
                idea.setSum(idea.getSum() - idea.getVotedVoters().get(voter));
                idea.getVotedVoters().remove(voter);
                float newRating = (float) idea.getSum() / idea.getVotedVoters().size();
                idea.setRating(newRating);
                idea.getVotedVoters().remove(voter);
            }
        }
    }

    public void addAllIdeas(Voter voter, List<String> ideas) {
        for (String idea : ideas) {
            addIdea(voter, idea);
        }
    }

    public Idea getIdea(String ideaKey) throws ServerException {
        for (Idea idea : ideas) {
            if (idea.getKey().equals(ideaKey)) {
                return idea;
            }
        }
        throw new ServerException(ExceptionErrorCode.IDEA_NOT_FOUND);
    }

    public List<Idea> getAllVotersIdeas(List<String> logins) {
        List<Idea> voterIdeas = new ArrayList<>();
        for (Idea idea : ideas) {
            if (idea.getAuthor() != null && logins.contains(idea.getAuthor().getLogin())) {
                voterIdeas.add(idea);
            }
            if (idea.getAuthor() == null && logins.contains(null)) {
                voterIdeas.add(idea);
            }
        }
        return voterIdeas;
    }

    public String getKey(Voter voter, String text) throws ServerException {
        for (Idea idea : ideas) {
            if (idea.getAuthor() == voter && idea.getTextOfIdea().equals(text)) {
                return idea.getKey();
            }
        }
        throw new ServerException(ExceptionErrorCode.IDEA_NOT_FOUND);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        IdeaService that = (IdeaService) o;
        return ideas.equals(that.ideas);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ideas);
    }
}
