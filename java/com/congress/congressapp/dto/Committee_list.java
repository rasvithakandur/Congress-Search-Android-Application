package com.congress.congressapp.dto;

import java.io.Serializable;

public class Committee_list implements Serializable {

    String CommitteeId, CommitteeName, CommitteeChamber,CommitteeContact,CommitteeParent,CommitteeOffice;

    public Committee_list(String committeeId, String committeeName, String committeeChamber, String committeeContact, String committeeParent, String committeeOffice) {
        CommitteeId = committeeId;
        CommitteeName = committeeName;
        CommitteeChamber = committeeChamber;
        CommitteeContact = committeeContact;
        CommitteeParent = committeeParent;
        CommitteeOffice = committeeOffice;
    }

    public String getCommitteeId() {
        return CommitteeId;
    }

    public void setCommitteeId(String committeeId) {
        CommitteeId = committeeId;
    }

    public String getCommitteeName() {
        return CommitteeName;
    }

    public void setCommitteeName(String committeeName) {
        CommitteeName = committeeName;
    }

    public String getCommitteeChamber() {
        return CommitteeChamber;
    }

    public void setCommitteeChamber(String committeeChamber) {
        CommitteeChamber = committeeChamber;
    }

    public String getCommitteeContact() {
        return CommitteeContact;
    }

    public void setCommitteeContact(String committeeContact) {
        CommitteeContact = committeeContact;
    }

    public String getCommitteeParent() {
        return CommitteeParent;
    }

    public void setCommitteeParent(String committeeParent) {
        CommitteeParent = committeeParent;
    }

    public String getCommitteeOffice() {
        return CommitteeOffice;
    }

    public void setCommitteeOffice(String committeeOffice) {
        CommitteeOffice = committeeOffice;
    }
}
