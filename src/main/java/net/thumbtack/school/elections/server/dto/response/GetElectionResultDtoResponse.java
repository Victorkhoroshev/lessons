package net.thumbtack.school.elections.server.dto.response;

import net.thumbtack.school.elections.server.model.Candidate;
import java.util.Set;

public class GetElectionResultDtoResponse {
    private final Set<Candidate> candidateSet;

    public GetElectionResultDtoResponse(Set<Candidate> candidateSet) {
        this.candidateSet = candidateSet;
    }
}
