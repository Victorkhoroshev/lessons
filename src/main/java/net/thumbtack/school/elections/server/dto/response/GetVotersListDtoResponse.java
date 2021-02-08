package net.thumbtack.school.elections.server.dto.response;

import net.thumbtack.school.elections.server.model.Voter;
import java.util.Set;

public class GetVotersListDtoResponse {
    private final Set<Voter> voters;

    public GetVotersListDtoResponse(Set<Voter> voters) {
        this.voters = voters;
    }
}
