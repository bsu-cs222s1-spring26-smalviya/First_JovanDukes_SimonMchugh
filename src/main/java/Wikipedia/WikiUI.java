package Wikipedia;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.concurrent.Task;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

public class WikiUI extends Application {

    private final WikipediaClient client = new WikipediaClient();
    private final RevisionParser parser = new RevisionParser();
    private final RevisionFormatter formatter = new RevisionFormatter();

    private TextField articleField;
    private Button searchButton;
    private ListView<String> resultsList;
    private Label redirectLabel;

    @Override
    public void start(Stage stage) {
        stage.setTitle("Wikipedia Revision Tracker");

        articleField = new TextField();
        articleField.setPromptText("Enter Wikipedia article name");

        searchButton = new Button("Search");
        searchButton.setOnAction(e -> handleSearch());

        resultsList = new ListView<>();
        redirectLabel = new Label();
        redirectLabel.setStyle("-fx-text-fill: darkblue;");

        HBox topBar = new HBox(10, articleField, searchButton);
        topBar.setPadding(new Insets(10));

        BorderPane root = new BorderPane();
        root.setTop(topBar);
        root.setCenter(resultsList);
        root.setBottom(redirectLabel);
        BorderPane.setMargin(redirectLabel, new Insets(10));

        stage.setScene(new Scene(root, 600, 400));
        stage.show();
    }

    private void handleSearch() {
        String articleTitle = articleField.getText().trim();

        if (articleTitle.isEmpty()) {
            showModal("Input Error", "Please enter an article name.");
            return;
        }

        disableUI(true);
        redirectLabel.setText("");
        resultsList.getItems().clear();

        Task<Void> task = new Task<>() {
            @Override
            protected Void call() throws Exception {
                try {
                    String json = client.fetchRevisions(articleTitle);
                    ParsedResult result = parser.parse(json);
                    Platform.runLater(() -> updateUI(result));
                } catch (PageNotFoundException e) {
                    Platform.runLater(() ->
                            resultsList.setItems(FXCollections.observableArrayList(
                                    "Page not found."
                            )));
                } catch (IOException e) {
                    Platform.runLater(() ->
                            showModal("Network Error",
                                    "A network error occurred.")
                    );
                }
                return null;
            }
            @Override
            protected void succeeded() {
                disableUI(false);
            }
            @Override
            protected void failed() {
                disableUI(false);
            }
        };

        new Thread(task).start();
    }

    private void updateUI(ParsedResult result) {
        if (result.isRedirect()) {
            redirectLabel.setText("Redirected to: " +
                    result.getRedirectTarget());
        }

        List<Revision> revisions = result.getRevisions();

        if (revisions.isEmpty()) {
            resultsList.setItems(FXCollections.observableArrayList(
                    "No revisions found."
            ));
            return;
        }

        int limit = Math.min(15, revisions.size());
        for (int i = 0; i < limit; i++) {
            resultsList.getItems().add(
                    formatter.format(revisions.get(i), i + 1)
            );
        }
    }

    private void disableUI(boolean disable) {
        articleField.setDisable(disable);
        searchButton.setDisable(disable);
    }

    private void showModal(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.initModality(Modality.APPLICATION_MODAL);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
    public static void main(String[] args) {
        launch(args);
    }
}
