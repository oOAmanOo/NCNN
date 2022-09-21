package com.tencent.nanodetncnn;

import android.app.Dialog;
import android.app.TimePickerDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;

import androidx.fragment.app.DialogFragment;

import java.util.Calendar;


public class editfridge_dfragment4 extends DialogFragment {
    public Dialog efdialog4;
    @Override
    public View onCreateView(LayoutInflater inflater,  ViewGroup container,  Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.editfridge4_layout, container);
        efdialog4 = this.getDialog();
        efdialog4.getWindow().requestFeature(Window.FEATURE_NO_TITLE);

        Spinner ef4_spinner = (Spinner) view.findViewById(R.id.ef4_spinner);
        String[] spinner_array = new String[MainActivity.notify_user_index];
        for (int i = 0; i < MainActivity.notify_user_index; i++) {
            spinner_array[i] = MainActivity.notify_user_name[i];
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String> (MainActivity.mContext2, android.R.layout.simple_spinner_item, spinner_array);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ef4_spinner.setAdapter(adapter);
        ef4_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                MainActivity.notify_uid = MainActivity.notify_user_id[ef4_spinner.getSelectedItemPosition()];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        TextView ef4_time_textview = (TextView) view.findViewById(R.id.ef4_time_textview);
        ef4_time_textview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimePicker timePicker = new TimePicker(MainActivity.mContext2);
                Calendar calendar = Calendar.getInstance();
                int hourOfDay = calendar.get(Calendar.HOUR_OF_DAY);
                int month = calendar.get(Calendar.MINUTE);
                TimePickerDialog timePickerDialog = new TimePickerDialog(MainActivity.mContext2, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        String time = String.valueOf(hourOfDay) + ":" + String.valueOf(minute);
                        ef4_time_textview.setText(time);
                        MainActivity.notify_notifyTime = time + ":00";
                    }
                },20,0,true);
//                timePickerDialog.
//                        setMinDate(System.currentTimeMillis()-10000);
                timePickerDialog.show();
            }
        });

        EditText ef4_remark_edittext = (EditText) view.findViewById(R.id.ef4_remark_edittext);
        ef4_remark_edittext.setText(MainActivity.notify_notification);
        ef4_remark_edittext.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(!(ef4_remark_edittext.getText().toString().matches(""))){
                    MainActivity.notify_notification = ef4_remark_edittext.getText().toString();
                }else{
                    MainActivity.notify_notification = "NULL";
                }
            }
        });

        Button re_button = (Button) view.findViewById(R.id.ef4_re_button);
        re_button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                efdialog4.dismiss();
                MainActivity.current_editdialog = -1;
                MainActivity.editdialog_change(MainActivity.current_editdialog, MainActivity.origin_editdialog, MainActivity.fm_p);
            }
        });

        Button next_button = (Button) view.findViewById(R.id.ef4_next_button);
        next_button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                MainActivity.notify_dialog = 1;
                MainActivity.current_editdialog = 0;
                efdialog4.dismiss();
                MainActivity.editdialog_change(MainActivity.current_editdialog, MainActivity.origin_editdialog, MainActivity.fm_p);
            }
        });

        return view;
    }
    @Override
    public void onStart() {
        super.onStart();

        Dialog dialog = getDialog();
        if (dialog != null) {
            WindowManager.LayoutParams params = dialog.getWindow().getAttributes();
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            dialog.getWindow().setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            dialog.getWindow().setGravity(Gravity.CENTER);
        }
    }
}
