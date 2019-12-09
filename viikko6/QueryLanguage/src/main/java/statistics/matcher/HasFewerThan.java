package statistics.matcher;

import statistics.Player;

import java.lang.reflect.Method;

public class HasFewerThan implements Matcher{

    private int value;
    private String fieldName;
    private Matcher[] matchers;

    public HasFewerThan(int value, String category) {
        this.value = value;
        fieldName = "get" + Character.toUpperCase(category.charAt(0))+category.substring(1, category.length());

    }

    @Override
    public boolean matches(Player p) {
        try {
            Method method = p.getClass().getMethod(fieldName);
            int playersValue = (Integer)method.invoke(p);
            return playersValue < value;

        } catch (Exception erhe) {
            System.out.println(erhe);
            throw new IllegalStateException("Player does not have field "+fieldName.substring(3, fieldName.length()).toLowerCase());
        }
    }
}
