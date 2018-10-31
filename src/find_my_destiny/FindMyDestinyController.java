package find_my_destiny;

import java.util.Date;
import javax.enterprise.inject.Model;

@Model
public class FindMyDestinyController {
	private String visitor = "visitor";
	
	public String getVisitor() {return visitor;}
	
	public void setVisitor(String visitor) {this.visitor= visitor;}
	
	public Date getTime() {return new Date();}
}
