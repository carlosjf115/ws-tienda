/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tienda.modelo;

import java.sql.Connection;

/**
 *
 * @author cjflores
 */
public abstract class AbstractFacade {
    
    public abstract void insert(Object entity,Connection conexion)throws Exception;
    public abstract void delete(Object entity,Connection conexion)throws Exception;
    public abstract void update(Object entity,Connection conexion)throws Exception;
    public abstract Object find(Object id,Connection conexion);
    
}
