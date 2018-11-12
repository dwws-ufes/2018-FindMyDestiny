package find_my_destiny;

import java.util.List;
import java.util.ArrayList;

import org.json.JSONObject;
import org.json.JSONArray;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.inject.Named;

// Wraper for Google places API. This extracts its JSON contents as being an Java Object
@SuppressWarnings({"deprecation", "unused"})
@ManagedBean
@SessionScoped
@Named
public class GooglePlacesSearchApi {
	private ArrayList<JSONObject> addressComponents;
	
    private JSONObject geometry;
    private JSONObject location;
    private float lat;
    private float lng;
    private JSONObject viewport;
    private JSONObject northeast;
    private float northeastLat;
    private float northeastLng;
    private JSONObject southwest;
    private float southwestLat;
    private float southwestLng;
    
    private JSONObject photos;
    private JSONObject types;
	
	private String adr_address;
    private String formattedAddress = null;
    private String icon;
    private String id;
    private String name;
    private String placeId;
    private String reference;
    private String scope;
    private String url;
    private String vicinity;
	
    private int utc_offset;
    
	private float latitude = 0.0f;
	private float longitude = 0.0f;
	
    public void buildApi(JSONObject response)
    {
        if (response.getString("status").equals("OK"))
        {
            JSONObject result = (JSONObject)response.get("result");
            getAddressComponents(result);
            getGeometry(result);
            
            formattedAddress = result.getString("formatted_address");
        }
        else
        {
            System.out.println("Error trying to get api data");
            
        }
    }
    
    public List<JSONObject> getAddressComponents(JSONObject response)
    {
        addressComponents = new ArrayList<JSONObject>();
        JSONArray array = response.getJSONArray("address_components");
        for (int i = 0; i < array.length(); i++)
        {
            addressComponents.add(array.getJSONObject(i));
        }
        
        return addressComponents;
    }
	
    
    public void getGeometry(JSONObject response)
    {
        geometry = (JSONObject)response.get("geometry");
        location = (JSONObject)geometry.get("location");
        lat = location.getFloat("lat");
        lng = location.getFloat("lng");
        
        viewport = (JSONObject)geometry.get("viewport");
        northeast = (JSONObject)viewport.get("northeast");
        northeastLat = northeast.getFloat("lat");
        northeastLng = northeast.getFloat("lng");
        
        southwest = (JSONObject)viewport.get("southwest");
        southwestLat = southwest.getFloat("lat");
        southwestLng = southwest.getFloat("lng");
    }
    
    
    public String getFormattedAddress()
    {
        return formattedAddress;
    }
    public float getLatitude()
    {
        return lat;
    }
    public float getLongitude()
    {
        return lng;
    }
    
    public String toString()
    {
        return ("place name:" + formattedAddress + 
                "latitude:" + lat + "longitude:" + lng);
    }
}
