package br.com.ottimizza.statusreportapi.query.empresa;

import br.com.ottimizza.statusreportapi.domain.criteria.PageCriteria;

public class EmpresaQuery {

    public static String empresasEmProjetoQuery(String cnpjContabilidade, PageCriteria pageCriteria, boolean contagem){
        
        StringBuilder soql = new StringBuilder();
        soql.append("   SELECT                                                                  ");
        
        if(contagem)
        soql.append("       COUNT(*)                                                            ");
        else{            
        soql.append("       id,                                                                 ");
        soql.append("       name,                                                               ");
        soql.append("       envolvidos__c,                                                      ");
        soql.append("       status_resumido__c,                                                 ");
        soql.append("       status_report_data__c,                                              ");
        soql.append("       codigo_empresa_erp__c,                                              ");
        soql.append("       nome_contabilidade__c,                                              ");
        soql.append("       proximo_passo__c,                                                   ");
        soql.append("       nome_resumido__c,                                                   ");
        soql.append("       resumo_prox_passo__c,                                               ");
        soql.append("       o_que_foi_feito_hoje__c                                             ");
        }
        
        soql.append("	FROM salesforce.Empresa__c empresa                                      ");
        soql.append("       WHERE empresa.Empresa_em_Projeto__c < 7                             ");
        soql.append("       AND empresa.Contabilidade_Teste__c = FALSE                          ");
        soql.append("       AND empresa.cnpj_cont_numeros__c  = '"+cnpjContabilidade+"'         ");
        
        if(!contagem){
        soql.append("       ORDER BY name                                                       ");
        soql.append("       LIMIT "+pageCriteria.getPageSize()+"                                ");
        soql.append("       OFFSET "+(pageCriteria.getPageIndex()*pageCriteria.getPageSize())+" ");
        }

        return soql.toString();
    }

    public static String empresasIntegradosQuery(String cnpjContabilidade, PageCriteria pageCriteria, boolean contagem){
        
        StringBuilder soql = new StringBuilder();
        soql.append("   SELECT                                                                  ");
        
        if(contagem)
        soql.append("       COUNT(*)                                                            ");
        else{            
        soql.append("       id,                                                                 ");
        soql.append("       name,                                                               ");
        soql.append("       envolvidos__c,                                                      ");
        soql.append("       status_resumido__c,                                                 ");
        soql.append("       status_report_data__c,                                              ");
        soql.append("       codigo_empresa_erp__c,                                              ");
        soql.append("       nome_contabilidade__c,                                              ");
        soql.append("       proximo_passo__c,                                                   ");
        soql.append("       nome_resumido__c,                                                   ");
        soql.append("       resumo_prox_passo__c,                                               ");
        soql.append("       o_que_foi_feito_hoje__c                                             ");
        }
        
        soql.append("	FROM salesforce.Empresa__c empresa                                      ");
        soql.append("       WHERE empresa.Empresa_em_Projeto__c IN (7,8)                        ");
        soql.append("       AND empresa.Contabilidade_Teste__c = FALSE                          ");
        soql.append("       AND empresa.cnpj_cont_numeros__c  = '"+cnpjContabilidade+"'         ");
        
        if(!contagem){
        soql.append("       ORDER BY name                                                       ");
        soql.append("       LIMIT "+pageCriteria.getPageSize()+"                                ");
        soql.append("       OFFSET "+(pageCriteria.getPageIndex()*pageCriteria.getPageSize())+" ");
        }

        return soql.toString();
    }
}