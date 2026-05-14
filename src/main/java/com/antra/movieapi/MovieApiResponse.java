package com.antra.movieapi;

import java.util.List;

public class MovieApiResponse {
    private int page;
    private int total_pages;
    private List<Movie> data;

    public int getPage(){
        return page;
    }
    public void setPage(int p){
        this.page = p;
    }
    public int getTotal_pages(){
        return total_pages;
    }
    public void setTotal_pages(int t){
        this.total_pages = t;
    }
    public List<Movie> getData(){
        return data;
    }
    public void setData(List<Movie> d){
        this.data = d;
    }
}
