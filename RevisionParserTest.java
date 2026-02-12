package Wikipedia;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class RevisionParserTest {
    @Test
    void testSimpleParse() throws Exception {

        String json = """
        {
          "query": {
            "pages": {
              "123": {
                "revisions": [
                  {
                    "user": "TestUser",
                    "timestamp": "2024-01-01T00:00:00Z"
                  }
                ]
              }
            }
          }
        }
        """;

        RevisionParser parser = new RevisionParser();
        ParsedResult result = parser.parse(json);
        assertEquals(1, result.getRevisions().size());
        assertEquals("TestUser", result.getRevisions().get(0).getUser());
    }
}
