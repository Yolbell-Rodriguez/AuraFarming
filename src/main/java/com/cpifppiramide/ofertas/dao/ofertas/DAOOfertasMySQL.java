package com.cpifppiramide.ofertas.dao.ofertas;

import com.cpifppiramide.ofertas.db.DBConnection;
import com.cpifppiramide.ofertas.entities.Oferta;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DAOOfertasMySQL implements DAOOfertas {

    // SELECT: lee todas las filas de la tabla "ofertas" y las convierte en objetos Oferta
    @Override
    public List<Oferta> getAll() {
        List<Oferta> ofertas = new ArrayList<>();
        Connection con = DBConnection.getInstance();
        try (PreparedStatement ps = con.prepareStatement("SELECT titulo, descripcion FROM ofertas");
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Oferta oferta = new Oferta();
                oferta.setTitulo(rs.getString("titulo"));
                oferta.setDescripcion(rs.getString("descripcion"));
                ofertas.add(oferta);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return ofertas;
    }

    // INSERT: guarda una oferta nueva. Cada ? se rellena con setString (1 = primer ?, 2 = segundo ?)
    @Override
    public void save(Oferta oferta) {
        Connection con = DBConnection.getInstance();
        try (PreparedStatement ps = con.prepareStatement("INSERT INTO ofertas (titulo, descripcion) VALUES (?, ?)")) {
            ps.setString(1, oferta.getTitulo());
            ps.setString(2, oferta.getDescripcion());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // UPDATE: cambia título y descripción de la oferta cuyo título era "titulo" (el antiguo)
    @Override
    public void update(Oferta oferta, String titulo) {
        Connection con = DBConnection.getInstance();
        try (PreparedStatement ps = con.prepareStatement("UPDATE ofertas SET titulo = ?, descripcion = ? WHERE titulo = ?")) {
            ps.setString(1, oferta.getTitulo());
            ps.setString(2, oferta.getDescripcion());
            ps.setString(3, titulo);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // DELETE: borra la oferta con ese título
    @Override
    public void delete(String titulo) {
        Connection con = DBConnection.getInstance();
        try (PreparedStatement ps = con.prepareStatement("DELETE FROM ofertas WHERE titulo = ?")) {
            ps.setString(1, titulo);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
