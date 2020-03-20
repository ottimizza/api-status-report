package br.com.ottimizza.statusreportapi.domain.responses;

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
}