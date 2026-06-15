package com.ppcb.cafemerchant.api.controller;

import com.ppcb.cafemerchant.api.service.category.CategoryService;
import com.ppcb.cafemerchant.common.components.api.BaseController;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
@RequestMapping("/api/v1/ca/category")
@RequiredArgsConstructor
public class CategoryController extends BaseController {

    private final CategoryService categoryService;

    @GetMapping("")
    public Object getAllCategories(
            @RequestParam(name = "page_number", required = false, defaultValue = "0") Integer pageNumber,
            @RequestParam(name = "page_size", required = false, defaultValue = "10") Integer pageSize,
            @RequestParam(name = "start_date") @DateTimeFormat(pattern = "yyyyMMdd") LocalDate startDate,
            @RequestParam(name = "end_date") @DateTimeFormat(pattern = "yyyyMMdd") LocalDate endDate,
            @RequestParam(name = "sort_columns", required = false, defaultValue = "id:desc") String sortColumns
    ) {
        System.out.println("Page Size " + pageSize);
        return ok(categoryService.getAllCategories(pageNumber, pageSize, startDate, endDate, sortColumns));
    }
}
