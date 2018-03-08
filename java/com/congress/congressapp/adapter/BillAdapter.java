package com.congress.congressapp.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.congress.congressapp.R;
import com.congress.congressapp.dto.Bill_list;

import java.util.ArrayList;

public class BillAdapter extends ArrayAdapter<Bill_list>{


    ArrayList<Bill_list> billList;
    Context context;
    int resource;

    public BillAdapter(Context context, int resource, ArrayList<Bill_list> billList) {
        super(context, resource, billList);

        this.billList=billList;
        this.context=context;
        this.resource=resource;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if(convertView==null){
            LayoutInflater layoutInflater= (LayoutInflater) getContext().getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            convertView=layoutInflater.inflate(R.layout.bill_items,null,true);

        }
        Bill_list list=getItem(position);

        TextView textViewId= (TextView) convertView.findViewById(R.id.textViewId);
        TextView textViewTitle= (TextView) convertView.findViewById(R.id.textViewTitle);
        TextView textViewDate= (TextView) convertView.findViewById(R.id.textViewDate);

        textViewId.setText(list.getBillId());
        textViewTitle.setText(list.getBillTitle());
        textViewDate.setText(list.getBillIntroducedOn());

        return convertView;
    }
}
