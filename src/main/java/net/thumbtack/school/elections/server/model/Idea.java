package net.thumbtack.school.elections.server.model;

import java.util.HashMap;
import java.util.Map;

public class Idea {
    private Voter author;
    private final String textOfIdea;
    private int sum;
    private int count;
    private int rating;
    private Map<Voter, Integer> votedVoters;

    public Idea(Voter author, String idea) {
        setAuthor(author);
        this.textOfIdea = idea;
        sum = 0;
        count = 0;
        setRating(5, author);
        setVotedVoters(new HashMap<>());
    }

    private void setVotedVoters(Map<Voter, Integer> votedVoters) {
        this.votedVoters = votedVoters;
    }

    public Map<Voter, Integer> getVotedVoters() {
        return votedVoters;
    }

    public void setAuthor(Voter author) {
        this.author = author;
    }

    public void setRating(int rating, Voter voter) {
        count++;
        this.rating = (sum + rating) / count;
        sum += sum + rating;
        votedVoters.put(voter, rating);
    }

    public void changeRating(Voter voter , int rating) {
        this.rating = (sum + rating - votedVoters.get(voter)) / count;
        votedVoters.put(voter, rating);
    }

    public void removeRating(Voter voter) {
        count--;
        this.rating = (sum - votedVoters.get(voter)) / count;
        votedVoters.remove(voter);
    }


    public Voter getAuthor() {
        return author;
    }

    public String getTextOfIdea() {
        return textOfIdea;
    }

    public int getRating() {
        return rating;
    }
}
