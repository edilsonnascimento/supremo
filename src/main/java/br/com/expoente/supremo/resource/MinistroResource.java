package br.com.expoente.supremo.resource;

import br.com.expoente.supremo.dao.MinistroDao;
import br.com.expoente.supremo.dao.PlanilhaDAO;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import org.jboss.resteasy.plugins.providers.multipart.MultipartFormDataInput;

import java.io.IOException;
import java.io.InputStream;

import java.net.URI;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * REST Web Service
 *
 * @author ednascimento
 */
@Path("ministro")
@Produces(MediaType.APPLICATION_JSON)
public class MinistroResource {

    @Inject
    MinistroDao ministroDao;

    @Inject
    PlanilhaDAO planilhaDAO;

    public MinistroResource() {
    }

    @GET
    public String getJson() {
        return "TESTE DE RETORNO";
    }

    @PUT
    @Consumes("multipart/form-data")
    public Response updadeFile(MultipartFormDataInput file, @QueryParam("codigoPlanilha") Integer codigoPlanilha) throws IOException {

        InputStream in = file.getFormDataPart("file", InputStream.class, null);
        //Planilha
        Workbook workbook = new XSSFWorkbook(in);

        in.close();

        planilhaDAO.planilha(workbook, codigoPlanilha);

        URI uri = URI.create("/importar/" + codigoPlanilha);

        return Response.created(uri).build();
    }
}
