package com.repol.jd.example;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;

import com.repol.jd.example.ViewModel.ContactListViewModel;
import com.repol.jd.example.adapter.ContactAdapter;
import com.repol.jd.example.databinding.ActivityMainBinding;
import com.repol.jd.example.models.ContactModel;
import com.repol.jd.example.ui.ContactClickCallBack;

/**
 * Created on 1/5/2562.
 */
public class MainActivity extends AppCompatActivity implements ContactClickCallBack {
    private ActivityMainBinding binding;
    private ContactListViewModel contactListViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        setSupportActionBar(binding.toolbar);

        contactListViewModel = ViewModelProviders.of(this).get(ContactListViewModel.class);
        ContactAdapter adapter = new ContactAdapter(this);
        contactListViewModel.contactList.observe(this, adapter::submitList);
        binding.recyclerView.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_add:
                Intent intent = new Intent(this, ContactActivity.class);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onClick(ContactModel contact) {
        Intent intent = new Intent(this, ContactActivity.class);
        intent.putExtra("data", contact);
        startActivity(intent);
    }

    @Override
    public void onDelete(ContactModel contact) {
        contactListViewModel.delete(contact);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        contactListViewModel.contactList.removeObservers(this);
        contactListViewModel = null;
    }
}
