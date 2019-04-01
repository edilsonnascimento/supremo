package br.com.expoente.supremo.config;

import br.com.expoente.supremo.entity.Ministro;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Schedule;
import javax.ejb.Singleton;
import javax.inject.Inject;

/**
 *
 * @author ednascimento
 */
@Singleton
public class ImportacaoClienteSchedule {

    @Inject
    Ministro ministro;
    private static final Logger LOG = Logger.getLogger(ImportacaoClienteSchedule.class.getName());

    //@Schedule(hour = "*", minute = "1", second = "*/30", persistent = false)
    @Schedule(hour = "*", minute = "*/2", second = "0", persistent = false)
    public void importar() {
        LOG.log(Level.INFO, "Importação de clientes iniciada em {0}", new Date());
    }
}
