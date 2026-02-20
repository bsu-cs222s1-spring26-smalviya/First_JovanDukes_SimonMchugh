package Wikipedia;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class RevisionFormatterTest {

    @Test
    void format_ShouldReturnCorrectFormattedString() {
        Revision revision = new Revision("Simon", "2025-02-19T10:15:30Z");
        RevisionFormatter formatter = new RevisionFormatter();

        String result = formatter.format(revision, 1);

        assertEquals("1) 2025-02-19, 10:15:30 , by user \"Simon\"", result);
    }

    @Test
    void format_ShouldReplaceTAndZCorrectly() {
        Revision revision = new Revision("Jovan", "2024-01-01T00:00:00Z");
        RevisionFormatter formatter = new RevisionFormatter();

        String result = formatter.format(revision, 5);

        assertTrue(result.contains("2024-01-01, 00:00:00 "));
    }
}