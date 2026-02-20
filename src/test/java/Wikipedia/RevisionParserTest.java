package Wikipedia;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class RevisionParserTest {

    private final RevisionParser parser = new RevisionParser();

    @Test
    void parse_ShouldParseValidJson() throws Exception {
        String json = """
        {
          "query": {
            "pages": {
              "123": {
                "revisions": [
                  {
                    "user": "Simon",
                    "timestamp": "2025-02-19T10:00:00Z"
                  },
                  {
                    "user": "Jovan",
                    "timestamp": "2025-02-18T09:00:00Z"
                  }
                ]
              }
            }
          }
        }
        """;

        ParsedResult result = parser.parse(json);

        assertFalse(result.isRedirect());
        assertEquals(2, result.getRevisions().size());
        assertEquals("Simon", result.getRevisions().get(0).getUser());
    }

    @Test
    void parse_ShouldDetectRedirect() throws Exception {
        String json = """
        {
          "query": {
            "redirects": [
              { "to": "NewTitle" }
            ],
            "pages": {
              "123": {
                "revisions": []
              }
            }
          }
        }
        """;

        ParsedResult result = parser.parse(json);

        assertTrue(result.isRedirect());
        assertEquals("NewTitle", result.getRedirectTarget());
    }

    @Test
    void parse_ShouldThrowPageNotFoundException() {
        String json = """
        {
          "query": {
            "pages": {
              "123": {
                "missing": ""
              }
            }
          }
        }
        """;

        assertThrows(PageNotFoundException.class,
                () -> parser.parse(json));
    }
}