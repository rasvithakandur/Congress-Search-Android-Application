package com.congress.congressapp.bills;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;
import com.congress.congressapp.R;
import com.congress.congressapp.adapter.BillAdapter;
import com.congress.congressapp.dto.Bill_list;
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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class ActiveBillFragment extends Fragment {

    ListView showdata;
    ArrayList<Bill_list> showlist = new ArrayList<Bill_list>();
    BillAdapter adpater;
    int count;

    public ActiveBillFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.bill_list, container, false);

        showdata = (ListView) v.findViewById(R.id.listViewBill);

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

        BillTask task=new BillTask();
        task.execute("http://104.198.0.197:8080/bills?per_page=50&history.active=true&order=introduced_on");

        return v;
    }



    class BillTask extends AsyncTask<String, Void, String> {

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
                URL url = new URL(params[0]);
                connection = (HttpURLConnection) url.openConnection();
                connection.connect();

                InputStream stream = connection.getInputStream();
                reader = new BufferedReader(new InputStreamReader(stream));
                StringBuffer buffer = new StringBuffer();

                String line = "";
                while ((line = reader.readLine()) != null) {
                    buffer.append(line);
                }
                return buffer.toString();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (connection != null) {
                    connection.disconnect();
                }
                try {
                    if (reader != null) {
                        reader.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }


        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            dialogadd.dismiss();
            try {

                //Parse the entire JSON String
                JSONObject jObject = new JSONObject(result);

                JSONArray array = jObject.getJSONArray("results");
                for (int i = 0; i < array.length(); i++) {

                    JSONObject billobj = array.getJSONObject(i);

                    String billid = billobj.getString("bill_id");
                    String title = billobj.getString("official_title");
                    String date = billobj.getString("introduced_on");
                    String type = billobj.getString("bill_type");

                    JSONObject jsonObject = billobj.getJSONObject("sponsor");
                    String spon_title = jsonObject.getString("title");
                    String spon_lname = jsonObject.getString("last_name");
                    String spon_fname = jsonObject.getString("first_name");

                    String chamber = billobj.getString("chamber");


                    JSONObject jsonObject1 = billobj.getJSONObject("history");
                    String status = jsonObject1.getString("active");

                    JSONObject jsonObject2 = billobj.getJSONObject("urls");
                    String congress = jsonObject2.getString("congress");

                    JSONObject jsonObject3 = billobj.getJSONObject("last_version");
                    String version_name = jsonObject3.getString("version_name");

                    JSONObject jsonObject4 = jsonObject3.getJSONObject("urls");
                    String pdf = jsonObject4.getString("pdf");

                    SimpleDateFormat spf = new SimpleDateFormat("yyyy-mm-dd");
                    Date newDate = spf.parse(date);
                    spf = new SimpleDateFormat("MMM dd yyyy");
                    String newDateString = spf.format(newDate);

                    //Create a Bill list object that we will populate with JSON Data
                    Bill_list list = new Bill_list(billid, title, newDateString, type, spon_title, spon_lname, spon_fname,chamber, status, congress,version_name,pdf);
                    showlist.add(list);

                }

            } catch (JSONException e) {
                e.printStackTrace();
            } catch (ParseException e) {
                e.printStackTrace();
            }

            adpater=new BillAdapter(getActivity(),R.layout.bill_items,showlist);
            showdata.setAdapter(adpater);
        }
    }
}
