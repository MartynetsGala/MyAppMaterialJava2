package ru.martynets.myappmaterialjava2;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.android.material.navigation.NavigationView;

public class BottomNavigationDrawerFragment extends BottomSheetDialogFragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_bottomsheet, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        NavigationView navigationView  = (NavigationView) getView().findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                int mCurrentMenuEntry = menuItem.getItemId();
                switch (mCurrentMenuEntry) {
                    case (R.id.nav1):
                        Toast.makeText(BottomNavigationDrawerFragment.this.getActivity().getApplicationContext(),
                                "nav1", Toast.LENGTH_SHORT).show();
                        break;

                    case (R.id.nav2):
                        Toast.makeText(BottomNavigationDrawerFragment.this.getActivity().getApplicationContext(),
                                "nav2", Toast.LENGTH_SHORT).show();
                        break;
                    case (R.id.nav3):
                        Toast.makeText(BottomNavigationDrawerFragment.this.getActivity().getApplicationContext(),
                                "nav3", Toast.LENGTH_SHORT).show();
                        break;
                }
                //menuItem.setChecked(false);
                return true;
            }
        });

       /* NavigationMenuView navigationMenuView = (NavigationMenuView) navigationView.getChildAt(0);
        navigationMenuView.setVerticalScrollBarEnabled(false);*/

    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final BottomSheetDialog dialog = (BottomSheetDialog) super.onCreateDialog(savedInstanceState);
        dialog.setOnShowListener(new DialogInterface.OnShowListener() {
            BottomSheetDialog d = dialog;
            FrameLayout bottomSheet = (FrameLayout) d.findViewById(R.id.design_bottom_sheet);
            BottomSheetBehavior bottomSheetBehavior = BottomSheetBehavior.from(bottomSheet);
            @Override
            public void onShow(DialogInterface dialog) {

            }

        });
        return dialog;
    }

}
