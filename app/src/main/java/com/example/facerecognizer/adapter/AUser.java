package com.example.facerecognizer.adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.facerecognizer.PengenalanActivity;
import com.example.facerecognizer.R;
import com.example.facerecognizer.model.User;

import java.util.List;

public class AUser extends RecyclerView.Adapter<AUser.ViewHolder> {

    private Context context;
    private List<User> users;

    public AUser(Context context, List<User> users) {
        this.context = context;
        this.users = users;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_user, viewGroup, false);
        return new ViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int i) {

        holder.tv_nama.setText(""+users.get(i).getNama());
        holder.tv_nim.setText(""+users.get(i).getNim());
        holder.cv_user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, PengenalanActivity.class);
                intent.putExtra("nim", users.get(i).getNim());
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private TextView tv_nim, tv_nama;
        private CardView cv_user;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tv_nim = itemView.findViewById(R.id.tv_nim);
            tv_nama = itemView.findViewById(R.id.tv_nama);
            cv_user = itemView.findViewById(R.id.cv_user);
        }
    }
}
