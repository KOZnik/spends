package pl.koznik.spends.control;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.StateListDrawable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SimpleAdapter;

import java.util.List;
import java.util.Map;

public class CategoryGridViewAdapter extends SimpleAdapter {

    public CategoryGridViewAdapter(Context context, List<? extends Map<String, ?>> data, int resource, String[] from, int[] to) {
        super(context, data, resource, from, to);
    }

    /*@Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = super.getView(position, convertView, parent);

        StateListDrawable drawable = (StateListDrawable) view.getBackground();
        drawable.addState(new int[]{android.R.attr.state_pressed},
                new ColorDrawable(Color.parseColor("#ffffff")));

        return view;
    }*/
}
