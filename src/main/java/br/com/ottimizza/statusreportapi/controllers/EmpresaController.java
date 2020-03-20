package br.com.ottimizza.statusreportapi.controllers;

import javax.inject.Inject;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import br.com.ottimizza.statusreportapi.services.EmpresaService;
import br.com.ottimizza.statusreportapi.domain.criteria.PageCriteria;
import br.com.ottimizza.statusreportapi.domain.dtos.empresa.EmpresaDTO;
import javax.validation.Valid;

@RestController
@RequestMapping("api/empresa")
public class EmpresaController {
    
    @Inject
    EmpresaService empresaService;

    @GetMapping("projeto")
    public ResponseEntity<?> buscaEmpresasEmProjeto(
            @Valid EmpresaDTO empresaDTO,
            @Valid PageCriteria pageCriteria, 
            OAuth2Authentication authentication) throws Exception {
        return ResponseEntity.ok(empresaService.buscaListaEmpresasProjeto(empresaDTO, pageCriteria, authentication));
    }
    
    @GetMapping("projeto/quantidade")
    public ResponseEntity<?> buscaQuantidadeEmpresasEmProjeto(OAuth2Authentication authentication) throws Exception {
        return ResponseEntity.ok(empresaService.buscaQuantidadeEmpresasProjeto(authentication));
    }
    
    @GetMapping("integrado")
    public ResponseEntity<?> buscaEmpresasIntegrados(
            @Valid EmpresaDTO empresaDTO,
            @Valid PageCriteria pageCriteria, 
            OAuth2Authentication authentication) throws Exception {
        return ResponseEntity.ok(empresaService.buscaListaEmpresasIntegrados(empresaDTO, pageCriteria, authentication));
    }
    
    @GetMapping("integrado/quantidade")
    public ResponseEntity<?> buscaQuantidadeEmpresasIntegrados(OAuth2Authentication authentication) throws Exception {
        return ResponseEntity.ok(empresaService.buscaQuantidadeEmpresasIntegrados(authentication));
    }
    
    @GetMapping("lotes_processados")
    public ResponseEntity<?> quantidadeDeLotesProcessadosEmpresas(OAuth2Authentication authentication) throws Exception {
        return ResponseEntity.ok(empresaService.quantidadeDeLotesProcessadosEmpresas(authentication));
    }
}