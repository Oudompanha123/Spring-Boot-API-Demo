package com.ppcb.cafemerchant.api.payload.category;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import jakarta.persistence.criteria.Predicate;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
public class DateRangeSpecification {

    /**
     * Builds a date-range Specification for any entity.
     *
     * @param fieldName the entity's date/datetime field (e.g. "createdAt")
     * @param startDate inclusive lower bound (nullable)
     * @param endDate   inclusive upper bound (nullable)
     */
    public <T> Specification<T> between(String fieldName, LocalDate startDate, LocalDate endDate) {
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (startDate != null) {
                predicates.add(cb.greaterThanOrEqualTo(
                        root.get(fieldName).as(LocalDate.class), startDate));
            }

            if (endDate != null) {
                predicates.add(cb.lessThanOrEqualTo(
                        root.get(fieldName).as(LocalDate.class), endDate));
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }
}
