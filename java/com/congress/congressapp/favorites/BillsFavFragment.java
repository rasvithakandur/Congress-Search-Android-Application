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
import com.congress.congressapp.adapter.BillAdapter;
import com.congress.congressapp.bills.BillDetailActivity;
import com.congress.congressapp.dto.Bill_list;
import com.congress.congressapp.sqlite.DatabaseHelper;

import java.util.ArrayList;

public class BillsFavFragment extends Fragment {

    ListView showdata;
    ArrayList<Bill_list> showlist = new ArrayList<Bill_list>();
    BillAdapter adapter;
    DatabaseHelper myDb;

    public BillsFavFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.bill_list, container, false);
        myDb=new DatabaseHelper(getActivity());
        showdata = (ListView) v.findViewById(R.id.listViewBill);

        //Show all favorites bill
        showlist=myDb.allBillView();

        showdata.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                //Get item position
                Bill_list list= (Bill_list) parent.getItemAtPosition(position);

                Intent in1=new Intent(getActivity(),BillDetailActivity.class);
                in1.putExtra("list",list);
                in1.putExtra("Status",list.getBillStatus());
                startActivity(in1);
            }
        });

        adapter=new BillAdapter(getActivity(),R.layout.bill_items,showlist);
        showdata.setAdapter(adapter);

        return v;
    }
}
