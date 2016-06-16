package com.elementarycode.testapplication;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

public class TabLinearLayout extends LinearLayout {
    private LayoutInflater inflater;

    private int defaultBackgroundColor = Color.TRANSPARENT;
    private int selectedBackgroundColor = Color.WHITE;

    private int defaultTextColor = Color.WHITE;
    private int selectedTextColor = Color.BLACK;

    private int selectedTab = 0;

    public TabLinearLayout(Context context) {
        super(context);

        if (!isInEditMode())
            init();
    }

    public TabLinearLayout(Context context, AttributeSet attrs) {
        super(context, attrs);

        if (!isInEditMode())
            init();
    }

    public TabLinearLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        if (!isInEditMode())
            init();
    }

    private void init() {
        inflater = LayoutInflater.from(getContext());
    }

    public void setDefaultBackgroundColor(int color) {
        defaultBackgroundColor = color;
    }

    public void setSelectedBackgroundColor(int color) {
        selectedBackgroundColor = color;
    }

    public void setDefaultTextColor(int defaultTextColor) {
        this.defaultTextColor = defaultTextColor;
    }

    public void setSelectedTextColor(int selectedTextColor) {
        this.selectedTextColor = selectedTextColor;
    }

    public void createTabLayout(List<String> list, OnTabClickListener listener) {
        if (list != null) {
            this.removeAllViews();

            for (int i = 0; i < list.size(); i++) {
                createTab(list.get(i), i, listener);
            }
        }
    }

    public void createTabLayout(List<String> list) {
        createTabLayout(list, null);
    }

    private void createTab(String title, int position, OnTabClickListener listener) {
        View view = inflater.inflate(R.layout.tab_text_with_divider, null);

        view.setOnClickListener(onClickListener(listener));

        view.setTag(position);

        TextView text = (TextView) view.findViewById(R.id.text_view_tab_title);
        text.setText(title);

        if (position != 0) {
            view.setBackgroundColor(defaultBackgroundColor);
            text.setTextColor(defaultTextColor);
        } else {
            view.setBackgroundColor(selectedBackgroundColor);
            text.setTextColor(selectedTextColor);
        }

        this.addView(view, position);
    }

    private View.OnClickListener onClickListener(final OnTabClickListener listener) {
        return new OnClickListener() {
            @Override
            public void onClick(View v) {
                View view = getChildAt(selectedTab);
                TextView text = (TextView) view.findViewById(R.id.text_view_tab_title);

                view.setBackgroundColor(defaultBackgroundColor);
                text.setTextColor(defaultTextColor);

                selectedTab = (int) v.getTag();

                v.setBackgroundColor(selectedBackgroundColor);
                text = (TextView) v.findViewById(R.id.text_view_tab_title);
                text.setTextColor(selectedTextColor);

                if (listener != null)
                    listener.onTabClickListener();
            }
        };
    }

    public int getSelectedTabPosition() {
        return selectedTab;
    }

    public interface OnTabClickListener {
        void onTabClickListener();
    }

}
