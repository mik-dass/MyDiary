package com.example.joy.projecttest;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

/**
 * Created by Joy on 07/03/2015.
 */
public class AddViewer extends android.support.v4.app.Fragment {
    ArrayAdapter<String > arrayadapt;
    String[] str={"Happy","Sad","Excited","Lonely","Crazy","Shocked","Cool"};
    EditText edt;
    Spinner spin;
    DataAdapter dateadapt;
    Communicator comm;
    public AddViewer() {
        // TODO Auto-generated constructor stub
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootview=inflater.inflate(R.layout.add_fragment, container, false);
        arrayadapt=new ArrayAdapter<>(getActivity(), android.R.layout.simple_expandable_list_item_1, str);
        spin=(Spinner) rootview.findViewById(R.id.spinner);
        edt=(EditText) rootview.findViewById(R.id.editdescription);
        Button btn=(Button) rootview.findViewById(R.id.buttonsave);
        btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                String description=edt.getText().toString();
                String mood=spin.getSelectedItem().toString();
                if(description.trim().matches("")){
                    Toast.makeText(getActivity(), "Write Something", Toast.LENGTH_LONG).show();
                    edt.setText("");
                }
                else
                {
                    dateadapt=new DataAdapter(getActivity());
                    long i=dateadapt.insert(description, mood);
                    if(i>=0){Toast.makeText(getActivity(), "Saved", Toast.LENGTH_LONG).show();}
                    update();
                }
            }
        });
        spin.setAdapter(arrayadapt);
        return rootview;
    }

    public  void update() {
        // TODO Auto-generated method stub
        edt.setText("");
        spin.setSelection(0);
        comm=(Communicator) getActivity();
        comm.respond();
    }
}
