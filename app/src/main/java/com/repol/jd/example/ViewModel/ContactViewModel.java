package com.repol.jd.example.ViewModel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;

import com.repol.jd.example.db.ContactDatabase;
import com.repol.jd.example.db.dao.ContactDAO;
import com.repol.jd.example.models.ContactModel;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created on 2/5/2562.
 */
public class ContactViewModel extends AndroidViewModel {
    private ContactDAO contactDAO;

    public ContactViewModel(Application application) {
        super(application);
        this.contactDAO = ContactDatabase.getInstance(getApplication()).getContactDAO();
    }

    public void save(ContactModel contactModel) {
        if (contactModel.getId() == null)
            Observable.just(contactDAO)
                    .subscribeOn(Schedulers.io())
                    .subscribe(contactDAO1 -> contactDAO1.insert(contactModel));
        else
            Observable.just(contactDAO)
                    .subscribeOn(Schedulers.io())
                    .subscribe(contactDAO1 -> contactDAO1.update(contactModel));
    }
}
