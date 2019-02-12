package br.com.expoente.supremo.resource;

import br.com.expoente.supremo.dao.MinistroDAO;
import br.com.expoente.supremo.dao.PlanilhaDAO;
import br.com.expoente.supremo.entity.Ministro;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import org.jboss.resteasy.plugins.providers.multipart.MultipartFormDataInput;

import java.io.IOException;
import java.io.InputStream;

import java.net.URI;
import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
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
    MinistroDAO ministroDao;

    @Inject
    PlanilhaDAO planilhaDAO;

    public MinistroResource() {
    }

    @GET
    @Path("test")
    public String getJson() {
        return "TESTE DE RETORNO";
    }

    //URI exemplo: http://localhost:8080/supremo/api/ministro/2
    @GET
    @Path("{id}")
    public Ministro retornaMinistro(@PathParam("id") Integer id) {
        return ministroDao.buscaMinistro(id);
    }

    @GET
    public List<Ministro> retornaMinistros() {
        return ministroDao.buscaMinistros();
    }

    // URI exemplo: http://localhost:8080/supremo/api/ministro/?codigoPlanilha=302
    @POST
    @Path("planilha")
    @Consumes("multipart/form-data")
    public Response updadeFile(MultipartFormDataInput file, @QueryParam("codigoPlanilha") Integer codigoPlanilha) throws IOException {

        InputStream in = file.getFormDataPart("file", InputStream.class, null);

        //Planilha
        Workbook workbook = new XSSFWorkbook(in);

        planilhaDAO.planilha(workbook, codigoPlanilha);

        URI uri = URI.create("/importar/" + codigoPlanilha);

        in.close();

        return Response.created(uri).build();
    }

    @GET
    @Path("aposentam/{quantidadeAnos}")
    public List<Ministro> aposentamEm(@PathParam("quantidadeAnos") Integer quantidadeAnos) {
        return ministroDao.ministroMaisVelho(quantidadeAnos);
    }

    @GET
    @Path("presidente/{nome}")
    public List<Ministro> nomeado(@PathParam("nome") String presidente) {
        return ministroDao.ministroIndicados(presidente);
    }

//    Content-Type: application/json
//    {"dataNascimento": "1972-08-11T00:00:00-02:00[America/Sao_Paulo]",
//     "nome": "Sérgio Fernando Moro",
//      "presidente": "Bolsonaro"}
//     http://localhost:8080/supremo/api/ministro/
    @POST
    public Response cadastrar(Ministro ministro) {
        ministroDao.salvar(ministro);
        URI uri = URI.create("/ministro/" + ministro.getId());
        return Response.created(uri).build();
    }

}
