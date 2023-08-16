package org.example.repository.impl;

import org.example.ConexionBD;
import org.example.Producto;
import org.example.repository.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Repositoryimpl implements Repository {

    @Override
    public void save(Object o) {

    }

    @Override
    public void delete(Long id) {

    }
    private Connection getConnection() throws SQLException {
        return ConexionBD.getInstance();
    }
    private Producto createProduct(ResultSet resultSet)throws SQLException {
        Producto producto = new Producto();
        producto.setId(resultSet.getLong("id"));
        producto.setNombre(resultSet.getString("nombre"));
        producto.setPrecio(resultSet.getDouble("precio"));
        producto.setFechaRegistro(resultSet.getDate("fecha_registro").toLocalDate());
        return producto;
    }
    public List<Producto> list(){
        List<Producto> productoList = new ArrayList<>();
        try(Statement statement = getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT* fromproductos")){
            while(resultSet.next()){
                Producto producto=createProduct(resultSet);
                productoList.add(producto);
            }
        }catch(SQLException e){
            e.printStackTrace(); }
        return productoList;
    }
    public Producto byId(Long id){
        Producto producto = null;
        try(PreparedStatement preparedStatement = getConnection()
                .prepareStatement("SELECT*FROMproductosWHEREid =?")){
            preparedStatement.setLong(1,id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                producto = createProduct(resultSet);
            }
            resultSet.close();
        }catch(SQLException e){
            e.printStackTrace();
        } return producto; }
}
