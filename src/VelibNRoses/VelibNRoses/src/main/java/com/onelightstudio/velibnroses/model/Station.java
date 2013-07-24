package com.onelightstudio.velibnroses.model;

import com.google.android.gms.internal.bp;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.onelightstudio.velibnroses.Constants;

import org.json.JSONObject;

public class Station {

    public String number;
    public String name;
    public String address;
    public LatLng latLng;
    public double lat;
    public double lng;
    public boolean banking;
    public boolean bonus;
    public String status;
    public String contractName;
    public int bikeStands;
    public int availableBikeStands;
    public int availableBikes;
    public long lastUpdate;

    private Marker marker;

    public Station(JSONObject pStationJSON) {
        number = pStationJSON.optString(Constants.JCD_NUMBER_KEY);
        name = pStationJSON.optString(Constants.JCD_NAME_KEY);
        address = pStationJSON.optString(Constants.JCD_ADDRESS_KEY);
        banking = pStationJSON.optBoolean(Constants.JCD_BANKING_KEY);
        bonus = pStationJSON.optBoolean(Constants.JCD_BONUS_KEY);
        status = pStationJSON.optString(Constants.JCD_STATUS_KEY);
        contractName = pStationJSON.optString(Constants.JCD_CONTRACT_NAME_KEY);
        bikeStands = pStationJSON.optInt(Constants.JCD_BIKE_STANDS_KEY);
        availableBikeStands = pStationJSON.optInt(Constants.JCD_AVAILABLE_BIKE_STANDS_KEY);
        availableBikes = pStationJSON.optInt(Constants.JCD_AVAILABLE_BIKE_KEY);
        lastUpdate = pStationJSON.optLong(Constants.JCD_LAST_UPDATE_KEY);

        JSONObject pos = pStationJSON.optJSONObject(Constants.JCD_POSITION_KEY);
        lat = pos.optDouble(Constants.JCD_LAT_KEY);
        lng = pos.optDouble(Constants.JCD_LNG_KEY);
        latLng = new LatLng(lat, lng);
    }

    public void addMarker(GoogleMap pMap) {
        if (marker == null) {
            marker = pMap.addMarker(new MarkerOptions().position(latLng).title(name).snippet("Bikes : "+availableBikes+"/"+bikeStands));
        }
    }

    public void removeMarker() {
        if (marker != null) {
            marker.remove();
            marker = null;
        }
    }
}