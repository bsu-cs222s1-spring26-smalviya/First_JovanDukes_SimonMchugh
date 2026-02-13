package Wikipedia;

public class RevisionFormatter {

    public String format(Revision revision, int counter) {
        return counter + "  "
                + revision.getTimestamp()
                + "  "
                + revision.getUser();
    }
}
