package com.congress.congressapp.committee;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.ConnectivityManager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.congress.congressapp.R;
import com.congress.congressapp.bills.BillDetailActivity;
import com.congress.congressapp.dto.Committee_list;
import com.congress.congressapp.favorites.FavoritesActivity;
import com.congress.congressapp.sqlite.DatabaseHelper;

public class CommitteeDetailActivity extends AppCompatActivity {

    private TextView textViewComId,textViewComName,textViewComChamber,textViewComContact,textViewComParent,textViewComOffice;
    ImageView imageLogo;
    ImageButton like_button;
    DatabaseHelper myDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_committeesdetail);
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
        Committee_list list = (Committee_list) i.getSerializableExtra("list");
        String chamber= (String) i.getSerializableExtra("chamber");


        //set status bar
        ActionBar ab = getSupportActionBar();
        ab.setTitle("Committee Info");
        ab.setHomeButtonEnabled(true);
        ab.setDisplayHomeAsUpEnabled(true);
        ab.show();

        //Findout id's
        textViewComId= (TextView) findViewById(R.id.textViewComId);
        textViewComName= (TextView) findViewById(R.id.textViewComName);
        textViewComChamber= (TextView) findViewById(R.id.textViewComChamber);
        textViewComContact= (TextView) findViewById(R.id.textViewComContact);
        textViewComParent= (TextView) findViewById(R.id.textViewComParent);
        textViewComOffice= (TextView) findViewById(R.id.textViewComOffice);

        like_button= (ImageButton) findViewById(R.id.like_button);
        imageLogo= (ImageView) findViewById(R.id.imageLogo);

        textViewComId.setText(Html.fromHtml(list.getCommitteeId()));
        textViewComName.setText(Html.fromHtml(list.getCommitteeName()));
        textViewComChamber.setText(Html.fromHtml(list.getCommitteeChamber()));
        textViewComParent.setText(Html.fromHtml(list.getCommitteeParent()));
        textViewComContact.setText(Html.fromHtml(list.getCommitteeContact()));
        textViewComOffice.setText(Html.fromHtml(list.getCommitteeOffice()));

        LoadData(list.getCommitteeId());

        if(chamber.equals("house")){
            imageLogo.setImageResource(R.drawable.h);
        }else if(chamber.equals("senate")){
            imageLogo.setImageResource(R.drawable.s);
        }else if(chamber.equals("joint")){
            imageLogo.setImageResource(R.drawable.s);
        }
    }

    public void AddData() {

        like_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                boolean isInserted = myDb.insertComData(textViewComId.getText().toString(),
                        textViewComName.getText().toString(),
                        textViewComChamber.getText().toString(),
                        textViewComParent.getText().toString(),
                        textViewComContact.getText().toString(),
                        textViewComOffice.getText().toString());


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

                Integer del=myDb.deleteComData(textViewComId.getText().toString());

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

        if(myDb.allComView(id)){
            like_button.setBackgroundResource(R.drawable.ic_star_rate_on);
            Delete();

        }else{
            like_button.setBackgroundResource(R.drawable.ic_star_rate_off);
            AddData();

        }
    }
}
