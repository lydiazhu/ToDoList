package com.example.android.todolist;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.support.v4.app.DialogFragment;
import android.view.View;
import android.widget.EditText;


public class AddDialog extends DialogFragment {

        public interface NoticeDialogListener{
            public void onDialogPositiveClick(DialogFragment dialog, EditText toDoListItem);

        }

        NoticeDialogListener mlistener;

        @Override
        public void onAttach(Activity activity){
            super.onAttach(activity);
            try{
                mlistener = (NoticeDialogListener) activity;
            }catch (ClassCastException e){
                throw new ClassCastException(activity.toString()
                                +" must implement AddDialog");
            }
        }
        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            LayoutInflater inflater = getActivity().getLayoutInflater();
            View layout = inflater.inflate(R.layout.add_dialog,null);
            builder.setView(layout);
            final EditText toDoListItem = (EditText) layout.findViewById(R.id.addToDo);
            builder.setPositiveButton(R.string.okay, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int id) {
                    mlistener.onDialogPositiveClick(AddDialog.this, toDoListItem);
                }
            })
                    .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    AddDialog.this.getDialog().cancel();
                                }
                            }
                    );
            return builder.create();
        }

}
