package br.inf.ufes.findmydestiny.rdf;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.jena.datatypes.xsd.XSDDatatype;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.Property;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.rdf.model.ResourceFactory;
import org.apache.jena.vocabulary.RDF;
import org.apache.jena.vocabulary.RDFS;

import br.inf.ufes.findmydestiny.core.domain.TourismPackage;
import br.inf.ufes.findmydestiny.core.domain.User;
import br.inf.ufes.findmydestiny.core.persistence.TourismPackageDAO;
import br.inf.ufes.findmydestiny.core.persistence.UserDAO;

@WebServlet(urlPatterns= {"/data/tp"})
public class ListDateInRDFServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Inject
	private TourismPackageDAO tpDAO;
	
	@Inject
	private UserDAO userDAO;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		resp.setContentType("text/xml");
		
		List<TourismPackage> packs = tpDAO.retrieveAll();
		
		Model model = ModelFactory.createDefaultModel();
		String myNS = "http://localhost:8080/findmydestiny/data/tp/";
		String grNS = "http://purl.org/goodrelations/v1#"; //Packages
		String scNS = "https://schema.org/"; //Personal
		model.setNsPrefix("gr", grNS);
		//Personal information:
		Resource scPerson = ResourceFactory.createResource(scNS+"Person");		
		Property scTelephone1 = ResourceFactory.createProperty(scNS+"telephone");
		Property scTelephone2 = ResourceFactory.createProperty(scNS+"telephone");
		Property scEmail = ResourceFactory.createProperty(scNS+"email");
		//Packages information:
		Resource grOffering = ResourceFactory.createResource(grNS+"Offering");
		Resource grPriceSpecification = ResourceFactory.createResource(grNS+"PriceSpecification");
		Property grAvailabilityStarts = ResourceFactory.createProperty(grNS+"availabilityStarts");
		Property grAvailabilityEnds = ResourceFactory.createProperty(grNS+"availabilityEnds");
		Property grHasPriceSpecification = ResourceFactory.createProperty(grNS+"hasPriceSpecification");
		Property grHasCurrentValue = ResourceFactory.createProperty(grNS+"hasCurrentValue");
		
		for(TourismPackage pack: packs) {
			Long id = pack.getUserid();
			User user = userDAO.searchById(id);
			model.createResource(myNS + pack.getPackageId())
			.addProperty(RDF.type, grOffering)
			.addProperty(RDFS.label,pack.getPackageName())
			.addLiteral(grAvailabilityStarts,ResourceFactory.createTypedLiteral(pack.getStartDate(),XSDDatatype.XSDdateTime))
			.addLiteral(grAvailabilityEnds,ResourceFactory.createTypedLiteral(pack.getEndDate(),XSDDatatype.XSDdateTime))
			.addProperty(grHasPriceSpecification,model.createResource().addProperty(RDF.type, grPriceSpecification).addLiteral(grHasCurrentValue, pack.getPrice()))
			.addProperty(RDF.type, scPerson)
			.addProperty(RDFS.label, user.getName())
			.addLiteral(scTelephone1,user.getTelephone())
			.addLiteral(scTelephone2, user.getCellphone())
			.addLiteral(scEmail, user.getEmail());
		}
		
		try(PrintWriter out = resp.getWriter()){
			model.write(out, "RDF/XML");
		}
		
	}
	
}
