package com.congress.congressapp.favorites;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import com.congress.congressapp.R;
import com.congress.congressapp.adapter.CommitteeAdapter;
import com.congress.congressapp.committee.CommitteeDetailActivity;
import com.congress.congressapp.dto.Committee_list;
import com.congress.congressapp.sqlite.DatabaseHelper;

import java.util.ArrayList;

public class CommitteesFavFragment extends Fragment {

    ListView showdata;
    ArrayList<Committee_list> showlist = new ArrayList<Committee_list>();
    CommitteeAdapter adapter;
    DatabaseHelper myDb;


    public CommitteesFavFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.committees_list, container, false);
        myDb=new DatabaseHelper(getActivity());
        showdata = (ListView) v.findViewById(R.id.listViewCommittees);

        //Show all favorites committee
        showlist=myDb.allCommitteeView();

        showdata.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                //Get item position
                Committee_list list= (Committee_list) parent.getItemAtPosition(position);

                Intent in1=new Intent(getActivity(),CommitteeDetailActivity.class);
                in1.putExtra("list", list);
                in1.putExtra("chamber",list.getCommitteeChamber());
                startActivity(in1);
            }
        });

        adapter=new CommitteeAdapter(getActivity(),R.layout.committees_item,showlist);
        showdata.setAdapter(adapter);
        return v;
    }

}
