package com.ppcb.cafemerchant.api.payload.category;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.ppcb.cafemerchant.common.components.api.Pagination;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
@NoArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class CategoryMainRes {

    @JsonProperty("categories")
    private List<CategoryResponse> categoryResponses;
    private Pagination pagination;

    @Builder
    public CategoryMainRes(List<CategoryResponse> categoryResponses, Pagination pagination) {
        this.categoryResponses = categoryResponses;
        this.pagination = pagination;
    }
}
