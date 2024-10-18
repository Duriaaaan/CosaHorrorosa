package com.example.cosahorrosa;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;


public class CatalogActivity extends AppCompatActivity {

    RecyclerView catalogRecyclerView;
    List<MangaItem> catalogItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catalog); // Enlazar el layout con la actividad

        // Enlazar el RecyclerView desde el layout
        catalogRecyclerView = findViewById(R.id.catalog_recycler_view);

        // Crear lista de productos del catálogo
        catalogItems = new ArrayList<>();
        catalogItems.add(new MangaItem("Naruto Shonen Jump vol. 1", "Un manga sobre el ninja Naruto.", "$8.990", R.drawable.naruto_cover));
        catalogItems.add(new MangaItem("One Piece vol. 1", "Luffy quiere ser el rey de los piratas.", "$10.990", R.drawable.onepiece_cover));
        catalogItems.add(new MangaItem("Attack on Titan vol. 1", "El mundo está siendo atacado por titanes.", "$9.990", R.drawable.attackontitan_cover));
        catalogItems.add(new MangaItem("Jujutsu Kaisen vol. 1", "Yuji debe enfrentarse a las maldiciones y vencer a Sukuna.", "$9.990", R.drawable.jujutsukaisen_cover));

        
        
        // Configurar el RecyclerView con el adaptador
        CatalogAdapter adapter = new CatalogAdapter(catalogItems, this);
        catalogRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        catalogRecyclerView.setAdapter(adapter);
    }
}


