package com.ppcb.cafemerchant.common.components.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.springframework.data.domain.Page;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class Pagination {

    private boolean last;

    private boolean first;

    private Integer size;

    @JsonProperty("total_pages")
    private Integer totalPages;

    @JsonProperty("current_page")
    private Integer currentPage;

    @JsonProperty("current_total_elements")
    private Integer currentTotalElements;

    @JsonProperty("total_elements")
    private Long totalElements;

    private boolean empty;

    @Builder
    public Pagination(Page<?> page) {

        this.last = page.isLast();
        this.totalPages = page.getTotalPages();
        this.totalElements = page.getTotalElements();
        this.currentPage = page.getNumber();
        this.first = page.isFirst();
        this.size = page.getSize();
        this.empty = page.isEmpty();
        this.currentTotalElements = page.getNumberOfElements();
    }
}
