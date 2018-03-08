package com.congress.congressapp.dto;

import java.io.Serializable;

public class Bill_list implements Serializable {

    String BillId, BillTitle, BillIntroducedOn, BillType, BillSponTitle, BillSponLName, BillSponFname,BillChamber, BillStatus, BillCongressUrl,BillVersionStatus,BillUrl;

    public Bill_list() {
    }

    public Bill_list(String billId, String billTitle, String billIntroducedOn, String billType, String billSponTitle, String billSponLName, String billSponFname, String billChamber,String billStatus, String billCongressUrl, String billVersionStatus, String billUrl) {
        BillId = billId;
        BillTitle = billTitle;
        BillIntroducedOn = billIntroducedOn;
        BillType = billType;
        BillSponTitle = billSponTitle;
        BillSponLName = billSponLName;
        BillSponFname = billSponFname;
        BillChamber = billChamber;
        BillStatus = billStatus;
        BillCongressUrl = billCongressUrl;
        BillVersionStatus = billVersionStatus;
        BillUrl = billUrl;
    }

    public String getBillId() {
        return BillId;
    }

    public void setBillId(String billId) {
        BillId = billId;
    }

    public String getBillTitle() {
        return BillTitle;
    }

    public void setBillTitle(String billTitle) {
        BillTitle = billTitle;
    }

    public String getBillIntroducedOn() {
        return BillIntroducedOn;
    }

    public void setBillIntroducedOn(String billIntroducedOn) {
        BillIntroducedOn = billIntroducedOn;
    }

    public String getBillType() {
        return BillType;
    }

    public void setBillType(String billType) {
        BillType = billType;
    }

    public String getBillSponTitle() {
        return BillSponTitle;
    }

    public void setBillSponTitle(String billSponTitle) {
        BillSponTitle = billSponTitle;
    }

    public String getBillSponLName() {
        return BillSponLName;
    }

    public void setBillSponLName(String billSponLName) {
        BillSponLName = billSponLName;
    }

    public String getBillSponFname() {
        return BillSponFname;
    }

    public void setBillSponFname(String billSponFname) {
        BillSponFname = billSponFname;
    }

    public String getBillChamber() {
        return BillChamber;
    }

    public void setBillChamber(String billChamber) {
        BillChamber = billChamber;
    }

    public String getBillStatus() {
        return BillStatus;
    }

    public void setBillStatus(String billStatus) {
        BillStatus = billStatus;
    }

    public String getBillCongressUrl() {
        return BillCongressUrl;
    }

    public void setBillCongressUrl(String billCongressUrl) {
        BillCongressUrl = billCongressUrl;
    }

    public String getBillVersionStatus() {
        return BillVersionStatus;
    }

    public void setBillVersionStatus(String billVersionStatus) {
        BillVersionStatus = billVersionStatus;
    }

    public String getBillUrl() {
        return BillUrl;
    }

    public void setBillUrl(String billUrl) {
        BillUrl = billUrl;
    }
}