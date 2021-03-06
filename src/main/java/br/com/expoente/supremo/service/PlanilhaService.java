package br.com.expoente.supremo.service;

import br.com.expoente.supremo.entity.Ministro;
import java.util.Calendar;
import java.util.Date;
import javax.ejb.Stateless;
import javax.inject.Inject;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

@Stateless
public class PlanilhaService {

	@Inject
	private MinistroService ministroService;

	public void planilha(Workbook planilha, Integer codigoPlanilha) {
		Sheet sheet = planilha.getSheetAt(0);

		for (int i = 1; i <= sheet.getLastRowNum(); i++) {
			Row row = sheet.getRow(i);
			Ministro ministro = new Ministro();
			for (int j = 0; j <= 2; j++) {
				Cell cell = row.getCell(j);
				switch (cell.getColumnIndex()) {
					case 0:
						ministro.setNome(cell.getStringCellValue());
						break;
					case 1:
						Calendar calendar = Calendar.getInstance();
						Date data = cell.getDateCellValue();
						calendar.setTime(data);
						ministro.setDataNascimento(calendar);
						break;
					case 2:
						ministro.setPresidente(cell.getStringCellValue());
						break;
				}
			}
			ministroService.salvar(ministro);
		}
	}
}
