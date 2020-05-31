package com.example.shoppinglistapp;

import android.content.Context;

import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.text.MessageFormat;
import java.util.List;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ViewHolder> {

    int requestCode;
    private List<RecyclerViewContact> Dataset;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView nameTextView;
        public TextView descriptionTextView;
        public View view;
        public TextView textView;
        public ViewHolder(View frameLayout,TextView name,TextView description) {
            super(frameLayout);
            this.view = frameLayout;
            nameTextView = name;
            descriptionTextView = description;
        }
    }

    public ItemAdapter(List<RecyclerViewContact> Dataset) {
        this.Dataset = Dataset;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, final int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_recyclerview_textview, parent, false);
        TextView nameTextView = view.findViewById(R.id.item_name);
        TextView descriptionTextView = view.findViewById(R.id.item_describtion);
        final ViewHolder vh = new ViewHolder(view, nameTextView, descriptionTextView);
        vh.nameTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (vh.nameTextView.getPaintFlags() != 16) {
                    vh.nameTextView.setPaintFlags(Paint.STRIKE_THRU_TEXT_FLAG);
                    vh.descriptionTextView.setPaintFlags(Paint.STRIKE_THRU_TEXT_FLAG);
                } else {
                    vh.nameTextView.setPaintFlags( vh.nameTextView.getPaintFlags() & (~ Paint.STRIKE_THRU_TEXT_FLAG));
                    vh.descriptionTextView.setPaintFlags( vh.descriptionTextView.getPaintFlags() & (~ Paint.STRIKE_THRU_TEXT_FLAG));
                }
            }
        });
        vh.descriptionTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (vh.nameTextView.getPaintFlags() != 16) {
                    vh.nameTextView.setPaintFlags(Paint.STRIKE_THRU_TEXT_FLAG);
                    vh.descriptionTextView.setPaintFlags(Paint.STRIKE_THRU_TEXT_FLAG);
                } else {
                    vh.nameTextView.setPaintFlags( vh.nameTextView.getPaintFlags() & (~ Paint.STRIKE_THRU_TEXT_FLAG));
                    vh.descriptionTextView.setPaintFlags( vh.descriptionTextView.getPaintFlags() & (~ Paint.STRIKE_THRU_TEXT_FLAG));
                }
            }
        });
        vh.nameTextView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                MainActivity.editItem(v,vh.nameTextView.getText().toString(),vh.descriptionTextView.getText().toString());

                return true;
            }
        });
        vh.descriptionTextView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                MainActivity.editItem(v,vh.nameTextView.getText().toString(),vh.descriptionTextView.getText().toString());
                return true;
            }
        });
        return vh;
    }



    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {

        RecyclerViewContact contact = Dataset.get(position);

        TextView nameTextView = viewHolder.nameTextView;
        TextView descriptionTextView = viewHolder.descriptionTextView;
        nameTextView.setText(MessageFormat.format("{0} x {1}", contact.getAmount(), contact.getName()));
        descriptionTextView.setText(contact.getDescription());

    }

    @Override
    public int getItemCount() {
        return Dataset.size();
    }
}