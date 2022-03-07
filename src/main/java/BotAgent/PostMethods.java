package BotAgent;

import org.json.JSONArray;
import org.json.JSONObject;
import org.mortbay.util.ajax.JSON;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

public class PostMethods {


    String CreateGame(String game)//MERGEE
    {
        try {
            URL url = new URL("https://team-kilo-server.herokuapp.com/api/create-game");
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setDoOutput(true);
            con.setDoInput(true);
            con.setUseCaches(false);
            con.setRequestProperty("Content-Type", "application/json");
            con.setRequestProperty("Accept", "application/json");
            con.setRequestMethod("POST");
            con.connect();
            JSONObject jsonInputString = new JSONObject();
            jsonInputString.put("game_type",game);
            OutputStream os = con.getOutputStream();
            OutputStreamWriter osw = new OutputStreamWriter(os, "UTF-8");
            osw.write(jsonInputString.toString());
            osw.flush();
            osw.close();
            StringBuilder result = new StringBuilder();
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(con.getInputStream()));
            for (String line; (line = reader.readLine()) != null; ) {
                result.append(line);
            }
            con.disconnect();
            JSONObject obj = new JSONObject (result.toString());
            return obj.get("game_id").toString();
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }
    String JoinGame(String game_id,String user)
    {
        try {
            URL url = new URL("https://team-kilo-server.herokuapp.com/api/"+game_id+"/join-game");
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setDoOutput(true);
            con.setDoInput(true);
            con.setUseCaches(false);
            con.setRequestProperty("Content-Type", "application/json");
            con.setRequestProperty("Accept", "application/json");
            con.setRequestMethod("POST");
            con.connect();

            JSONObject jsonInputString = new JSONObject();
            jsonInputString.put("username",user);
            OutputStream os = con.getOutputStream();
            OutputStreamWriter osw = new OutputStreamWriter(os,"UTF-8");
            osw.write(jsonInputString.toString());
            osw.flush();
            osw.close();

            StringBuilder result = new StringBuilder();
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(con.getInputStream()));
            for (String line; (line = reader.readLine()) != null; ) {
                result.append(line);
            }
            JSONObject obj = new JSONObject(result.toString());
            con.disconnect();
            return obj.getString("session_id");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";

    }


    void SubmitMove(String game_id, String session_id, int column)
    {
        try {
            URL url = new URL("https://team-kilo-server.herokuapp.com/api/"+game_id+"/submit-move");
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setDoOutput(true);
            con.setDoInput(true);
            con.setUseCaches(false);
            con.setRequestProperty("Content-Type", "application/json");
            con.setRequestProperty("Accept", "application/json");
            con.setRequestMethod("POST");
            con.connect();

            JSONObject jsonInputString = new JSONObject();
            JSONObject nest = new JSONObject();
            nest.put("game_type", "connect_4");
            nest.put("column",column);
            jsonInputString.put("session_id",session_id);
            jsonInputString.put("payload", nest);


            OutputStream os = con.getOutputStream();
            OutputStreamWriter osw = new OutputStreamWriter(os,"UTF-8");
            osw.write(jsonInputString.toString());
            osw.flush();
            osw.close();
            StringBuilder result = new StringBuilder();
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(con.getInputStream()));
            for (String line; (line = reader.readLine()) != null; ) {
                result.append(line);
            }
            con.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
