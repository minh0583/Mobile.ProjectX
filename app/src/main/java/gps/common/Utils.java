package gps.common;

import android.content.Context;
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

}
