package gps.common;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.provider.Settings.Secure;
import android.view.View;

/**
 * Created by ADMIN on 3/24/2015.
 */
public class Utils {
    public static String getDeviceID(Context context){
        String str = Secure.getString(context.getContentResolver(), Secure.ANDROID_ID);
        if (str != null){
            str = str.toUpperCase();
        }
        return str;
    }

    public static void showErrorMessage(Context context, String message){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMessage(message);
        builder.setCancelable(true);
        //builder.setIcon(R.drawable.delete);
        builder.setPositiveButton("OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });

        AlertDialog alert = builder.create();
        alert.show();
    }
}
