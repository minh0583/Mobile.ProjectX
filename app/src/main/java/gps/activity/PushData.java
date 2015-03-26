package gps.activity;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.io.IOException;

import gps.activity.R;
import gps.model.LocationObj;
import gps.services.RestClient;

public class PushData extends ActionBarActivity {

    private ProgressDialog progressdialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_push_data);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_push_data, menu);
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

    public void pushLocation(View view) {

        final RestClient restClient = RestClient.getInstancePushLocation();

        AsyncTask<Void, Void, Void> task = new AsyncTask<Void, Void, Void>() {
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                progressdialog = new ProgressDialog(PushData.this);
                progressdialog.setMessage("Just a moment...");
                progressdialog.show();
            }

            @Override
            protected Void doInBackground(Void... params) {

                LocationObj locationObj = new LocationObj();
                locationObj.decription = "Test Data 1";
                locationObj.licenseNumber = "";
                locationObj.id = "00000000-0000-0000-0000-000000000000";
                locationObj.lastChangedBy = "";
                locationObj.lastChanged = "";
                locationObj.latitude = 18;
                locationObj.longitude = -72;

                restClient.pushLocation(locationObj);

                try {
                    restClient.sendHttpPost();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {

                progressdialog.dismiss();


            }
        };task.execute((Void[]) null);

    }
}
