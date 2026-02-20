package Wikipedia;

import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.stage.Stage;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.concurrent.CountDownLatch;

import static org.junit.jupiter.api.Assertions.*;

public class WikiUITest {

    @BeforeAll
    static void initJfxRuntime() {
        new JFXPanel();
    }

    @Test
    void updateUI_ShouldPopulateListView() throws Exception {

        CountDownLatch latch = new CountDownLatch(1);

        Platform.runLater(() -> {
            try {
                WikiUI ui = new WikiUI();
                ui.start(new Stage());

                ParsedResult result = new ParsedResult(
                        List.of(
                                new Revision("Alice", "2025-02-19T10:00:00Z")
                        ),
                        false,
                        null
                );

                ui.updateUI(result);

                assertEquals(1, ui.getResultsList().getItems().size());

            } catch (Exception e) {
                fail(e);
            } finally {
                latch.countDown();
            }
        });

        latch.await();
    }
}