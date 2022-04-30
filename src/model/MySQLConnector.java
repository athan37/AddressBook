package model;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;  
public class MySQLConnector {
	private static final String DBNAME = "addressBook";
	private static final String USERNAME = "root";
	private static final String PASSWORD = "abcd1234";
	
	public MySQLConnector() throws SQLException, ClassNotFoundException {
		Class.forName("com.mysql.cj.jdbc.Driver");  
	}
	
	private Connection getConnection() throws SQLException {
		return DriverManager.getConnection("jdbc:mysql://localhost:3306/" + DBNAME, USERNAME, PASSWORD);  
	}
	
	public List<Contact> loadContacts() {
		List<Contact> contacts = new ArrayList<>();
		try {  
			Connection connection = getConnection();
			Statement statement = connection.createStatement();  
			ResultSet resultSet = statement.executeQuery("SELECT * FROM contacts");  
			
			while(resultSet.next())   {
				contacts.add(new Contact(
					resultSet.getString(1),
					resultSet.getString(2),
					resultSet.getString(3) == "F" ? Gender.F : Gender.M,
					resultSet.getString(4),
					resultSet.getInt(5),
					resultSet.getString(6)
				));
			}
				connection.close();  
			} catch(Exception e){ 
				System.out.println(e);
			}  
		
		return contacts;
	}
	
	public void writeContacts(List<Contact> contacts) {
		try {  
			String deleteTableSQL = "DROP TABLE contacts";
			String newTableSQL    = "CREATE TABLE contacts(\r\n"
					+ "   first_name VARCHAR(255) NOT NULL,\r\n"
					+ "   last_name  VARCHAR(255) NOT NULL,\r\n"
					+ "   gender    VARCHAR(255) NOT NULL,\r\n"
					+ "   number    VARCHAR(255) PRIMARY KEY,\r\n"
					+ "   age       INT NOT NULL,\r\n"
					+ "   email     VARCHAR(255) NOT NULL\r\n"
					+ ");";
			
			Connection connection = getConnection();
			Statement statement = connection.createStatement();
			
			//These executions return nothing
			statement.executeUpdate(deleteTableSQL);
			statement.executeUpdate(newTableSQL);
			
			String query = "INSERT INTO contacts(first_name, last_name, gender, number, age, email) VALUES (?, ?, ?, ?, ?, ?)";
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			
			for (Contact contact: contacts) {
				preparedStatement.setString(1, contact.getFirstName());
				preparedStatement.setString(2, contact.getLastName());
				preparedStatement.setString(3, contact.getGender().toString());
				preparedStatement.setString(4, contact.getNumber());
				preparedStatement.setInt(5, contact.getAge());
				preparedStatement.setString(6, contact.getEmail());
				
				preparedStatement.addBatch();
			}
			
			preparedStatement.executeLargeBatch();
			
			connection.close();  
			} catch(Exception e){ 
				System.out.println(e);
			}  
	}
}
