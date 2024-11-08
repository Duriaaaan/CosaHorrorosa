package com.example.cosahorrosa;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class CartActivity extends AppCompatActivity {

    RecyclerView cartRecyclerView;
    List<MangaItem> cartItems;
    CartAdapter cartAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        cartRecyclerView = findViewById(R.id.cart_recycler_view);
        cartRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Cargar los productos en el carrito (esto puede ser desde una lista persistente o de prueba)
        cartItems = new ArrayList<>();
        cartItems.add(new MangaItem("Naruto Shonen Jump vol. 1", "Un manga sobre el ninja Naruto.", "$8.990", R.drawable.naruto_cover));
        cartItems.add(new MangaItem("One Piece vol. 1", "Luffy quiere ser el rey de los piratas.", "$10.990", R.drawable.onepiece_cover));
        cartItems.add(new MangaItem("Attack on Titan vol. 1", "El mundo está siendo atacado por titanes.", "$9.990", R.drawable.attackontitan_cover));

        // Configurar el adaptador
        cartAdapter = new CartAdapter(cartItems, this);
        cartRecyclerView.setAdapter(cartAdapter);

        // Botón para finalizar compra
        findViewById(R.id.checkout_button).setOnClickListener(v -> {
            Toast.makeText(this, "Compra finalizada", Toast.LENGTH_SHORT).show();
            cartItems.clear();
            cartAdapter.notifyDataSetChanged(); // Actualizar la lista del carrito después de la compra
        });
    }
}



