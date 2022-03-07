package BotAgent;
import org.json.*;

import java.io.*;
import java.net.*;

public class GetMethods {

    void ListGames() throws Exception //MERGEEEE
    {
        StringBuilder result = new StringBuilder();
        URL url = new URL("https://team-kilo-server.herokuapp.com/api/list-games");
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        try {
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(conn.getInputStream()));
            for (String line; (line = reader.readLine()) != null; ) {
                result.append(line);
            }
        }
        catch (Exception e)
        {e.printStackTrace();}
        System.out.println(result.toString());
    }

    JSONObject GetGameState (String game_id) throws Exception
    {
        StringBuilder result = new StringBuilder();
        URL url = new URL("https://team-kilo-server.herokuapp.com/api/"+game_id+"/get-state");
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        try {
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(conn.getInputStream()));
            for (String line; (line = reader.readLine()) != null; ) {
                result.append(line);
            }
        }
        catch (Exception e)
        {e.printStackTrace();}
        //System.out.println(result.toString());
        return new JSONObject(result.toString());
    }

}
