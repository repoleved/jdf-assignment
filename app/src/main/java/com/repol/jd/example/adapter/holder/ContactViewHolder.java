package com.repol.jd.example.adapter.holder;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.repol.jd.example.databinding.ListitemContactBinding;

/**
 * Created on 2/5/2562.
 */
public class ContactViewHolder extends RecyclerView.ViewHolder {
    public final ListitemContactBinding listitemContactBinding;

    public ContactViewHolder(ListitemContactBinding binding) {
        super(binding.getRoot());
        listitemContactBinding = binding;
    }
}
