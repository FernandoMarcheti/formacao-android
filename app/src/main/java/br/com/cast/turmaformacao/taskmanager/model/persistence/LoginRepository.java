package br.com.cast.turmaformacao.taskmanager.model.persistence;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.List;

import br.com.cast.turmaformacao.taskmanager.model.entities.User;

public final class LoginRepository {

    public static List<User> getAll() {
        DatabaseHelper databaseHelper = DatabaseHelper.getInstance();
        SQLiteDatabase db = databaseHelper.getReadableDatabase();

        Cursor cursor = db.query(LoginContract.TABLE, LoginContract.COLUMNS, null, null, null, null, LoginContract.ID);
        List<User> values = LoginContract.getUsers(cursor);

        db.close();
        databaseHelper.close();
        return values;
    }

    public static User get() {
        DatabaseHelper databaseHelper = DatabaseHelper.getInstance();
        SQLiteDatabase db = databaseHelper.getReadableDatabase();

        Cursor cursor = db.query(LoginContract.TABLE, LoginContract.COLUMNS, null, null, null, null, LoginContract.ID);
        User value = LoginContract.getUser(cursor);

        db.close();
        databaseHelper.close();
        return value;
    }

    public static void save(User user) {
        DatabaseHelper databaseHelper = DatabaseHelper.getInstance();
        SQLiteDatabase db = databaseHelper.getWritableDatabase();

        ContentValues values = LoginContract.getContentValues(user);
        if (user.getId() == null) {
            db.insert(LoginContract.TABLE, null, values);
        } else {
            String where = LoginContract.ID + " = ? ";
            String[] params = {user.getId().toString()};
            db.update(LoginContract.TABLE, values, where, params);
        }

        db.close();
        databaseHelper.close();
    }

    public static User getById(long id) {
        DatabaseHelper databaseHelper = DatabaseHelper.getInstance();
        SQLiteDatabase db = databaseHelper.getReadableDatabase();

        String where = LoginContract.ID + " = ? ";
        String[] params = {String.valueOf(id)};

        Cursor cursor = db.query(LoginContract.TABLE, LoginContract.COLUMNS, where, params, null, null, null);
        User user = LoginContract.getUser(cursor);

        db.close();
        databaseHelper.close();
        return user;
    }
}
