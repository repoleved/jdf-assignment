package com.repol.jd.example.ui;

import com.repol.jd.example.models.ContactModel;

/**
 * Created on 2/5/2562.
 */
public interface ContactDetailClickCallBack {
    void onSaveClicked(ContactModel model);

    void onCancelClicked();
}
