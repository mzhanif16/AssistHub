package MyAssistHub.App.helpers;

import java.text.NumberFormat;
import java.util.Locale;


public class Utils {

    public static String moneyFormat(long priceService ){
        Locale localeIndonesia = new Locale("id", "ID");
        NumberFormat n = NumberFormat.getCurrencyInstance(localeIndonesia);
        final String price = n.format(priceService);
        return price;
    }
}
