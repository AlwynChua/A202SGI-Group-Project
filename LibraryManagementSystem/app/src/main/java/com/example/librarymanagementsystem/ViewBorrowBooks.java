package com.example.librarymanagementsystem;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class ViewBorrowBooks extends AppCompatActivity {

    RecyclerView recyclerView;
    FloatingActionButton add_button;

    BorrowDatabase myDB;
    ArrayList<String> user_id, user_name, user_book, user_borrow_date, user_due_date;
    BorrowAdapter borrowAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_borrow_books);

        recyclerView = findViewById(R.id.recyclerView);
        add_button = findViewById(R.id.add_button);

        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ViewBorrowBooks.this, BorrowActivity.class);
                startActivity(intent);
            }
        });

        myDB = new BorrowDatabase(ViewBorrowBooks.this);
        user_id = new ArrayList<>();
        user_name = new ArrayList<>();
        user_book = new ArrayList<>();
        user_borrow_date = new ArrayList<>();
        user_due_date = new ArrayList<>();

        storeDataInArrays();

        borrowAdapter = new BorrowAdapter(ViewBorrowBooks.this, this, user_id, user_name, user_book, user_borrow_date, user_due_date);
        recyclerView.setAdapter(borrowAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(ViewBorrowBooks.this));

    }

    //recreates page with new data
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            recreate();
        }
    }

    void storeDataInArrays() {
        Cursor cursor = myDB.readAllData();
        if (cursor.getCount() == 0) {
            Toast.makeText(this, "No Data.", Toast.LENGTH_SHORT).show();
        } else {
            while (cursor.moveToNext()) {
                user_id.add(cursor.getString(0));
                user_name.add(cursor.getString(1));
                user_book.add(cursor.getString(2));
                user_borrow_date.add(cursor.getString(3));
                user_due_date.add(cursor.getString(4));

            }
        }
    }

}