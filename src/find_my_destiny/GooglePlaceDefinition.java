package find_my_destiny;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.json.JSONObject;
import org.json.JSONArray;

@SuppressWarnings("deprecation")
@ManagedBean
@SessionScoped
public class GooglePlaceDefinition
{
    private JSONObject geometry;
    private JSONObject location;
    private float lat;
    private float lng;
    private String name;
    private JSONArray photos;
    private JSONArray types;
    
    private int rating;
    private String vicinity;
    
    public GooglePlaceDefinition(JSONObject jsonPlace)
    {
        geometry = jsonPlace.getJSONObject("geometry");
        location = (JSONObject)geometry.get("location");
        lat = location.getFloat("lat");
        lng = location.getFloat("lng");
        name = jsonPlace.getString("name");
        types = (JSONArray)jsonPlace.get("types");
        
        // In the case it is actually an attraction, not the place itself
        if (!((String)types.get(0)).equals("locality"))
        {
            //rating = jsonPlace.getInt("rating");
            vicinity = jsonPlace.getString("vicinity");
            //photos = jsonPlace.getJSONArray("photos");
        }
        
    }
    
    public float getLat()
    {
        return lat;
    }
    public float getLng()
    {
        return lng;
    }
    public String getName()
    {
        return name;
    }
    public String toString()
    {
        return ("name: "+name+
                "\nlatitude: "+lat+
                "\nlongitude: "+lng);
    }
}