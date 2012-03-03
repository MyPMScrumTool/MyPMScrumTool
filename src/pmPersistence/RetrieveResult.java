package pmPersistence;

import java.lang.reflect.InvocationTargetException;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Set;

public class RetrieveResult {

	@SuppressWarnings("rawtypes")
	private Class myClass;
	private ResultSet myResultSet;
	private DBTable myTable;
	@SuppressWarnings("rawtypes")
	private Class[] myCtorArgs;
	RetrieveResult(String persistentClass, ResultSet rs, DBTable table)
	{
		myTable = table;
		try {
			myClass = Class.forName(persistentClass);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		myCtorArgs = new Class[1];
		myCtorArgs[0] = DBTable.class;
		myResultSet = rs;
		
		 
	}
		
	@SuppressWarnings("unchecked")
	public PersistentObject next()
	{
		PersistentObject ret = null;
		try
		{
			if(myResultSet.next())
			{
				try {
					ret = (PersistentObject)myClass.getConstructor(myCtorArgs).newInstance(myTable);
					//initialize the object...
					retrieveRow(ret);
				}
				catch(InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException s)
				{
					
				}
			}
		}
		catch(SQLException s)
		{
		
		}
		return ret;
	}
	
	private void retrieveRow(PersistentObject obj)
	{
		Set<String> fields = myTable.Fields;
		for(String fname : fields)
		{
			Object prop = obj.getProperties().get(fname);
			
			obj.getProperties().put(fname, getPropertyValue(prop, fname));
		}
	}
	
	private Object getPropertyValue(Object obj, String field)
	{
		Object ret = null;
		try
		{
			if(obj != null)
			{
				/*
				if(obj instanceof PersistentObject)
				{
					myResultSet.
					//retrieve a copy of the persistent object...
					PersistentObject po = (PersistentObject)obj;
					String whereStmt = po.getTable().KeyField + " = " + myResultSet.getString(column);
					myTable.Db.retrievePersistentObjects(obj.getClass().getName(), myTable.Name, whereStmt);
					
					//get the foreign key...
					ret = getValueString(po.getProperties().get(po.getTable().KeyField));
				}
				else */
				if(obj instanceof String)
				{
					return myTable.Db.unsanitize(myResultSet.getString(field));
				}
				else if(obj instanceof Integer)
				{
					ret = myResultSet.getInt(field);
					if(ret == null)
					{
						ret = new Integer(0);
					}
				}
				else if(obj instanceof Long)
				{
					ret = myResultSet.getLong(field);
					if(ret == null)
					{
						ret = new Long(0);
					}
				}
				else if(obj instanceof Float)
				{
					ret = myResultSet.getFloat(field);
					if(ret == null)
					{
						ret = new Float(0.0);
					}
				}
				else if(obj instanceof Double)
				{
					ret =  myResultSet.getDouble(field);
					if(ret == null)
					{
						ret = new Double(0.0);
					}
				}
				else if(obj instanceof Boolean)
				{
					ret = myResultSet.getBoolean(field);
					if(ret == null)
					{
						ret = new Boolean(false);
					}
				}
				else if(obj instanceof Byte)
				{
					ret = myResultSet.getByte(field);
					if(ret == null)
					{
						ret = new Byte((byte)0);
					}
				}
				else if(obj instanceof Date)
				{
					ret = myResultSet.getDate(field);
					if(ret == null)
					{
						ret = new Date(0);
					}
				}
				else if(obj instanceof Time)
				{
					ret = myResultSet.getTime(field);
					if(ret == null)
					{
						ret = new Time(0);
					}
				}
				else if(obj instanceof Timestamp)
				{
					ret = myResultSet.getTimestamp(field);
					if(ret == null)
					{
						ret = new Timestamp(0);
					}
				}
				else
				{
					//don't know how to handle this type...
					//obj. = myTable.Db.unsanitize(obj.toString());
				}
			}
		}
		catch(SQLException s)
		{
		}
		return ret;
	}
	
}
