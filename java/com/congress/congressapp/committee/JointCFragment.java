package com.congress.congressapp.committee;


import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import com.congress.congressapp.R;
import com.congress.congressapp.adapter.CommitteeAdapter;
import com.congress.congressapp.dto.Committee_list;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class JointCFragment extends Fragment {

    ListView showdata;
    ArrayList<Committee_list> showlist = new ArrayList<Committee_list>();
    CommitteeAdapter adapter;
    String office;
    String parent_committee_id;


    public JointCFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.committees_list, container, false);

        showdata = (ListView) v.findViewById(R.id.listViewCommittees);

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


        CommitteesTask task=new CommitteesTask();
        task.execute("http://104.198.0.197:8080/committees?per_page=all&chamber=joint");

        return v;
    }
    class CommitteesTask extends AsyncTask<String, Void, String> {

        ProgressDialog dialogadd = new ProgressDialog(getActivity());
        @Override
        protected void onPreExecute() {
            dialogadd.setMessage("Please wait.....");
            dialogadd.show();
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... params) {

            HttpURLConnection connection = null;
            BufferedReader reader = null;
            try {
                URL url=new URL(params[0]);
                connection= (HttpURLConnection) url.openConnection();
                connection.connect();

                InputStream stream=connection.getInputStream();
                reader=new BufferedReader(new InputStreamReader(stream));
                StringBuffer buffer=new StringBuffer();

                String line="";
                while((line=reader.readLine())!=null){
                    buffer.append(line);
                }
                return buffer.toString();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }finally {
                if(connection !=null) {
                    connection.disconnect();
                }
                try {
                    if(reader !=null) {
                        reader.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return  null;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            dialogadd.dismiss();
            try {

                //Parse the entire JSON String
                JSONObject jObject = new JSONObject(result);

                JSONArray array=jObject.getJSONArray("results");
                for(int i=0;i<array.length();i++){

                    JSONObject jobj=array.getJSONObject(i);

                    String com_id=jobj.getString("committee_id");
                    String name=jobj.getString("name");
                    String chamber=jobj.getString("chamber");


                    Committee_list list=new Committee_list(com_id,name,chamber,"N.A.","N.A.","N.A.");
                    Collections.sort(showlist, new Comparator<Committee_list>() {
                        @Override
                        public int compare(Committee_list committee_list, Committee_list t1) {
                            return committee_list.getCommitteeName().compareTo(t1.getCommitteeName());
                        }
                    });
                    showlist.add(list);

                }

            } catch (JSONException e) {
                e.printStackTrace();
            }
            adapter=new CommitteeAdapter(getActivity(),R.layout.committees_item,showlist);
            showdata.setAdapter(adapter);
        }
    }

}
