package statistics;

import java.util.*;

import statistics.matcher.*;

public class QueryBuilder {
    Matcher matcher;
    ArrayList<Matcher> matchers;
    Map<Integer, String> playingIn;

    public QueryBuilder() {
        matchers = new ArrayList<>();
        matcher = new All();
        playingIn = new HashMap<>();
    }

    public Matcher build() {
        Matcher[] args = new Matcher[matchers.size()];
        args = matchers.toArray(args);

        return new And(args);
    }

    public QueryBuilder playsIn(String team) {
        matchers.add(new PlaysIn(team));
        return this;
    }

    public QueryBuilder hasAtLeast(int value, String category) {
        matchers.add(new HasAtLeast(value, category));
        return this;
    }

    public QueryBuilder hasFewerThan(int value, String category) {
        matchers.add(new HasFewerThan(value, category));
        return this;
    }

}
