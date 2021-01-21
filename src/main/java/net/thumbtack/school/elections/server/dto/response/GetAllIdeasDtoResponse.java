package net.thumbtack.school.elections.server.dto.response;

import net.thumbtack.school.elections.server.model.Idea;
import java.util.List;

public class GetAllIdeasDtoResponse {
    private List<Idea> ideas;

    public GetAllIdeasDtoResponse(List<Idea> ideas) {
        this.ideas = ideas;
    }

    public List<Idea> getIdeas() {
        return ideas;
    }

    public void setIdeas(List<Idea> ideas) {
        this.ideas = ideas;
    }
}
