package com.example.librarymanagementsystem;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class UpdateBorrowActivity extends AppCompatActivity {

    EditText name_input, book_input, borrow_date_input, due_date_input;
    Button update_button, delete_button;

    String id, name, book, borrow_date, due_date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_borrow);

        name_input = findViewById(R.id.name_input2);
        book_input = findViewById(R.id.book_input2);
        borrow_date_input = findViewById(R.id.borrow_date_input2);
        due_date_input = findViewById(R.id.due_date_input2);
        update_button = findViewById(R.id.update_button);
        delete_button = findViewById(R.id.delete_button);

        //call this method
        getAndSetIntentData();

        ActionBar ab = getSupportActionBar();
        if (ab != null) {
            ab.setTitle("Update/Delete A Borrowed book");
        }

        update_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //call method and pass value
                BorrowDatabase myDB = new BorrowDatabase(UpdateBorrowActivity.this);
                //pass the newly input value into database
                name = name_input.getText().toString().trim();
                book = book_input.getText().toString().trim();
                borrow_date = borrow_date_input.getText().toString().trim();
                due_date = due_date_input.getText().toString().trim();
                myDB.updateData(id, name, book, borrow_date, due_date);
            }
        });

        delete_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //call method to initiate yes or no option
                confirmDialog();
            }
        });

    }

    void getAndSetIntentData() {
        if (getIntent().hasExtra("id") && getIntent().hasExtra("name") &&
                getIntent().hasExtra("book") && getIntent().hasExtra("borrow_date") &&
                getIntent().hasExtra("due_date")) {
            //get data from intent
            id = getIntent().getStringExtra("id");
            name = getIntent().getStringExtra("name");
            book = getIntent().getStringExtra("book");
            borrow_date = getIntent().getStringExtra("borrow_date");
            due_date = getIntent().getStringExtra("due_date");


            //set intent data
            name_input.setText(name);
            book_input.setText(book);
            borrow_date_input.setText(borrow_date);
            due_date_input.setText(due_date);

        } else {
            Toast.makeText(this, "No Data", Toast.LENGTH_SHORT).show();
        }

    }

    //pop up alert dialog
    void confirmDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Delete " + name + " ?");
        builder.setMessage("Are you sure you want to delete " + name + " ?");
        //on click yes and no
        //delete if yes
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int i) {
                //call method and pass value from MyDataBaseHelper, just like update_button
                BorrowDatabase myDB = new BorrowDatabase(UpdateBorrowActivity.this);
                myDB.deleteOneRow(id);
                finish();
            }
        });
        //delete if no
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int i) {

            }
        });
        //show alert dialog
        builder.create().show();
    }

}
