package com.example.app7;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.telecom.Call;
import android.widget.DatePicker;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import java.util.Calendar;

public class DatePickerFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        final Calendar cale = Calendar.getInstance();
        int year = cale.get(Calendar.YEAR);
        int mont = cale.get(Calendar.MONTH);
        int day = cale.get(Calendar.DAY_OF_MONTH);
        return new DatePickerDialog(requireContext(), this, year, mont, day);
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        Toast.makeText(getContext(), "Date " + dayOfMonth + "/" + month + "/" + year, Toast.LENGTH_SHORT).show();

    }
}
