package find_my_destiny;

import javax.enterprise.inject.Model;
import javax.inject.Inject;

@Model
public class SessionController {
	private boolean logged;
	
	@Inject
	private User loggedUser;
	
	private User getLoggedUser()
	{
		return loggedUser;
	}
}
