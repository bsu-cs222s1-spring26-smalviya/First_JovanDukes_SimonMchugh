package Wikipedia;

import com.jayway.jsonpath.Configuration;
import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.Option;
import com.jayway.jsonpath.ReadContext;

import java.util.ArrayList;
import java.util.List;

public class RevisionParser {

    public ParsedResult parse(String json) throws PageNotFoundException {
        Configuration config = Configuration.defaultConfiguration()
                .addOptions(Option.SUPPRESS_EXCEPTIONS);
        ReadContext ctx = JsonPath.using(config).parse(json);

        List<String> redirects = ctx.read("$.query.redirects[*].to");
        if (redirects == null) {
            redirects = List.of();
        }

        boolean isRedirect = !redirects.isEmpty();
        String redirectTarget = isRedirect ? redirects.get(0) : null;
        List<Object> missing = ctx.read("$.query.pages.*.missing");
        if (missing != null && !missing.isEmpty()) {
            throw new PageNotFoundException();
        }

        List<String> users =
                ctx.read("$.query.pages.*.revisions[*].user");

        List<String> timestamps =
                ctx.read("$.query.pages.*.revisions[*].timestamp");

        if (users == null || timestamps == null) {
            throw new PageNotFoundException();
        }

        List<Revision> revisions = new ArrayList<>();

        for (int i = 0; i < users.size(); i++) {
            revisions.add(
                    new Revision(users.get(i), timestamps.get(i))
            );
        }

        return new ParsedResult(revisions, isRedirect, redirectTarget);
    }
}
