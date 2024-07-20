package org.javakov.washapp.fragment;

import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.res.ResourcesCompat;
import androidx.fragment.app.Fragment;

import org.javakov.washapp.R;
import org.javakov.washapp.helper.CustomTypefaceSpan;

public class InfoFragment extends Fragment {

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_about, container, false);

        TextView phone = view.findViewById(R.id.phone);
        TextView whatsapp = view.findViewById(R.id.whatsapp);
        TextView telegram = view.findViewById(R.id.telegram);

        String phoneText = " Звоните: +7 (999) 789-31-87";
        SpannableString phoneSpannableString = new SpannableString(phoneText);

        Typeface ubuntuPhone = ResourcesCompat.getFont(requireContext(), R.font.ubuntu);
        CustomTypefaceSpan ubuntuSpanPhone = new CustomTypefaceSpan(ubuntuPhone);
        phoneSpannableString.setSpan(ubuntuSpanPhone, 0, 10, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

        Typeface ubuntuItalicPhone = ResourcesCompat.getFont(requireContext(), R.font.ubuntu_italic);
        CustomTypefaceSpan italicSpanPhone = new CustomTypefaceSpan(ubuntuItalicPhone);
        phoneSpannableString.setSpan(italicSpanPhone, 10, phoneText.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        phoneSpannableString.setSpan(new UnderlineSpan(), 10, phoneText.length(), 0);

        phone.setText(phoneSpannableString);




        String whatsappText = " WhatsApp: https://wa.me/79997893187";
        SpannableString whatsappSpannableString = new SpannableString(whatsappText);

        Typeface ubuntuWhatsApp = ResourcesCompat.getFont(requireContext(), R.font.ubuntu);
        CustomTypefaceSpan ubuntuSpanWhatsApp = new CustomTypefaceSpan(ubuntuWhatsApp);
        whatsappSpannableString.setSpan(ubuntuSpanWhatsApp, 0, 10, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

        Typeface ubuntuItalicWhatsApp = ResourcesCompat.getFont(requireContext(), R.font.ubuntu_italic);
        CustomTypefaceSpan italicSpanWhatsApp = new CustomTypefaceSpan(ubuntuItalicWhatsApp);
        whatsappSpannableString.setSpan(italicSpanWhatsApp, 10, whatsappText.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        whatsappSpannableString.setSpan(new UnderlineSpan(), 11, whatsappText.length(), 0);

        whatsapp.setText(whatsappSpannableString);


        String telegramText = " Telegram: https://t.me/Mosal63";
        SpannableString telegramSpannableString = new SpannableString(telegramText);

        Typeface ubuntuTypeface = ResourcesCompat.getFont(requireContext(), R.font.ubuntu);
        CustomTypefaceSpan ubuntuSpan = new CustomTypefaceSpan(ubuntuTypeface);
        telegramSpannableString.setSpan(ubuntuSpan, 0, 10, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

        Typeface ubuntuItalicTypeface = ResourcesCompat.getFont(requireContext(), R.font.ubuntu_italic);
        CustomTypefaceSpan italicSpan = new CustomTypefaceSpan(ubuntuItalicTypeface);
        telegramSpannableString.setSpan(italicSpan, 10, telegramText.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        telegramSpannableString.setSpan(new UnderlineSpan(), 11, telegramText.length(), 0);
        telegram.setText(telegramSpannableString);

        phone.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_DIAL);
            intent.setData(Uri.parse("tel:+79997893187"));
            startActivity(intent);
        });

        whatsapp.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse("https://wa.me/79997893187"));
            startActivity(intent);
        });

        telegram.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse("https://t.me/Mosal63"));
            startActivity(intent);
        });

        return view;
    }
}