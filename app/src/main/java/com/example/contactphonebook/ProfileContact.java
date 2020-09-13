package com.example.contactphonebook;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import static com.example.contactphonebook.Key.KEY_CONTACT_ID;

public class ProfileContact extends AppCompatActivity {

    Button btnCancel, btnEdit;
    TextView txtFirstName, txtLastName, txtMobile, txtPhone, txtEmail, txtAddress;
    private int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_contact);

        init();
        setDate();

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ProfileContact.this, MainActivity.class);
                startActivity(intent);
            }
        });

        ContactHelper helper = new ContactHelper(this);

        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(ProfileContact.this, MainActivity.class);
                startActivity(intent);
            }
        });

    }

    private void init() {
        btnCancel = findViewById(R.id.profileContact_btn_Cancle);
        btnEdit = findViewById(R.id.profileContact_btn_Edit);
        txtFirstName = findViewById(R.id.profileContact_tv_firstName);
        txtLastName = findViewById(R.id.profileContact_tv_lastName);
        txtMobile = findViewById(R.id.profileContact_tv_mobile);
        txtPhone = findViewById(R.id.profileContact_tv_Phone);
        txtEmail = findViewById(R.id.profileContact_tv_Email);
        txtAddress = findViewById(R.id.profileContact_tv_Address);

        id = getIntent().getIntExtra(KEY_CONTACT_ID, 0);
    }

    private void setDate()
    {
        if(id != 0)
        {
            Contact contact = new Contact();
            ContactHelper helper = new ContactHelper(this);
            Cursor cursor = helper.selectOne(id);
            cursor.moveToFirst();

            contact.setFirstName(cursor.getString(cursor.getColumnIndex(ContactHelper.col_firstName)));
            contact.setLastName(cursor.getString(cursor.getColumnIndex(ContactHelper.col_lastName)));
            contact.setMobile(cursor.getString(cursor.getColumnIndex(ContactHelper.col_mobile)));
            contact.setPhone(cursor.getString(cursor.getColumnIndex(ContactHelper.col_phone)));
            contact.setEmail(cursor.getString(cursor.getColumnIndex(ContactHelper.col_email)));
            contact.setAddress(cursor.getString(cursor.getColumnIndex(ContactHelper.col_address)));

            txtFirstName.setText(contact.getFirstName());
            txtLastName.setText(contact.getLastName());
            txtMobile.setText(contact.getMobile());
            txtPhone.setText(contact.getPhone());
            txtEmail.setText(contact.getEmail());
            txtAddress.setText(contact.getAddress());
        }
    }
}
