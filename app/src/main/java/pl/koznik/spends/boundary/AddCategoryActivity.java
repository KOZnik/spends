package pl.koznik.spends.boundary;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;

import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.annotations.res.StringRes;

import java.util.ArrayList;
import java.util.List;

import pl.koznik.spends.R;
import pl.koznik.spends.control.Response;
import pl.koznik.spends.control.SpendsRepository;

@EActivity(R.layout.activity_add_category)
public class AddCategoryActivity extends Activity {

    private List<EditText> positionList;

    @Bean
    SpendsRepository spendsRepository;

    @ViewById(R.id.category_name)
    EditText categoryName;
    @ViewById(R.id.positions)
    LinearLayout positionsLayout;

    @StringRes(R.string.position_name)
    String positionName;
    @StringRes(R.string.category_validation_error)
    String categoryValidationErrorMessage;
    @StringRes(R.string.validation_error)
    String validationErrorMessage;

    final LinearLayout.LayoutParams POSITION_LAYOUT_PARAMS = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.WRAP_CONTENT);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        positionList = new ArrayList<>();
    }

    public void addPosition(View view) {
        EditText position = new EditText(this);
        position.setLayoutParams(POSITION_LAYOUT_PARAMS);
        position.setHint(positionName);
        positionsLayout.addView(position);
        positionList.add(position);
    }

    public void saveCategory(View view) {
        List<String> positions = resolvePositionNamesFrom(positionList);
        if (categoryName.getText().toString() == null || categoryName.getText().toString().isEmpty()) {
            new AlertHandler(this).showAlert(validationErrorMessage, categoryValidationErrorMessage);
        } else {
            Response<String> response = spendsRepository.createCategory(categoryName.getText().toString(), positions);
            if (response.isNotValid()) {
                new AlertHandler(this).showAlert(validationErrorMessage, response.getErrorMessage());
            } else {
                onBackPressed();
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
