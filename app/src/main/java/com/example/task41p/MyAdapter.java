package com.example.task41p;

import android.content.Context;
import android.content.Intent;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    Context mContext;
    ArrayList<MyDataModel> timersList;



    public MyAdapter(Context mContext, ArrayList<MyDataModel> timersList) {
        this.mContext = mContext;
        this.timersList = timersList;
    }

    @NonNull
    @Override
    public MyAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(mContext).inflate(R.layout.recyclerview_layout, parent, false);
        return new MyViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull MyAdapter.MyViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {

        return timersList.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {

        EditText mTextView, mTextViewTimer, mTextViewTimer2;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            mTextView = itemView.findViewById(R.id.nameEditText);
            mTextViewTimer = itemView.findViewById(R.id.timeEditText);
            mTextViewTimer2 = itemView.findViewById(R.id.timeEditText2);

            itemView.findViewById(R.id.deleteButton).setOnClickListener(view -> {
             int position = getLayoutPosition();
             timersList.remove(position);
             notifyItemRemoved(position);

            });

            //itemView.setOnClickListener(this);

            mTextView.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                    int position = getLayoutPosition();
                    timersList.get(position).setTaskName(charSequence.toString());


                }

                @Override
                public void afterTextChanged(Editable editable) {

                }
            });



            mTextViewTimer.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                    int position = getLayoutPosition();
                    timersList.get(position).setTimer(charSequence.toString());



                }

                @Override
                public void afterTextChanged(Editable editable) {

                }
            });
            mTextViewTimer2.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                    int position = getLayoutPosition();
                    timersList.get(position).setTimer2(charSequence.toString());



                }

                @Override
                public void afterTextChanged(Editable editable) {

                }
            });

        }

    }
}
