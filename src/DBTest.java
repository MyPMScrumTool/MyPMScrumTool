import domainModel.User;
import pmPersistence.Database;
import pmPersistence.RetrieveResult;


public class DBTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String dbName="mypmscrumdb";
		String user="root";
		String pass="scrumPM2012";
		Database db = new Database("jdbc:mysql://localhost:3306/", 
									"com.mysql.jdbc.Driver", 
									dbName, 
									user, 
									pass);
		
		User usr = (User)db.createPersistentObject("User", "users");
		
		int uid = usr.getUserId();
		
		usr.setUserName("Brian");
		
		db.updatePersistentObject(usr);
		
		RetrieveResult result = db.retrievePersistentObjects("User", "users", "UserID = " + Integer.toString(uid)); 
		User usr2 = (User) result.next();
		
		if(usr2 != null)
		{
			System.out.println(usr2.getUserName());
		}
		
	}

}
