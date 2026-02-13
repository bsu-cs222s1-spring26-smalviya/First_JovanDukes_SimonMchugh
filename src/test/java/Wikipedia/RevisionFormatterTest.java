package Wikipedia;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RevisionFormatterTest {

    @Test
    void shouldFormatRevisionCorrectly() {
        Revision revision = new Revision("Jovan", "2024-02-01T10:00:00Z");
        RevisionFormatter formatter = new RevisionFormatter();
        String result = formatter.format(revision, 1);
        assertEquals("1. User: Jovan | Timestamp: 2024-02-01T10:00:00Z", result);
    }
}
