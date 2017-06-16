package com.example.eli.projetofacisa.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class NoticiasCatalog {
    @SerializedName("articles")
    private List<Noticias> articles;

    public List<Noticias> getArticles() {
        return articles;
    }

    public void setArticles(List<Noticias> articles) {
        this.articles = articles;
    }
}
