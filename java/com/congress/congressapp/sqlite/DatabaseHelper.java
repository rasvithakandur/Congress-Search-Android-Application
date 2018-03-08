package com.congress.congressapp.sqlite;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.congress.congressapp.dto.Bill_list;
import com.congress.congressapp.dto.Committee_list;
import com.congress.congressapp.dto.Legislator_list;

import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "DB2_Congress";

    //Committee Table and their columns
    private static final String TABLE_COMMITTEE = "Committee";
    public static final String COMMITTEE_ID = "C_ID";
    public static final String COMMITTEE_NAME = "C_NAME";
    public static final String COMMITTEE_CHAMBER = "C_CHAMBER";
    public static final String COMMITTEE_PARENT = "C_PARENT";
    public static final String COMMITTEE_CONTACT = "C_CONTACT";
    public static final String COMMITTEE_OFFICE = "C_OFFICE";

    //Bill Table and their columns
    private static final String TABLE_BILL = "Bill";
    public static final String BILL_ID = "B_ID";
    public static final String BILL_NAME = "B_NAME";
    public static final String BILL_DATE = "B_DATE";
    public static final String BILL_TYPE = "B_TYPE";
    public static final String BILL_SPONTITLE = "B_SPONTITLE";
    public static final String BILL_SPONLNAME = "B_SPONLNAME";
    public static final String BILL_SPONFNAME = "B_SPONFNAME";
    public static final String BILL_CHAMBER = "B_CHAMBER";
    public static final String BILL_STATUS = "B_STATUS";
    public static final String BILL_CONURL = "B_CONURL";
    public static final String BILL_VERSTATUS = "B_VERSTATUS";
    public static final String BILL_URL = "B_URL";

    //Legislator Table and their columns
    private static final String TABLE_LEGISLATOR = "Legislator";
    public static final String LEG_IMAGE = "L_IMAGE";
    public static final String LEG_LNAME = "L_LNAME";
    public static final String LEG_FNAME = "L_FNAME";
    public static final String LEG_PARTY = "L_PARTY";
    public static final String LEG_STATENAME = "L_STATENAME";
    public static final String LEG_DISTRICT = "L_DISTRICT";
    public static final String LEG_DPARTY = "L_DPARTY";
    public static final String LEG_TITLE = "L_TITLE";
    public static final String LEG_EMAIL = "L_EMAIL";
    public static final String LEG_CHAMBER = "L_CHAMBER";
    public static final String LEG_CONTACT = "L_CONTACT";
    public static final String LEG_STERM = "L_STERM";
    public static final String LEG_ETERM = "L_ETERM";
    public static final String LEG_OFFICE = "L_OFFICE";
    public static final String LEG_STATE = "L_STATE";
    public static final String LEG_FAX = "L_FAX";
    public static final String LEG_BDAY = "L_BDAY";
    public static final String LEG_WEB = "L_WEB";
    public static final String LEG_TWITTER = "L_TWITTER";
    public static final String LEG_FACEBOOK = "L_FACEBOOK";


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
        SQLiteDatabase db = this.getWritableDatabase();
    }

    //Create table here
    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("CREATE TABLE " + TABLE_COMMITTEE + "(C_ID VARCHAR(100),C_NAME VARCHAR(300),C_CHAMBER VARCHAR(100),C_PARENT VARCHAR(100),C_CONTACT VARCHAR(100),C_OFFICE VARCHAR(100))");
        db.execSQL("CREATE TABLE " + TABLE_BILL + "(B_ID VARCHAR(100),B_NAME VARCHAR(300),B_DATE VARCHAR(100),B_TYPE VARCHAR(100),B_SPONTITLE VARCHAR(100),B_SPONLNAME VARCHAR(100),B_SPONFNAME VARCHAR(100),B_CHAMBER VARCHAR(100),B_STATUS VARCHAR(100),B_CONURL VARCHAR(100),B_VERSTATUS VARCHAR(100),B_URL VARCHAR(100))");
        db.execSQL("CREATE TABLE " + TABLE_LEGISLATOR + "(L_IMAGE VARCHAR(100),L_LNAME VARCHAR(300),L_FNAME VARCHAR(100),L_PARTY VARCHAR(100),L_STATENAME VARCHAR(100),L_DISTRICT VARCHAR(100),L_DPARTY VARCHAR(100),L_TITLE VARCHAR(100),L_EMAIL VARCHAR(100),L_CHAMBER VARCHAR(100),L_CONTACT VARCHAR(100),L_STERM VARCHAR(100),L_ETERM VARCHAR(100),L_OFFICE VARCHAR(100),L_STATE VARCHAR(100),L_FAX VARCHAR(100),L_BDAY VARCHAR(100),L_WEB VARCHAR(100),L_FACEBOOK VARCHAR(100),L_TWITTER VARCHAR(100))");

    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_COMMITTEE);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_BILL);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_LEGISLATOR);

        onCreate(db);
    }
    //Insert data Committee
    public boolean insertComData(String id, String name, String chamber,String parent,String contact,String office) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues cv = new ContentValues();
        cv.put(COMMITTEE_ID, id);
        cv.put(COMMITTEE_NAME, name);
        cv.put(COMMITTEE_CHAMBER, chamber);
        cv.put(COMMITTEE_PARENT, parent);
        cv.put(COMMITTEE_CONTACT, contact);
        cv.put(COMMITTEE_OFFICE, office);

        long result = db.insert(TABLE_COMMITTEE, null, cv);

        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }

    //Insert Data Bill
    public boolean insertLegData(String image,String lname, String fname,String party,String statename,String district,String dparty,String title,String email,String chamber,String contact,String strem,String eterm,String office,String state,String fax,String bday,String web,String twitter, String facebook) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues cv = new ContentValues();
        cv.put(LEG_IMAGE, image);
        cv.put(LEG_LNAME,lname);
        cv.put(LEG_FNAME,fname);
        cv.put(LEG_PARTY,party);
        cv.put(LEG_STATENAME,statename);
        cv.put(LEG_DISTRICT,district);
        cv.put(LEG_DPARTY,dparty);
        cv.put(LEG_TITLE, title);
        cv.put(LEG_EMAIL,email);
        cv.put(LEG_CHAMBER,chamber);
        cv.put(LEG_CONTACT,contact);
        cv.put(LEG_STERM,strem);
        cv.put(LEG_ETERM,eterm);
        cv.put(LEG_OFFICE,office);
        cv.put(LEG_STATE,state);
        cv.put(LEG_FAX, fax);
        cv.put(LEG_BDAY, bday);
        cv.put(LEG_WEB, web);
        cv.put(LEG_TWITTER, twitter);
        cv.put(LEG_FACEBOOK, facebook);


        long result = db.insert(TABLE_LEGISLATOR, null, cv);

        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }

    //Insert Data Legislator
    public boolean insertBillData(String id, String name, String date,String type,String spontitle,String sponlname,String sponfname,String chamber,String status,String conurl,String verstatus,String billurl) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues cv = new ContentValues();
        cv.put(BILL_ID, id);
        cv.put(BILL_NAME,name);
        cv.put(BILL_DATE,date);
        cv.put(BILL_TYPE,type);
        cv.put(BILL_SPONTITLE,spontitle);
        cv.put(BILL_SPONLNAME,sponlname);
        cv.put(BILL_SPONFNAME,sponfname);
        cv.put(BILL_CHAMBER,chamber);
        cv.put(BILL_STATUS,status);
        cv.put(BILL_CONURL,conurl);
        cv.put(BILL_VERSTATUS,verstatus);
        cv.put(BILL_URL,billurl);

        long result = db.insert(TABLE_BILL, null, cv);

        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }

    //Get Data Committee
    public ArrayList<Committee_list> allCommitteeView() {

        SQLiteDatabase db=this.getReadableDatabase();
        ArrayList<Committee_list> list=new ArrayList<Committee_list>();
        Cursor c=db.rawQuery("select * from "+TABLE_COMMITTEE,null);

        while (c.moveToNext()){
            String c_id=c.getString(0);
            String c_name=c.getString(1);
            String c_chamber=c.getString(2);
            String c_parent=c.getString(4);
            String c_phone=c.getString(3);
            String c_office=c.getString(5);

            Committee_list list1=new Committee_list(c_id,c_name,c_chamber,c_parent,c_phone,c_office);
            list.add(list1);
        }

        return list;
    }

    //Get Data Committee2
    public boolean allComView(String id) {

        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<Committee_list> list = new ArrayList<Committee_list>();
        Cursor c = db.rawQuery("select * from " + TABLE_COMMITTEE + " where C_ID='" + id + "'", null);

        if(c.getCount()>0){
            return true;
        }else{
            return false;
        }

    }

    //Get Data Bill
    public ArrayList<Bill_list> allBillView() {

        SQLiteDatabase db=this.getReadableDatabase();
        ArrayList<Bill_list> list=new ArrayList<Bill_list>();
        Cursor c=db.rawQuery("select * from "+TABLE_BILL,null);

        while (c.moveToNext()){
            String id=c.getString(0);
            String title=c.getString(1);
            String date=c.getString(2);
            String type=c.getString(3);
            String spon_title=c.getString(5);
            String spon_lname=c.getString(6);
            String spon_fname=c.getString(7);
            String chamber=c.getString(4);
            String status=c.getString(8);
            String conurl=c.getString(9);
            String ver_status=c.getString(10);
            String url=c.getString(11);

            Bill_list list1=new Bill_list(id,title,date,type,chamber,spon_title,spon_lname,spon_fname,status,conurl,ver_status,url);
            list.add(list1);
        }

        return list;
    }

    //Get Data Bill2
    public boolean allBillView(String id) {

        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<Bill_list> list = new ArrayList<Bill_list>();
        Cursor c = db.rawQuery("select * from " + TABLE_BILL + " where B_ID='" + id + "'", null);

        if(c.getCount()>0){
            return true;
        }else{
            return false;
        }
    }


    //Get Data Legislator
    public ArrayList<Legislator_list> allLegView() {

        SQLiteDatabase db=this.getReadableDatabase();
        ArrayList<Legislator_list> list=new ArrayList<Legislator_list>();
        Cursor c1=db.rawQuery("select * from "+TABLE_LEGISLATOR,null);

        while (c1.moveToNext()){
            String image=c1.getString(0);
            String lname=c1.getString(1);
            String fname=c1.getString(2);
            String partyname=c1.getString(3);
            String statename=c1.getString(4);
            String district=c1.getString(5);
            String dparty=c1.getString(6);
            String title=c1.getString(7);
            String email=c1.getString(8);
            String chamber=c1.getString(9);
            String phone=c1.getString(10);
            String sterm=c1.getString(11);
            String eterm=c1.getString(12);
            String office=c1.getString(13);
            String state=c1.getString(14);
            String fax=c1.getString(15);
            String birthday=c1.getString(16);
            String website=c1.getString(17);
            String twitter=c1.getString(18);
            String facebook=c1.getString(19);


           Legislator_list list1=new Legislator_list(image,lname,fname,partyname,statename,district,dparty,title,email,chamber,phone,sterm,eterm,office,state,fax,birthday,website,twitter,facebook);
            list.add(list1);
        }

        return list;
    }

    //Get Data Leg2
    public boolean allLegView(String id) {

        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<Legislator_list> list = new ArrayList<Legislator_list>();
        Cursor c = db.rawQuery("select * from " + TABLE_LEGISLATOR + " where L_IMAGE='" + id + "'", null);

        if(c.getCount()>0){
            return true;
        }else{
            return false;
        }
    }

    //Delete Committee
    public Integer deleteComData(String id){
        SQLiteDatabase db=this.getWritableDatabase();

       return db.delete(TABLE_COMMITTEE, "C_ID='"+id+"'", null);
    }

    //Delete Bill
    public Integer deleteBillData(String id){
        SQLiteDatabase db=this.getWritableDatabase();

        return db.delete(TABLE_BILL, "B_ID='"+id+"'", null);
    }

    //Delete Legislator
    public Integer deleteLegData(String image){
        SQLiteDatabase db=this.getWritableDatabase();

        return db.delete(TABLE_LEGISLATOR, "L_IMAGE='"+image+"'", null);
    }
}
