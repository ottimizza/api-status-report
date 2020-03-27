package br.com.ottimizza.statusreportapi.domain.dtos.empresa;

import java.io.Serializable;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder(toBuilder = true)
@AllArgsConstructor @NoArgsConstructor
public class EmpresaDTO implements Serializable{
    
    private Integer id;
    private String  name;
    private String  envolvidos__c;
    private String  status_resumido__c;
    private Date    status_report_data__c;
    private String  codigo_empresa_erp__c;
    private String  nome_contabilidade__c;
    private Date    proximo_passo__c;
    private String  nome_resumido__c;
    private String  resumo_prox_passo__c;
    private String  o_que_foi_feito_hoje__c;
    private Date    ultimo_lote_processado__c;
    private Double  lotes_processados__c;
}