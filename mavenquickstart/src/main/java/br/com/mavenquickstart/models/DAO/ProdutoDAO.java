package br.com.mavenquickstart.models.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.mavenquickstart.infrastructure.database.ConnectionFactory;
import br.com.mavenquickstart.models.Produto;

public class ProdutoDAO {
	private final Connection connection;
	public ProdutoDAO() throws SQLException {
		this.connection = new ConnectionFactory().getConnection();
	}

// --------- Insert ---------------
	public void Inserir(Produto produto) throws SQLException {
		String sql = "INSERT INTO PRODUTO(ID, NOME, PRECO) VALUES (?, ?, ?)";
		try {
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1, produto.getId());
			statement.setString(2, produto.getNome());
			statement.setDouble(3, produto.getPreco());
		}catch(SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
// --------- Update ---------------
	public void Atualizar(Produto produto) {
		String sql = "UPDATE PRODUTO SET NOME = ?, PRECO = ? WHERE ID = ?";
		try {
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(3, produto.getId());
			statement.setString(1, produto.getNome());
			statement.setDouble(2, produto.getPreco());
		}catch(SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
// --------- Delete ---------------
	public void Deletar(int id) {
		String sql = "DELETE FROM PRODUTO WHERE ID = ?";
		try {
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1, id);
		}catch(SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
// --------- Select ---------------
	public List<Produto> Buscar(){
		String sql = "SELECT * FROM PRODUTO";
		ArrayList<Produto> listadeProdutos = new ArrayList<Produto>();
		try {
			PreparedStatement statement = connection.prepareStatement(sql);
			try {
				 ResultSet rs = statement.executeQuery();
				while(rs.next()) {
					Produto produto = new Produto();
					produto.setId(rs.getInt("ID"));
					produto.setNome(rs.getString("NOME"));
					produto.setPreco(rs.getDouble("PRECO"));
					listadeProdutos.add(produto);
				}
			return listadeProdutos;
			}catch(SQLException e) {
				throw new RuntimeException(e);
			}
		}catch(SQLException e) {
			throw new RuntimeException(e);
		}
	}
}