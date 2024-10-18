package com.example.cosahorrosa;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class PurchaseHistoryActivity extends AppCompatActivity {
    ListView purchaseHistoryList;
    ArrayList<String> purchaseHistory;
    ArrayAdapter<String> purchaseHistoryAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState)  {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_purchase_history);

        purchaseHistoryList = findViewById(R.id.purchase_history_list);

        //cargar historial de compras
        purchaseHistory = new ArrayList<>();
        purchaseHistory.add("Naruto Shonen Jump vol. 1 - $8.990");
        purchaseHistory.add("One Piece vol. 1 - $10.990");

        purchaseHistoryAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, purchaseHistory);
        purchaseHistoryList.setAdapter(purchaseHistoryAdapter);
    }
}
