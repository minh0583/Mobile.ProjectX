package gps.activity;

import android.content.Context;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

import com.androidquery.AQuery;
import com.androidquery.callback.AjaxCallback;
import com.androidquery.callback.AjaxStatus;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONObject;
import java.io.InputStream;
import gps.common.Utils;
import gps.common.models.LocationModel;

public class MainActivity extends ActionBarActivity {

    EditText txtDeviceID;
    EditText txtLicenseNumber;
    Button btnActive;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //set up no title
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        renderContent(getApplicationContext(), getWindow().getDecorView().getRootView());

        txtDeviceID = (EditText) findViewById(R.id.txtDeviceID);
        txtLicenseNumber = (EditText) findViewById(R.id.txtLicenseNumber);
        btnActive = (Button)findViewById(R.id.btnActive);

    }

    public void renderContent(Context context, View view){
        AQuery aq = new AQuery(view);
        aq.id(R.id.txtDeviceID).text(Utils.getDeviceID(context));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void activeDevice(View view){
        AQuery aq = new AQuery(view);
        //String deviceId = aq.id(R.id.txtDeviceID).getText().toString();
        //String licenseNumber = aq.id(R.id.txtLicenseNumber).getText().toString();

        JSONObject jsonObject = new JSONObject();
        //jsonObject.putOpt("","");
        //jsonObject.accumulate("DeviceID", "deviceId");
        //jsonObject.accumulate("LicenseNumber", "licenseNumber");

        String url = "http://www.license.somee.com/LicenseService.svc/ActiveDevice";

        aq.post(url, jsonObject, JSONObject.class, new AjaxCallback<JSONObject>(){
            @Override
            public void callback(String url, JSONObject object, AjaxStatus status) {
                //super.callback(url, object, status);
            }
        });
    }



    public void DoPostData(String deviceId, String licenseNumber){
        InputStream inputStream = null;
        String result = "";
        try {

            // 1. create HttpClient
            HttpClient httpclient = new DefaultHttpClient();

            // 2. make POST request to the given URL
            HttpPost httpPost = new HttpPost("http://www.license.somee.com/LicenseService.svc/ActiveDevice");

            String json = "";

            // 3. build jsonObject
            JSONObject jsonObject = new JSONObject();
            jsonObject.accumulate("DeviceID", deviceId);
            jsonObject.accumulate("LicenseNumber", licenseNumber);

            // 4. convert JSONObject to JSON to String
            json = jsonObject.toString();

            // ** Alternative way to convert Person object to JSON string usin Jackson Lib
            // ObjectMapper mapper = new ObjectMapper();
            // json = mapper.writeValueAsString(person);

            // 5. set json to StringEntity
            StringEntity se = new StringEntity(json);

            // 6. set httpPost Entity
            httpPost.setEntity(se);

            // 7. Set some headers to inform server about the type of the content
            httpPost.setHeader("Content-type", "application/json");

            // 8. Execute POST request to the given URL
            HttpResponse httpResponse = httpclient.execute(httpPost);

            // 9. receive response as inputStream
            inputStream = httpResponse.getEntity().getContent();

            //txt.setText("OK");
        } catch (Exception e) {
            //Log.d("InputStream", e.getLocalizedMessage());
            //txt.setText(e.getLocalizedMessage());
            String ab = e.getLocalizedMessage();
        }
    }

    public void abc(View view) {
        LocationModel data = new LocationModel();
        data.decription = "minh 03/24/2015";
        data.latitude = 1;
        data.longitude = 1;

        InputStream inputStream = null;
        String result = "";
        try {

            // 1. create HttpClient
            HttpClient httpclient = new DefaultHttpClient();

            // 2. make POST request to the given URL
            HttpPost httpPost = new HttpPost("http://minh0583.somee.com/service.svc/PushLocation");

            String json = "";

            // 3. build jsonObject
            JSONObject jsonObject = new JSONObject();
            jsonObject.accumulate("Decription", data.decription);
            jsonObject.accumulate("Latitude", data.latitude);
            jsonObject.accumulate("Longitude", data.longitude);

            // 4. convert JSONObject to JSON to String
            json = jsonObject.toString();

            // ** Alternative way to convert Person object to JSON string usin Jackson Lib
            // ObjectMapper mapper = new ObjectMapper();
            // json = mapper.writeValueAsString(person);

            // 5. set json to StringEntity
            StringEntity se = new StringEntity(json);

            // 6. set httpPost Entity
            httpPost.setEntity(se);

            // 7. Set some headers to inform server about the type of the content
            httpPost.setHeader("Accept", "application/json");
            httpPost.setHeader("Content-type", "application/json");

            // 8. Execute POST request to the given URL
            HttpResponse httpResponse = httpclient.execute(httpPost);

            // 9. receive response as inputStream
            inputStream = httpResponse.getEntity().getContent();

            //txt.setText("OK");
        } catch (Exception e) {
            //Log.d("InputStream", e.getLocalizedMessage());
            //txt.setText(e.getLocalizedMessage());
        }
    }
}
