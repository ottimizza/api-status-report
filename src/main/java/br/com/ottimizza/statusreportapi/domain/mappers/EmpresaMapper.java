package br.com.ottimizza.statusreportapi.domain.mappers;

import br.com.ottimizza.statusreportapi.domain.dtos.empresa.EmpresaDTO;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class EmpresaMapper {
    
    public static EmpresaDTO fromObjectArray(Object[] objects){

        //TRATAMENTO DE DATA (status_report_data__c)
        Date statusReportData = null;
        if(objects[4] != null){
            SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
            try {
                statusReportData = dateFormatter.parse((String) objects[4]);
            } catch (Exception e) {}
        }

        SimpleDateFormat dateTimeStampFormatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
        //TRATAMENTO DE DATA (proximo_passo__c)
        Date proximoPasso = null;
        if(objects[7] != null){
            try {
                proximoPasso = dateTimeStampFormatter.parse((String) objects[7]);
            } catch (Exception e) {}
        }

        //TRATAMENTO DE DATA (proximo_passo__c)
        Date ultimoLoteProcessado = null;
        if(objects[11] != null){
            try {
                ultimoLoteProcessado = dateTimeStampFormatter.parse((String) objects[11]);
            } catch (Exception e) {}
        }

        return EmpresaDTO.builder()
            .id                         ((Integer) objects[0])
            .name                       ((String) objects[1])
            .envolvidos__c              (objects[2]  != null ? (String) objects[2]  : null)
            .status_resumido__c         (objects[3]  != null ? (String) objects[3]  : null)
            .status_report_data__c      (statusReportData)
            .codigo_empresa_erp__c      (objects[5]  != null ? (String) objects[5]  : null)
            .nome_contabilidade__c      (objects[6]  != null ? (String) objects[6]  : null)
            .proximo_passo__c           (proximoPasso)
            .nome_resumido__c           (objects[8]  != null ? (String) objects[8]  : null)
            .resumo_prox_passo__c       (objects[9]  != null ? (String) objects[9]  : null)
            .o_que_foi_feito_hoje__c    (objects[10] != null ? (String) objects[10] : null)
            .ultimo_lote_processado__c  (ultimoLoteProcessado)
            .lotes_processados__c       ((Double) objects[12])
        .build();
    }

    public static List<EmpresaDTO> fromObjects(List<Object[]> listObjects) {
        return listObjects.stream().map(objects -> fromObjectArray(objects)).collect(Collectors.toList());
    }
}