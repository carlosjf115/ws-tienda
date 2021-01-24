
package com.tienda.db;

/**
 *
 * @author cjflores
 */
import java.sql.*;
public class Conexion {
   private static Connection cnx = null;
   public static Connection conectar() throws SQLException, ClassNotFoundException {
      if (cnx == null) {
         try {
            Class.forName("com.mysql.jdbc.Driver");
            cnx = DriverManager.getConnection("jdbc:mysql://localhost/tienda", "root", "");
         } catch (SQLException ex) {
            throw new SQLException(ex);
         } catch (ClassNotFoundException ex) {
            throw new ClassCastException(ex.getMessage());
         }
      }
      return cnx;
   }
   public static void desconectar() throws SQLException {
      if (cnx != null) {
         cnx.close();
      }
   }
}