package com.congress.congressapp.legislators;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.congress.congressapp.R;
import com.congress.congressapp.adapter.LegislatorAdapter;
import com.congress.congressapp.dto.Legislator_list;
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
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class HouseLFragment extends Fragment {

    ListView showdata;
    ArrayList<Legislator_list> showlist = new ArrayList<Legislator_list>();
    LegislatorAdapter adapter;
    String dparty;
    int partylogo;
    String website;
    String twitter;
    String facebook;
    View v;
    LinearLayout indexLayout;
    Map<String, Integer> mapIndex;
    public static final String IMAGE_URL="https://theunitedstates.io/images/congress/225x275/";

    public HouseLFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.legislator_list, container, false);

        indexLayout = (LinearLayout) v.findViewById(R.id.side_index);
        showdata = (ListView) v.findViewById(R.id.listViewLegislator);

        showdata.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                //Get item position
                Legislator_list list= (Legislator_list) parent.getItemAtPosition(position);

                Intent in1 = new Intent(getActivity(), LegislatorsDetailActivity.class);
                in1.putExtra("image", list.getLegImage());
                in1.putExtra("lname", list.getLegLName());
                in1.putExtra("fname", list.getLegFName());
                in1.putExtra("partyname", list.getLegPartyName());
                in1.putExtra("statename", list.getLegStatename());
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

        LegislatorTask task=new LegislatorTask();
        task.execute("http://104.198.0.197:8080/legislators?per_page=all&chamber=house&order=last_name__asc");

        return v;
    }
    class LegislatorTask extends AsyncTask<String, Void, String> {

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
        protected void onPostExecute(String s) {
            dialogadd.dismiss();
            try {
                JSONObject jsonObject=new JSONObject(s);
                JSONArray array=jsonObject.getJSONArray("results");

                for (int i=0;i<array.length();i++){

                    JSONObject jsonObject1=array.getJSONObject(i);

                    String title= jsonObject1.getString("title");
                    String lname= jsonObject1.getString("last_name");
                    String fname= jsonObject1.getString("first_name");
                    String partyname= jsonObject1.getString("party");
                    String statename= jsonObject1.getString("state_name");
                    String district= jsonObject1.getString("district");
                    String email= jsonObject1.getString("oc_email");
                    String chamber= jsonObject1.getString("chamber");
                    String phone= jsonObject1.getString("phone");
                    String startterm= jsonObject1.getString("term_start");
                    String endterm= jsonObject1.getString("term_end");
                    String office= jsonObject1.getString("office");
                    String state= jsonObject1.getString("state");
                    String fax= jsonObject1.getString("fax");
                    String birthday= jsonObject1.getString("birthday");
                    String bioguide_id= jsonObject1.getString("bioguide_id");
                    String image=IMAGE_URL+bioguide_id+".jpg";

                    if (jsonObject1.has("website")) {

                        website= jsonObject1.getString("website");
                    } else{
                        website="N.A.";
                    }
                    if (jsonObject1.has("twitter_id")) {

                        twitter= jsonObject1.getString("twitter_id");
                    }else{
                        twitter="N.A.";
                    }
                    if(jsonObject1.has("facebook_id")){

                        facebook= jsonObject1.getString("facebook_id");
                    }else{
                        facebook="N.A.";
                    }


                    if(partyname.equals("D")){
                        dparty="Democrat";
                    }else if(partyname.equals("R")){
                        dparty="Republican";
                    }

                    if(partyname.equals("D")){
                        partylogo=R.drawable.d;
                    }else if(partyname.equals("R")){
                        partylogo=R.drawable.r;
                    }

                    Legislator_list list=new Legislator_list(image,lname,fname,partyname,statename,district,dparty,title,email,chamber,phone,startterm,endterm,office,state,fax,birthday,website,twitter,facebook);
                    Collections.sort(showlist, new Comparator<Legislator_list>() {
                        @Override
                        public int compare(Legislator_list legislator_list, Legislator_list t1) {
                            return legislator_list.getLegChamber().compareTo(t1.getLegChamber());
                        }
                    });
                    showlist.add(list);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

            //set base adapter
            adapter=new LegislatorAdapter(getActivity(),R.layout.legislator_item,showlist);
            showdata.setAdapter(adapter);

            LinearLayout indexLayout = (LinearLayout) v.findViewById(R.id.side_index);

            getIndexList(getList());
            displayIndex();
        }
    }

    private void getIndexList(ArrayList<Legislator_list> mlist) {
        mapIndex = new LinkedHashMap<String, Integer>();
        for (int i = 0; i < mlist.size(); i++) {
            String fruit = mlist.get(i).getLegLName();
            String index = fruit.substring(0, 1);

            if (mapIndex.get(index) == null)
                mapIndex.put(index, i);
        }
    }

    private void displayIndex() {

        TextView textView;
        List<String> indexList = new ArrayList<String>(mapIndex.keySet());
        for (String index : indexList) {
            textView = (TextView) getActivity().getLayoutInflater().inflate(
                    R.layout.side_index_item, null);
            textView.setText(index);
            textView.setTextSize(11);
            textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    TextView selectedIndex = (TextView) view;
                    showdata.setSelection(mapIndex.get(selectedIndex.getText()));
                }
            });
            indexLayout.addView(textView);
        }
    }

    public ArrayList<Legislator_list> getList(){


        Collections.sort(showlist, new Comparator<Legislator_list>() {

            @Override
            public int compare(Legislator_list legislator_list, Legislator_list t1) {
                return legislator_list.getLegLName().compareTo(t1.getLegLName());
            }
        });

        return   showlist;
    }
}