package pl.koznik.spends.control;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteConstraintException;
import android.database.sqlite.SQLiteDatabase;

import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.res.StringRes;

import java.util.ArrayList;
import java.util.List;

import pl.koznik.spends.R;

@EBean(scope = EBean.Scope.Singleton)
public class SpendsRepository {

    @Bean
    SpendsSQLiteOpenHelper helper;

    @StringRes(R.string.category_name_aready_exist_error)
    String categoryNameAlreadyExistErrorMessage;
    @StringRes(R.string.category_position_already_exist_error)
    String categoryPositionAlreadyExistErrorMessage;

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
            return Response.FAIL(categoryNameAlreadyExistErrorMessage);
        }

        for (String position : positions) {
            values = new ContentValues();
            values.put(SpendsSQLiteOpenHelper.SpendsTableConstants.CATEGORY_POSITION_COLUMN_CATEGORY_ID, categoryName);
            values.put(SpendsSQLiteOpenHelper.SpendsTableConstants.CATEGORY_POSITION_COLUMN_NAME, position);
            try {
                db.insertOrThrow(SpendsSQLiteOpenHelper.SpendsTableConstants.TABLE_CATEGORY_POSITION, null, values);
            } catch (SQLiteConstraintException e) {
                return Response.FAIL(categoryPositionAlreadyExistErrorMessage + ": " + position);
            }
        }
        return Response.OK(categoryName);
    }

    public Response<List<String>> allCategories() {
        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor cursor = db.query(
                SpendsSQLiteOpenHelper.SpendsTableConstants.TABLE_CATEGORY,
                new String[]{SpendsSQLiteOpenHelper.SpendsTableConstants.CATEGORY_COLUMN_NAME}, null, null, null, null,
                SpendsSQLiteOpenHelper.SpendsTableConstants.CATEGORY_COLUMN_CREATE_DATE + " DESC"
        );
        List<String> categoryNames = new ArrayList<>();
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            categoryNames.add(cursor.getString(cursor.getColumnIndexOrThrow(SpendsSQLiteOpenHelper.SpendsTableConstants.CATEGORY_COLUMN_NAME)));
            while (cursor.moveToNext()) {
                categoryNames.add(cursor.getString(cursor.getColumnIndexOrThrow(SpendsSQLiteOpenHelper.SpendsTableConstants.CATEGORY_COLUMN_NAME)));
            }
        }
        return Response.OK(categoryNames);
    }

}
