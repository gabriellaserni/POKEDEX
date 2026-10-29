package br.com.fiap.dao;

import br.com.fiap.to.PokemonTO;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PokemonDAO {

    public ArrayList<PokemonTO> findAll() {
        ArrayList<PokemonTO> pokemons = new ArrayList<PokemonTO>();
        String sql = "select * from ddd_pokemons order by codigo";
        try (PreparedStatement ps = ConnectionFactory.getConnection().prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                pokemons.add(montarPokemon(rs));
            }
        } catch (SQLException e) {
            System.out.println("Erro na consulta: " + e.getMessage());
            return null;
        } finally {
            ConnectionFactory.closeConnection();
        }
        return pokemons;
    }

    public PokemonTO findByCodigo(Long codigo) {
        String sql = "select * from ddd_pokemons where codigo = ?";
        try (PreparedStatement ps = ConnectionFactory.getConnection().prepareStatement(sql)) {
            ps.setLong(1, codigo);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return montarPokemon(rs);
                }
            }
        } catch (SQLException e) {
            System.out.println("Erro na consulta: " + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection();
        }
        return null;
    }

    public PokemonTO save(PokemonTO pokemon) {
        String sql = "insert into ddd_pokemons (nome, tipo, nivel, data_da_captura) values(?, ?, ?, ?)";
        // pede ao Oracle o código gerado pelo IDENTITY para devolver no JSON
        try (PreparedStatement ps = ConnectionFactory.getConnection().prepareStatement(sql, new String[]{"codigo"})) {
            ps.setString(1, pokemon.getNome());
            ps.setString(2, pokemon.getTipo());
            ps.setInt(3, pokemon.getNivel());
            ps.setDate(4, Date.valueOf(pokemon.getDataDaCaptura()));
            if (ps.executeUpdate() > 0) {
                try (ResultSet chaves = ps.getGeneratedKeys()) {
                    if (chaves.next()) {
                        pokemon.setCodigo(chaves.getLong(1));
                    }
                }
                return pokemon;
            } else {
                return null;
            }
        } catch (SQLException e) {
            System.out.println("Erro ao salvar: " + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection();
        }
        return null;
    }

    public PokemonTO update(PokemonTO pokemon) {
        String sql = "update ddd_pokemons set nome=?, tipo=?, nivel=?, data_da_captura=? where codigo=?";
        try (PreparedStatement ps = ConnectionFactory.getConnection().prepareStatement(sql)) {
            ps.setString(1, pokemon.getNome());
            ps.setString(2, pokemon.getTipo());
            ps.setInt(3, pokemon.getNivel());
            ps.setDate(4, Date.valueOf(pokemon.getDataDaCaptura()));
            ps.setLong(5, pokemon.getCodigo());
            if (ps.executeUpdate() > 0) {
                return pokemon;
            } else {
                return null;
            }
        } catch (SQLException e) {
            System.out.println("Erro ao atualizar: " + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection();
        }
        return null;
    }

    public boolean delete(Long codigo) {
        String sql = "delete from ddd_pokemons where codigo = ?";
        try (PreparedStatement ps = ConnectionFactory.getConnection().prepareStatement(sql)) {
            ps.setLong(1, codigo);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Erro ao excluir: " + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection();
        }
        return false;
    }

    private PokemonTO montarPokemon(ResultSet rs) throws SQLException {
        PokemonTO pokemon = new PokemonTO();
        pokemon.setCodigo(rs.getLong("codigo"));
        pokemon.setNome(rs.getString("nome"));
        pokemon.setTipo(rs.getString("tipo"));
        pokemon.setNivel(rs.getInt("nivel"));
        pokemon.setDataDaCaptura(rs.getDate("data_da_captura").toLocalDate());
        return pokemon;
    }
}
