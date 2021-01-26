package net.thumbtack.school.elections.server.dto.response;

import java.util.Set;

public class GetVotersListDtoResponse {
    private Set<String> voters;

    public GetVotersListDtoResponse(Set<String> voters) {
        setVoters(voters);
    }

    public void setVoters(Set<String> voters) {
        this.voters = voters;
    }

    public Set<String> getVoters() {
        return voters;
    }
}
