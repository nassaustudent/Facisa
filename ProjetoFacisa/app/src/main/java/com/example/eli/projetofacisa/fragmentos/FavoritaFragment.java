package com.example.eli.projetofacisa.fragmentos;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.eli.projetofacisa.R;
import com.example.eli.projetofacisa.adapters.NoticiaAdapter;
import com.example.eli.projetofacisa.dao.AppDatabase;
import com.example.eli.projetofacisa.dao.NoticiaDao;
import com.example.eli.projetofacisa.models.Noticias;
import com.example.eli.projetofacisa.util.RecyclerTouchListener;
import com.example.eli.projetofacisa.util.RecyclerViewMargin;
import com.example.eli.projetofacisa.view.NoticiaActivity;

import org.parceler.Parcels;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FavoritaFragment extends Fragment {

    @BindView(R.id.lstNoticias)
    RecyclerView lstnoticias;

    private NoticiaAdapter adapter;
    NoticiaDao db;
    private List<Noticias> lst;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_favorita, null);

        ButterKnife.bind(this, view);
        db = AppDatabase.getInMemoryDatabase(getContext()).noticiaModel();

        lstnoticias.setLayoutManager(new LinearLayoutManager(inflater.getContext(), LinearLayoutManager.VERTICAL, false));
        lstnoticias.addItemDecoration(new RecyclerViewMargin(5, 5));

        lstnoticias.addOnItemTouchListener(
                new RecyclerTouchListener(getActivity(), lstnoticias,
                        new RecyclerTouchListener.OnTouchActionListener() {
                            public void onClick(View view, int position) {

                                Noticias noticias = adapter.getNoticias().get(position);
                                startActivity(new Intent(getContext(), NoticiaActivity.class).putExtra("noticias", Parcels.wrap(noticias)));
                            }

                            @Override
                            public void onRightSwipe(View view, int position) {
                                Noticias noticias = adapter.getNoticias().get(position);
                                db.DeleteNoticias(noticias);
                                Toast.makeText(getContext(), "Retirado das Notícias Favoritas", Toast.LENGTH_SHORT).show();

                                List<Noticias>noticias2=db.GetNoticias();
                                adapter = new NoticiaAdapter(getContext(), noticias2);
                                lstnoticias.setAdapter(adapter);

                            }
                            @Override
                            public void onLeftSwipe(View view, int position) {

                            }
                        }));

        List<Noticias>noticias=db.GetNoticias();

        adapter = new NoticiaAdapter(getContext(), noticias);
        lstnoticias.setAdapter(adapter);
        return(view);
    }
}