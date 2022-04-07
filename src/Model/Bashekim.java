package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Bashekim extends User {
	Connection con = conn.connDb();
	Statement st = null;
	ResultSet rs = null;
	PreparedStatement preparedStatement = null;

	public Bashekim(int id, String tCKN, String name, String password, String type) {
		super(id, tCKN, name, password, type);
	}

	public Bashekim() {
	}

	public ArrayList<User> getDoctorList() throws SQLException {
		ArrayList<User> list = new ArrayList<>();
		User obj;
		try {
			st = con.createStatement();
			rs = st.executeQuery("SELECT * FROM [user] WHERE type = 'doktor'");
			while (rs.next()) {
				obj = new User(rs.getInt("id"), rs.getString("TCKN"), rs.getString("password"), rs.getString("name"),
						rs.getString("type"));
				list.add(obj);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	public boolean addDoctor(String tcno, String password, String name) throws SQLException {

		boolean key = false;
		String query = "INSERT INTO [user] (TCKN,password,name,type) VALUES" + "(?,?,?,?)";
		try {
			st = con.createStatement();
			preparedStatement = con.prepareStatement(query);
			preparedStatement.setString(1, tcno);
			preparedStatement.setString(2, password);
			preparedStatement.setString(3, name);
			preparedStatement.setString(4, "doktor");
			preparedStatement.executeUpdate();
			key = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (key) {
			return true;
		} else {
			return false;
		}

	}

	public boolean addWorker(int user_id, int clinic_id) throws SQLException {
		
		String query = "INSERT INTO worker " + "(user_id,clinic_id) VALUES" + "(?,?)";
		boolean key = false;
		int count = 0;
		try {
			st = con.createStatement();
			rs = st.executeQuery("SELECT * FROM worker WHERE user_id=" + user_id + "AND clinic_id=" + clinic_id);
			while (rs.next()) {
				count++;
			}
			if (count == 0) {
				preparedStatement = con.prepareStatement(query);
				preparedStatement.setInt(1, user_id);
				preparedStatement.setInt(2, clinic_id);
				preparedStatement.executeUpdate();
			}
			key = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (key) {
			return true;
		} else {
			return false;
		}

	}

	public boolean delDoctor(int id) {
		boolean key = false;
		String query = "DELETE FROM [user] WHERE id=" + "?";
		try {
			st = con.createStatement();
			preparedStatement = con.prepareStatement(query);
			preparedStatement.setInt(1, id);
			preparedStatement.executeUpdate();
			key = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (key) {
			return true;
		} else {
			return false;
		}
	}

	public boolean updateDoctor(int id, String tcno, String password, String name) throws SQLException {
		boolean key = false;
		String query = "UPDATE [user] SET password=?,name=?,TCKN=? WHERE id=?";
		try {
			st = con.createStatement();
			preparedStatement = con.prepareStatement(query);
			preparedStatement.setString(1, password);
			preparedStatement.setString(2, name);
			preparedStatement.setString(3, tcno);
			preparedStatement.setInt(4, id);
			preparedStatement.executeUpdate();
			key = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (key) {
			return true;
		} else {
			return false;
		}
	}
	
	public ArrayList<User> getClinicDoctorList(int clinic_id) throws SQLException {
		ArrayList<User> list = new ArrayList<>();
		User obj;
		try {
			st = con.createStatement(); 
			rs = st.executeQuery(
					"SELECT u.id,u.TCKN,u.type,u.name,u.password FROM worker w LEFT JOIN [user] u ON w.user_id=u.id WHERE clinic_id= "
							+ clinic_id);

			while (rs.next()) {
				obj = new User(rs.getInt("id"), rs.getString("TCKN"),rs.getString("password"), rs.getString("name"), rs.getString("type"));
				list.add(obj);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

}
