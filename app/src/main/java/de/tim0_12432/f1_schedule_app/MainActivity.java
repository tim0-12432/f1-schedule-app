package de.tim0_12432.f1_schedule_app;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.progressindicator.LinearProgressIndicator;

import androidx.activity.OnBackPressedDispatcher;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import java.util.Objects;

import de.tim0_12432.f1_schedule_app.databinding.ActivityMainBinding;
import de.tim0_12432.f1_schedule_app.ui.schedule.ScheduleFragment;
import de.tim0_12432.f1_schedule_app.utility.Logger;

public class MainActivity extends AppCompatActivity {

    private static Resources resources;
    @SuppressLint("StaticFieldLeak")
    private static Context context;

    private ActivityMainBinding binding;

    private Menu menu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        resources = getResources();
        context = getApplicationContext();

        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        BottomNavigationView navView = findViewById(R.id.nav_view);
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_circuits, R.id.navigation_schedule, R.id.navigation_world_cup)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(binding.navView, navController);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getSupportFragmentManager();
                if (fragmentManager.getBackStackEntryCount() > 0) {
                    fragmentManager.popBackStack();
                } else {
                    navController.navigate(R.id.navigation_schedule);
                }
            }
        });

        navController.addOnDestinationChangedListener(new NavController.OnDestinationChangedListener() {
            @Override
            public void onDestinationChanged(@NonNull NavController navController, @NonNull NavDestination navDestination, @Nullable Bundle bundle) {
                if (menu != null) {
                    if (navDestination.getId() == R.id.navigation_schedule || Objects.requireNonNull(navDestination.getLabel()).toString().equals("{raceName}")) {
                        menu.clear();
                        getMenuInflater().inflate(R.menu.app_bar_menu, menu);
                    } else {
                        menu.clear();
                    }
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        this.menu = menu;
        getMenuInflater().inflate(R.menu.app_bar_menu, menu);
        return true;
    }

    @Override
    @SuppressLint("DetachAndAttachSameFragment")
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.refresh_action) {
            findViewById(R.id.schedule_progress).setVisibility(View.VISIBLE);
            ScheduleFragment.fetchLatestInformation();
            Fragment schedule = getSupportFragmentManager().findFragmentById(R.id.navigation_schedule);
            if (schedule != null) {
                getSupportFragmentManager().beginTransaction().detach(schedule).attach(schedule).commit();
            }
            findViewById(R.id.schedule_progress).setVisibility(View.INVISIBLE);
        }
        return super.onOptionsItemSelected(item);
    }

    public static Resources getAppResources() {
        return resources;
    }

    public static Context getAppContext() {
        return context;
    }
}