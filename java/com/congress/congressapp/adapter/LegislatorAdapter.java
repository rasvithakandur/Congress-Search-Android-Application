package com.congress.congressapp.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.congress.congressapp.R;
import com.congress.congressapp.dto.Legislator_list;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
public class LegislatorAdapter extends ArrayAdapter<Legislator_list>{

    ArrayList<Legislator_list> legList;
    Context context;
    int resource;


    public LegislatorAdapter(Context context, int resource, ArrayList<Legislator_list> legList) {
        super(context, resource, legList);

        this.legList=legList;
        this.context=context;
        this.resource=resource;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LinearLayout ll=new LinearLayout(context);
        if(convertView==null){
            LayoutInflater layoutInflater= (LayoutInflater) getContext().getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            convertView=layoutInflater.inflate(R.layout.legislator_item,null,true);

        }

        Legislator_list list=getItem(position);
        ImageView imageViewLeg= (ImageView) convertView.findViewById(R.id.imageViewLeg);
        Picasso.with(context).load(list.getLegImage()).into(imageViewLeg);

        TextView textViewLegLName= (TextView) convertView.findViewById(R.id.textViewLegLName);
        TextView textViewLegFName= (TextView) convertView.findViewById(R.id.textViewLegFName);
        TextView textViewLegParty= (TextView) convertView.findViewById(R.id.textViewLegParty);
        TextView textViewLegStatename= (TextView) convertView.findViewById(R.id.textViewLegStatename);
        TextView textViewLegDistrict= (TextView) convertView.findViewById(R.id.textViewLegDistrict);

        textViewLegLName.setText(list.getLegLName());
        textViewLegFName.setText(list.getLegFName());
        textViewLegParty.setText(list.getLegPartyName());
        textViewLegStatename.setText(list.getLegStatename());
        textViewLegDistrict.setText(list.getLegDistrict());

        if(list.getLegDistrict().equals("null")){
            textViewLegDistrict.setText("0");
        }
        return convertView;

    }

}
