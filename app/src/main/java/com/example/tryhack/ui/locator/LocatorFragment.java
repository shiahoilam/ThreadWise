package com.example.tryhack.ui.locator;

import com.example.tryhack.R;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.osmdroid.config.Configuration;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.Marker;

public class LocatorFragment extends Fragment {

    private MapView mapView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Initialize OSMDroid configuration
        Configuration.getInstance().load(getContext(), android.preference.PreferenceManager.getDefaultSharedPreferences(getContext()));

        // Inflate the layout
        View rootView = inflater.inflate(R.layout.fragment_locator, container, false);

        // Set up the MapView
        mapView = rootView.findViewById(R.id.mapview); // Update to mapview
        mapView.setTileSource(org.osmdroid.tileprovider.tilesource.TileSourceFactory.MAPNIK);
        mapView.setMultiTouchControls(true);

        // Set initial zoom and location
        mapView.getController().setZoom(16.0);
        mapView.getController().setCenter(new org.osmdroid.util.GeoPoint(1.3230538740544584, 103.75643253732824));

        // Add a marker
        addMarker(1.3182363186154022, 103.76545425451434, "Alt-To-Fit", "Alteration Service. Clementi Ave 4, #09-87 Block 318, Singapore 120318");
        addMarker(1.3139164093312512, 103.76275279815427, "Clementi Tailor", "Alteration Service. Floor 1 | 431 Clementi Ave 3, Singapore 120431");
        addMarker(1.3018274598083561, 103.78760273751989, "Textile Recycling Bin @ Fushionopolis One",
                "At B2 North (near toilets), escalator. (Next to MRT )\n" +
                "1 Fusionopolis Way Connexis\n" +
                "Singapore 138632, Singapore");
        addMarker(1.3375441324594048, 103.74647355281036, "Textile Recycling Bin @ G2000 IMM",
                "Only clean clothes and garments\n" +
                "2 Jurong East Street 21 #01-119\n" +
                "Singapore 609601, Singapore");
        addMarker(1.3136114394460179, 103.76447071534041, "琯竤服装 Kon Foong Tailoring", "448 Clementi Ave 3, #01-53, Singapore 120448");
        addMarker(1.3124239053800881, 103.75954627116492, "Anlina Boutique","Alteration Service. Floor 2 | 504 W Coast Dr, Singapore 120504");
        addMarker(1.3130223313547262, 103.76480501534043, "Mr Paul Street Cobbler & Shoe Repair", "Shoes Repair Service. Located in : Ma Kuang TCM Clinic @ Clementi. 447 Clementi Ave 3, Singapore 120447");
        addMarker(1.2992237639772515, 103.78838742434674, "Textile Recycling Bin @ Fushionopolis Two", "At Kinesis, Level 1 Carpark Lift Lobby\n" +
                "4 Fusionopolis Way\n" +
                "Singapore 138635, Singapore");

        return rootView;
    }

    private void addMarker(double latitude, double longitude, String title, String snippet) {
        Marker marker = new Marker(mapView);
        marker.setPosition(new org.osmdroid.util.GeoPoint(latitude, longitude));
        marker.setTextLabelBackgroundColor(android.graphics.Color.RED);
        marker.setTextLabelForegroundColor(android.graphics.Color.WHITE);
        marker.setTitle(title);
        if (snippet != null) {
            marker.setSnippet(snippet);
        }
        marker.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM);

        // Check if the marker is for "Your Location"
        if (title.equals("Your Location")) {
            Log.d("LocatorFragment", "Setting custom icon for 'Your Location'");
            marker.setIcon(ContextCompat.getDrawable(getContext(), R.drawable.baseline_assistant_navigation_24));
        } else {
            Log.d("LocatorFragment", "Using default marker icon for other locations");
        }

        mapView.getOverlays().add(marker);
        mapView.invalidate();
    }


    @Override
    public void onResume() {
        super.onResume();
        mapView.onResume(); // Refresh map
    }

    @Override
    public void onPause() {
        super.onPause();
        mapView.onPause(); // Pause map
    }
}
