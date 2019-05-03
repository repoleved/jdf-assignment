package com.repol.jd.example;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;

import com.repol.jd.example.ViewModel.ContactViewModel;
import com.repol.jd.example.databinding.ActivityContactBinding;
import com.repol.jd.example.models.ContactModel;
import com.repol.jd.example.ui.ContactDetailClickCallBack;

/**
 * Created on 2/5/2562.
 */
public class ContactActivity extends AppCompatActivity implements ContactDetailClickCallBack {
    private ActivityContactBinding binding;
    private ContactModel contactModel = null;
    private ContactViewModel contactViewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        contactViewModel = ViewModelProviders.of(this).get(ContactViewModel.class);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_contact);
        setSupportActionBar(binding.toolbar);

        Intent intent = getIntent();
        if (intent.hasExtra("data"))
            contactModel = intent.getParcelableExtra("data");

        if (contactModel == null)
            contactModel = new ContactModel();

        binding.setModel(contactModel);
        binding.setCallback(this);
    }

    @Override
    public void onSaveClicked(ContactModel model) {
        if (!TextUtils.isEmpty(binding.etName.getText()) && !TextUtils.isEmpty(binding.etTel.getText())) {
            model.setName(binding.etName.getText().toString());
            model.setTel(binding.etTel.getText().toString());
            contactViewModel.save(model);
        }
        finish();
    }

    @Override
    public void onCancelClicked() {
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        binding.setCallback(null);
        binding.setModel(null);
        contactViewModel = null;
    }
}
