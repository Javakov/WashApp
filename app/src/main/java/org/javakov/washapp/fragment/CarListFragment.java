package org.javakov.washapp.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import com.google.android.material.button.MaterialButton;

import org.javakov.washapp.R;

import java.util.Objects;

public class CarListFragment extends Fragment {

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_carlist, container, false);

        MaterialButton button1 = view.findViewById(R.id.add0);
        MaterialButton button2 = view.findViewById(R.id.add1);
        MaterialButton button3 = view.findViewById(R.id.add2);

        button1.setOnClickListener(this::showDialog);
        button2.setOnClickListener(this::showDialog);
        button3.setOnClickListener(this::showDialog);

        return view;
    }

    private void showDialog(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
        View dialogView = LayoutInflater.from(view.getContext()).inflate(R.layout.dialog_request, null);

        builder.setView(dialogView);

        AlertDialog dialog = builder.create();
        Objects.requireNonNull(dialog.getWindow()).setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();

        dialogView.findViewById(R.id.phone_button).setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_DIAL);
            intent.setData(Uri.parse("tel:+79997893187"));
            startActivity(intent);

            dialog.dismiss();
        });

        dialogView.findViewById(R.id.whatsapp_button).setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse("https://wa.me/79997893187"));
            startActivity(intent);

            dialog.dismiss();
        });

        dialogView.findViewById(R.id.telegram_button).setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse("https://t.me/Mosal63"));
            startActivity(intent);

            dialog.dismiss();
        });
    }
}
