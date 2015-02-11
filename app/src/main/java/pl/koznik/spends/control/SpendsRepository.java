package pl.koznik.spends.control;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteConstraintException;
import android.database.sqlite.SQLiteDatabase;

import com.example.myapplication2.app.R;

import java.util.List;

public class SpendsRepository {

    private SpendsSQLiteOpenHelper helper;
    private Context context;

    public SpendsRepository(Context context) {
        this.context = context;
        helper = new SpendsSQLiteOpenHelper(context);
    }

    public Response<String> createCategory(String categoryName, List<String> positions) {
        if (categoryName == null || categoryName.isEmpty()) {
            throw new IllegalArgumentException("Category name cannot be empty!");
        }
        SQLiteDatabase db = helper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(SpendsSQLiteOpenHelper.SpendsTableConstants.CATEGORY_COLUMN_NAME, categoryName);
        try {
            db.insertOrThrow(SpendsSQLiteOpenHelper.SpendsTableConstants.TABLE_CATEGORY, null, values);
        } catch (Exception e) {
            return Response.FAIL(context.getResources().getString(R.string.category_name_aready_exist_error));
        }

        for (String position : positions) {
            values = new ContentValues();
            values.put(SpendsSQLiteOpenHelper.SpendsTableConstants.CATEGORY_POSITION_COLUMN_CATEGORY_ID, categoryName);
            values.put(SpendsSQLiteOpenHelper.SpendsTableConstants.CATEGORY_POSITION_COLUMN_NAME, position);
            try {
                db.insertOrThrow(SpendsSQLiteOpenHelper.SpendsTableConstants.TABLE_CATEGORY_POSITION, null, values);
            } catch (SQLiteConstraintException e) {
                return Response.FAIL(context.getResources().getString(R.string.category_position_already_exist_error) + ": " + position);
            }
        }
        return Response.OK(categoryName);
    }

}
