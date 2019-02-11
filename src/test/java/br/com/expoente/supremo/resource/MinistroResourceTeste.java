package br.com.expoente.supremo.resource;

import br.com.expoente.supremo.dao.MinistroDAO;
import br.com.expoente.supremo.entity.Ministro;

/**
 *
 * @author edilson
 */
public class MinistroResourceTeste {
    
    
    public static void main (String[] args){
        MinistroDAO ministroDao = new MinistroDAO();
        Ministro ministro = ministroDao.ministroMaisVelho();
        
        System.out.print(ministro);
        
    }
}