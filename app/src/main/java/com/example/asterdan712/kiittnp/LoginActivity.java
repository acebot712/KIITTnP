package com.example.asterdan712.kiittnp;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.ExecutionException;

/**
 * Created by skmishra on 9/12/2017.
 */
public class LoginActivity extends Activity {


    EditText login;
    EditText pwd;
    private String result;

    public class DownloadTask extends AsyncTask<String,Void,String> {

        @Override
        protected String doInBackground(String... urls) {

            String result = "";
            URL url;
            HttpURLConnection urlConnection;

            try{
                url = new URL(urls[0]);
                urlConnection = (HttpURLConnection)url.openConnection();
                InputStream in = urlConnection.getInputStream();
                InputStreamReader reader = new InputStreamReader(in);
                int data = reader.read();

                while (data != -1){
                    char current = (char) data;
                    result += current;
                    data = reader.read();
                }
                return result;
            }catch (Exception e){
                e.printStackTrace();
            }
            return null;
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        login=(EditText)findViewById(R.id.username);
        pwd=(EditText)findViewById(R.id.pwd);

    }
    public void login(View v)
    {
        String usernameX=login.getText().toString();
        String pwd=login.getText().toString();
        try {
            DownloadTask task=new DownloadTask();
                    result = task.execute("http://127.0.0.1/usr/login.php?username="+usernameX+"&pwd="+pwd).get();
            JSONObject mJSON=new JSONObject(result);
            if(mJSON.getBoolean("status"))
            {
                Intent i=new Intent(this,MainActivity.class);
                startActivity(i);
            }else
            {
                Toast.makeText(this,"Wrong Password",Toast.LENGTH_LONG).show();

            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
