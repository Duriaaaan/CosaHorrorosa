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

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartViewHolder> {

    private final List<MangaItem> cartItems;
    private final Context context;

    public CartAdapter(List<MangaItem> cartItems, Context context) {
        this.cartItems = cartItems;
        this.context = context;
    }

    @NonNull
    @Override
    public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cart, parent, false);
        return new CartViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CartViewHolder holder, int position) {
        MangaItem item = cartItems.get(position);

        // Set the data
        holder.itemName.setText(item.getName());
        holder.itemDescription.setText(item.getDescription());
        holder.itemPrice.setText(item.getPrice());
        holder.itemImage.setImageResource(item.getImageResource());

        // Manejar el clic en el botón "Eliminar del carrito"
        holder.removeFromCartButton.setOnClickListener(v -> {
            cartItems.remove(position); // Eliminar el producto del carrito
            notifyItemRemoved(position); // Notificar al adaptador sobre la eliminación
            notifyItemRangeChanged(position, cartItems.size());
            Toast.makeText(context, item.getName() + " eliminado del carrito", Toast.LENGTH_SHORT).show();
        });
    }

    @Override
    public int getItemCount() {
        return cartItems.size();
    }

    static class CartViewHolder extends RecyclerView.ViewHolder {
        TextView itemName, itemDescription, itemPrice;
        ImageView itemImage;
        Button removeFromCartButton;

        public CartViewHolder(@NonNull View itemView) {
            super(itemView);
            itemName = itemView.findViewById(R.id.item_name);
            itemDescription = itemView.findViewById(R.id.item_description);
            itemPrice = itemView.findViewById(R.id.item_price);
            itemImage = itemView.findViewById(R.id.item_image);
            removeFromCartButton = itemView.findViewById(R.id.remove_from_cart_button);
        }
    }
}
