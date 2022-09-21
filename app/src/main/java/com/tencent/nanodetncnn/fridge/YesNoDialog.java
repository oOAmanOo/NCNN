package com.tencent.nanodetncnn.fridge;


import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import com.tencent.nanodetncnn.R;


public class YesNoDialog extends Dialog {


    private Listener_DialogYesNo mListenerYesNoDialog;

    public YesNoDialog(final Context context,
                       final Listener_DialogYesNo mListenerYesNoDialog

    ) {
        super(context);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog_yes_no);
        this.mListenerYesNoDialog = mListenerYesNoDialog;


        TextView dialog_yes_no_btn_confirm = findViewById(R.id.dialog_yes_no_btn_confirm);
        dialog_yes_no_btn_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                 mListenerYesNoDialog.onYes();
                dismiss();


            }
        });


        TextView dialog_yes_no_btn_cancel = findViewById(R.id.dialog_yes_no_btn_cancel);
        dialog_yes_no_btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListenerYesNoDialog.onNo();
                dismiss();

            }
        });

    }


}


