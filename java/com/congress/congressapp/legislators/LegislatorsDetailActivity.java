package com.congress.congressapp.legislators;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import com.congress.congressapp.R;
import com.congress.congressapp.sqlite.DatabaseHelper;
import com.squareup.picasso.Picasso;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public class LegislatorsDetailActivity extends AppCompatActivity {

    public static final String FACEBOOK = "https://www.facebook.com/";
    public static final String TWITTER = "https://www.twitter.com/";
    DatabaseHelper myDb;
    ProgressBar legProgressBar;
    private ImageView imageViewLeg, imageLogo;
    private final static String nulll = "null";
    ImageButton like_button;

    private TextView textViewLegTitle, textViewLegLName, textViewLegFName, textViewLegEmail, textViewLegChamber, textViewLegContact, textViewLegSTrem, textViewLegETerm, textViewLegOffice, textViewLegState, textViewLegFax, textViewLegBirthday, textViewWeb, textViewLegPartyName, textViewTwitter, textViewFacebook, textViewLegDistrict, textViewLegBiogudiId, textViewLegDParty, textViewLegStatename;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_legislatorsdetail);
        myDb = new DatabaseHelper(this);

        ConnectivityManager cm = (ConnectivityManager) getSystemService(LegislatorsDetailActivity.CONNECTIVITY_SERVICE);
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

        //retrieves the thumbnail data
        Bundle bundle = getIntent().getExtras();
        String image = bundle.getString("image");
        String lname = bundle.getString("lname");
        String fname = bundle.getString("fname");
        String partyname = bundle.getString("partyname");
        String statename = bundle.getString("statename");
        String district = bundle.getString("district");
        String party = bundle.getString("dparty");
        String title = bundle.getString("title");
        String email = bundle.getString("email");
        String chamber = bundle.getString("chamber");
        String contact = bundle.getString("contact");
        String start_term = bundle.getString("start_term");
        String end_term = bundle.getString("end_term");
        String office = bundle.getString("office");
        String state = bundle.getString("state");
        String fax = bundle.getString("fax");
        String birthday = bundle.getString("birthday");
        String website = bundle.getString("website");
        String twitter = TWITTER + bundle.getString("twitter_id");
        String facebook = FACEBOOK + bundle.getString("facebook_id");


        //set status bar
        ActionBar ab = getSupportActionBar();
        ab.setTitle("Legislator Info");
        ab.setHomeButtonEnabled(true);
        ab.setDisplayHomeAsUpEnabled(true);
        ab.show();

        //Findout id's
        textViewLegLName = (TextView) findViewById(R.id.textViewLegLName);
        textViewLegFName = (TextView) findViewById(R.id.textViewLegFName);
        textViewLegTitle = (TextView) findViewById(R.id.textViewLegTitle);
        textViewLegEmail = (TextView) findViewById(R.id.textViewLegEmail);
        textViewLegChamber = (TextView) findViewById(R.id.textViewLegChamber);
        textViewLegContact = (TextView) findViewById(R.id.textViewLegContact);
        textViewLegSTrem = (TextView) findViewById(R.id.textViewLegSTrem);
        textViewLegETerm = (TextView) findViewById(R.id.textViewLegETerm);
        textViewLegOffice = (TextView) findViewById(R.id.textViewLegOffice);
        textViewLegState = (TextView) findViewById(R.id.textViewLegState);
        textViewLegDistrict = (TextView) findViewById(R.id.textViewLegDistrict);
        textViewLegFax = (TextView) findViewById(R.id.textViewLegFax);
        textViewLegBirthday = (TextView) findViewById(R.id.textViewLegBirthday);
        textViewWeb = (TextView) findViewById(R.id.textViewWeb);
        textViewLegPartyName = (TextView) findViewById(R.id.textViewLegPartyName);
        textViewTwitter = (TextView) findViewById(R.id.textViewTwitter);
        textViewFacebook = (TextView) findViewById(R.id.textViewFacebook);
        textViewLegBiogudiId = (TextView) findViewById(R.id.textViewLegBioguidId);
        textViewLegDParty = (TextView) findViewById(R.id.textViewLegParty);
        textViewLegStatename = (TextView) findViewById(R.id.textViewLegStatename);
        like_button = (ImageButton) findViewById(R.id.like_button);
        imageLogo = (ImageView) findViewById(R.id.imageLogo);
        legProgressBar= (ProgressBar) findViewById(R.id.legProgressBar);

        textViewLegBiogudiId.setText(Html.fromHtml(image));
        textViewLegLName.setText(Html.fromHtml(lname));
        textViewLegFName.setText(Html.fromHtml(fname));
        textViewLegPartyName.setText(Html.fromHtml(party));
        textViewLegStatename.setText(Html.fromHtml(statename));
        textViewLegDistrict.setText(Html.fromHtml(district));
        textViewLegDParty.setText(Html.fromHtml(partyname));
        textViewLegTitle.setText(Html.fromHtml(title));
        textViewLegEmail.setText(Html.fromHtml(email));
        textViewLegChamber.setText(Html.fromHtml(chamber));
        textViewLegContact.setText(Html.fromHtml(contact));
        textViewLegSTrem.setText(Html.fromHtml(start_term));
        textViewLegETerm.setText(Html.fromHtml(end_term));
        textViewLegOffice.setText(Html.fromHtml(office));
        textViewLegState.setText(Html.fromHtml(state));
        textViewLegFax.setText(Html.fromHtml(fax));
        textViewLegBirthday.setText(Html.fromHtml(birthday));
        textViewWeb.setText(Html.fromHtml(website));
        textViewTwitter.setText(Html.fromHtml(twitter));
        textViewFacebook.setText(Html.fromHtml(facebook));

        LoadData(image);

        if (party.equals("Republican")) {
            imageLogo.setImageResource(R.drawable.r);
        } else if (party.equals("Democrat")) {
            imageLogo.setImageResource(R.drawable.d);
        }

        textViewLegEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = textViewLegEmail.getText().toString();

                if (email.equals(nulll)) {
                    Toast.makeText(getApplicationContext(), "No email here", Toast.LENGTH_LONG).show();
                } else {
                    Intent i = new Intent(Intent.ACTION_SEND);
                    i.setData(Uri.parse("mailto:"));
                    i.putExtra(android.content.Intent.EXTRA_EMAIL, new String[]{email});
                    i.setType("message/rfc822");
                    startActivity(Intent.createChooser(i, "Send Email"));
                }
            }
        });

        textViewWeb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String web = textViewWeb.getText().toString();

                if (web.equals(nulll)) {
                    Toast.makeText(getApplicationContext(), "No link found here", Toast.LENGTH_LONG).show();
                } else {
                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.setData(Uri.parse(web));
                    startActivity(intent);
                }
            }
        });

        textViewTwitter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String twitter = textViewTwitter.getText().toString();

                if (twitter.equals(nulll)) {
                    Toast.makeText(getApplicationContext(), "No twitter page found here", Toast.LENGTH_LONG).show();
                } else {

                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.setData(Uri.parse(twitter));
                    startActivity(intent);
                }
            }
        });

        textViewFacebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String facebook = textViewFacebook.getText().toString();

                if (facebook.equals(nulll)) {
                    Toast.makeText(getApplicationContext(), "No facebook page found here", Toast.LENGTH_LONG).show();
                } else {

                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.setData(Uri.parse(facebook));
                    startActivity(intent);
                }
            }
        });

        //set image url
        imageViewLeg = (ImageView) findViewById(R.id.imageLegislator);
        Picasso.with(this).load(image).into(imageViewLeg);

        getTimeRemaining();
    }

    public void getTimeRemaining() {

        String sterm = new String(textViewLegSTrem.getText().toString());
        String eterm = textViewLegETerm.getText().toString();

        //get current date
        Calendar c=Calendar.getInstance();
        SimpleDateFormat dates = new SimpleDateFormat("yyyy-MM-dd");
        String now=dates.format(c.getTime());

        //get percentage
        int eq1=getPercentage(now,sterm);
        int eq2=getPercentage(eterm,sterm);

        float total=(Float.parseFloat(String.valueOf(eq1))/Float.parseFloat(String.valueOf(eq2)))*100.0f;
        legProgressBar.setProgress((int) total);

    }

    public Integer getPercentage(String CurrentDate,String FinalDate) {

        try {

            Date date1;
            Date date2;

            SimpleDateFormat dates = new SimpleDateFormat("yyyy-MM-dd");

            //Setting dates
            date1 = dates.parse(CurrentDate);
            date2 = dates.parse(FinalDate);

            //Comparing dates
            long difference = Math.abs(date1.getTime() - date2.getTime());
            long differenceDates = difference / (24 * 60 * 60 * 1000);

            //Convert long to String
            String dayDifference = Long.toString(differenceDates);

            Log.e("HERE", "HERE: " + dayDifference);

            return Integer.parseInt(dayDifference);

        } catch (Exception exception) {
            Log.e("DIDN'T WORK", "exception " + exception);

            return null;
        }

    }

    //Insert Data in database
    public void AddData() {

        like_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                boolean isInserted = myDb.insertLegData(textViewLegBiogudiId.getText().toString(),
                        textViewLegLName.getText().toString(),
                        textViewLegFName.getText().toString(),
                        textViewLegPartyName.getText().toString(),
                        textViewLegStatename.getText().toString(),
                        textViewLegDistrict.getText().toString(),
                        textViewLegDParty.getText().toString(),
                        textViewLegTitle.getText().toString(),
                        textViewLegEmail.getText().toString(),
                        textViewLegChamber.getText().toString(),
                        textViewLegContact.getText().toString(),
                        textViewLegSTrem.getText().toString(),
                        textViewLegETerm.getText().toString(),
                        textViewLegOffice.getText().toString(),
                        textViewLegState.getText().toString(),
                        textViewLegFax.getText().toString(),
                        textViewLegBirthday.getText().toString(),
                        textViewWeb.getText().toString(),
                        textViewTwitter.getText().toString(),
                        textViewFacebook.getText().toString());


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

                Integer del=myDb.deleteLegData(textViewLegBiogudiId.getText().toString());

                like_button.setBackgroundResource(R.drawable.ic_star_rate_off);

                Toast.makeText(getApplicationContext(),"Remove from favorites",Toast.LENGTH_LONG).show();

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

        if(myDb.allLegView(id)){
            like_button.setBackgroundResource(R.drawable.ic_star_rate_on);
            Delete();

        }else{
            like_button.setBackgroundResource(R.drawable.ic_star_rate_off);
            AddData();

        }
    }
}
