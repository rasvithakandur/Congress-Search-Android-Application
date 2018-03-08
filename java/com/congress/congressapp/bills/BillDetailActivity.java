package com.congress.congressapp.bills;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import com.congress.congressapp.R;
import com.congress.congressapp.dto.Bill_list;
import com.congress.congressapp.favorites.FavoritesActivity;
import com.congress.congressapp.sqlite.DatabaseHelper;

public class BillDetailActivity extends AppCompatActivity {

    ImageButton like_button;
    private TextView textViewBillID,textViewBillTitle,textViewBillType,textViewBillIntroducedOn,textViewBillStatus,textViewBillChamber,textViewBillSponTitle,textViewBillSponLName,textViewBillSponFName,textViewBillCongressUrl,textViewBillVersionStatus,textViewBillUrl;
    DatabaseHelper myDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_billdetail);
        myDb=new DatabaseHelper(this);

        ConnectivityManager cm = (ConnectivityManager) getSystemService(BillDetailActivity.CONNECTIVITY_SERVICE);
        android.net.NetworkInfo wifi = cm
                .getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        android.net.NetworkInfo datac = cm
                .getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
        if ((wifi != null & datac != null)
                && (wifi.isConnected() | datac.isConnected())) {
        } else {
            AlertDialog.Builder adb = new AlertDialog.Builder(this);
            adb.setTitle("Check Your Internet Connection!!");
            adb.setCancelable(true);
            adb.setNegativeButton("Ok", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    finish();
                }
            });
            adb.show();
        }

        //getIntent data from previous activity
        Intent i = getIntent();
        Bill_list list = (Bill_list) i.getSerializableExtra("list");
        String status= (String) i.getSerializableExtra("Status");

        //set status bar
        ActionBar ab = getSupportActionBar();
        ab.setTitle("Bill Info");
        ab.setHomeButtonEnabled(true);
        ab.setDisplayHomeAsUpEnabled(true);
        ab.show();

        //Findout id's
        textViewBillID= (TextView) findViewById(R.id.textViewBillID);
        textViewBillTitle= (TextView) findViewById(R.id.textViewBillTitle);
        textViewBillType= (TextView) findViewById(R.id.textViewBillType);
        textViewBillSponTitle= (TextView) findViewById(R.id.textViewBillSponTitle);
        textViewBillSponLName= (TextView) findViewById(R.id.textViewBillSponLName);
        textViewBillSponFName= (TextView) findViewById(R.id.textViewBillSponFName);
        textViewBillChamber= (TextView) findViewById(R.id.textViewBillChamber);
        textViewBillStatus= (TextView) findViewById(R.id.textViewBillStatus);
        textViewBillIntroducedOn= (TextView) findViewById(R.id.textViewBillIntroducedOn);
        textViewBillCongressUrl= (TextView) findViewById(R.id.textViewBillCongressUrl);
        textViewBillVersionStatus= (TextView) findViewById(R.id.textViewBillVersionStatus);
        textViewBillUrl= (TextView) findViewById(R.id.textViewBillUrl);
        like_button= (ImageButton) findViewById(R.id.like_button);

        textViewBillID.setText(Html.fromHtml(list.getBillId()));
        textViewBillTitle.setText(Html.fromHtml(list.getBillTitle()));
        textViewBillType.setText(Html.fromHtml(list.getBillType()));
        textViewBillSponTitle.setText(Html.fromHtml(list.getBillSponTitle()));
        textViewBillSponLName.setText(Html.fromHtml(list.getBillSponLName()));
        textViewBillSponFName.setText(Html.fromHtml(list.getBillSponFname()));
        textViewBillChamber.setText(Html.fromHtml(list.getBillChamber()));
        textViewBillIntroducedOn.setText(Html.fromHtml(list.getBillIntroducedOn()));
        textViewBillCongressUrl.setText(Html.fromHtml(list.getBillCongressUrl()));
        textViewBillVersionStatus.setText(Html.fromHtml(list.getBillVersionStatus()));
        textViewBillUrl.setText(Html.fromHtml(list.getBillUrl()));


        LoadData(list.getBillId());

        if(status.equals("true")){
            textViewBillStatus.setText("Active");
        }else{
            textViewBillStatus.setText("Inactive");
        }
    }

    public void AddData() {

        like_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                boolean isInserted = myDb.insertBillData(textViewBillID.getText().toString(),
                        textViewBillTitle.getText().toString(),
                        textViewBillIntroducedOn.getText().toString(),
                        textViewBillType.getText().toString(),
                        textViewBillSponTitle.getText().toString(),
                        textViewBillSponLName.getText().toString(),
                        textViewBillSponFName.getText().toString(),
                        textViewBillChamber.getText().toString(),
                        textViewBillStatus.getText().toString(),
                        textViewBillCongressUrl.getText().toString(),
                        textViewBillVersionStatus.getText().toString(),
                        textViewBillUrl.getText().toString());


                if (isInserted = true) {

                    like_button.setBackgroundResource(R.drawable.ic_star_rate_on);

                    Toast.makeText(getApplicationContext(), "Added to favorites", Toast.LENGTH_LONG).show();
                } else {

                    Toast.makeText(getApplicationContext(), "Not Added", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
    public void Delete(){

        like_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Integer del=myDb.deleteBillData(textViewBillID.getText().toString());

                like_button.setBackgroundResource(R.drawable.ic_star_rate_off);

                Toast.makeText(getApplicationContext(),"Remove from favorites",Toast.LENGTH_LONG).show();

                Intent in=new Intent(getApplicationContext(),FavoritesActivity.class);
                startActivity(in);
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(android.R.id.home==item.getItemId()){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    public void LoadData(String id){

        if(myDb.allBillView(id)){
            like_button.setBackgroundResource(R.drawable.ic_star_rate_on);
            Delete();

        }else{
            like_button.setBackgroundResource(R.drawable.ic_star_rate_off);
            AddData();

        }
    }
}
