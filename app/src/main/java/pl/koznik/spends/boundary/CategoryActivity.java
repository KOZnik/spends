package pl.koznik.spends.boundary;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;

import com.example.myapplication2.app.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import pl.koznik.spends.control.CategoryGridViewAdapter;
import pl.koznik.spends.control.SpendsRepository;

public class CategoryActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
        List<String> categoryNames = new SpendsRepository(this).allCategories().getReturnObject();

        fillCategoriesGrid(categoryNames);
    }

    private void fillCategoriesGrid(List<String> categoryNames) {
        List<Map<String, String>> aList = new ArrayList<>();

        for (String categoryName : categoryNames) {
            Map<String, String> hm = new HashMap<>();
            hm.put("txt", categoryName);
            aList.add(hm);
        }

        CategoryGridViewAdapter adapter = new CategoryGridViewAdapter(getBaseContext(), aList, R.layout.category_gridview_layout, new String[]{"txt"}, new int[]{R.id.categoryButton});
        GridView gridView = (GridView) findViewById(R.id.category_grid);

        AdapterView.OnItemClickListener categoryClickListener = new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView parent, View v, int position, long id) {
                System.out.println(((TextView) v).getText());
            }
        };
        gridView.setOnItemClickListener(categoryClickListener);
        gridView.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_category, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.add_category) {
            startActivity(new Intent(this, AddCategoryActivity.class));
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
