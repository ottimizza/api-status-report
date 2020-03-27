package br.com.ottimizza.statusreportapi.domain.criteria;

import java.io.Serializable;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PageCriteria implements Serializable {

    public Integer pageIndex = 0;
    public Integer pageSize = 10;

    public String sortBy;
    public String sortOrder;

    public static class Order {
        public static final String ASC = "asc";
        public static final String DESC = "desc";
    }
}