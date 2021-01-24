
package com.tienda.modelo;

import com.tienda.entidad.Cliente;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;

/**
 *
 * @author cjflores
 */
@Stateless
public class ClienteFacade extends AbstractFacade {
    
    @Override
    public void insert(Object entity,Connection conexion) {
        Cliente cliente = (Cliente) entity;
        try{
            PreparedStatement query = conexion.prepareStatement("INSERT INTO CLIENTE(id_cliente, nombres_cliente, apellidos_cliente) VALUES (null,?,?)");
            //query.setLong(1, cliente.getIdCliente());
            query.setString(1, cliente.getNombresCliente());
            query.setString(2, cliente.getApellidosCliente());
            
            query.executeUpdate();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    
    public List<Cliente> findAll(Connection conexion){
        List<Cliente> listClientes = new ArrayList<>();
        ResultSet rs;
        try{
            PreparedStatement query = conexion.prepareStatement("SELECT * FROM CLIENTE");
            rs = query.executeQuery();
            while(rs.next()){
                Cliente cliente = new Cliente();
                cliente.setIdCliente(rs.getLong("id_cliente"));
                cliente.setNombresCliente(rs.getString("nombres_cliente"));
                cliente.setApellidosCliente(rs.getString("apellidos_cliente"));
                
                listClientes.add(cliente);
            }
            
        } catch (Exception e){
            e.printStackTrace();
        }
     
        return listClientes;
    }

    @Override
    public void delete(Object entity, Connection conexion) throws Exception {
         Cliente cliente = (Cliente) entity;
        try{
            PreparedStatement query = conexion.prepareStatement("DELETE FROM CLIENTE WHERE id_cliente = ?");
            query.setLong(1, cliente.getIdCliente());
            
            query.executeUpdate();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Object entity, Connection conexion) throws Exception {
        Cliente cliente = (Cliente) entity;
        try{
            PreparedStatement query = conexion.prepareStatement("UPDATE CLIENTE SET nombres_cliente = ?, apellidos_cliente = ? WHERE id_cliente = ?");
            query.setString(1, cliente.getNombresCliente());
            query.setString(2, cliente.getApellidosCliente());
            query.setLong(3, cliente.getIdCliente());
            
            query.executeUpdate();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Cliente find(Object id, Connection conexion) {
        Cliente cliente = null;
        String idCliente = (String) (Object) id;
        ResultSet rs;
        try {
            PreparedStatement query = conexion.prepareStatement("SELECT * FROM CLIENTE WHERE id_cliente = ?");
            query.setString(1, idCliente);
            rs = query.executeQuery();
            if (rs.next()) {
                cliente = new Cliente();
                cliente.setIdCliente(rs.getLong("id_cliente"));
                cliente.setNombresCliente(rs.getString("nombres_cliente"));
                cliente.setApellidosCliente(rs.getString("apellidos_cliente"));
            } else {
                cliente = null;
            }

        } catch (Exception e) {
            cliente = null;
            e.printStackTrace();
        }

        return cliente;
    }
    
}
