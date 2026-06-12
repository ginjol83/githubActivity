import org.ginjol.githubactivity.ConnectionGithub;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ConnectionGithubTest {

    @Test
    @DisplayName("Should return if user can connect to github")
    public void testConnectToGithub() {
        String userName = "ginjol83";
        ConnectionGithub connection = new ConnectionGithub(userName);
        assertEquals(userName, connection.connect().get(0).getActor().getLogin());
    }

}