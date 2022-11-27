package com.example.librarymanagementsystem;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class BorrowActivity extends AppCompatActivity {
    EditText name_input, book_input, borrow_date_input, due_date_input;
    Button add_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_borrow);

        name_input = findViewById(R.id.name_input);
        book_input = findViewById(R.id.book_input);
        borrow_date_input = findViewById(R.id.borrow_date_input);
        due_date_input = findViewById(R.id.due_date_input);
        add_button = findViewById(R.id.add_button);

        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BorrowDatabase myDB = new BorrowDatabase(BorrowActivity.this);
                myDB.borrowBook(name_input.getText().toString().trim(),
                        book_input.getText().toString().trim(),
                        borrow_date_input.getText().toString().trim(),
                        due_date_input.getText().toString().trim());

            }
        });
    }
}
