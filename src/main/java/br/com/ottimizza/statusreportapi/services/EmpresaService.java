package br.com.ottimizza.statusreportapi.services;

import br.com.ottimizza.statusreportapi.client.OAuthClient;
import br.com.ottimizza.statusreportapi.client.SalesforceClient;
import br.com.ottimizza.statusreportapi.constraints.SalesForceAPIMethodExecution;
import br.com.ottimizza.statusreportapi.domain.criteria.PageCriteria;
import br.com.ottimizza.statusreportapi.domain.dtos.empresa.EmpresaDTO;
import br.com.ottimizza.statusreportapi.domain.dtos.user.UserDTO;
import br.com.ottimizza.statusreportapi.domain.responses.GenericResponse;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;
import org.springframework.stereotype.Service;

import static br.com.ottimizza.statusreportapi.domain.mappers.EmpresaMapper.fromObjects;
import br.com.ottimizza.statusreportapi.domain.responses.GenericPageableResponse;
import br.com.ottimizza.statusreportapi.domain.responses.PageInfoResponseObject;
import static br.com.ottimizza.statusreportapi.query.empresa.EmpresaQuery.empresasEmProjetoQuery;

@Service
public class EmpresaService {
    
    @Inject
    SalesforceClient salesforceClient;
    
    @Inject
    OAuthClient oauthClient;
    
    public <T> T executeSOQL(PageCriteria pageCriteria, OAuth2Authentication authentication) throws Exception {
        final OAuth2AuthenticationDetails details = (OAuth2AuthenticationDetails) authentication.getDetails();
        String accessToken = "Bearer " + details.getTokenValue();

        ResponseEntity<GenericResponse<UserDTO>> usuario = oauthClient.getUserInfo(accessToken);
        String cnpjContabilidade = usuario.getBody().getRecord().getOrganization().getCnpj();

        // LISTA DE EMPRESAS
        HttpEntity entityLista = salesforceClient.getQuery(
            empresasEmProjetoQuery(cnpjContabilidade, pageCriteria, false), 
            SalesForceAPIMethodExecution.HEROKU_CONNECT, 
            accessToken);

        List<Object[]> salesForceListResult = new ArrayList<>();
        ((List) entityLista.getBody()).stream().forEach(
            objects -> salesForceListResult.add(((ArrayList<Object>) objects).toArray())
        );

        // CONTAGEM DE EMPRESAS
        HttpEntity entityContagem = salesforceClient.getQuery(
            empresasEmProjetoQuery(cnpjContabilidade, pageCriteria, true), 
            SalesForceAPIMethodExecution.HEROKU_CONNECT, 
            accessToken);

        Integer quantidade =  (Integer)(((List) entityContagem.getBody()).get(0));
        
        PageInfoResponseObject pageInfo = new PageInfoResponseObject(false, false, pageCriteria.getPageSize(), pageCriteria.getPageIndex(), -1, quantidade);

        GenericPageableResponse<EmpresaDTO> empresasEmProjeto = new GenericPageableResponse<>(
                fromObjects(salesForceListResult), 
                pageInfo);

        return (T) empresasEmProjeto;
    }
}