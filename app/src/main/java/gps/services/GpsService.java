package gps.services;

import gps.common.GpsServiceInterface;
import gps.common.models.LocationModel;

/**
 * Created by ADMIN on 3/24/2015.
 */
public class GpsService implements GpsServiceInterface {

    @Override
    public boolean pushLocation(LocationModel data) {
        return false;
    }
}
