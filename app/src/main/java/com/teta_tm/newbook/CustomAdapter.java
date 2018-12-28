package com.teta_tm.newbook;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class CustomAdapter extends ArrayAdapter<String> {
    private Context mContext;
    private ArrayList<String> chapters, sections;
    private ArrayList<Integer> stars;


    public CustomAdapter(Context mContext, int layout, ArrayList<String> chapters) {
        super(mContext, layout, chapters);
        this.mContext = mContext;
        this.chapters = chapters;
    }

    public CustomAdapter(Context mContext, int layout, ArrayList<String> chapters, ArrayList<String> sections) {
        super(mContext, layout, chapters);
        this.mContext = mContext;
        this.chapters = chapters;
        this.sections = sections;
    }

    @SuppressLint("ViewHolder")
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater in = LayoutInflater.from(mContext);
        int layout;
        if (sections == null) {
            layout = R.layout.chapters_row;
            View row = in.inflate(layout, parent, false);
            TextView name = row.findViewById(R.id.chapter_title);
            name.setText(chapters.get(position));
        } else {
            layout = R.layout.result_row;
            View row = in.inflate(layout, parent, false);
            TextView chapter = row.findViewById(R.id.tv_result_chapter);
            TextView sections = row.findViewById(R.id.tv_result_section);
            chapter.setText(chapters.get(position));
            //  sections.setText(sections.get(position));


        }
    }
}


//        name.setTypeface(Typeface.createFromAsset(getAssets(), "font/koodak.ttf"));
        // return (row

