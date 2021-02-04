package net.thumbtack.school.elections.server.dto.response;
import net.thumbtack.school.elections.server.model.Idea;
import java.util.Map;

public class GetAllIdeasDtoResponse {
    private final Map<Idea, Float> ideas;

    public GetAllIdeasDtoResponse(Map<Idea, Float> ideas) {
        this.ideas = ideas;
    }
}
