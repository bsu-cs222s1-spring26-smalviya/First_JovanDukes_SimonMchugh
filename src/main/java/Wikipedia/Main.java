package Wikipedia;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter article name: ");
        String articleTitle = scanner.nextLine().trim();

        if (articleTitle.isEmpty()) {
            System.err.println("Error: No article name provided.");
            return;
        }

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
            int limit = Math.min(15, revisions.size());

            for (int i = 0; i < limit; i++) {
                System.out.println(formatter.format(revisions.get(i), i + 1));
            }

        } catch (PageNotFoundException e) {
            System.err.println("Error: Page not found.");
        } catch (IOException e) {
            System.err.println("Error: Network error.");
        }
    }
}
