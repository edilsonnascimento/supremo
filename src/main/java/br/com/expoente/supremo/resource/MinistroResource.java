package br.com.expoente.supremo.resource;

import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.core.MediaType;

/**
 * REST Web Service
 *
 * @author ednascimento
 */
@Path("ministro")
@Produces(MediaType.APPLICATION_JSON)
public class MinistroResource {

    public MinistroResource() {
    }

    @GET
    public String getJson() {
        return "TESTE DE RETORNO";
    }

    @PUT
    public void putJson(String content) {
    }
}
