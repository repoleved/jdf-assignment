package com.repol.jd.example.ViewModel;

import android.app.Application;
import android.content.Context;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;

import com.repol.jd.example.db.ContactDatabase;
import com.repol.jd.example.db.dao.ContactDAO;
import com.repol.jd.example.models.ContactModel;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created on 2/5/2562.
 */
public class ContactListViewModel extends AndroidViewModel {
    private ContactDAO contactDAO;
    public final LiveData<PagedList<ContactModel>> contactList;

    public ContactListViewModel(Application application) {
        super(application);
        this.contactDAO = ContactDatabase.getInstance(getApplication()).getContactDAO();
        contactList = new LivePagedListBuilder<>(contactDAO.getContacts(), 50).build();
    }

    public void delete(ContactModel contactModel) {
        Observable.just(contactDAO)
                .subscribeOn(Schedulers.io())
                .subscribe(contactDAO1 -> contactDAO1.delete(contactModel));
    }
}
