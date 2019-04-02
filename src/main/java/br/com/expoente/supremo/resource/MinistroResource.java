package br.com.expoente.supremo.resource;

import br.com.expoente.supremo.service.MinistroService;
import br.com.expoente.supremo.service.PlanilhaService;
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
@Path("ministros")
@Produces(MediaType.APPLICATION_JSON)
public class MinistroResource {

	@Inject
	MinistroService ministroService;

	@Inject
	PlanilhaService planilhaService;

	public MinistroResource() {
	}

	@GET
	@Path("testar")
	public String getJson() {
		return "<TESTE DE RETORNO>";
	}

	//URI exemplo: http://localhost:8080/supremo/api/ministro/2
	@GET
	@Path("{id}")
	public Ministro retornaMinistro(@PathParam("id") Integer id) {
		return ministroService.buscaMinistro(id);
	}

	@GET
	public List<Ministro> retornaMinistros() {
		return ministroService.buscaMinistros();
	}

	// URI exemplo: http://localhost:8080/supremo/api/ministro/?codigoPlanilha=302
	@POST
	@Path("importar")
	@Consumes("multipart/form-data")
	public Response updadeFile(MultipartFormDataInput file, @QueryParam("codigoPlanilha") Integer codigoPlanilha) throws IOException {

		InputStream in = file.getFormDataPart("file", InputStream.class, null);

		//Planilha
		Workbook workbook = new XSSFWorkbook(in);

		planilhaService.planilha(workbook, codigoPlanilha);

		URI uri = URI.create("/importar/" + codigoPlanilha);

		in.close();

		return Response.created(uri).build();
	}

	@GET
	@Path("aposentam/{quantidadeAnos}")
	public List<Ministro> aposentamEm(@PathParam("quantidadeAnos") Integer quantidadeAnos) {
		return ministroService.ministroMaisVelho(quantidadeAnos);
	}

	@GET
	@Path("presidente/{nome}")
	public List<Ministro> nomeado(@PathParam("nome") String presidente) {
		return ministroService.ministroIndicados(presidente);
	}

//    Content-Type: application/json
//    {"dataNascimento": "1972-08-11T00:00:00-02:00[America/Sao_Paulo]",
//     "nome": "Sérgio Fernando Moro",
//      "presidente": "Bolsonaro"}
//     http://localhost:8080/supremo/api/ministro/
	@POST
	public Response cadastrar(Ministro ministro) {
		ministroService.salvar(ministro);
		URI uri = URI.create("/ministros/" + ministro.getId());
		return Response.created(uri).build();
	}

	@GET
	@Path("ministro")
	public List<Ministro> pesquisaNome(@QueryParam("nome") String nome) {
		return ministroService.buscaNome(nome);
	}

}
