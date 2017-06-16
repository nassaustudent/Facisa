package com.example.eli.projetofacisa.services;

import com.example.eli.projetofacisa.models.NoticiasCatalog;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface Service {


        @GET("articles?source=the-next-web&sortBy=latest&apiKey=27e1e176c42248b1afb73334b8525eac")
        retrofit2.Call<NoticiasCatalog> ListCatalogNoticias();


        //@POST("/Usuario/PostUsuario")
        //Call<String> PostUsuario(@Body Usuario usuario);
}
