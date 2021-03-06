package com.example.eli.projetofacisa.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.example.eli.projetofacisa.models.Noticias;

import java.util.List;

@Dao
public interface NoticiaDao {
        //@Insert()
        @Insert(onConflict = OnConflictStrategy.REPLACE)
        void InsertNoticias(Noticias noticias);


        @Query("Select * from noticias")
        List<Noticias> GetNoticias();

        @Delete()
        void DeleteNoticias(Noticias noticias);

}
