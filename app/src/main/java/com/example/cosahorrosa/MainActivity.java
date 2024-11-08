package com.example.cosahorrosa;

import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.VideoView;
import android.view.ViewGroup;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Configurar los botones y establecer sus listeners
        Button cartButton = findViewById(R.id.cart_button);
        Button historyButton = findViewById(R.id.history_button);
        Button mapButton = findViewById(R.id.map_button);
        Button catalogButton = findViewById(R.id.catalog_button);
        Button manageUsersButton = findViewById(R.id.manage_users_button);

        // Navegación a otras actividades con los listeners correctos
        cartButton.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, CartActivity.class)));
        historyButton.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, PurchaseHistoryActivity.class)));
        mapButton.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, MapActivity.class)));
        catalogButton.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, CatalogActivity.class)));
        manageUsersButton.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, ManageUsersActivity.class)));

        // Mostrar el primer popup de video después de 10 segundos
        handler.postDelayed(this::showVideoPopup, 10000);

        // Mostrar el popup de la página web después de 20 segundos
        handler.postDelayed(this::showWebPopup, 20000);
    }

    // Método para mostrar el popup de video
    private void showVideoPopup() {
        Dialog videoDialog = new Dialog(this);
        videoDialog.setContentView(R.layout.dialog_video_popup);

        // Configurar el botón de cierre (comienza deshabilitado)
        ImageButton closeButton = videoDialog.findViewById(R.id.close_video_button);
        closeButton.setEnabled(false);

        // Configurar el VideoView y el botón de reproducción
        VideoView videoView = videoDialog.findViewById(R.id.video_view);
        Button playButton = videoDialog.findViewById(R.id.start_video_button);

        Uri videoUri = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.sample_video);
        videoView.setVideoURI(videoUri);

        // Habilitar el botón de cierre solo después de iniciar el video
        playButton.setOnClickListener(v -> {
            videoView.start();
            closeButton.setEnabled(true);
        });

        // Cerrar el popup cuando el botón de cierre esté habilitado
        closeButton.setOnClickListener(v -> videoDialog.dismiss());

        videoDialog.show();
        videoDialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
    }

    // Método para mostrar el popup de la página web
    private void showWebPopup() {
        Dialog webDialog = new Dialog(this);
        webDialog.setContentView(R.layout.dialog_web_popup);

        // Configurar el botón de cierre
        ImageButton closeButton = webDialog.findViewById(R.id.close_web_button);
        closeButton.setOnClickListener(v -> webDialog.dismiss());

        // Configurar el WebView
        WebView webView = webDialog.findViewById(R.id.web_view);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl("https://www.tusitio.com"); // Reemplaza con la URL deseada

        webDialog.show();
        webDialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // Detener el handler cuando se destruye la actividad
        handler.removeCallbacksAndMessages(null);
    }
}



