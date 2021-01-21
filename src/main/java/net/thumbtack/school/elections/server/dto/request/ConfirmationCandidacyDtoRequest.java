package net.thumbtack.school.elections.server.dto.request;

import net.thumbtack.school.elections.server.model.Idea;
import java.util.List;

public class ConfirmationCandidacyDtoRequest {
    private String token;
    private List<Idea> candidateIdeas;

    public ConfirmationCandidacyDtoRequest(String token, List<Idea> candidateIdeas) {
        setToken(token);
        setCandidateIdeas(candidateIdeas);
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public List<Idea> getCandidateIdeas() {
        return candidateIdeas;
    }

    public void setCandidateIdeas(List<Idea> candidateIdeas) {
        this.candidateIdeas = candidateIdeas;
    }

    public boolean requiredFieldsIsNotNull() {
        return token != null && candidateIdeas != null;
    }
}
