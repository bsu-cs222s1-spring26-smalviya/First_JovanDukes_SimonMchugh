package Wikipedia;

public class RevisionFormatter {

    public String format(Revision revision, int counter) {
        String preformattedString = "%d) %s, by user \"%s\"";
        var timestamp = formatTimestampString(revision.getTimestamp());
        var user = revision.getUser();
        return String.format(preformattedString, counter, timestamp, user);
    }

    private String formatTimestampString(String text) {
        text = text.replace("T", ", ");
        text = text.replace("Z", " ");
        return text;
    }
}
