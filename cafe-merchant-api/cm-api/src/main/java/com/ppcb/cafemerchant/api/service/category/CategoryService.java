package com.ppcb.cafemerchant.api.service.category;

import java.time.LocalDate;

public interface CategoryService {
    Object getAllCategories(Integer pageNumber, Integer pageSize, LocalDate startDate, LocalDate endDate, String sortColumns);
}
