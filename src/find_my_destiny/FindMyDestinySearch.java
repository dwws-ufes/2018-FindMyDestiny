package find_my_destiny;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.json.JSONArray;
import org.json.JSONObject;

@SuppressWarnings("deprecation")
@ManagedBean
@SessionScoped
public class FindMyDestinySearch 
{
	private String destinationQuery = null;
	
	private String placeSearchAPIUrl = "https://maps.googleapis.com/maps/api/place/findplacefromtext/";
	
	private String placeDetailsAPIUrl = "https://maps.googleapis.com/maps/api/place/details/";
			
	private String retrivalFormat = "json";
	private String myGoogleAPIKey = "AIzaSyDvbyVqbMLuaj-GB2GUfQKdU1SapIp6Dao";
	
	private String placeId = null;
	
	public String getDestinationQuery()
	{
		return destinationQuery;
	}
	public void setDestinationQuery(String destinationQuery)
	{
		this.destinationQuery = destinationQuery;
	}
	
	// Search for destination. Do not return anything in case it can't find destination
	public boolean searchDestination()
	{	
		boolean searchStatus = false;
		
		try
		{
			JSONObject json = fetchJSONDataFromAPI(placeSearchAPIUrl + retrivalFormat+ "?key=" + myGoogleAPIKey + "&input=" + destinationQuery + "&inputtype=textquery");
			
			if (json != null && json.getString("status").equals("OK"))
			{
				searchStatus = true;
				JSONArray destinationsArray = (JSONArray) json.get("candidates");
				JSONObject destinationsObject = (JSONObject)destinationsArray.get(0);
				placeId = destinationsObject.getString("place_id");
				System.out.println("Destiny Id: "+placeId);
				
				json = fetchJSONDataFromAPI(placeDetailsAPIUrl + retrivalFormat+ "?placeid=" + placeId + "&key="+myGoogleAPIKey);
				if (json.getString("status").equals("OK"))
				{
					json = (JSONObject)json.get("result");
					destinationsArray = (JSONArray)json.get("address_components");
					JSONObject Locality = (JSONObject)destinationsArray.get(0);
					String CityName = Locality.getString("long_name");
					System.out.println("Nome da cidade: "+CityName);
				}
			}
			else
			{
				searchStatus = false;
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return searchStatus;
	}
	
	private JSONObject fetchJSONDataFromAPI(String UrlToFetchFrom)
	{
		StringBuilder stringBuilder = new StringBuilder();
		
		try
		{
			URL url = new URL(UrlToFetchFrom);
			BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream(), "UTF-8"));
			for (String line; (line = reader.readLine()) != null;) 
			{
				stringBuilder.append(line);
			}
		}
		
		catch(Exception e)
		{
			return null;
		}
		
		return new JSONObject(stringBuilder.toString()); 
	}
}
