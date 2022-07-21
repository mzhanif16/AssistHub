package MyAssistHub.App.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

import MyAssistHub.App.model.Booking;


public class BookingTable {
    private final WeakReference<Context> mContext;
    private DatabaseHelper mDbHelper;
    private SQLiteDatabase mDb;

    public BookingTable(Context context){
        mContext = new WeakReference<>(context);
    }
    //open connection sqlite
    private void open() {
        mDbHelper = new DatabaseHelper(mContext.get());
        mDb = mDbHelper.getWritableDatabase();
    }
    //close connection sqlite
    private void close() {
        mDbHelper.close();
    }

    public boolean addBooking(String service,String note,String username,long price, int totalUnit, String hour, String date, String category){
        try {
            ContentValues values = new ContentValues();
            values.put(AppDB.BookingTable.COLUMN_SERVICE,service);
            values.put(AppDB.BookingTable.COLUMN_NOTE,note);
            values.put(AppDB.BookingTable.COLUMN_USERNAME,username);
            values.put(AppDB.BookingTable.COLUMN_PRICE_SERVICE,price);
            values.put(AppDB.BookingTable.COLUMN_TOTAL_UNIT,totalUnit);
            values.put(AppDB.BookingTable.COLUMN_HOUR,hour);
            values.put(AppDB.BookingTable.COLUMN_DATE,date);
            values.put(AppDB.BookingTable.COLUMN_CATEGORY,category);
            open();
            mDb.beginTransaction();
            mDb.insert(AppDB.BookingTable.TABLE_NAME, null, values);
            mDb.setTransactionSuccessful();
            mDb.endTransaction();
            close();
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    public void deleteAllBooking(){
        try {
            open();
            mDb.beginTransaction();
            mDb.delete(AppDB.BookingTable.TABLE_NAME, null, null);
            mDb.setTransactionSuccessful();
            mDb.endTransaction();
            close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void updateBooking(long id, int status) {
        ContentValues values = new ContentValues();
        values.put(AppDB.BookingTable.COLUMN_STATUS, status);
        open();
        mDb.beginTransaction();
        mDb.update(AppDB.BookingTable.TABLE_NAME,
                values, AppDB.BookingTable.COLUMN_ID + " = ?", new String[]{String.valueOf(id)});
        mDb.setTransactionSuccessful();
        mDb.endTransaction();
        close();
    }

    public List<Booking> getList(int status){
        List<Booking> bookingList = new ArrayList<>();
        String selectQuery = "SELECT * FROM " + AppDB.BookingTable.TABLE_NAME
                +" WHERE "+AppDB.BookingTable.COLUMN_STATUS +"="+status
                +" ORDER BY " + AppDB.BookingTable.COLUMN_ID + " DESC ";
        open();
        Cursor cursor = mDb.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                Booking item = new Booking();
                item.setId(cursor.getLong(0));
                item.setServiceName(cursor.getString(1));
                item.setNoteBooking(cursor.getString(2));
                item.setPriceService(cursor.getLong(3));
                item.setUserName(cursor.getString(4));
                item.setTotalUnit(cursor.getInt(5));
                item.setStatus(cursor.getInt(6));
                item.setHourBooking(cursor.getString(7));
                item.setDateBooking(cursor.getString(8));
                item.setCategory(cursor.getString(9));
                bookingList.add(item);
            } while (cursor.moveToNext());
        }
        cursor.close();
        close();
        return bookingList;
    }


}
