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

public class CheckRouteDialog extends Dialog {

    public CheckRouteDialog(Context context) {
        super(context);
    }

    public TextView dialog_check_TextView;
    public Button dialog_check_Btn;

    String dialog_check_Text;
    String dialog_check_Btn_Text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog_check_layout);

        dialog_check_TextView = (TextView)findViewById(R.id.dialog_check_TextView);
        dialog_check_Btn = (Button)findViewById(R.id.dialog_check_Btn);

    }


    public String getDialog_check_Text() {
        return dialog_check_Text;
    }

    public void setDialog_check_Text(String dialog_check_Text) {
        this.dialog_check_Text = dialog_check_Text;
        dialog_check_TextView.setText(dialog_check_Text);
    }

    public String getDialog_check_Btn_Text() {
        return dialog_check_Btn_Text;
    }

    public void setDialog_check_Btn_Text(String dialog_check_Btn_Text) {
        this.dialog_check_Btn_Text = dialog_check_Btn_Text;
        dialog_check_Btn.setText(dialog_check_Btn_Text);
    }
}


