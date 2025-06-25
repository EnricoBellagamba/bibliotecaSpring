package com.develhope.co.biblioteca_prova.utils;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

public class PaginationUtils {

   public static Pageable createPage(Integer pageNumber, Integer pageSize){
       if (pageNumber < 0 ) {
           pageNumber = 0;
       }
       if (pageSize < 1 ){
           pageSize = 10;
       }

       if (pageSize > 100) {//if separati
           pageSize = 100;
       }
       Pageable pageable = PageRequest.of(pageNumber, pageSize);
       return pageable;
   }
}
