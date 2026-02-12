package Wikipedia;

import java.util.List;

public class ParsedResult {

    private final List<Revision> revisions;
    private final boolean redirect;
    private final String redirectTarget;

    public ParsedResult(List<Revision> revisions,
                        boolean redirect,
                        String redirectTarget) {
        this.revisions = revisions;
        this.redirect = redirect;
        this.redirectTarget = redirectTarget;
    }

    public List<Revision> getRevisions() {
        return revisions;
    }

    public boolean isRedirect() {
        return redirect;
    }

    public String getRedirectTarget() {
        return redirectTarget;
    }
}
