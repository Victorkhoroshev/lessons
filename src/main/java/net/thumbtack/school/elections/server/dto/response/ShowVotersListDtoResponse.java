package net.thumbtack.school.elections.server.dto.response;

import java.util.Set;

public class ShowVotersListDtoResponse {
    private Set<String> voters;

    public ShowVotersListDtoResponse(Set<String> voters) {
        setVoters(voters);
    }

    public void setVoters(Set<String> voters) {
        this.voters = voters;
    }

    public Set<String> getVoters() {
        return voters;
    }
}
