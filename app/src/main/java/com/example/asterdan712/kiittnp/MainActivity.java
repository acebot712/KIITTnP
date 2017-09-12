package com.example.asterdan712.kiittnp;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {

    ArrayList<String> noticeURLs = new ArrayList<>();
    ArrayList<String> noticeDesc = new ArrayList<>();
    ArrayList<CustomObject> objects = new ArrayList<>();

    public class DownloadTask extends AsyncTask<String,Void,String>{

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
        setContentView(R.layout.activity_main);

        DownloadTask task = new DownloadTask();
        String result;
        Log.e("My Change ","Cool!");
        ListView myListView = (ListView) findViewById(R.id.myListView);

        try {
            result = task.execute("http://kiittnp.in/usr/notice.php").get();


            Pattern p = Pattern.compile("<h3>(.*?)</h3>");
            Matcher m = p.matcher(result);

            while (m.find()){

                noticeURLs.add(m.group(1));
            }

            p = Pattern.compile("<b>(.*?)</b>");
            m = p.matcher(result);

            while (m.find()){

                noticeDesc.add(m.group(1));
            }


            Log.i("Contents of URL",result);
        }catch (InterruptedException e){
            e.printStackTrace();
        }catch (ExecutionException e){
            e.printStackTrace();
        }

     Log.e("Notif c",noticeURLs.toString());




        if(noticeURLs.size()>0) {
            for (int i = 0; i < noticeURLs.size(); i++) {


                CustomObject mObs=new CustomObject();
                mObs.setNoticeDesc(noticeDesc.get(i));
                mObs.setNoticeURL(noticeURLs.get(i));

                objects.add(mObs);


                 }
        }else{


        }

        CustomAdapter customAdapter = new CustomAdapter(this,objects);
        myListView.setAdapter(customAdapter);
    }
}
