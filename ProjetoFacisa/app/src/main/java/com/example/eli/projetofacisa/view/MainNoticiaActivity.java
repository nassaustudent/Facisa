package com.example.eli.projetofacisa.view;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;


import com.example.eli.projetofacisa.R;
import com.example.eli.projetofacisa.fragmentos.FavoritaFragment;
import com.example.eli.projetofacisa.fragmentos.NoticiaFragment;

public class MainNoticiaActivity extends AppCompatActivity {

    private TextView mTextMessage;
    FragmentManager fm = getSupportFragmentManager();

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:

                    // Create new fragment and transaction
                    NoticiaFragment frag1 = new NoticiaFragment();
                    android.support.v4.app.FragmentTransaction ft = fm.beginTransaction();
                    ft.replace(R.id.content, frag1, "frag1");
                    ft.commit();
                    return true;


                case R.id.navigation_dashboard:
                    FavoritaFragment frag2 = new FavoritaFragment();
                    android.support.v4.app.FragmentTransaction ft2 = fm.beginTransaction();
                    ft2.replace(R.id.content, frag2, "frag2");
                    ft2.commit();
                    return true;


                //case R.id.navigation_notifications:
                  //  Toast.makeText(MainNoticiaActivity.this, "Nenhuma Notificação", Toast.LENGTH_SHORT).show();
                   // return true;
            }
            return false;
        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_noticia);


        NoticiaFragment frag1 = new NoticiaFragment();
        android.support.v4.app.FragmentTransaction ft = fm.beginTransaction();
        ft.add(R.id.content, frag1, "frag1");
        ft.commit();
        //return true;

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

}