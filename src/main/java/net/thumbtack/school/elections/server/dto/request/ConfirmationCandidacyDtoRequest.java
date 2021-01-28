package net.thumbtack.school.elections.server.dto.request;

import java.util.List;

public class ConfirmationCandidacyDtoRequest {
    private String token;
    private List<String> candidateIdeas;

    public ConfirmationCandidacyDtoRequest(String token, List<String> candidateIdeas) {
        setToken(token);
        setCandidateIdeas(candidateIdeas);
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public List<String> getCandidateIdeas() {
        return candidateIdeas;
    }

    public void setCandidateIdeas(List<String> candidateIdeas) {
        this.candidateIdeas = candidateIdeas;
    }

    public boolean requiredFieldsIsNotNull() {
        return token != null && candidateIdeas != null;
    }
}
