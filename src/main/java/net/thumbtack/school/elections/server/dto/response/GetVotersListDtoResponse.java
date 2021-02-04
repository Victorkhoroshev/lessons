package net.thumbtack.school.elections.server.dto.response;

import java.util.Set;

public class GetVotersListDtoResponse {
    private final Set<String> voters;

    public GetVotersListDtoResponse(Set<String> voters) {
        this.voters = voters;
    }
}
