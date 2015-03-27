package gps.model;

import com.google.gson.annotations.SerializedName;

 /**
 * Created by NhatHoang on 3/25/2015.
 */
public class LocationModel {

     @SerializedName("ID")
     public String id;

     @SerializedName("Longitude")
     public float longitude ;

     @SerializedName("Latitude")
     public float latitude;

     @SerializedName("Decription")
     public String decription ;

     @SerializedName("LastChanged")
     public String lastChanged ;

     @SerializedName("LastChangedBy")
     public String lastChangedBy ;


}
