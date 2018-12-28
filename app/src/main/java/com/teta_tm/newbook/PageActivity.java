package com.teta_tm.newbook;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class PageActivity extends AppCompatActivity {
    private TextView page_title,page_text,page_number;
    private Button next,prev;
    private String section;
    private ArrayList<PageClass> pages;
    private DbHelper dbHelper;
    private int current_page,total;
    private int next_page,prew_page;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page);
        pages = new ArrayList<>();
        dbHelper = new DbHelper(this);
        init();
        section = getIntent().getStringExtra("section");
        page_title.setText(section);

        refresher();
    }

    public void init(){
        page_number = findViewById(R.id.tv_pagenumber);
        page_text = findViewById(R.id.tv_section_text);
        page_title = findViewById(R.id.tv_section_title);
        next = findViewById(R.id.btn_next);
        prev = findViewById(R.id.btn_prev);
    }

    public void refresher(){
        dbHelper.opendatabase();
        int numOfPages = dbHelper.page_counter("page",section);
        for(int i = 0 ; i < numOfPages ; i++){

            String text = dbHelper.text_getter("text",section,i+1);
            pages.add(new PageClass(dbHelper.text_getter("text",section,(i+1)),i+1));
        }

        page_text.setText(pages.get(0).getPage_text());
        page_number.setText(pages.get(0).getPage_number()+"");
        dbHelper.close();
    }

    private void checkButton(){
        if (current_page==1){
            prev.setEnabled(false);
            next.setEnabled(true);
        }
        else
            prev.setEnabled(true);
        next.setEnabled(true);
    }
    public void goToNextPage(){
        next_page=current_page+1;
        if (current_page<=total){

        }
    }
}
