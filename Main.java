package Wikipedia;

import java.io.IOException;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        if (args.length == 0) {
            System.err.println("Error: No article name provided.");
            return;
        }

        String articleTitle = String.join(" ", args);
        WikipediaClient client = new WikipediaClient();
        RevisionParser parser = new RevisionParser();
        RevisionFormatter formatter = new RevisionFormatter();

        try {
            String json = client.fetchRevisions(articleTitle);
            ParsedResult result = parser.parse(json);

            if (result.isRedirect()) {
                System.out.println("Redirected to " + result.getRedirectTarget());
            }

            List<Revision> revisions = result.getRevisions();
            int counter = 1;
            for (Revision revision : revisions) {
                System.out.println(formatter.format(revision, counter));
                counter++;
            }

        } catch (PageNotFoundException e) {
            System.err.println("Error: Page not found.");
        } catch (IOException e) {
            System.err.println("Error: Network error.");
        }
    }
}

