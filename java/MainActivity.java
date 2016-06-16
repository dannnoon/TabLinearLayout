package com.elementarycode.testapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private TabLinearLayout tabLinearLayout;

    private TextView textView;

    private List<String> someTitles;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initList();

        initComponents();
    }

    private void initList() {
        someTitles = new ArrayList<>();

        someTitles.add("Pierwszy");
        someTitles.add("Drugi");
        someTitles.add("Trzeci");
        someTitles.add("Pierwszy");
        someTitles.add("Drugi");
        someTitles.add("Trzeci");
        someTitles.add("Pierwszy");
        someTitles.add("Drugi");
        someTitles.add("Trzeci");
        someTitles.add("Pierwszy");
        someTitles.add("Drugi");
        someTitles.add("Trzeci");
    }

    private void initComponents() {
        tabLinearLayout = (TabLinearLayout) findViewById(R.id.tab_linear_layout);
        tabLinearLayout.createTabLayout(someTitles, onTabClickListener());

        textView = (TextView) findViewById(R.id.text_view_position);
        textView.setText(String.format(Locale.getDefault(), "%d", tabLinearLayout.getSelectedTabPosition()));
    }

    private TabLinearLayout.OnTabClickListener onTabClickListener() {
        return new TabLinearLayout.OnTabClickListener() {
            @Override
            public void onTabClickListener() {
                textView.setText(String.format(Locale.getDefault(), "%d", tabLinearLayout.getSelectedTabPosition()));
            }
        };
    }
}
