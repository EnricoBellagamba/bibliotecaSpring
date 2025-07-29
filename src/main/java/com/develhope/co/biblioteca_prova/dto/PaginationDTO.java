package com.develhope.co.biblioteca_prova.dto;

public class PaginationDTO {
    private Integer pageNumber;
    private Integer pageSize;
    private String sortDir;
    private String sortField;

    //Costruttore default

    public PaginationDTO(){
        pageNumber = 0;
        pageSize = 10;
        sortDir = "ASC";
    }

    //Getters e setters

    public Integer getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(Integer pageNumber) {
        this.pageNumber = pageNumber;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public String getSortDir() {
        return sortDir;
    }

    public void setSortDir(String sortDir) {
        this.sortDir = sortDir;
    }

    public String getSortField() {
        return sortField;
    }

    public void setSortField(String sortField) {
        this.sortField = sortField;
    }
}
