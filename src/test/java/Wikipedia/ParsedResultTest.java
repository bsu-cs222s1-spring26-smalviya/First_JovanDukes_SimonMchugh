package Wikipedia;

import org.junit.jupiter.api.Test;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ParsedResultTest {

    @Test
    void constructor_ShouldStoreValuesCorrectly() {
        Revision rev = new Revision("User1", "2025-02-19T10:00:00Z");
        ParsedResult result = new ParsedResult(
                List.of(rev),
                true,
                "TargetPage"
        );

        assertTrue(result.isRedirect());
        assertEquals("TargetPage", result.getRedirectTarget());
        assertEquals(1, result.getRevisions().size());
        assertEquals("User1", result.getRevisions().get(0).getUser());
    }
}