package gps.common;

import gps.common.models.LocationModel;

/**
 * Created by ADMIN on 3/24/2015.
 */
public interface GpsServiceInterface {
    boolean pushLocation(LocationModel data);

}
