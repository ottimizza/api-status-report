package br.com.ottimizza.statusreportapi.services;

import br.com.ottimizza.statusreportapi.client.OAuthClient;
import br.com.ottimizza.statusreportapi.client.SalesforceClient;
import br.com.ottimizza.statusreportapi.constraints.SalesForceAPIMethodExecution;
import br.com.ottimizza.statusreportapi.domain.criteria.PageCriteria;
import br.com.ottimizza.statusreportapi.domain.dtos.empresa.EmpresaDTO;
import br.com.ottimizza.statusreportapi.domain.dtos.user.UserDTO;
import br.com.ottimizza.statusreportapi.domain.responses.GenericResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.inject.Inject;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;
import org.springframework.stereotype.Service;

import br.com.ottimizza.statusreportapi.domain.responses.GenericPageableResponse;
import br.com.ottimizza.statusreportapi.domain.responses.PageInfoResponseObject;
import static br.com.ottimizza.statusreportapi.domain.mappers.EmpresaMapper.fromObjects;
import static br.com.ottimizza.statusreportapi.query.empresa.EmpresaQuery.empresasEmProjetoQuery;
import static br.com.ottimizza.statusreportapi.query.empresa.EmpresaQuery.empresasIntegradosQuery;
import static br.com.ottimizza.statusreportapi.query.empresa.EmpresaQuery.lotesProcessadosEmpressasQuery;

@Service
public class EmpresaService {
    
    @Inject
    SalesforceClient salesforceClient;
    
    @Inject
    OAuthClient oauthClient;

    public <T> T buscaListaEmpresasProjeto(EmpresaDTO empresaDTO, PageCriteria pageCriteria, OAuth2Authentication authentication) throws Exception {
        final OAuth2AuthenticationDetails details = (OAuth2AuthenticationDetails) authentication.getDetails();
        String accessToken = "Bearer " + details.getTokenValue();

        ResponseEntity<GenericResponse<UserDTO>> usuario = oauthClient.getUserInfo(accessToken);
        String cnpjContabilidade = usuario.getBody().getRecord().getOrganization().getCnpj();

        // LISTA DE EMPRESAS
        HttpEntity entityLista = salesforceClient.getQuery(
            empresasEmProjetoQuery(cnpjContabilidade, empresaDTO, pageCriteria, false),
            SalesForceAPIMethodExecution.REST,
            accessToken);

        HashMap<List<Object[]>, Object> map = (HashMap<List<Object[]>, Object>) entityLista.getBody();
        List<EmpresaDTO> empresas = (List<EmpresaDTO>) map.get("records");

        /*List<Object[]> salesForceListResult = new ArrayList<>();
        ((List) entityLista.getBody()).stream().forEach(
                objects -> salesForceListResult.add(((ArrayList<Object>) objects).toArray())
        );*/

        // CONTAGEM DE EMPRESAS
        HttpEntity entityContagem = salesforceClient.getQuery(
            empresasEmProjetoQuery(cnpjContabilidade, empresaDTO, pageCriteria, true),
            SalesForceAPIMethodExecution.REST,
            accessToken);

        HashMap<List<Object[]>, Object> cont = (HashMap<List<Object[]>, Object>) entityContagem.getBody();
        Integer quantidade =  (Integer) cont.get("totalSize");

        GenericPageableResponse<EmpresaDTO> empresasEmProjeto = new GenericPageableResponse<>(
                empresas,
                new PageInfoResponseObject(pageCriteria, quantidade));

        return (T) empresasEmProjeto;
    }

    
    public <T> T buscaQuantidadeEmpresasProjeto(OAuth2Authentication authentication) throws Exception {
        final OAuth2AuthenticationDetails details = (OAuth2AuthenticationDetails) authentication.getDetails();
        String accessToken = "Bearer " + details.getTokenValue();

        ResponseEntity<GenericResponse<UserDTO>> usuario = oauthClient.getUserInfo(accessToken);
        String cnpjContabilidade = usuario.getBody().getRecord().getOrganization().getCnpj();
        
        // CONTAGEM DE EMPRESAS
        HttpEntity entityContagem = salesforceClient.getQuery(
            empresasEmProjetoQuery(cnpjContabilidade, null, null, true),
            SalesForceAPIMethodExecution.REST,
            accessToken);

        HashMap<List<Object[]>, Object> cont = (HashMap<List<Object[]>, Object>) entityContagem.getBody();
        Integer quantidade =  (Integer) cont.get("totalSize");

        return (T) new GenericResponse<Integer>(quantidade);
    }
    
    public <T> T buscaListaEmpresasIntegrados(EmpresaDTO empresaDTO, PageCriteria pageCriteria, OAuth2Authentication authentication) throws Exception {
        final OAuth2AuthenticationDetails details = (OAuth2AuthenticationDetails) authentication.getDetails();
        String accessToken = "Bearer " + details.getTokenValue();

        ResponseEntity<GenericResponse<UserDTO>> usuario = oauthClient.getUserInfo(accessToken);
        String cnpjContabilidade = usuario.getBody().getRecord().getOrganization().getCnpj();

        // LISTA DE EMPRESAS
        HttpEntity entityLista = salesforceClient.getQuery(
            empresasIntegradosQuery(cnpjContabilidade, empresaDTO, pageCriteria, false),
            SalesForceAPIMethodExecution.REST,
            accessToken);

        HashMap<List<Object[]>, Object> map = (HashMap<List<Object[]>, Object>) entityLista.getBody();
        List<EmpresaDTO> empresas = (List<EmpresaDTO>) map.get("records");

        /*List<Object[]> salesForceListResult = new ArrayList<>();
        ((List) entityLista.getBody()).stream().forEach(
            objects -> salesForceListResult.add(((ArrayList<Object>) objects).toArray())
        );*/

        // CONTAGEM DE EMPRESAS
        HttpEntity entityContagem = salesforceClient.getQuery(
            empresasIntegradosQuery(cnpjContabilidade, empresaDTO, pageCriteria, true),
            SalesForceAPIMethodExecution.REST,
            accessToken);

        HashMap<List<Object[]>, Object> cont = (HashMap<List<Object[]>, Object>) entityContagem.getBody();
        Integer quantidade =  (Integer) cont.get("totalSize");

        GenericPageableResponse<EmpresaDTO> empresasEmProjeto = new GenericPageableResponse<>(
                empresas,
                new PageInfoResponseObject(pageCriteria,quantidade));

        return (T) empresasEmProjeto;
    }
    
    public <T> T buscaQuantidadeEmpresasIntegrados(OAuth2Authentication authentication) throws Exception {
        final OAuth2AuthenticationDetails details = (OAuth2AuthenticationDetails) authentication.getDetails();
        String accessToken = "Bearer " + details.getTokenValue();

        ResponseEntity<GenericResponse<UserDTO>> usuario = oauthClient.getUserInfo(accessToken);
        String cnpjContabilidade = usuario.getBody().getRecord().getOrganization().getCnpj();
        
        // CONTAGEM DE EMPRESAS
        HttpEntity entityContagem = salesforceClient.getQuery(
            empresasIntegradosQuery(cnpjContabilidade, null, null, true),
            SalesForceAPIMethodExecution.REST,
            accessToken);

        HashMap<List<Object[]>, Object> cont = (HashMap<List<Object[]>, Object>) entityContagem.getBody();
        Integer quantidade =  (Integer) cont.get("totalSize");

        return (T) new GenericResponse<Integer>(quantidade);
    }
    
    public <T> T quantidadeDeLotesProcessadosEmpresas(OAuth2Authentication authentication) throws Exception {
        final OAuth2AuthenticationDetails details = (OAuth2AuthenticationDetails) authentication.getDetails();
        String accessToken = "Bearer " + details.getTokenValue();

        ResponseEntity<GenericResponse<UserDTO>> usuario = oauthClient.getUserInfo(accessToken);
        String cnpjContabilidade = usuario.getBody().getRecord().getOrganization().getCnpj();
        
        // CONTAGEM DE EMPRESAS
        HttpEntity entityContagem = salesforceClient.getQuery(
            lotesProcessadosEmpressasQuery(cnpjContabilidade),
            SalesForceAPIMethodExecution.REST,
            accessToken);

        HashMap<List<Object[]>, Object> cont = (HashMap<List<Object[]>, Object>) entityContagem.getBody();
        Integer quantidade =  (Integer) cont.get("totalSize");

        return (T) new GenericResponse<Integer>(quantidade);
    }
}