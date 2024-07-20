package org.javakov.washapp;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import androidx.activity.EdgeToEdge;
import androidx.activity.OnBackPressedCallback;
import androidx.activity.OnBackPressedDispatcher;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.content.res.ResourcesCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.color.MaterialColors;

import org.javakov.washapp.fragment.CarListFragment;
import org.javakov.washapp.fragment.InfoFragment;
import org.javakov.washapp.helper.CustomTypefaceSpan;

import java.util.Optional;

public class MainActivity extends AppCompatActivity {
    private CarListFragment carListFragment;
    private InfoFragment infoFragment;
    private Fragment activeFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        carListFragment = new CarListFragment();
        infoFragment = new InfoFragment();
        activeFragment = getSupportFragmentManager().findFragmentById(R.id.fragmentLayout);
        MaterialToolbar toolbar = findViewById(R.id.toolbarView);

        setSupportActionBar(toolbar);
        toolbar.setTitleTextAppearance(this, R.style.UbuntuBoldTextAppearance);
        toolbar.setLogo(R.drawable.baseline_home_repair_service_24);
        setTitle(" Ремонт бытовой техники");
        toolbar.setSubtitle(" Тверь");

        int dynamicColor = MaterialColors.getColor(this, com.google.android.material.R.attr.colorPrimary, Color.BLACK);
        Drawable logoDrawable = ContextCompat.getDrawable(this, R.drawable.baseline_home_repair_service_24);
        if (logoDrawable != null) {
            logoDrawable.setTint(dynamicColor);
            toolbar.setLogo(logoDrawable);
        }

        BottomNavigationView bottomNav = findViewById(R.id.bottomNav);
        bottomNav.setOnItemSelectedListener(item -> {
            Optional<Fragment> fragment = Optional.empty();

            if (item.getItemId() == R.id.tab_repairs) {
                fragment = Optional.of(carListFragment);
            } else if (item.getItemId() == R.id.tab_about) {
                fragment = Optional.of(infoFragment);
            }

            if (fragment.isPresent() && activeFragment != fragment.get()) {
                loadFragment(fragment.get());
                return true;
            }

            return false;
        });


        Menu menu = bottomNav.getMenu();
        for (int i = 0; i < menu.size(); i++) {
            MenuItem item = menu.getItem(i);
            SpannableString spannableString = new SpannableString(item.getTitle());
            Typeface typeface = ResourcesCompat.getFont(this, R.font.ubuntu_medium);
            spannableString.setSpan(new CustomTypefaceSpan(typeface), 0, spannableString.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            item.setTitle(spannableString);
        }

        if (savedInstanceState == null) {
            loadFragment(carListFragment);
            Log.d(TAG, "зарегал при входе кардфрагмент");
        }
        else {
            if (activeFragment != null) {
                Log.d(TAG, "Активный фрагмент" + activeFragment);
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragmentLayout, activeFragment)
                        .show(activeFragment)
                        .commitNow();

                if (activeFragment instanceof CarListFragment) {
                    bottomNav.setSelectedItemId(R.id.tab_repairs);
                }
            }
            else {
                loadFragment(carListFragment);
                Log.d(TAG, "Загружен новый кардфрагмент");
            }
        }

        OnBackPressedDispatcher onBackPressedDispatcher = this.getOnBackPressedDispatcher();
        onBackPressedDispatcher.addCallback(this, new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                bottomNav.setSelectedItemId(R.id.tab_repairs);
            }
        });
    }

    private void loadFragment(Fragment fragment) {

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        if (activeFragment != null) {
            transaction.hide(activeFragment);
        }

        if (fragment.isAdded() && !fragment.isVisible()) {
            transaction.show(fragment);
        } else {
            transaction.add(R.id.fragmentLayout, fragment);
        }

        activeFragment = fragment;

        transaction.addToBackStack(null);
        transaction.commit();

        getSupportFragmentManager().addOnBackStackChangedListener(new FragmentManager.OnBackStackChangedListener() {
            @Override
            public void onBackStackChanged() {
                if (getSupportFragmentManager().getBackStackEntryCount() > 0) {
                    getSupportFragmentManager().removeOnBackStackChangedListener(this);
                }
            }
        });
    }
}