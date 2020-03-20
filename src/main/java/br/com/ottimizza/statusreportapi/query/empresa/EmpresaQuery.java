package br.com.ottimizza.statusreportapi.query.empresa;

import br.com.ottimizza.statusreportapi.domain.criteria.PageCriteria;
import br.com.ottimizza.statusreportapi.domain.dtos.empresa.EmpresaDTO;

import java.text.SimpleDateFormat;
import static br.com.ottimizza.statusreportapi.query.utils.UtilsQuery.makeStartsAndEndsWith;
import static br.com.ottimizza.statusreportapi.query.utils.UtilsQuery.stringFormat;
import java.lang.reflect.Field;

public class EmpresaQuery {

    public static String empresasEmProjetoQuery(String cnpjContabilidade, EmpresaDTO empresaDTO, PageCriteria pageCriteria, boolean contagem){
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
        soql.append("       o_que_foi_feito_hoje__c,                                            ");
        soql.append("       ultimo_lote_processado__c,                                          ");
        soql.append("       lotes_processados__c                                                ");
        }
        
        soql.append("	FROM salesforce.Empresa__c empresa                                      ");
        soql.append("       WHERE empresa.Empresa_em_Projeto__c < 7                             ");
        soql.append("       AND empresa.Contabilidade_Teste__c = FALSE                          ");
        soql.append("       AND empresa.tipo_de_projeto__c = 'Possui OIC'                       ");
        soql.append("       AND empresa.cnpj_cont_numeros__c  = '"+cnpjContabilidade+"'         ");
        
        // FILTROS
        soql.append(empresaFiltroQuery(empresaDTO));
        
        if(!contagem){
        soql.append("       ORDER BY name                                                       ");
        soql.append(empresaOrderByAndPaginationQuery(pageCriteria));
        }

        return soql.toString();
    }

    public static String empresasIntegradosQuery(String cnpjContabilidade, EmpresaDTO empresaDTO, PageCriteria pageCriteria, boolean contagem){
        
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
        soql.append("       o_que_foi_feito_hoje__c,                                            ");
        soql.append("       ultimo_lote_processado__c,                                          ");
        soql.append("       lotes_processados__c                                                ");
        }
        
        soql.append("	FROM salesforce.Empresa__c empresa                                      ");
        soql.append("       WHERE empresa.Empresa_em_Projeto__c IN (7,8)                        ");
        soql.append("       AND empresa.Contabilidade_Teste__c = FALSE                          ");
        soql.append("       AND empresa.tipo_de_projeto__c = 'Possui OIC'                       ");
        soql.append("       AND empresa.cnpj_cont_numeros__c  = '"+cnpjContabilidade+"'         ");
        
        // FILTROS
        soql.append(empresaFiltroQuery(empresaDTO));
        
        if(!contagem){
        soql.append("       ORDER BY name                                                       ");
        soql.append(empresaOrderByAndPaginationQuery(pageCriteria));
        }
        
        return soql.toString();
    }
    
    public static String lotesProcessadosEmpressasQuery(String cnpjContabilidade){
        
        StringBuilder soql = new StringBuilder();
        soql.append("   SELECT                                                                  ");
        soql.append("       SUM(lotes_processados__c)                                           ");
        soql.append("	FROM salesforce.empresa__c empresa                                      ");
        soql.append("       WHERE empresa.empresa_em_projeto__c IN (7,8)                        ");
        soql.append("       AND empresa.contabilidade_teste__c = FALSE                          ");
        soql.append("       AND empresa.tipo_de_projeto__c = 'Possui OIC'                       ");
        soql.append("       AND empresa.cnpj_cont_numeros__c  = '"+cnpjContabilidade+"'         ");
        
        return soql.toString();
    }

    //QUERIES UTILITÁRIOS EMPRESA

    //<editor-fold defaultstate="collapsed" desc="FILTROS">
    private static String empresaFiltroQuery(EmpresaDTO filtro){
        StringBuilder filtroQuery = new StringBuilder();

        // ID
        if(filtro.getId() != null)
        filtroQuery.append("       AND empresa.id = "+filtro.getId());

        // NOME DA EMPRESA
        if(filtro.getName() != null && filtro.getName() != "")
        filtroQuery.append("       AND empresa.name ILIKE " + makeStartsAndEndsWith(filtro.getName()));

        // ENVOLVIDOS NA EMPRESA
        if(filtro.getEnvolvidos__c() != null && filtro.getEnvolvidos__c() != "")
        filtroQuery.append("       AND empresa.envolvidos__c ILIKE " + makeStartsAndEndsWith(filtro.getEnvolvidos__c()));

        // STATUS DA EMPRESA
        if(filtro.getStatus_resumido__c() != null && filtro.getStatus_resumido__c() != "")
        filtroQuery.append("       AND empresa.empresa_em_projeto__c = " + filtro.getStatus_resumido__c());

        // CODIGO ERP EMPRESA
        if(filtro.getCodigo_empresa_erp__c() != null && filtro.getCodigo_empresa_erp__c() != "")
        filtroQuery.append("       AND empresa.codigo_empresa_erp__c ILIKE " + makeStartsAndEndsWith(filtro.getCodigo_empresa_erp__c()));

        // DATA DO STATUS REPORT
        if(filtro.getStatus_report_data__c() != null){
        SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
        filtroQuery.append("       AND empresa.status_report_data__c = " + stringFormat(dateFormatter.format(filtro.getStatus_report_data__c())));
        }

        // O QUE FOI FEITO NA EMPRESA
        if(filtro.getO_que_foi_feito_hoje__c() != null && filtro.getO_que_foi_feito_hoje__c() != "")
        filtroQuery.append("       AND empresa.o_que_foi_feito_hoje__c ILIKE " + makeStartsAndEndsWith(filtro.getO_que_foi_feito_hoje__c()));

        return filtroQuery.toString();
    }
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="ORDENAÇÃO E PAGINAÇÃO">
    private static String empresaOrderByAndPaginationQuery(PageCriteria pageCriteria){
        StringBuilder orderByPagination = new StringBuilder();
        
        orderByPagination.append("  LIMIT "+pageCriteria.getPageSize());
        orderByPagination.append("  OFFSET "+(pageCriteria.getPageIndex()*pageCriteria.getPageSize()));
        return orderByPagination.toString();
    }
    //</editor-fold>
}