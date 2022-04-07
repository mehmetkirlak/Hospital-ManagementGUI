package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Helper.DBConnection;

public class Clinic {
	private int id;
	private String name;

	DBConnection conn = new DBConnection();
	Connection con = conn.connDb();
	Statement st = null;
	ResultSet rs = null;
	PreparedStatement preparedStatement = null;

	public Clinic() {

	}

	public Clinic(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public ArrayList<Clinic> getClinicList() throws SQLException {
		ArrayList<Clinic> list = new ArrayList<>();
		Clinic obj;
		try {
			st = con.createStatement();
			rs = st.executeQuery("SELECT * FROM [clinic]");
			while (rs.next()) {
				obj = new Clinic(rs.getInt("id"), rs.getString("name"));
				list.add(obj);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// con.close();
		}
		return list;
	}

	public boolean addClinic(String name) throws SQLException {

		boolean key = false;
		String query = "INSERT INTO [clinic] (name) VALUES" + "(?)";
		try {
			st = con.createStatement();
			preparedStatement = con.prepareStatement(query);
			preparedStatement.setString(1, name);
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
	
	public boolean delClinic(int id) {
		boolean key = false;
		String query = "DELETE FROM [clinic] WHERE id=" + "?";
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
	
	public boolean updateClinic(int id, String name) throws SQLException {
		boolean key = false;
		String query = "UPDATE [clinic] SET name=? WHERE id=?";
		try {
			st = con.createStatement();
			preparedStatement = con.prepareStatement(query);
			preparedStatement.setString(1, name);
			preparedStatement.setInt(2, id);
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

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
