package pl.koznik.spends.boundary;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import com.example.myapplication2.app.R;
import pl.koznik.spends.control.SpendsRepository;

import java.util.List;

public class CategoryActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
        List<String> categoryNames = new SpendsRepository(this).allCategories().getReturnObject();
        GridView categoryGrid = (GridView) findViewById(R.id.category_grid);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, categoryNames);
        categoryGrid.setAdapter(adapter);
        //http://developer.android.com/guide/topics/ui/declaring-layout.html#AdapterViews
        //http://wptrafficanalyzer.in/blog/listing-images-in-gridview-using-simple-adapter-in-android/
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
