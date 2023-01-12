package bookingSystem;

public class AdminAcc extends UserAcc //Inheritance from UserAcc
{
	private String securityCode;
	
	public AdminAcc()
	{
		
	}
	
	//setter and getter
	public String getSecurityCode() 
	{
		return securityCode;
	}

	public void setSecurityCode(String securityCode) 
	{
		this.securityCode = securityCode;
	}
	
}
