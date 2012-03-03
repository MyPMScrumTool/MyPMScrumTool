package domainModel;
import pmPersistence.DBTable;
import pmPersistence.PersistentObject;


public class User extends PersistentObject {

	//private String myEmail;
	
	public User(DBTable table) {
		super(table);
		//myEmail="";
		setPersistentValue("UserID", new Integer(0));
		setPersistentValue("ProjectID", new Integer(0));
		setPersistentValue("AccessLevelID", new Integer(0));
		setPersistentValue("UserName", "");
		setPersistentValue("UserPassword", "");
		//registerPersistentField("Email", myEmail);
		
	}
	
	public int getUserId()
	{
		Integer i =(Integer)getPersistentValue("UserID");
		return i.intValue();
	}
	
	public int getProjectId()
	{
		Integer i =(Integer)getPersistentValue("ProjectID");
		return i.intValue();
	}
	
	public int getAccessLevelId()
	{
		Integer i =(Integer)getPersistentValue("AccessLevelID");
		return i.intValue();
	}
	
	public String getUserName()
	{
		return (String)getPersistentValue("UserName");
	}
	
	public String getPassword()
	{
		return (String)getPersistentValue("UserPassword");
	}
	
	public void setProjectId(int id)
	{
		setPersistentValue("ProjectID", new Integer(id));
	}
	
	public void setAccessLevelId(int id)
	{
		setPersistentValue("AccessLevelID", new Integer(id));
	}
	
	public void setUserName(String username)
	{
		setPersistentValue("UserName", username);
	}
	
	public void setPassword(String password)
	{
		setPersistentValue("UserPassword", password);
	}
	
	

}
