package de.tim0_12432.f1_schedule_app.ui.circuit;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.osmdroid.config.Configuration;
import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapController;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.Marker;

import de.tim0_12432.f1_schedule_app.R;
import de.tim0_12432.f1_schedule_app.data.entity.Circuit;
import de.tim0_12432.f1_schedule_app.data.entity.Location;
import de.tim0_12432.f1_schedule_app.data.entity.Nationality;
import de.tim0_12432.f1_schedule_app.databinding.FragmentCircuitDetailsBinding;
import de.tim0_12432.f1_schedule_app.utility.Logger;

public class CircuitDetailsFragment extends Fragment {

    private FragmentCircuitDetailsBinding binding;

    private Circuit circuit;

    private MapView osm;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            circuit = (Circuit) getArguments().getSerializable("circuitObj");
        }

        if (Build.VERSION.SDK_INT >= 23) {
            if (isStoragePermissionGranted()) {
                // empty check
            }
        }
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    public void addMarker (GeoPoint point){
        Marker marker = new Marker(osm);
        marker.setPosition(point);
        marker.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM);
        marker.setIcon(getResources().getDrawable(R.drawable.ic_location_24));
        osm.getOverlays().clear();
        osm.getOverlays().add(marker);
        osm.invalidate();
        marker.setTitle(circuit.getName());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentCircuitDetailsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        Configuration.getInstance().load(getContext(), PreferenceManager.getDefaultSharedPreferences(getContext()));
        osm = binding.circuitScreenMap;
        osm.setBuiltInZoomControls(true);
        osm.setMultiTouchControls(true);
        osm.setTileSource(TileSourceFactory.MAPNIK);
        osm.setHasTransientState(true);

        MapController osmController = (MapController) osm.getController();
        osmController.setZoom(15);

        if (circuit != null) {
            Location location = circuit.getLocation();
            GeoPoint point = new GeoPoint(location.getLatitude(), location.getLongitude());
            osmController.animateTo(point);
            addMarker(point);

            setText(binding.circuitScreenName, R.string.name, circuit.getName());
            setText(binding.circuitScreenLocality, R.string.locality, location.getLocality());
            setText(binding.circuitScreenCountry, R.string.country, location.getCountry() + " " + Nationality.getNationalityOfCountry(location.getCountry()).getEmojiFlag());

            binding.circuitScreenWikiButton.setOnClickListener(v -> {
                Logger.log("Wikipedia opened for", circuit.getName());
                Uri url = Uri.parse(circuit.getUrl());
                requireActivity().startActivity(new Intent(Intent.ACTION_VIEW, url));
            });
        }

        return root;
    }

    public boolean isStoragePermissionGranted() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (requireActivity().checkSelfPermission(android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    == PackageManager.PERMISSION_GRANTED) {
                Logger.log("Permission is already granted!");
                return true;
            } else {

                Logger.log("Permission is revoked!");
                ActivityCompat.requestPermissions(requireActivity(), new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.ACCESS_FINE_LOCATION}, 1);
                return false;
            }
        } else { //permission is automatically granted on sdk<23 upon installation
            Logger.log("Permission is granted!");
            return true;
        }
    }

    public void onResume(){
        super.onResume();
        osm.onResume();
    }

    public void onPause(){
        super.onPause();
        osm.onPause();
    }

    private void setText(TextView view, int prefixId, String text) {
        String prefix = getString(prefixId);
        setText(view, prefix, text);
    }

    private void setText(TextView view, String prefix, String text) {
        setText(view, prefix, ':', text);
    }

    @SuppressLint("SetTextI18n")
    private void setText(TextView view, String prefix, char concatenate, String text) {
        if (view != null && text != null) {
            view.setText(prefix + concatenate + ' ' + text);
        }
    }
}