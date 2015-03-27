package gps.activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import com.androidquery.AQuery;
import java.io.IOException;
import gps.common.Utils;
import gps.model.LicenseModel;
import gps.services.RestClient;

public class MainActivity extends ActionBarActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //set up no title
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        renderContent(getApplicationContext(), getWindow().getDecorView().getRootView());
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
        AQuery aq = new AQuery(view.getRootView());
        LicenseModel model = new LicenseModel();
        model.DeviceID = aq.id(R.id.txtDeviceID).getText().toString();
        model.LicenseNumber = aq.id(R.id.txtLicenseNumber).getText().toString();
        RestClient client = RestClient.getInstancePushLocation(MainActivity.this, "http://www.license.somee.com/LicenseService.svc/ActiveDevice");
        client.doPost(model);
    }

}
