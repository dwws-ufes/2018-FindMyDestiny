function SelectPersonForm(typeOfPerson)
{
	var privatePersonArea = document.getElementById('registration_private_person');
	var enterpriseArea = document.getElementById('registration_enterprise');

	if (typeOfPerson == 'private_person')
	{
		privatePersonArea.hidden = false;
		var privatePersonButton = document.getElementById('form_private_person');
		privatePersonButton.style.backgroundColor = "green"; 
		if (enterpriseArea != null) 
		{
			enterpriseArea.hidden = true;
			var enterpriseButton = document.getElementById('form_enterprise');
			enterpriseButton.style.backgroundColor = "#3c96ff";
		}
	}

	else if (typeOfPerson == 'enterprise')
	{
		enterpriseArea.hidden = false;
		if (privatePersonArea != null)
		{
			privatePersonArea.hidden = true;
		}
	}
}