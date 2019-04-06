package com.example.mylistapp;

import android.graphics.Color;
import android.support.annotation.ColorInt;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView list = findViewById(R.id.am_list);

        TextListAdapter adapter = new TextListAdapter();
        list.setAdapter(adapter);

        list.setLayoutManager(new LinearLayoutManager(this));

        adapter.setItems(generateItems());
    }

    private List<ListItemData> generateItems(){

        List<ListItemData> items = new ArrayList<>();


        for(int i = 0; i<20; i++) {
            ListItemData itemData = new ListItemData(
                    "Title " + String.valueOf(i),
                    "Subtitle " + String.valueOf(i),
                    Color.rgb(
                            new Random().nextInt(255),
                            new Random().nextInt(255),
                            new Random().nextInt(255)
                    )
            );

            items.add(itemData);
        }
        return items;
    }

    // To Set background
    private class ListItemData {
        private String title;
        private String subtitle;
        private int backgroundcolor;

        public ListItemData(String title, String subtitle, @ColorInt int backgroundcolor){
            this.title = title;
            this.subtitle = subtitle;
            this.backgroundcolor = backgroundcolor;

        }

        public String getTitle()
        {
            return this.title;
        }

        public String getSubtitle()
        {
            return this.subtitle;
        }

        public int getBackgroundcolor()
        {
            return this.backgroundcolor;
        }
    }


    private static class TextListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

        private List<ListItemData> items = new ArrayList<>();

        private void setItems(List<ListItemData> items) {
            this.items = items;
            notifyDataSetChanged();
        }

        @NonNull
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int type) {

            View layoutView = LayoutInflater.from(viewGroup.getContext())
                    .inflate(R.layout.list_item_text, viewGroup, false);

            return new TextViewHolder(layoutView);
        }

        @Override
        public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
            if (viewHolder instanceof TextViewHolder) {

                TextViewHolder txtViewHolder = ((TextViewHolder) viewHolder);

                ListItemData item = items.get(position);
                txtViewHolder.setText(item.getTitle());
                txtViewHolder.setBackgroundColor(item.getBackgroundcolor());
            }
        }

        @Override
        public int getItemCount() {
            return items.size();
        }

        @Override
        public int getItemViewType(int position) {
            return 0; //super.getItemViewType(position);  //Can also be return 0;
        }

    }

    private static class TextViewHolder extends RecyclerView.ViewHolder {

        private TextView textView;

        public TextViewHolder(@NonNull View itemView) {
            super(itemView);

            textView = itemView.findViewById(R.id.lit_text_view);
        }

        public void setText(String text) {
            textView.setText((text));
        }

        public void setBackgroundColor(int color) {
            itemView.setBackgroundColor(color);
        }
    }
}


