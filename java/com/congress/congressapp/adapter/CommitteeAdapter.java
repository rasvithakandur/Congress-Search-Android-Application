package com.congress.congressapp.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import com.congress.congressapp.R;
import com.congress.congressapp.dto.Committee_list;

import java.util.ArrayList;

public class CommitteeAdapter extends ArrayAdapter<Committee_list>{

    ArrayList<Committee_list> comList;
    Context context;
    int resource;

    public CommitteeAdapter(Context context, int resource, ArrayList<Committee_list> comList) {
        super(context, resource, comList);

        this.comList=comList;
        this.context=context;
        this.resource=resource;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if(convertView==null){
            LayoutInflater layoutInflater= (LayoutInflater) getContext().getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            convertView=layoutInflater.inflate(R.layout.committees_item,null,true);

        }
        Committee_list list=getItem(position);

        TextView textViewComId= (TextView) convertView.findViewById(R.id.textViewComId);
        TextView textViewComName= (TextView) convertView.findViewById(R.id.textViewComName);
        TextView textViewComChamber= (TextView) convertView.findViewById(R.id.textViewComChamber);

        textViewComId.setText(list.getCommitteeId());
        textViewComName.setText(list.getCommitteeName());
        textViewComChamber.setText(list.getCommitteeChamber());

        return convertView;
    }
}
