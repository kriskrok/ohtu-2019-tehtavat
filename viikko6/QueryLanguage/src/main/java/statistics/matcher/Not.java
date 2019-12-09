package statistics.matcher;

import statistics.Player;

public class Not implements Matcher{

    private Matcher[] matchers;

    public Not(Matcher... matchers) {
        this.matchers = matchers;
    }

    @Override
    public boolean matches(Player p) {
        if (matchers.length < 1) {
            return false;
        }
        return !matchers[0].matches(p);
    }
}
