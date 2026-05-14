package com.antra.movieapi;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Movie {
    @JsonProperty("Title")
    private String title;
    @JsonProperty("Year")
    private Object year;
    @JsonProperty("imdbID")
    private String imdbId;

    public String getTitle(){
        return title;
    }
    public void setTitle(String t){
        this.title = t;
    }
    public Object getYear() {
        return year;
    }
    public void setYear(Object y){
        this.year = y;
    }
    public String getImdbId(){
        return imdbId;
    }
    public void setImdbId(String id){
        this.imdbId = id;
    }
}
