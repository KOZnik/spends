package pl.koznik.spends.boundary;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.example.myapplication2.app.R;

public class AddCategoryActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_category);
    }

    public void addPosition(View view) {
        LinearLayout positionsLayout = (LinearLayout) findViewById(R.id.positions);
        EditText position = new EditText(this);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        position.setLayoutParams(params);
        position.setHint(R.string.position_name);
        positionsLayout.addView(position);
    }
}
