package com.example.librarymanagementsystem;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class BorrowAdapter extends RecyclerView.Adapter<BorrowAdapter.MyViewHolder>{

    private Context context;
    Activity activity;
    private ArrayList user_id, user_name, user_book, user_borrow_date, user_due_date;

    int position;

    BorrowAdapter(Activity activity, Context context, ArrayList user_id, ArrayList user_name, ArrayList user_book, ArrayList user_borrow_date, ArrayList user_due_date){
        this.activity = activity;
        this.context = context;
        this.user_id = user_id;
        this.user_book = user_book;
        this.user_name = user_name;
        this.user_borrow_date = user_borrow_date;
        this.user_due_date = user_due_date;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.my_borrow_row, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {

        holder.user_id_txt.setText(String.valueOf(user_id.get(position)));
        holder.user_name_txt.setText(String.valueOf(user_name.get(position)));
        holder.user_book_txt.setText(String.valueOf(user_book.get(position)));
        holder.user_borrow_date_txt.setText(String.valueOf(user_borrow_date.get(position)));
        holder.user_due_date_txt.setText(String.valueOf(user_due_date.get(position)));

        //allows to update ViewBooks data
        holder.mainLayout.setOnClickListener(v -> {
            Intent intent = new Intent(context, UpdateBorrowActivity.class);
            intent.putExtra("id", String.valueOf(user_id.get(position)));
            intent.putExtra("name", String.valueOf(user_name.get(position)));
            intent.putExtra("book", String.valueOf(user_book.get(position)));
            intent.putExtra("borrow_date", String.valueOf(user_borrow_date.get(position)));
            intent.putExtra("due_date", String.valueOf(user_due_date.get(position)));
            activity.startActivityForResult(intent, 1);
        });
    }

    @Override
    public int getItemCount() {
        return user_id.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView user_id_txt, user_name_txt, user_book_txt, user_borrow_date_txt, user_due_date_txt;
        LinearLayout mainLayout;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            user_id_txt = itemView.findViewById(R.id.user_id_txt);
            user_name_txt = itemView.findViewById(R.id.user_name_txt);
            user_book_txt = itemView.findViewById(R.id.user_book_txt);
            user_borrow_date_txt = itemView.findViewById(R.id.user_borrow_date_txt);
            user_due_date_txt = itemView.findViewById(R.id.user_due_date_txt);
            mainLayout = itemView.findViewById(R.id.mainLayout);

        }
    }
}
