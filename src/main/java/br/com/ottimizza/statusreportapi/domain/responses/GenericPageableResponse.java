package br.com.ottimizza.statusreportapi.domain.responses;


import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder(toBuilder = true)
@AllArgsConstructor @NoArgsConstructor
public class GenericPageableResponse<T> implements Serializable {

    @JsonProperty("records")
    private List<T> records;

    @JsonProperty("pageInfo")
    private PageInfoResponseObject pageInfo;
}