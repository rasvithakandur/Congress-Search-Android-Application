package com.congress.congressapp.favorites;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import com.congress.congressapp.R;
import com.congress.congressapp.dto.Legislator_list;
import com.congress.congressapp.sqlite.DatabaseHelper;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


public class LegislatorFavFragment extends Fragment {

    ListView showdata;
    ArrayList<Legislator_list> showlist = new ArrayList<Legislator_list>();
    ImageView imageViewLeg;
    TextView textViewLegLName,textViewLegFName,textViewLegParty,textViewLegStatename,textViewLegDistrict;
    myadapter adpshow;
    View v;
    DatabaseHelper myDb;

    public LegislatorFavFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.legislator_favdetaillist, container, false);
        myDb=new DatabaseHelper(getActivity());

        showdata = (ListView) v.findViewById(R.id.listViewLegislator);

        //Show all favorites bill
        showlist=myDb.allLegView();

        showdata.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                //Get item position
                Legislator_list list = (Legislator_list) parent.getItemAtPosition(position);

                Intent in1 = new Intent(getActivity(), LegFavDetailActivity.class);
                in1.putExtra("image", list.getLegImage());
                in1.putExtra("lname", list.getLegLName());
                in1.putExtra("fname", list.getLegFName());
                in1.putExtra("partyname", list.getLegPartyName());
                in1.putExtra("statename", list.getLegState());
                in1.putExtra("district",list.getLegDistrict());
                in1.putExtra("dparty", list.getLegDparty());
                in1.putExtra("title", list.getLegTitle());
                in1.putExtra("email", list.getLegEmail());
                in1.putExtra("chamber", list.getLegChamber());
                in1.putExtra("contact", list.getLegPhone());
                in1.putExtra("start_term", list.getLegStartTerm());
                in1.putExtra("end_term", list.getLegEndTerm());
                in1.putExtra("office", list.getLegOffice());
                in1.putExtra("state", list.getLegState());
                in1.putExtra("fax", list.getLegFax());
                in1.putExtra("birthday", list.getLegBirthday());
                in1.putExtra("website", list.getLegWebsite());
                in1.putExtra("twitter_id",list.getLegTwitter());
                in1.putExtra("facebook_id",list.getLegFacebook());


                startActivity(in1);
            }
        });

        adpshow=new myadapter();
        showdata.setAdapter(adpshow);
        return v;

    }
    class myadapter extends BaseAdapter{

        @Override
        public int getCount() {
            return showlist.size();
        }

        @Override
        public Object getItem(int position) {
            return showlist.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {

            final Legislator_list list=showlist.get(position);

            LayoutInflater inflater =getActivity().getLayoutInflater();
            View v=inflater.inflate(R.layout.legislator_item,null);

            textViewLegLName= (TextView) v.findViewById(R.id.textViewLegLName);
            textViewLegFName= (TextView) v.findViewById(R.id.textViewLegFName);
            textViewLegParty= (TextView) v.findViewById(R.id.textViewLegParty);
            textViewLegStatename= (TextView) v.findViewById(R.id.textViewLegStatename);
            textViewLegDistrict= (TextView) v.findViewById(R.id.textViewLegDistrict);
            imageViewLeg= (ImageView) v.findViewById(R.id.imageViewLeg);
            Picasso.with(getActivity()).load(list.getLegImage()).into(imageViewLeg);

            textViewLegLName.setText(list.getLegLName());
            textViewLegFName.setText(list.getLegFName());
            textViewLegParty.setText(list.getLegDparty());
            textViewLegStatename.setText(list.getLegStatename());
            textViewLegDistrict.setText(list.getLegDistrict());

            if(list.getLegDistrict().equals("null")){
                textViewLegDistrict.setText("0");
            }

            Collections.sort(showlist, new Comparator<Legislator_list>() {
                @Override
                public int compare(Legislator_list legislator_list, Legislator_list t1) {
                    return legislator_list.getLegLName().compareTo(t1.getLegLName());
                }
            });
            return v;
        }
    }

}
