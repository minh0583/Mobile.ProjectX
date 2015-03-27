package gps.services;

import com.google.gson.Gson;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.IOException;

import gps.model.LocationModel;

/**
 * Created by NhatHoang on 3/25/2015.
 */
public class RestClient {

    private String url;

    private static RestClient instance;

    private LocationModel locationModel;

    public  static  RestClient getInstancePushLocation(){
        instance = new RestClient("http://www.minh0583.somee.com/service.svc/PushLocation");
        return instance;
    }

    public  RestClient(String url){
        this.url = url;
    }

    public void sendHttpPost() throws ClientProtocolException, IOException {
        HttpPost httpPostRequest = new HttpPost(url);

        String asJson = new Gson().toJson(this.locationModel);

        DefaultHttpClient client = new DefaultHttpClient();

        httpPostRequest.setHeader("Content-Type","application/json");

//        List<NameValuePair> pairs = new ArrayList<NameValuePair>();
//        pairs.add(new BasicNameValuePair("JSON", asJson));
//        httpPostRequest.setEntity(new UrlEncodedFormEntity(pairs));
        httpPostRequest.setEntity(new StringEntity(asJson));


        HttpResponse response = client.execute(httpPostRequest);

        int a = response.getStatusLine().getStatusCode();

        int b = a;


    }

    public void pushLocation(LocationModel locationModel){
        this.locationModel = locationModel;
    }
}
