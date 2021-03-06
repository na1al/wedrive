package com.dev.wedrive.dialog;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.dev.wedrive.R;

import lombok.Setter;
import lombok.experimental.Accessors;

@Accessors(chain = true)
public class InformDialog implements DialogInterface {

    private Context context;

    @Setter
    private String headerText;

    @Setter
    private String messageText;


    public InformDialog(Context context) {
        this.context = context;
    }

    public AlertDialog create() {

        AlertDialog dialogBuilder = new AlertDialog.Builder(context).create();

        LayoutInflater inflater = LayoutInflater.from(context);
        View dialogView = inflater.inflate(R.layout.dialog_inform, null);

        TextView header = dialogView.findViewById(R.id.dialog_header);
        TextView message = dialogView.findViewById(R.id.dialog_message);
        header.setText(headerText);
        message.setText(messageText);

        Button ok = dialogView.findViewById(R.id.ok_btn);

        ok.setOnClickListener((v) -> dialogBuilder.cancel());

        dialogBuilder.setView(dialogView);
        return dialogBuilder;
    }
}
