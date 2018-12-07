package br.inf.ufes.findmydestiny.search;

import br.inf.ufes.findmydestiny.core.view.*;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import java.util.List;
import java.util.ArrayList;

import org.json.JSONObject;
import org.json.JSONArray;
import java.io.Serializable;

// TODO: Unbean this!
@SuppressWarnings("deprecation")
//@ManagedBean
@javax.enterprise.context.SessionScoped @Named
public class GoogleNearbyApi implements Serializable
{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String nextPageToken;
    private JSONArray results;
    
    private List<String> places;
    private int resultsCount = 0;
    
    private String htmlListOfPlaces = null;
    private int placeIndex = 0;
    
    public void buildApi(JSONObject response)
    {
        if (response.getString("status").equals("OK"))
        {
            places = new ArrayList<String>();
            results = response.getJSONArray("results");
            //nextPageToken = response.getString("next_page_token");
            resultsCount = results.length();
            
            for(int i = 0; i < results.length(); i++)
            {
                //GooglePlaceDefinition place = new GooglePlaceDefinition(results.getJSONObject(i));
                JSONObject place = results.getJSONObject(i);
                places.add(place.getString("name"));
                System.out.println(places.get(i));
            }
            
            htmlListOfPlaces = "";
            FindMyDestinyI18n i18n = new FindMyDestinyI18n();
            for (int i = 0; i < resultsCount; i++)
            {
                htmlListOfPlaces += "<li>"+
                    places.get(i)+
                    "</li>";
            }
        }
    }
    
    public String getHtmlListOfPlaces()
    {
        return htmlListOfPlaces;
    }
    
    public void setPlaceIndex(int placeIndex)
    {
        this.placeIndex = placeIndex;
    }
    
    public int getResultsCount()
    {
        return resultsCount;
    }
    
    public GoogleNearbyApi getApi()
    {
    	return new GoogleNearbyApi();
    }
}
