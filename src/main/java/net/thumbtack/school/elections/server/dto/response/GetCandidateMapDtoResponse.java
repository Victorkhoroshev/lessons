package net.thumbtack.school.elections.server.dto.response;

import net.thumbtack.school.elections.server.model.Candidate;
import net.thumbtack.school.elections.server.model.Idea;
import java.util.List;
import java.util.Map;

public class GetCandidateMapDtoResponse {
    private final Map<Candidate, List<Idea>> candidateMap;

    public GetCandidateMapDtoResponse(Map<Candidate, List<Idea>> candidateMap) {
        this.candidateMap = candidateMap;
    }
}
