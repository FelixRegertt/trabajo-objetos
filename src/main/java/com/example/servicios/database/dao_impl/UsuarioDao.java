package com.example.servicios.database.dao_impl;

import com.example.modelo.Usuario;
import com.example.servicios.database.Dao;
import com.example.servicios.database.IDao;

import java.util.ArrayList;
import java.util.List;

import java.sql.SQLException;

public class UsuarioDao extends Dao implements IDao<Usuario, String> {

	private static UsuarioDao usuarioDao = null;

	private UsuarioDao(){}

	public static UsuarioDao getInstance(){
		if (usuarioDao == null)
			usuarioDao = new UsuarioDao();
		return usuarioDao;
	}

	private List<Usuario> getUsuarios(String query) throws SQLException {
		List<Usuario> usuarios = new ArrayList<>();

		connection = getConnection();
		ptmt = connection.prepareStatement(query);
		resultSet = ptmt.executeQuery();

		while (resultSet.next()) {
			Usuario u = new Usuario(resultSet.getString("usmail"),
									resultSet.getString("usnombre"),
									resultSet.getInt("uspuntaje"));

			u.setAdmin(resultSet.getBoolean("usesadmin"));
			usuarios.add(u);
		}

		if (resultSet != null)
			resultSet.close();
		if (ptmt != null)
			ptmt.close();
		if (connection != null)
			connection.close();

		return usuarios;
	}

    @Override
    public List<Usuario> getAll() throws SQLException {
		String query = "SELECT * FROM usuarios";
		return this.getUsuarios(query);
	}

    @Override 
    public Usuario get(String mail) throws SQLException {
		Usuario usuario = new Usuario();

		String queryString = "SELECT * FROM usuarios WHERE usmail = ?";
		connection = getConnection();
		ptmt = connection.prepareStatement(queryString);
		ptmt.setString(1, mail);
		resultSet = ptmt.executeQuery();

		if (resultSet.next()) {
			usuario.setNombre(resultSet.getString("usnombre"));
			usuario.setPuntaje(resultSet.getInt("uspuntaje"));
			usuario.setMail(resultSet.getString("usmail"));
			usuario.setPassword(resultSet.getString("password"));
			usuario.setAdmin(resultSet.getBoolean("usesadmin"));
		}

		if (resultSet != null)
			resultSet.close();
		if (ptmt != null)
			ptmt.close();
		if (connection != null)
			connection.close();

		return usuario;

	}

    @Override
    public void delete(String mail) throws SQLException {

		String queryString = "DELETE FROM usuarios WHERE usmail=?";
		connection = getConnection();
		ptmt = connection.prepareStatement(queryString);
		ptmt.setString(1, mail);
		ptmt.executeUpdate();

		System.out.println("Usuario eliminado exitosamente");

		if (ptmt != null)
			ptmt.close();
		if (connection != null)
			connection.close();
	}

    @Override
    public void add(Usuario usuario) throws SQLException {

		String queryString = "INSERT INTO usuarios(usmail, usnombre, uspuntaje, usesadmin, password) VALUES(?,?,?,?,?)";
		connection = getConnection();
		ptmt = connection.prepareStatement(queryString);

		ptmt.setString(1, usuario.getMail());
		ptmt.setString(2, usuario.getNombre());
		ptmt.setInt(3, usuario.getPuntaje());
		ptmt.setBoolean(4, false);
		ptmt.setString(5, usuario.getPassword());
		ptmt.executeUpdate();

		System.out.println("Usuario dado de alta correctamente");

		if (ptmt != null)
			ptmt.close();
		if (connection != null)
			connection.close();

    }

	@Override
	public boolean exist(String id) throws SQLException {

		String queryString = "SELECT usmail FROM usuarios WHERE usmail = ?";
		connection = getConnection();
		ptmt = connection.prepareStatement(queryString);
		ptmt.setString(1, id);
		resultSet = ptmt.executeQuery();

		boolean resultado = resultSet.next();

			if (resultSet != null)
				resultSet.close();
			if (ptmt != null)
				ptmt.close();
			if (connection != null)
				connection.close();

		return resultado;
	}

	@Override
    public void update(Usuario usuario) throws SQLException {

		String queryString = "UPDATE usuarios SET usnombre=?, uspuntaje=? WHERE usmail=?";
		connection = getConnection();
		ptmt = connection.prepareStatement(queryString);
		ptmt.setString(1, usuario.getNombre());
		ptmt.setInt(2, usuario.getPuntaje());
		ptmt.setString(3, usuario.getMail());
		ptmt.executeUpdate();
		System.out.println("Usuario actualizado exitosamente");

		if (ptmt != null)
			ptmt.close();
		if (connection != null)
			connection.close();
	}

    public boolean esAdmin(String usuario) throws SQLException{

		String queryString = "SELECT usesadmin FROM usuarios WHERE usmail = ?";
		connection = getConnection();

		ptmt = connection.prepareStatement(queryString);
		ptmt.setString(1, usuario);
		resultSet = ptmt.executeQuery();

		boolean resultado;
		if (resultSet.next()) {
			resultado = resultSet.getBoolean("usesadmin");
		}else
			resultado = false;

		if (ptmt != null)
			ptmt.close();
		if (connection != null)
			connection.close();

		return resultado;
    }

	//todo: esta bien usar un metodo aux?
	public List<Usuario> getTop() throws SQLException {

		String queryString = "SELECT u.usmail, u.usnombre, u.uspuntaje, u.usesadmin FROM usuarios u JOIN elementos e on u.usmail = e.elpropietario\n" +
				"GROUP BY u.usmail order by count(usmail) desc limit 10";

		return this.getUsuarios(queryString);
	}
}
