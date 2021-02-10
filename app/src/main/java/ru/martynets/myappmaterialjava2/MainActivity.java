package ru.martynets.myappmaterialjava2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.widget.NestedScrollView;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomappbar.BottomAppBar;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private int currentFabAlignmentMode = BottomAppBar.FAB_ALIGNMENT_MODE_CENTER;
    private BottomAppBar barAppBottom;
    FloatingActionButton btnFab;
    private Button btnScreen;
    private TextView scrLabel;
    private CoordinatorLayout layCoordinate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        barAppBottom = (BottomAppBar) findViewById(R.id.bottom_app_bar);
        setSupportActionBar(barAppBottom);

        btnFab = (FloatingActionButton) findViewById(R.id.fab);
        btnScreen = (Button) findViewById(R.id.toggle_fab_alignment_button);
        scrLabel = (TextView) findViewById(R.id.screen_label);
        layCoordinate = (CoordinatorLayout) findViewById(R.id.coordinatorLayout);

        btnScreen.setOnClickListener(this);
        btnFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               /* Snackbar.make(layCoordinate, "Вы нажали FAB!", Snackbar.LENGTH_LONG)
                        .setAction("Action", null)
                        .show();*/
                int marginSide = 0;
                int marginBottom = 600;
                Snackbar snackbar = Snackbar.make(layCoordinate, "Вы нажали FAB!", Snackbar.LENGTH_LONG)
                        .setAction("Action", null);
                View snackbarView = snackbar.getView();
                CoordinatorLayout.LayoutParams params = (CoordinatorLayout.LayoutParams) snackbarView.getLayoutParams();
                params.setMargins(params.leftMargin + marginSide,
                        params.topMargin,
                        params.rightMargin + marginSide,
                        params.bottomMargin + marginBottom);
                snackbarView.setLayoutParams(params);
                snackbar.show();

            }
        });
    }

    @Override
    public boolean onOptionsItemSelected (MenuItem item){
        int id = item.getItemId();

        if (id == R.id.app_bar_fav) {
            Toast.makeText(getApplicationContext(),
                    "app_bar_fav", Toast.LENGTH_SHORT).show();
        }
        if (id == R.id.app_bar_search) {
            Toast.makeText(getApplicationContext(),
                    "app_bar_search", Toast.LENGTH_SHORT).show();
        }
        if (id == R.id.app_bar_settings) {
            Toast.makeText(getApplicationContext(),
                    "app_bar_settings", Toast.LENGTH_SHORT).show();
        }
        if (id == android.R.id.home) {
            BottomNavigationDrawerFragment bottomNavDrawerFragment = new BottomNavigationDrawerFragment();
            bottomNavDrawerFragment.show(getSupportFragmentManager(), bottomNavDrawerFragment.getTag());
        }
        return super.onOptionsItemSelected(item);
    }


    final FloatingActionButton.OnVisibilityChangedListener addVisibilityChangedListener = new FloatingActionButton.OnVisibilityChangedListener() {
        @Override
        public void onShown(final FloatingActionButton btnFab) {
            super.onShown(btnFab);
        }

        @Override
        public void onHidden(final FloatingActionButton btnFab) {
            super.onHidden(btnFab);
            if (currentFabAlignmentMode == BottomAppBar.FAB_ALIGNMENT_MODE_CENTER) {
                barAppBottom.setFabAlignmentMode(BottomAppBar.FAB_ALIGNMENT_MODE_END);
                currentFabAlignmentMode = BottomAppBar.FAB_ALIGNMENT_MODE_END;
            } else {
                barAppBottom.setFabAlignmentMode(BottomAppBar.FAB_ALIGNMENT_MODE_CENTER);
                currentFabAlignmentMode = BottomAppBar.FAB_ALIGNMENT_MODE_CENTER;
            }

            if (currentFabAlignmentMode == BottomAppBar.FAB_ALIGNMENT_MODE_CENTER) {
                barAppBottom.replaceMenu(R.menu.bottomappbar_menu);
            } else {
                barAppBottom.replaceMenu(R.menu.bottomappbar_menu_secondary);
            }

            if (currentFabAlignmentMode == BottomAppBar.FAB_ALIGNMENT_MODE_CENTER) {
                btnFab.setImageDrawable(getDrawable(R.drawable.ic_add_on_secondary_24dp));
            } else {
                btnFab.setImageDrawable(getDrawable(R.drawable.ic_backup));
            }

            btnFab.show();
        }
    };


    @Override
    public boolean onCreateOptionsMenu (Menu menu){
        getMenuInflater().inflate(R.menu.bottomappbar_menu, menu);
        return true;
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.toggle_fab_alignment_button: {
                btnFab.hide(addVisibilityChangedListener);
                invalidateOptionsMenu();
                if (barAppBottom.getNavigationIcon() != null) {
                    barAppBottom.setNavigationIcon(null);
                } else
                    barAppBottom.setNavigationIcon(getDrawable(R.drawable.ic_menu_control_normal_24dp));

                if (scrLabel.getText() == getString(R.string.primary_screen_text)) {
                    scrLabel.setText(getString(R.string.secondary_sceen_text));
                } else {
                    scrLabel.setText(getString(R.string.primary_screen_text));
                }
            }
        }
    }

}
