package net.thumbtack.school.elections.server;

import com.google.gson.Gson;
import net.thumbtack.school.elections.server.database.Database;
import net.thumbtack.school.elections.server.dto.request.RegisterVoterDtoRequest;
import net.thumbtack.school.elections.server.dto.response.RegisterVoterDtoResponse;
import net.thumbtack.school.elections.server.model.Voter;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestServer {
    private final Gson gson = new Gson();
    private final Server server = new Server();
    @Test
    public void testRegisterVoter()
    {
        RegisterVoterDtoResponse response1 = new RegisterVoterDtoResponse("1");
        server.registerVoter(gson.toJson(response1));
    }

}
