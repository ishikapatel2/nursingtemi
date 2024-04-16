package com.example.nursingtemi;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.robotemi.sdk.Robot;
import com.robotemi.sdk.UserInfo;

import java.util.ArrayList;
import java.util.List;

public class ContactsAdapter extends RecyclerView.Adapter<ContactsAdapter.ViewHolder> {
    Context context;
    ArrayList<UserInfo> contacts;
    OnItemClickListener clickListener;

    public interface OnItemClickListener {
        void onItemClick(UserInfo contact);
    }

    ContactsAdapter(Context context, ArrayList<UserInfo> contacts, OnItemClickListener clickListener) {
        this.context = context;
        this.contacts = contacts;
        this.clickListener = clickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.contact_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ContactsAdapter.ViewHolder holder, int position) {
        UserInfo contact = contacts.get(position);
        holder.name.setText(contacts.get(position).getName());
        Drawable drawable = ContextCompat.getDrawable(context, R.drawable.person);
        holder.photo.setImageDrawable(drawable);
        holder.itemView.setOnClickListener(v -> clickListener.onItemClick(contact));

    }

    @Override
    public int getItemCount() {
        return contacts.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView name;
        ImageView photo;
        public ViewHolder (View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            photo = itemView.findViewById(R.id.photo);
        }

    }
}
