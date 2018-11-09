function SelectPersonForm(typeOfPerson)
{
	var privatePersonArea = document.getElementById('registration_private_person');
	var enterpriseArea = document.getElementById('registration_enterprise');

	if (typeOfPerson == 'private_person')
	{
		privatePersonArea.hidden = false;
		var privatePersonButton = document.getElementById('form_private_person');	
		privatePersonButton.classList.add("btn-common"); 
		privatePersonButton.classList.add("btn-effect"); 
		privatePersonButton.classList.remove("btn-border"); 

		if (enterpriseArea != null) 
		{
			enterpriseArea.hidden = true;
			var enterpriseButton = document.getElementById('form_enterprise');
			enterpriseButton.classList.add("btn-border"); 
			enterpriseButton.classList.remove("btn-common"); 
			enterpriseButton.classList.remove("btn-effect"); 
		}
	}

	else if (typeOfPerson == 'enterprise')
	{
		enterpriseArea.hidden = false;
		var enterpriseButton = document.getElementById('form_enterprise');
		enterpriseButton.classList.add("btn-common"); 
		enterpriseButton.classList.add("btn-effect");
		enterpriseButton.classList.remove("btn-border"); 
		if (privatePersonArea != null)
		{
			privatePersonArea.hidden = true;
			var privatePersonButton = document.getElementById('form_private_person');
			privatePersonButton.classList.add("btn-border");
			privatePersonButton.classList.remove("btn-common"); 
			privatePersonButton.classList.remove("btn-effect"); 
		}
	}
}