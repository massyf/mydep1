package com.teta_tm.newbook;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class Bookmarks extends AppCompatActivity {
    ListView bookmark_list;
    DbHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bookmarks);
        dbHelper = new DbHelper(this);
        bookmark_list = findViewById(R.id.bookmark_list);
    }

    public class CustomAdapter extends ArrayAdapter<String> {
        private Context mContext;
        private ArrayList<String> sections;

        public CustomAdapter(@NonNull Context mContext, int layout, @NonNull ArrayList<String> sections) {
            super(mContext, layout, sections);
            this.mContext = mContext;
            this.sections = sections;
        }

        public View getView(int position, View convertView, ViewGroup parent) {
            LayoutInflater in = getLayoutInflater();
            int layout;
            TextView sections=findViewById(R.id.msg_section);
            ImageView delete=findViewById();
            layout = R.layout.activity_bookmarks;
            View row = in.inflate(layout, parent, false);
            TextView title = row.findViewById(R.id.title);
            title.setText(sections.get(position));
        }
    }
}