package br.com.ottimizza.statusreportapi.domain.responses;

import br.com.ottimizza.statusreportapi.domain.criteria.PageCriteria;
import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@AllArgsConstructor @NoArgsConstructor
public class PageInfoResponseObject implements Serializable {

    @JsonProperty("hasNext")
    private boolean hasNext;

    @JsonProperty("hasPrevious")
    private boolean hasPrevious;

    @JsonProperty("pageSize")
    private int pageSize;

    @JsonProperty("pageIndex")
    private int pageIndex;

    @JsonProperty("totalPages")
    private int totalPages;

    @JsonProperty("totalElements")
    private long totalElements;
    
    public PageInfoResponseObject(PageCriteria pageCriteria, int quantidadeResultados) {
        this.pageIndex = pageCriteria.pageIndex;
        this.pageSize = pageCriteria.pageSize;
        this.totalElements = quantidadeResultados;
        
        this.totalPages = Math.floorDiv(quantidadeResultados, pageSize);
        if(quantidadeResultados % pageSize > 0) this.totalPages++;
        
        this.hasNext = ((pageIndex + 1) * pageSize) < quantidadeResultados;
        this.hasPrevious = (pageIndex > 0 && ((pageIndex - 1) * pageSize) < quantidadeResultados);
    }
}