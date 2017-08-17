package com.iguideu.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import com.iguideu.R;

/**
 * Created by Hoyoung on 2017-08-02.
 */

public class CheckYOrNDialog extends Dialog {

    public CheckYOrNDialog(Context context) {
        super(context);
    }

    public TextView dialog_check_TextView;
    public Button dialog_Y_Btn;
    public Button dialog_N_Btn;

    String dialog_check_Text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog_y_or_n_layout);

        dialog_check_TextView = (TextView)findViewById(R.id.dialog_check_TextView);
        dialog_Y_Btn = (Button)findViewById(R.id.dialog_Y_Btn);
        dialog_N_Btn = (Button)findViewById(R.id.dialog_N_Btn);
    }


    public String getDialog_check_Text() {
        return dialog_check_Text;
    }

    public void setDialog_check_Text(String dialog_check_Text) {
        this.dialog_check_Text = dialog_check_Text;

        dialog_check_TextView.setText(dialog_check_Text);
    }

}


