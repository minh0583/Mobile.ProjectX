package gps.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by NhatHoang on 3/25/2015.
 */
public class LocationObj {

    @SerializedName("ID")
    public String id;

    @SerializedName("Decription")
    public String decription;

    @SerializedName("LastChangedBy")
    public String lastChangedBy;

    @SerializedName("Latitude")
    public float latitude;

    @SerializedName("Longitude")
    public float longitude;
}
