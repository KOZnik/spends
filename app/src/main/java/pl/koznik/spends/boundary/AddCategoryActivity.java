package pl.koznik.spends.boundary;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.example.myapplication2.app.R;

import java.util.ArrayList;
import java.util.List;

import pl.koznik.spends.control.Response;
import pl.koznik.spends.control.SpendsRepository;

public class AddCategoryActivity extends Activity {

    private List<EditText> positionList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        positionList = new ArrayList<>();
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
        positionList.add(position);
    }

    public void saveCategory(View view) {
        List<String> positions = resolvePositionNamesFrom(positionList);
        String categoryName = ((EditText) findViewById(R.id.category_name)).getText().toString();
        if (categoryName == null || categoryName.isEmpty()) {
            new AlertHandler(this).showAlert(getResources().getString(R.string.validation_error), getResources().getString(R.string.category_validation_error));
        } else {
            Response<String> response = new SpendsRepository(this).createCategory(categoryName, positions);
            if (response.isNotValid()) {
                new AlertHandler(this).showAlert(getResources().getString(R.string.validation_error), response.getErrorMessage());
            }
        }
    }

    private List<String> resolvePositionNamesFrom(List<EditText> positionList) {
        List<String> positionNames = new ArrayList<>();
        for (EditText positionEditText : positionList) {
            positionNames.add(positionEditText.getText().toString());
        }
        return positionNames;
    }
}
