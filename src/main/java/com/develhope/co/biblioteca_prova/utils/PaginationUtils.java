package com.develhope.co.biblioteca_prova.utils;

import com.develhope.co.biblioteca_prova.dto.PaginationDTO;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

public class PaginationUtils {

    public static Pageable createPage(PaginationDTO pg) {
        if (pg.getPageNumber() < 0) {
            pg.setPageNumber(0);
        }
        if (pg.getPageSize() < 1) {
            pg.setPageSize(10);
        }

        if (pg.getPageSize() > 100) {
            pg.setPageSize(100);
        }

        if (pg.getSortDir() == null){
            pg.setSortDir("ASC");
        }

        if (pg.getSortField()==null){
            return PageRequest.of(pg.getPageNumber(), pg.getPageSize());

        }
        return PageRequest.of(pg.getPageNumber(), pg.getPageSize(),
                Sort.by(Sort.Direction.fromString(pg.getSortDir()), pg.getSortField())
        );
    }
}
