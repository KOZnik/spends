package pl.koznik.spends.boundary;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.OptionsItem;
import org.androidannotations.annotations.OptionsMenu;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import pl.koznik.spends.R;
import pl.koznik.spends.control.SpendsRepository;

@EActivity(R.layout.activity_category)
@OptionsMenu(R.menu.menu_category)
public class CategoryActivity extends ActionBarActivity {

    @ViewById(R.id.category_grid)
    GridView gridView;

    @Bean
    SpendsRepository spendsRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @AfterViews
    void initBookmarkList() {
        fillCategoriesGrid(spendsRepository.allCategories().getReturnObject());
    }

    @Override
    public void onResume() {
        super.onResume();
        fillCategoriesGrid(spendsRepository.allCategories().getReturnObject());
    }

    @OptionsItem(R.id.add_category)
    void addCategoryClicked() {
        startActivity(new Intent(this, AddCategoryActivity_.class));
    }

    //TODO use adapter from AA
    private void fillCategoriesGrid(List<String> categoryNames) {
        List<Map<String, String>> aList = new ArrayList<>();

        for (String categoryName : categoryNames) {
            Map<String, String> hm = new HashMap<>();
            hm.put("txt", categoryName);
            aList.add(hm);
        }

        SimpleAdapter adapter = new SimpleAdapter(getBaseContext(), aList, R.layout.category_gridview_layout, new String[]{"txt"}, new int[]{R.id.categoryButton});

        AdapterView.OnItemClickListener categoryClickListener = new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView parent, View v, int position, long id) {
                System.out.println(((TextView) v).getText());
            }
        };
        gridView.setOnItemClickListener(categoryClickListener);
        gridView.setAdapter(adapter);
    }

}
