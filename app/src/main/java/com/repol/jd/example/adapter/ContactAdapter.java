package com.repol.jd.example.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.DiffUtil;

import com.repol.jd.example.R;
import com.repol.jd.example.adapter.holder.ContactViewHolder;
import com.repol.jd.example.databinding.ListitemContactBinding;
import com.repol.jd.example.models.ContactModel;
import com.repol.jd.example.ui.ContactClickCallBack;

/**
 * Created on 2/5/2562.
 */
public class ContactAdapter extends PagedListAdapter<ContactModel, ContactViewHolder> {
    private ContactClickCallBack callback;

    public ContactAdapter(ContactClickCallBack callback) {
        super(DIFF_CALLBACK);
        this.callback = callback;
    }

    @NonNull
    @Override
    public ContactViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ListitemContactBinding binding = DataBindingUtil
                .inflate(LayoutInflater.from(parent.getContext()), R.layout.listitem_contact, parent, false);
        binding.setCallback(callback);
        return new ContactViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ContactViewHolder holder, int position) {
        ContactModel contact = getItem(position);
        if (contact != null) {
            holder.listitemContactBinding.setModel(contact);
        } else {
            holder.listitemContactBinding.setModel(null);
        }
        holder.listitemContactBinding.executePendingBindings();
    }

    private static DiffUtil.ItemCallback<ContactModel> DIFF_CALLBACK = new DiffUtil.ItemCallback<ContactModel>() {
        @Override
        public boolean areItemsTheSame(ContactModel oldConcert, ContactModel newConcert) {
            return oldConcert.getId() == newConcert.getId();
        }

        @Override
        public boolean areContentsTheSame(ContactModel oldContact, ContactModel newContact) {
            return oldContact.equals(newContact);
        }
    };
}
