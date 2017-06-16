package com.example.eli.projetofacisa.fragmentos;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.eli.projetofacisa.R;
import com.example.eli.projetofacisa.adapters.NoticiaAdapter;
import com.example.eli.projetofacisa.models.Noticias;
import com.example.eli.projetofacisa.models.NoticiasCatalog;
import com.example.eli.projetofacisa.services.Service;
import com.example.eli.projetofacisa.services.ServiceGenerator;
import com.example.eli.projetofacisa.util.RecyclerTouchListener;
import com.example.eli.projetofacisa.util.RecyclerViewMargin;
import com.example.eli.projetofacisa.view.NoticiaActivity;

import org.parceler.Parcels;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NoticiaFragment extends Fragment {

    @BindView(R.id.lstNoticias)
    RecyclerView lstnoticias;

    private NoticiaAdapter adapter;

    private List<Noticias> lst;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_noticias, null);

        ButterKnife.bind(this, view);

        lstnoticias.setLayoutManager(new LinearLayoutManager(inflater.getContext(), LinearLayoutManager.VERTICAL, false));
        lstnoticias.addItemDecoration(new RecyclerViewMargin(5, 5));

        lstnoticias.addOnItemTouchListener(
                        new RecyclerTouchListener(getActivity(), lstnoticias,
                        new RecyclerTouchListener.OnTouchActionListener() {
                            public void onClick(View view, int position) {

                                Bundle bundle = new Bundle();
                                Noticias noticias = adapter.getNoticias().get(position);
                                //bundle.putParcelable();
                                startActivity(new Intent(getContext(), NoticiaActivity.class).putExtra("noticias", Parcels.wrap(noticias)));

                            }

                            @Override
                            public void onRightSwipe(View view, int position) {
                            }
                            @Override
                            public void onLeftSwipe(View view, int position) {
                            }

                        }));


        getAllNoticias();
        return(view);
    }

    private void getAllNoticias() {

        try {

            Service service = (Service) ServiceGenerator.createService(Service.class);

            final Call<NoticiasCatalog> noticias = service.ListCatalogNoticias();

            noticias.enqueue(new Callback<NoticiasCatalog>() {
                @Override
                public void onResponse(Call<NoticiasCatalog> call, final Response<NoticiasCatalog> noticias) {
                    //Toast.makeText(MainActivity.this, noticias.body().articles.size(), Toast.LENGTH_SHORT).show();

                    adapter = new NoticiaAdapter(getContext(), (List<Noticias>) noticias.body().getArticles());
                    lstnoticias.setAdapter(adapter);
                }

                @Override
                public void onFailure(Call<NoticiasCatalog> call, Throwable t) {
                    if(call.isCanceled())
                        Toast.makeText(getContext(), "Cancelado pelo usuário!", Toast.LENGTH_SHORT).show();
                    else
                        Toast.makeText(getContext(), "Sem conexão com internet!", Toast.LENGTH_SHORT).show();

                    //Log.d(">>>> ", t.getMessage());
                }
            });
        }
        catch (Exception e) {
            e.printStackTrace();
            Log.e("IO","IO"+e);
            Toast.makeText(getContext(), "Exeption: "+ e.getMessage(), Toast.LENGTH_SHORT).show();
        }
        catch(OutOfMemoryError e1) {
            e1.printStackTrace();
            Log.e("Memory exceptions","exceptions"+e1);
        }
    }
}