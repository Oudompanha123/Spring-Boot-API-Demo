package com.ppcb.cafemerchant.api.payload.category;

import org.springframework.data.domain.Sort;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MultiSortBuilder {
    private final List<Sort.Order> orders;

    public MultiSortBuilder() {
        this.orders = new ArrayList<>();
    }

    public final MultiSortBuilder with(String sortDirection) {
        Pattern pattern = Pattern.compile("(\\w+?)(:|<|>)(\\w+?),");
        Matcher matcher = pattern.matcher(sortDirection + ",");
        while (matcher.find()) {
            String field = mapFieldName(matcher.group(1));
            orders.add(new Sort.Order(Sort.Direction.fromString(matcher.group(3)), field));
        }
        return this;
    }

    private String mapFieldName(String field) {
        switch (field.toLowerCase()) {
            case "id": return "categoryId";
            case "name": return "categoryName";
            default: return field;
        }
    }

    public List<Sort.Order> build() {
        return orders.isEmpty() ? Collections.emptyList() : orders;
    }
}
