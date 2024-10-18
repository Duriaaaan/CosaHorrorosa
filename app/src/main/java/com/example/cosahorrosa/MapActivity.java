package com.example.cosahorrosa;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.AdapterView;

import org.osmdroid.api.IMapController;
import org.osmdroid.config.Configuration;
import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.Marker;

public class MapActivity extends AppCompatActivity {
    MapView mapView;
    IMapController mapController;
    Spinner mapTypeSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        // Configuración de OSM
        Configuration.getInstance().setUserAgentValue(getPackageName());

        mapView = findViewById(R.id.map);
        mapView.setTileSource(TileSourceFactory.MAPNIK); // Tipo de mapa por defecto
        mapView.setMultiTouchControls(true);

        // Controlador del mapa
        mapController = mapView.getController();
        mapController.setZoom(18.0);

        // Crear y centrar el mapa en el marcador de Eurocentro
        GeoPoint eurocentroPoint = new GeoPoint(-33.4420974, -70.6530203); // Coordenadas de Eurocentro
        addMarker(eurocentroPoint, "Eurocentro", "Ubicación: Ahumada 83, Santiago, Región Metropolitana.");
        mapController.setCenter(eurocentroPoint); // Centrar en el marcador de Eurocentro

        // Añadir otros marcadores
        addMarker(new GeoPoint(-33.4874729, -70.5782572), "Manga Naruto Shonen Jump vol. 1", "Naruto encuentra un gran poder y sigue su camino ninja para ser Hokage.");
        addMarker(new GeoPoint(-33.4544852, -70.6046885), "Manga Naruto Shonen Jump vol. 1", "Capital de Inglaterra.");
        addMarker(new GeoPoint(33.4402673, -70.648566), "One Piece Vol. 1", "Monkey D. Luffy quiere ser el rey de los piratas.");

        // Configuración del Spinner para cambiar el tipo de mapa
        mapTypeSpinner = findViewById(R.id.map_type_spinner);
        String[] mapTypes = {"MAPNIK", "USGS Topo", "OpenTopoMap"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, mapTypes);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mapTypeSpinner.setAdapter(adapter);

        // Manejar la selección del tipo de mapa
        mapTypeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        mapView.setTileSource(TileSourceFactory.MAPNIK); // Mapa predeterminado
                        break;
                    case 1:
                        mapView.setTileSource(TileSourceFactory.USGS_TOPO); // Mapa topográfico USGS
                        break;
                    case 2:
                        mapView.setTileSource(TileSourceFactory.OpenTopo); // OpenTopoMap
                        break;
                }
                mapView.invalidate(); // Refrescar el mapa para que el cambio tenga efecto
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // No se hace nada si no hay selección
            }
        });
    }

    // Método para añadir marcadores
    private void addMarker(GeoPoint point, String title, String description) {
        Marker marker = new Marker(mapView);
        marker.setPosition(point);
        marker.setTitle(title);
        marker.setSnippet(description);
        marker.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM);
        mapView.getOverlays().add(marker);
        mapView.invalidate(); // Refrescar el mapa
    }
}



