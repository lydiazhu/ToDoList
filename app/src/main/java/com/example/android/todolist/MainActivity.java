package com.example.android.todolist;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;


public class MainActivity extends ActionBarActivity  implements AddDialog.NoticeDialogListener {
    ToDoListFragment fragment = new ToDoListFragment();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, fragment)
                    .commit();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
    public void showAddDialog(){
        DialogFragment dialog = new AddDialog();
        dialog.show(getSupportFragmentManager(), "AddDialog");
    }
    public void onDialogPositiveClick(DialogFragment dialog,EditText toDoListItem){
        CheckBox checkBox = new CheckBox(this);
        checkBox.setText((CharSequence) toDoListItem.getText());
        fragment.mToDoList.add(checkBox);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        if (id == R.id.add){

            showAddDialog();
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class ToDoListFragment extends Fragment {

        public ToDoListFragment() {
        }

        ArrayAdapter<CheckBox> mToDoList;

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                Bundle savedInstanceState) {


            View rootview = inflater.inflate(R.layout.fragment_main,container,false);
            mToDoList = new ArrayAdapter<CheckBox>(
                    getActivity(),
                    R.layout.list_view_item,
                    new ArrayList<CheckBox>());

            ListView listView = (ListView) rootview.findViewById(R.id.toDoListView);
            listView.setAdapter(mToDoList);
            return rootview;
        }


    }
}

