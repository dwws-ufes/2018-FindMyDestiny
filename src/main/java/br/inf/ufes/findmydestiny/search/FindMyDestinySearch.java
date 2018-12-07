package br.inf.ufes.findmydestiny.search;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.net.URL;
import java.net.URLEncoder;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.security.PermitAll;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.Stateless;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Model;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.QueryExecutionFactory;
import org.apache.jena.query.QuerySolution;
import org.apache.jena.query.ResultSet;
import org.apache.jena.rdf.model.Literal;
import org.json.JSONArray;
import org.json.JSONObject;

import br.inf.ufes.findmydestiny.core.application.LoginMB;
import br.inf.ufes.findmydestiny.core.controller.LoginController;
import br.inf.ufes.findmydestiny.core.domain.TourismPackage;
import br.inf.ufes.findmydestiny.core.domain.User;
import br.inf.ufes.findmydestiny.core.persistence.TourismPackageDAO;



@SuppressWarnings({"deprecation", "unused"})
//@ManagedBean
@javax.enterprise.context.SessionScoped @Named
public class FindMyDestinySearch implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String destinationQuery = null;
	private String placeSearchAPIUrl = "https://maps.googleapis.com/maps/api/place/findplacefromtext/";
	private String placeDetailsAPIUrl = "https://maps.googleapis.com/maps/api/place/details/";
    private String nearbyApiUrl = "https://maps.googleapis.com/maps/api/place/nearbysearch/";
	private String retrivalFormat = "json";
	private String myGoogleAPIKey = "AIzaSyDvbyVqbMLuaj-GB2GUfQKdU1SapIp6Dao";
	private String placeId = null;
	private float latitude  = 0.0f;
	private float longitude = 0.0f;
	private String placeName = null;
	private String cityInfo = null;
	private String thumbnail = null;
	private List<String> tourpkgs;
	private String tourpkg;
	private String username;
	
	@Inject
	private LoginController loginc;
	
	@PostConstruct
	public void init() {
		username = loginc.getUsername();
	}
	
	

	
	public String getUsername() {
		return username;
	}




	public void setUsername(String username) {
		this.username = username;
	}




	public String getThumbnail() {
		return thumbnail;
	}

	public void setThumbnail(String thumbnail) {
		this.thumbnail = thumbnail;
	}

	public String getCityInfo() {
		return cityInfo;
	}
    
	public void setCityInfo(String cityInfo) {
		this.cityInfo = cityInfo;
	}

	@Inject
    private GooglePlacesSearchApi apiWraper;
    @Inject
    private GoogleNearbyApi nearbyApi;
    
    private int resultsCount = 0;
    
    private String htmlListOfPlaces = null;
	
    public String getDestinationQuery()
	{
		return destinationQuery;
	}
	public void setDestinationQuery(String destinationQuery)
	{
		this.destinationQuery = destinationQuery;
	}
	
	public float getLatitude()
	{
        if (apiWraper != null)
        {
            latitude = apiWraper.getLatitude();
        }
		
        return latitude;
	}
	
	public float getLongitude()
	{
        if (apiWraper != null)
        {
            longitude = apiWraper.getLongitude();
        }
        
        return longitude;
	}
    
    public String getPlaceName()
    {
        placeName = apiWraper.getFormattedAddress();
        return placeName;
    }
    
    //Get info with Jena:
    public void returnCityInfo(String temp) {
    	try {
			cityInfo = "Sem descrição";
			thumbnail = null;
    		System.out.println("temp: "+temp);
    		String query = "PREFIX dbo: <http://dbpedia.org/ontology/> \nPREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#> \n" +
    		"SELECT ?desc ?thumbs " +
    		"WHERE { ?x a dbo:Place ; " +
    		"rdfs:label " + "\"" + temp + "\"" + "@en ; " +
    		"dbo:thumbnail ?thumbs ; " +
    		"rdfs:comment ?desc . " +
    		"FILTER (langMatches(lang(?desc), \"PT\")) " +
    		"} LIMIT 2";
    		System.out.println(query);
    		QueryExecution queryExecution = QueryExecutionFactory.sparqlService("http://dbpedia.org/sparql", query);
    		ResultSet result = queryExecution.execSelect();
    		
    		if(result.hasNext()) {
    			QuerySolution qs = result.next();
    			cityInfo = qs.getLiteral("desc").getString();         		
     			thumbnail = qs.getResource("thumbs").getURI();
    		}
    		
    		System.out.println(thumbnail);
    		System.out.println("cityInfo: "+cityInfo+" --------- destQuery: "+temp);
    		
    	} catch(Exception e) {
    		e.printStackTrace();
    	}
    	
    	
    	
    	
    }
	
	// Search for destination. Do not return anything in case it can't find destination
	public void searchDestination()
	{	
		try
		{
			String temp = destinationQuery;
			destinationQuery = URLEncoder.encode(destinationQuery,"UTF-8");
			JSONObject json = fetchJSONDataFromAPI(placeSearchAPIUrl + retrivalFormat+ "?key=" + myGoogleAPIKey + "&input=" + destinationQuery + "&inputtype=textquery");
			System.out.println("query: "+ destinationQuery);
			
			if (json != null && json.getString("status").equals("OK"))
			{
				JSONArray destinationsArray = (JSONArray) json.get("candidates");
				JSONObject destinationsObject = (JSONObject)destinationsArray.get(0);
				placeId = destinationsObject.getString("place_id");
				System.out.println("Destiny Id: "+placeId);
				
				json = fetchJSONDataFromAPI(placeDetailsAPIUrl + retrivalFormat+ "?placeid=" + placeId + "&key="+myGoogleAPIKey);
                
                apiWraper.buildApi(json);
                System.out.println(apiWraper.toString());
                
                String urlBuild = nearbyApiUrl+retrivalFormat+
                    "?key="+myGoogleAPIKey+
                    "&location="+apiWraper.getLatitude()+","+apiWraper.getLongitude()+
                    "&radius=10000"+
                    "&rankedby=prominence"+
                    "&type=park|museum|movie_theater|casino|city_hall|shopping_mall|night_club|stadium|amusement_park|aquarium";
                json = fetchJSONDataFromAPI(urlBuild);
                nearbyApi.buildApi(json);
                
                System.out.println(nearbyApi.getResultsCount());
                
                this.returnCityInfo(temp);
                
                HttpServletRequest request = (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();
                HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
                ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
                externalContext.redirect("destination_search.xhtml");
                
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}
	
    public String refreshMap()
    {
        System.out.println("refreshing map");
        
        return "OK";
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
	
    
    private GooglePlacesSearchApi getApiWraper()
    {
        return apiWraper;
    }
    
    public String getHtmlListOfPlaces()
    {
        return nearbyApi.getHtmlListOfPlaces();
    }
    
    public int getResultsCount()
    {
        return nearbyApi.getResultsCount();
    }
    
    public void setPlace2Pkg() {
    	System.out.println("enviar "+tourpkg);
    }
 
    
//    @Inject
//	private TourismPackageDAO tpDAO;
    
	public List<String> getTourpkgs() {
		//Long userid = loginc.getUser().getUserId();
//		Long teste = new Long(1);
//		try {
//			List<TourismPackage> tps = tpDAO.searchPackages(teste);
//			for(TourismPackage t: tps) {
//				tourpkgs.add(t.getPackageName());
//			}
//			System.out.println(tourpkgs.size());
//			return tourpkgs;
//		} catch(Exception e) {
//			 //tourpkgs.add("No value");
//			 return tourpkgs;
//		}
//		
		return null;
	}

	public void setTourpkgs(List<String> tourpkgs) {
		
		this.tourpkgs = tourpkgs;
	}
	
	public String getTourpkg() {
		
		return tourpkg;
	}

	public void setTourpkg(String tourpkg) {
		
		this.tourpkg = tourpkg;
	}


    
}
