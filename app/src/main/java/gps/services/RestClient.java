package gps.services;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

import com.google.gson.Gson;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.IOException;

/**
 * Created by NhatHoang on 3/25/2015.
 */
public class RestClient<T> {
    private ProgressDialog progressdialog;

    private String url;

    private static Context context;

    private static RestClient instance;

    private T postData;

    public static RestClient getInstancePushLocation(Context appContext){
        context = appContext;
        instance = new RestClient("http://www.minh0583.somee.com/service.svc/PushLocation");
        return instance;
    }

    public static RestClient getInstancePushLocation(Context appContext, String url){
        context = appContext;
        instance = new RestClient(url);
        return instance;
    }

    private RestClient(String url){
        this.url = url;
    }

    public void doPost(T data) {
        this.postData = data;

        AsyncTask<Void, Void, Void> task = new AsyncTask<Void, Void, Void>() {
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                progressdialog = new ProgressDialog(context);
                progressdialog.setMessage("Just a moment...");
                progressdialog.show();
            }

            @Override
            protected Void doInBackground(Void... params) {
                try {
                    HttpPost httpPostRequest = new HttpPost(url);
                    String asJson = new Gson().toJson(postData);
                    DefaultHttpClient client = new DefaultHttpClient();
                    httpPostRequest.setHeader("Content-Type","application/json");
                    httpPostRequest.setEntity(new StringEntity(asJson));
                    HttpResponse response = client.execute(httpPostRequest);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                progressdialog.dismiss();
            }
        };

        task.execute((Void[]) null);
    }
}
