package com.ppcb.cafemerchant.api.service.category;

import com.ppcb.cafemerchant.api.payload.category.*;
import com.ppcb.cafemerchant.common.components.api.Pagination;
import com.ppcb.cafemerchant.common.domain.category.Category;
import com.ppcb.cafemerchant.common.domain.category.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;
    private final DateRangeSpecification dateRangeSpecification;

    @Override
    public Object getAllCategories(Integer pageNumber, Integer pageSize, LocalDate startDate, LocalDate endDate, String sortColumns) {
        List<Sort.Order> orders = new MultiSortBuilder().with(sortColumns).build();
        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by(orders));

        Specification<Category> spec = dateRangeSpecification.between("createdAt", startDate, endDate);

        Page<Category> categories = categoryRepository.findAll(spec, pageable);

        List<CategoryResponse> categoryResponses = categories.getContent().stream()
                .map(categoryMapper::toResponse)
                .collect(Collectors.toList());

        Pagination pagination = new Pagination(categories);

        return CategoryMainRes.builder()
                .categoryResponses(categoryResponses)
                .pagination(pagination)
                .build();
    }
}