package ohtu;

import com.google.gson.Gson;
import java.io.IOException;
import org.apache.http.client.fluent.Request;

import java.time.*;
import java.util.*;
import static java.time.temporal.ChronoUnit.DAYS;



public class Main {
    public static void main(String[] args) throws IOException {
        String url = "https://nhlstatisticsforohtu.herokuapp.com/players";

        String bodyText = Request.Get(url).execute().returnContent().asString();

        Gson mapper = new Gson();
        Player[] players = mapper.fromJson(bodyText, Player[].class);
        Arrays.sort(players, Collections.reverseOrder());

        LocalDate current = LocalDate.now();
        LocalDate christmas = LocalDate.of(current.getYear(), Month.DECEMBER, 25);
        long daysToWait = DAYS.between(current, christmas);


        System.out.println("Days until christmas: " + daysToWait + "\n");
        System.out.println("Players:");
        for (Player player : players) {
            if (player.getNationality().equals("FIN")) {
                System.out.println(player);
            }
        }
    }
}
