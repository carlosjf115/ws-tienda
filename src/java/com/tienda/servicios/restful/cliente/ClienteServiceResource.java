
package com.tienda.servicios.restful.cliente;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.tienda.entidad.Cliente;
import com.tienda.db.Conexion;
import javax.ejb.EJB;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.core.MediaType;
import com.tienda.modelo.ClienteFacade;
import java.util.ArrayList;
import java.util.List;
import static java.util.Locale.lookup;
import javax.ejb.Stateless;

/**
 * REST Web Service
 *
 * @author cjflores
 */

@Stateless
@Path("ClienteService")
public class ClienteServiceResource {
    
    
    @EJB
    ClienteFacade clienteFacade;
    /*
    @Context
    private UriInfo context;
    */
    /**
     * Creates a new instance of ClienteServiceResource
     */
    
    /*EJB(lookup = "java:app/ServiciosRestFulOmnisport-ejb/CuentaCorrienteFacadeJDBC")
    ClienteFacade clienteFacade;*/
    
    
    public ClienteServiceResource() {
    }

    /**
     * Retrieves representation of an instance of com.tienda.servicios.restful.cliente.ClienteServiceResource
     * @return an instance of java.lang.String
     */
    /*
    @GET
    @Produces(MediaType.APPLICATION_XML)
    public String getXml() {
        //TODO return proper representation object
        throw new UnsupportedOperationException();
    }
    */

    /**
     * PUT method for updating or creating an instance of ClienteServiceResource
     * @param content representation for the resource
     */
    @PUT
    @Produces("application/json")
    @Consumes("application/json")
    @Path("/obtenerClientes")
    public String buscarTodosClientes(String json) {
        JsonObject jason = new JsonObject();
        Gson gson = new Gson();
        String jsonResult = null;
        ClienteResponse response = null;
        //ClienteRequest request = gson.fromJson(json, ClienteRequest.class);
        try{
            List<Cliente> listaClientes = clienteFacade.findAll(Conexion.conectar());
            //response = construirResponseObtenerClientes(request);
            jsonResult = getJsonFromClientes(listaClientes);
            
        } catch (Exception e){
            e.printStackTrace();
            jason.addProperty("codEstado", 0);
            jason.addProperty("msjEstado", e.getMessage());
        }

       /* if (response != null) {
            return gson.toJson(response);
        } else {
            return gson.toJson(jason);
        }
        */
       
       return jsonResult;
    }
    
    @PUT
    @Produces("application/json")
    @Consumes("application/json")
    @Path("/obtenerCliente")
    public String buscarCliente(String json) {
        JsonObject jason = new JsonObject();
        Gson gson = new Gson();
        ClienteResponse response = null;
        ClienteRequest request = gson.fromJson(json, ClienteRequest.class);
        try {
            response = construirResponseObtenerCliente(request);

        } catch (Exception e) {
            e.printStackTrace();
            jason.addProperty("codEstado", 0);
            jason.addProperty("msjEstado", e.getMessage());
        }

        if (response != null) {
            return gson.toJson(response);
        } else {
            return gson.toJson(jason);
        }
    }
    
    
    
    @PUT
    @Produces("application/json")
    @Consumes("application/json")
    @Path("/crearCliente")
    public String insertarCliente(String json) {
        JsonObject jason = new JsonObject();
        Gson gson = new Gson();
        //ClienteResponse response = null;
        ClienteRequest request = gson.fromJson(json, ClienteRequest.class);
        try {
            Cliente cliente = new Cliente();
            //cliente.setIdCliente(Long.parseLong(request.getIdCliente()));
            cliente.setNombresCliente(request.getNombresCliente());
            cliente.setApellidosCliente(request.getApellidosCliente());
            
            clienteFacade.insert(cliente, Conexion.conectar());
            
            jason.addProperty("codEstado", 1);
            jason.addProperty("msjEstado", "Cliente creado exitosamente");

        } catch (Exception e) {
            e.printStackTrace();
            jason.addProperty("codEstado", 0);
            jason.addProperty("msjEstado", e.getMessage());
        }

        /*if (response != null) {
            return gson.toJson(response);
        } else {*/
            return gson.toJson(jason);
        //}
    }
    
    @PUT
    @Produces("application/json")
    @Consumes("application/json")
    @Path("/eliminarCliente")
    public String deleteCliente(String json) {
        JsonObject jason = new JsonObject();
        Gson gson = new Gson();
        //ClienteResponse response = null;
        ClienteRequest request = gson.fromJson(json, ClienteRequest.class);
        try {
            if (request.getIdCliente() == null || request.getIdCliente().trim().isEmpty()) {
                jason.addProperty("codEstado", 0);
                jason.addProperty("msjEstado", "idCliente es requerido");
            } else {
                Cliente cliente = new Cliente();
                cliente.setIdCliente(Long.parseLong(request.getIdCliente()));
                cliente.setNombresCliente(request.getNombresCliente());
                cliente.setApellidosCliente(request.getApellidosCliente());

                clienteFacade.delete(cliente, Conexion.conectar());

                jason.addProperty("codEstado", 1);
                jason.addProperty("msjEstado", "Cliente eliminado exitosamente");
            }
        } catch (Exception e) {
            e.printStackTrace();
            jason.addProperty("codEstado", 0);
            jason.addProperty("msjEstado", e.getMessage());
        }

        /*if (response != null) {
            return gson.toJson(response);
        } else {*/
        return gson.toJson(jason);
        //}
    }
    
    
    @PUT
    @Produces("application/json")
    @Consumes("application/json")
    @Path("/actualizarCliente")
    public String updateCliente(String json) {
        JsonObject jason = new JsonObject();
        Gson gson = new Gson();
        //ClienteResponse response = null;
        ClienteRequest request = gson.fromJson(json, ClienteRequest.class);
        try {
            Cliente cliente = new Cliente();
            cliente.setIdCliente(Long.parseLong(request.getIdCliente()));
            cliente.setNombresCliente(request.getNombresCliente());
            cliente.setApellidosCliente(request.getApellidosCliente());

            clienteFacade.update(cliente, Conexion.conectar());

            jason.addProperty("codEstado", 1);
            jason.addProperty("msjEstado", "Cliente creado exitosamente");

        } catch (Exception e) {
            e.printStackTrace();
            jason.addProperty("codEstado", 0);
            jason.addProperty("msjEstado", e.getMessage());
        }

        /*if (response != null) {
            return gson.toJson(response);
        } else {*/
        return gson.toJson(jason);
        //}
    }
    
    public String getJsonFromClientes(List<Cliente> listaClientes) {
        List<JsonObject> respuesta;
        List<List<JsonObject>> row = new ArrayList<List<JsonObject>>();
        JsonObject jason;
        Gson gson = new Gson();
        for (Cliente c : listaClientes) {
            respuesta = new ArrayList<JsonObject>();

            jason = new JsonObject();
            jason.addProperty("tipo", "cadena");
            jason.addProperty("columna", "idCliente");
            jason.addProperty("valor", c.getIdCliente().toString());
            respuesta.add(jason);

            jason = new JsonObject();
            jason.addProperty("tipo", "cadena");
            jason.addProperty("columna", "nombresCliente");
            jason.addProperty("valor", c.getNombresCliente());
            respuesta.add(jason);

            jason = new JsonObject();
            jason.addProperty("tipo", "cadena");
            jason.addProperty("columna", "apellidosCliente");
            jason.addProperty("valor", c.getApellidosCliente());
            respuesta.add(jason);

            row.add(respuesta);
        }

        return gson.toJson(row);

    }
        
    
    
    private ClienteResponse construirResponseObtenerClientes (ClienteRequest request) {
        ClienteResponse response = new ClienteResponse();
        List<Cliente> listaClientes = new ArrayList<>();
        try{
           // ClienteFacade clienteFacade = new ClienteFacade();
            listaClientes = clienteFacade.findAll(Conexion.conectar());
            if(listaClientes != null){
                List<ClienteResponseDetalle> listaDetalle = new ArrayList<>();
                for(Cliente result: listaClientes) {
                    ClienteResponseDetalle clienteDetalle = new ClienteResponseDetalle();
                    clienteDetalle.setIdCliente(result.getIdCliente().toString());
                    clienteDetalle.setNombresCliente(result.getNombresCliente());
                    clienteDetalle.setApellidosCliente(result.getApellidosCliente());
                    
                    listaDetalle.add(clienteDetalle);
                }
                /*response.setCodEstado(1);
                response.setMsjEstado("Transacción exitosa.");*/
                response.setListaDetalle(listaDetalle);
                
            } else {
                /*response.setCodEstado(3);
                response.setMsjEstado("No se recupero información para el indicador y fecha especificados.");*/
                response = null;
            }
            
        }catch (Exception e) {
            e.printStackTrace();
        }
        
        return response;
    }
    
    private ClienteResponse construirResponseObtenerCliente(ClienteRequest request) {
        ClienteResponse response = new ClienteResponse();
        Cliente cliente = new Cliente();
        try {
            cliente = clienteFacade.find(request.getIdCliente(), Conexion.conectar());
            if (cliente != null) {
                ClienteResponseDetalle clienteDetalle = new ClienteResponseDetalle();
                List<ClienteResponseDetalle> listaDetalle = new ArrayList<>();
                clienteDetalle.setIdCliente(cliente.getIdCliente().toString());
                clienteDetalle.setNombresCliente(cliente.getNombresCliente());
                clienteDetalle.setApellidosCliente(cliente.getApellidosCliente());

                listaDetalle.add(clienteDetalle);

                /*response.setCodEstado(1);
                response.setMsjEstado("Transacción exitosa.");*/
                response.setListaDetalle(listaDetalle);
            } else {
                /*response.setCodEstado(2);
                response.setMsjEstado("No se recupero información del cliente seleccionado");*/
                response = null;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return response;
    }
    
}
