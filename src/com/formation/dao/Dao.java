package com.formation.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.formation.model.Client;

public class Dao implements Idao {

	@Override
	public void save(Client client) {
		// TODO Auto-generated method stub
		PreparedStatement st = null;
		ResultSet rs = null;
		Connection cn = null;
		String nom = client.getNom();
		String prenom = client.getPrenom();
		int age = client.getAge();
		try {

			cn = connecter();

			String sql = "INSERT INTO client (nom, prenom, age) VALUES (?, ?, ?)";
			st = cn.prepareStatement(sql);
			st.setString(1, nom);
			st.setString(2, prenom);
			st.setInt(3, age);
			// etape 4 : exécution requete:
			st.execute();



		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			try {
				// etape 6 : liberer ressources de la mémoire
				cn.close();
				st.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public Client findById(int id) {
		PreparedStatement st = null;
		ResultSet rs = null;
		Connection cn = null;
		Client client = new Client();
		try {

			cn = connecter();

			String sql = "SELECT * FROM client WHERE id=?";
			st = cn.prepareStatement(sql);
		
			st.setInt(1, id);
		
			rs = st.executeQuery();
			while (rs.next()) {
				client.setNom(rs.getString("nom"));
				client.setPrenom(rs.getString("prenom"));
				client.setAge(rs.getInt("age"));
				System.out.println(rs.getString("nom")+ " " + rs.getString("prenom")+" "+ rs.getInt("age"));
				
			}
			

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			try {
				// etape 6 : liberer ressources de la mémoire
				cn.close();
				st.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return client;
	}

	@Override
	public void update(Client client) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(Client client) {
		// TODO Auto-generated method stub

	}

	@Override
	public Connection connecter() throws ClassNotFoundException, SQLException {

		String url = "jdbc:mysql://localhost/jdbcserv";
		String login = "root";
		String passwd = "";

	

		// etape 1 : Chargement du driver
		Class.forName("com.mysql.jdbc.Driver");
		// etape 2 : récuperation de la connexion
		Connection cn = DriverManager.getConnection(url, login, passwd);
		return cn;
	}
	
public static void main(String[] args) {
	Client client =new Client("Valentin","Rongier",25);
 System.out.println(client);
 
 Idao dao =new Dao();
 dao.findById(1);
}
}
