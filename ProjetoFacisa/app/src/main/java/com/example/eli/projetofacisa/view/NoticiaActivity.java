package com.example.eli.projetofacisa.view;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.eli.projetofacisa.R;
import com.example.eli.projetofacisa.dao.AppDatabase;
import com.example.eli.projetofacisa.dao.NoticiaDao;
import com.example.eli.projetofacisa.models.Noticias;
import org.parceler.Parcels;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class NoticiaActivity extends AppCompatActivity {

            @BindView(R.id.text_title)
            TextView txt_title;

            @BindView(R.id.txt_description)
            TextView txt_description;

            @BindView(R.id.image)
            ImageView image;

            @BindView(R.id.txt_publishedAt)
            TextView txt_publishedAt;

    Noticias noticias;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_noticia);

        ButterKnife.bind(this);

        noticias = Parcels.unwrap(getIntent().getParcelableExtra("noticias"));
        Toast.makeText(this, noticias.getTitle(), Toast.LENGTH_SHORT).show();

            txt_title.setText(noticias.getTitle());
            txt_description.setText(noticias.getDescripton());
            txt_publishedAt.setText(noticias.getPublishedAt());
            Glide.with(this).load(noticias.getUrlToImage()).into(image);
    }

    @OnClick(R.id.fab)
    public void noticias(View view) {

        NoticiaDao db = AppDatabase.getInMemoryDatabase(this).noticiaModel();
        db.InsertNoticias(noticias);
        List<Noticias>Noticias=db.GetNoticias();
        //Toast.makeText(this, db.GetNoticias().size(),Toast.LENGTH_SHORT).show();
        Toast.makeText(this, "Adicionado ao Favoritos", Toast.LENGTH_SHORT).show();

    }

}