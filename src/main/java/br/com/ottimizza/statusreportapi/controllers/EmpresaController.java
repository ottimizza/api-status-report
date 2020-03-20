package br.com.ottimizza.statusreportapi.controllers;

import javax.inject.Inject;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import br.com.ottimizza.statusreportapi.services.EmpresaService;
import br.com.ottimizza.statusreportapi.domain.criteria.PageCriteria;
import javax.validation.Valid;

@RestController
@RequestMapping("api/empresa")
public class EmpresaController {
    
    @Inject
    EmpresaService empresaService;

    @GetMapping("projeto")
    public ResponseEntity<?> buscaEmpresas(@Valid PageCriteria pageCriteria, OAuth2Authentication authentication) throws Exception {
        return ResponseEntity.ok(empresaService.executeSOQL(pageCriteria, authentication));
    }
}
