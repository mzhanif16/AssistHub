package MyAssistHub.App.database;

public class AppDB {

    public AppDB(){}

    public static final class BookingTable{
        public static final String TABLE_NAME = "tbl_booking";
        public static final String COLUMN_ID = "_id";
        public static final String COLUMN_SERVICE= "_service";
        public static final String COLUMN_NOTE= "_note";
        public static final String COLUMN_PRICE_SERVICE = "_priceService";
        public static final String COLUMN_USERNAME= "_username";
        public static final String COLUMN_TOTAL_UNIT = "_totalUnit";
        public static final String COLUMN_STATUS= "_status";
        public static final String COLUMN_HOUR= "_hour";
        public static final String COLUMN_DATE= "_date";
        public static final String COLUMN_CATEGORY= "_category";

        //CREATE TABLE BOOKING
        public static final String SQL_CREATE_TABLE ="CREATE TABLE IF NOT EXISTS "+ TABLE_NAME +
                "(" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                COLUMN_SERVICE + " TEXT," +
                COLUMN_NOTE + " TEXT," +
                COLUMN_PRICE_SERVICE+ " INTEGER," +
                COLUMN_USERNAME+ " TEXT," +
                COLUMN_TOTAL_UNIT+ " INTEGER," +
                COLUMN_STATUS+ " INTEGER DEFAULT 0," +
                COLUMN_HOUR+ " TEXT," +
                COLUMN_DATE+ " TEXT," +
                COLUMN_CATEGORY+ " TEXT )";
    }
}
