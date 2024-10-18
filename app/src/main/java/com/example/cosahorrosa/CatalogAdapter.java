package com.example.cosahorrosa;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CatalogAdapter extends RecyclerView.Adapter<CatalogAdapter.CatalogViewHolder> {

    private final List<MangaItem> catalogItems;
    private final Context context;

    public CatalogAdapter(List<MangaItem> catalogItems, Context context) {
        this.catalogItems = catalogItems;
        this.context = context;
    }

    @NonNull
    @Override
    public CatalogViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_catalog, parent, false);
        return new CatalogViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CatalogViewHolder holder, int position) {
        MangaItem item = catalogItems.get(position);

        // Set the data
        holder.itemName.setText(item.getName());
        holder.itemDescription.setText(item.getDescription());
        holder.itemPrice.setText(item.getPrice());
        holder.itemImage.setImageResource(item.getImageResource());

        // Manejar el clic en el botón "Agregar al carrito"
        holder.addToCartButton.setOnClickListener(v -> {
            // Aquí puedes agregar el producto al carrito
            Toast.makeText(context, item.getName() + " agregado al carrito", Toast.LENGTH_SHORT).show();

            // Lógica para agregar el producto al carrito (puedes usar SharedPreferences o enviar a otra actividad)
            // Ejemplo:
            // cartItems.add(item);
            // cartAdapter.notifyDataSetChanged(); (si se está mostrando en una vista de carrito)
        });
    }

    @Override
    public int getItemCount() {
        return catalogItems.size();
    }

    static class CatalogViewHolder extends RecyclerView.ViewHolder {
        TextView itemName, itemDescription, itemPrice;
        ImageView itemImage;
        Button addToCartButton; // Agregamos referencia al botón

        public CatalogViewHolder(@NonNull View itemView) {
            super(itemView);
            itemName = itemView.findViewById(R.id.item_name);
            itemDescription = itemView.findViewById(R.id.item_description);
            itemPrice = itemView.findViewById(R.id.item_price);
            itemImage = itemView.findViewById(R.id.item_image);
            addToCartButton = itemView.findViewById(R.id.add_to_cart_button); // Inicializamos el botón
        }
    }
}
