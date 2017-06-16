package com.example.eli.projetofacisa.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.example.eli.projetofacisa.models.Noticias;

import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.example.eli.projetofacisa.R;
public class NoticiaAdapter extends RecyclerView.Adapter<NoticiaAdapter.NoticiaViewHolder>{

    private List<Noticias> noticias;

    private Context context;

    public NoticiaAdapter(Context context, List<Noticias> noticia) {
        this.noticias = noticia;
        this.context = context;
    }

    public List<Noticias> getNoticias() {
        return noticias;
    }

    public void setNoticias(List<Noticias> noticias) {
        this.noticias = noticias;
    }

    @Override
    public NoticiaViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_da_lista, null);
        NoticiaViewHolder noticiaRowHolder = new NoticiaViewHolder(v);
        return noticiaRowHolder;
    }

    @Override
    public void onBindViewHolder(NoticiaViewHolder NoticiaViewHolder, int i) {
        Noticias noticia = noticias.get(i);

        NoticiaViewHolder.titulo.setText(noticia.getTitle());
        NoticiaViewHolder.data.setText(noticia.getPublishedAt());


        Glide.with(NoticiaViewHolder.imagem.getContext())
                .load(noticia.getUrlToImage())
                .into(NoticiaViewHolder.imagem);
    }

    @Override
    public int getItemCount() {
        return (null != noticias ? noticias.size() : 0);
    }

    public class NoticiaViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.imgNoticia)
        public ImageView imagem;
        @BindView(R.id.txtTitulo)
        public TextView titulo;
        @BindView(R.id.txtData)
        public TextView data;

        public NoticiaViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

    }
}