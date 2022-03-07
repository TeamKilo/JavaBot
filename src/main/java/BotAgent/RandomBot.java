package BotAgent;
import org.json.*;

import java.io.*;
import java.net.*;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.mortbay.util.ajax.JSON;
//import org.apache.http.impl.client.HttpClientBuilder;

public class RandomBot {


    void submit_move(JSONObject GameState,String user,String game_id, String session_id)
    {
        try
        {
            if(GameState.get("can_move").toString().equals(user))
            {
             PostMethods post = new PostMethods();
             post.SubmitMove(game_id,session_id,(int)Math.floor(Math.random()*7));
            }
        }
        catch (Exception e)
        {e.printStackTrace();}


    }
    void create_and_join() throws Exception
    {GetMethods get = new GetMethods();
    PostMethods post = new PostMethods();

    String game_id = post.CreateGame("connect_4");
    String user  = "BotRandomm";
    String session_id = post.JoinGame(game_id,user);

    while (true)
    {if(get.GetGameState(game_id).get("stage").toString().equals("in_progress"))
    {

        if(get.GetGameState(game_id).get("can_move").toString().equals("[\""+user+"\"]"))
        {
            PostMethods post_move = new PostMethods();
            if(!get.GetGameState(game_id).get("winners").toString().equals(""))
            post_move.SubmitMove(game_id,session_id,(int)Math.floor(Math.random()*7)+1);
            else return;
        }
    }

        if(get.GetGameState(game_id).get("stage").toString().equals("ended"))
        {
            return;
        }
    }


    }




    public static void main(String[] args) throws Exception{
        RandomBot bot = new RandomBot();
        bot.create_and_join();
    }
}
