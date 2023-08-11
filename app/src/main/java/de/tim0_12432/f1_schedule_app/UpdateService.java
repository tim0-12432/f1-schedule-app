package de.tim0_12432.f1_schedule_app;

import android.content.Intent;
import android.net.Uri;

import androidx.annotation.NonNull;

import com.google.android.material.snackbar.Snackbar;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.concurrent.CompletableFuture;

import de.tim0_12432.f1_schedule_app.data.HttpService;
import de.tim0_12432.f1_schedule_app.utility.Logger;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class UpdateService {

    private static final String RELEASE_URL = "https://api.github.com/repos/tim0-12432/f1-schedule-app/releases/latest";

    public static final String EMPTY_STRING = "";

    public CompletableFuture<String> checkForUpdates(String currentVersion) {
        CompletableFuture<String> future = new CompletableFuture<>();
        Request request = new Request.Builder()
                .url(RELEASE_URL)
                .get()
                .build();
        OkHttpClient client = HttpService.getClient();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                future.complete(EMPTY_STRING);
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                JSONObject json = null;
                try {
                    json = new JSONObject(response.body().string());
                    String latest = json.getString("name").replace("v", "");
                    if (currentVersion.contains(latest)) {
                        future.complete(EMPTY_STRING);
                    } else {
                        future.complete(latest);
                    }
                    Logger.log("=====> Latest version:", latest, "( current:", currentVersion, ")");
                } catch (JSONException e) {
                    Logger.log(Logger.LogLevel.ERROR, "Error while parsing JSON:", e);
                }
            }
        });
        return future;
    }

    public void showUpdateDialog(MainActivity mainActivity, String newVersion) {
        Snackbar snack = Snackbar.make(mainActivity.getCurrentFocus(), mainActivity.getString(R.string.new_version_available) + ": " + newVersion, Snackbar.LENGTH_INDEFINITE);
        snack.setAction(R.string.get_it_here, v -> {
                Logger.log("Github opened for", newVersion);
                Uri url = Uri.parse("https://github.com/tim0-12432/f1-schedule-app/releases/latest");
                mainActivity.startActivity(new Intent(Intent.ACTION_VIEW, url));
            });
        snack.setActionTextColor(mainActivity.getResources().getColor(R.color.red_auburn));
        snack.show();
    }
}
