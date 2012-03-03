package pmPersistence;

import java.sql.*;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Database {

	class FieldDefinition
	{
		public String name;
		public String datatype;
	};
	
	private Connection myConnection = null;
	public Database(String url, String driver, String name, String user, String pass)
	{
		myConnection = null;
		try {
			Class.forName(driver).newInstance();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			myConnection = DriverManager.getConnection(url+name, user, pass);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//System.out.println("Connected to the database");
		
	}
	
	public void closeDatabase()
	{
		try {
			myConnection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//System.out.println("Disconnected from database");
	}
	
	public DBTable getTable(String name)
	{
		Set<String> fields = new HashSet<String>();
		String keyField="";
		try
		{
			//Statement st = myConnection.createStatement();
			//ResultSet rs = st.executeQuery("DESCRIBE "+name);
			//ResultSetMetaData md = rs.getMetaData();
			//int col = md.getColumnCount();
			DatabaseMetaData dbm = myConnection.getMetaData();
			ResultSet rs = dbm.getColumns(null, "%", name, "%");
			String colName;
			while(rs.next()){
				colName = rs.getString("COLUMN_NAME");
				fields.add(colName);
			}
			/*
			for (int i = 1; i <= col; i++){
			  colName = md.getColumnName(i);
			  fields.add(colName);
			}
			*/
			
			ResultSet rs2 = dbm.getPrimaryKeys(null, "%", name);
			if(rs2.next())
			{
				keyField = rs2.getString("COLUMN_NAME");
			}
			if(rs2.next())
			{
				System.out.println("Error: Multiple key fields");
			}
		}
		catch (SQLException s){
		}
		return new DBTable(name, fields, keyField, this);
	}
	/*
	private void createTable(String name, FieldDefinition fields[]) {
		try {
			Statement st = myConnection.createStatement();
			String query = "CREATE TABLE "+name + "(";
			Boolean firstField = true;
			for(FieldDefinition field : fields)
			{
				if(!firstField)
				{
					query +=",";
				}
				query += field.name + " " + field.datatype;
				firstField = false;
			}
			query += ")";
			st.executeUpdate(query);
			//System.out.println("Table creation process successfully!");
		}
		catch(SQLException s){
			System.out.println("Table already exists!");
		}
	}
	*/
	public PersistentObject createPersistentObject(String persistentClass, String tableName)
	{
		PersistentObject ret = null;
		DBTable table = getTable(tableName);
		/*
		@SuppressWarnings("rawtypes")
		Class[] ctorArgs = new Class[1];
		ctorArgs[0] = DBTable.class;
		try {
			ret = (PersistentObject)Class.forName(persistentClass).getConstructor(ctorArgs).newInstance(table);
		} catch (InstantiationException | IllegalAccessException
				| IllegalArgumentException | InvocationTargetException
				| NoSuchMethodException | SecurityException
				| ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		*/
		//String query = "INSERT " + table.Name + " VALUES(";
		String query = "INSERT " + table.Name + " SET " ;
		//Map<String, Object> properties = ret.getProperties();
		Set<String> fields = table.Fields;
		boolean firstValue = true;
		for(String fname : fields)
		{
			if(!firstValue)
			{
				query += ", ";
			}
			firstValue = false;
			query += fname + "=" + "DEFAULT";
		}
		//query += ")";
		try
		{
			Statement st = myConnection.createStatement();
			st.executeUpdate(query, Statement.RETURN_GENERATED_KEYS);
			ResultSet rs = st.getGeneratedKeys();
			if(rs.next())
			{
				int key = rs.getInt(1);
				String whereStmt = table.KeyField + " = " + Integer.toString(key);
				ret = retrievePersistentObjects(persistentClass, tableName, whereStmt).next();
			}
		}
		catch(SQLException s)
		{
			//ret = null;
		}
		return ret;
	}
	
	public void updatePersistentObject(PersistentObject obj) {
		try
		{
			Statement st = myConnection.createStatement();
			String query = "UPDATE " + obj.getTable().Name + " SET ";
			Map<String, Object> properties = obj.getProperties();
			Set<String> fields = obj.getTable().Fields;
			boolean firstValue = true;
			for(String fname : fields)
			{
				if(!firstValue)
				{
					query += ", ";
				}
				firstValue = false;
				query += fname + "=" + getValueString(properties.get(fname));
			}
			query += " WHERE " + obj.getTable().KeyField + " = " + getValueString(properties.get(obj.getTable().KeyField));
			//query += ")";
			st.executeUpdate(query);
		}
		catch(SQLException x1)
		{
			System.out.println("Error...!");
			/*
			//failed to store the object ... make sure the table exists
			try
			{
				FieldDefinition fd[] = buildFieldDefinitions(obj);
				createTable(obj.getTable().Name, fd);
				storeInternal(obj);
			}
			catch(SQLException x2)
			{
				//failed to store the object
			}
			*/
		}
	}
/*	
	private FieldDefinition[] buildFieldDefinitions(PersistentObject obj)
	{
		Map<String, Object> propertyMap = obj.getProperties();
		FieldDefinition[] ret = new FieldDefinition[propertyMap.size()];
		Set<String> fieldNames = propertyMap.keySet();
		int index = 0;
		for(String fname : fieldNames)
		{
			Object o = propertyMap.get(fname);
			ret[index].name = fname;
			ret[index].datatype = getDatatypeName(o);
			++index;
		}
		return ret;
	}
	*/

	/*
	private String getDatatypeName(Object obj)
	{
		String ret;
		
		//if(obj instanceof PersistentObject)
		//{
		//	PersistentObject po = (PersistentObject)obj;
		//	//get the foreign key...
		//	ret = getDatatypeName(po.getProperties().get(po.getTable().KeyField));
		//}
		//else 
		if(obj instanceof String)
		{
			ret = "VARCHAR";
		}
		else if(obj instanceof Integer)
		{
			ret = "INTEGER";
		}
		else if(obj instanceof Long)
		{
			ret = "BIGINT";
		}
		else if(obj instanceof Float)
		{
			ret = "REAL";
		}
		else if(obj instanceof Double)
		{
			ret = "DOUBLE";
		}
		else if(obj instanceof Boolean)
		{
			ret = "BIT";
		}
		else if(obj instanceof Byte)
		{
			ret = "TINYINT";
		}
		else if(obj instanceof Date)
		{
			ret = "DATE";
		}
		else if(obj instanceof Time)
		{
			ret = "TIME";
		}
		else if(obj instanceof Timestamp)
		{
			ret = "TIMESTAMP";
		}
		else
		{
			ret = "VARCHAR"; //last resort, store toString...
		}
		return ret;
	}
	*/
	/*
	private void storeInternal(PersistentObject obj) throws SQLException
	{
		Statement st = myConnection.createStatement();
		String query = "INSERT " + obj.getTable().Name + " VALUES(";
		Map<String, Object> properties = obj.getProperties();
		Set<String> fields = obj.getTable().Fields;
		Boolean firstValue = true;
		for(String fname : fields)
		{
			if(!firstValue)
			{
				query += ",";
			}
			query += fname + "=" + getValueString(properties.get(fname));
		}
		query += ")";
		st.executeUpdate(query);
	}
	*/
	
	private String getValueString(Object obj)
	{
		String ret;
		if(obj == null)
		{
			ret = "DEFAULT";
		}
		/*
		else if(obj instanceof PersistentObject)
		{
			PersistentObject po = (PersistentObject)obj;
			//get the foreign key...
			ret = getValueString(po.getProperties().get(po.getTable().KeyField));
		}
		*/
		else if(obj instanceof String)
		{
			ret = sanitize((String)obj);
		}
		else if(obj instanceof Integer)
		{
			ret = ((Integer)obj).toString();
		}
		else if(obj instanceof Long)
		{
			ret = ((Long)obj).toString();
		}
		else if(obj instanceof Float)
		{
			ret = ((Float)obj).toString();
		}
		else if(obj instanceof Double)
		{
			ret=((Double)obj).toString();
		}
		else if(obj instanceof Boolean)
		{
			if(((Boolean)obj).booleanValue())
			{
				ret = "1";
			}
			else
			{
				ret = "0";
			}
		}
		else if(obj instanceof Byte)
		{
			ret = ((Byte)obj).toString();
		}
		else if(obj instanceof Date)
		{
			ret = ((Date)obj).toString();
		}
		else if(obj instanceof Time)
		{
			ret = ((Time)obj).toString();
		}
		else if(obj instanceof Timestamp)
		{
			ret = ((Timestamp)obj).toString();
		}
		else
		{
			ret = sanitize(obj.toString());
		}
		return ret;
	}
	
	public Boolean deletePersistentObject(PersistentObject obj) {
		Boolean ret = false;
		try {
			  Statement st = myConnection.createStatement();
			  String query = "DELETE FROM " + obj.getTable().Name + " WHERE " + obj.getTable().KeyField + " = " + getValueString(obj.getProperties().get(obj.getTable().KeyField));
			  int delete = st.executeUpdate(query);
			  if(delete == 1){
				  ret = true;
			  }
		  }
		  catch (SQLException s){
		  }
		return ret;
	}
	
	public String sanitize(String rawData)
	{
		//Make string data safe for storage in the database
		//replace any \ with a \\
		rawData.replace("\\","\\\\");
		//replace any ' with a \'
		rawData.replace("'","\\'");
		//replace any " with a \"
		rawData.replace("\"", "\\\"");
		//encapsulate in ''
		return "'" + rawData + "'";
	}
	
	public String unsanitize(String sanitizedData)
	{
		//Convert string back to original format...
		String ret = "";
		if(sanitizedData != null)
		{
			//ret = sanitizedData.substring(1, sanitizedData.length()-1);
			ret = sanitizedData;
			ret.replace("\\\"","\"");
			ret.replace("\\'","'");
			ret.replace("\\\\","\\");
		}
		return ret;
	}
	
	public RetrieveResult retrievePersistentObjects(String persistentClass, String tableName, String whereStmt) {
		RetrieveResult result = null;
		try
		{
			Statement st = myConnection.createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM "+tableName+ " WHERE " + whereStmt);
			result = new RetrieveResult(persistentClass, rs, getTable(tableName));
		}
		catch (SQLException s){
		}
		
		return result;
	}
}
