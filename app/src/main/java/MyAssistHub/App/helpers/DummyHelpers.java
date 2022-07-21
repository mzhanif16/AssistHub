package MyAssistHub.App.helpers;

import java.util.ArrayList;

import MyAssistHub.App.R;
import MyAssistHub.App.model.Categories;
import MyAssistHub.App.model.Subcategory;


public class DummyHelpers {

    public static ArrayList getCategories(){
         ArrayList<Categories> list = new ArrayList<>();
         String[] categoriesName={
                "Ac Repair",
                "Cleaning",
                "Ironing",
                "Laundry",
                "Shipping"
        };
         int[] categoriesImage={
                R.drawable.acrepair,
                R.drawable.cleaning,
                R.drawable.ironing3,
                R.drawable.laundry,
                R.drawable.shipping
        };

        for(int position = 0; position < categoriesName.length; position++){
            Categories categories = new Categories();
            categories.setCategories(categoriesName[position]);
            categories.setImage(categoriesImage[position]);
            list.add(categories);
        }
        return list;
    }

    //get dummy subcategorylist
    public static ArrayList getSubCategory(String category) {
        ArrayList<Subcategory> subcategories;
        switch (category) {
            case "Cleaning":
                subcategories = getListCleaning();
                break;
            case "Ac Repair":
                subcategories = getListAc();
                break;
            case "Ironing":
                subcategories = getListIroning();
                break;
            case "Laundry":
                subcategories = getListLaundry();
                break;
            case "Shipping":
                subcategories = getListShipping();
                break;
            default:
                subcategories = new ArrayList<>();
            break;
        }
        return subcategories;
    }

    // Cleaning service list
    private static ArrayList<Subcategory> getListCleaning() {
        String[] titleSub = {
                "Room Cleaning",
                "Toilet Cleaning",
                "Kitchen Cleaning",
                "Full Cleaning ( All Room )"
        };
        long[] priceSub = {
                30000,
                40000,
                35000,
                80000
        };
        int[] photoSub = {
                R.drawable.cleaning1,
                R.drawable.cleaning2,
                R.drawable.cleaning3,
                R.drawable.cleaning4
        };
        ArrayList<Subcategory> list = new ArrayList<>();
        for (int i = 0; i < titleSub.length; i++) {
            Subcategory acsubcategory = new Subcategory(titleSub[i], priceSub[i], photoSub[i]);
            list.add(acsubcategory);
        }
        return list;
    }

    // Cleaning service list
    private static ArrayList<Subcategory> getListIroning() {
        String[] titleSub = {
                "Reguler (2-3 days) ",
                "Express (1 day)",
                "Flash (4-6 hours)"
        };
        long[] priceSub = {
                4000,
                6000,
                8000,
        };
        int[] photoSub = {
                R.drawable.iron1,
                R.drawable.iron2,
                R.drawable.iron3,
        };
        ArrayList<Subcategory> list = new ArrayList<>();
        for (int i = 0; i < titleSub.length; i++) {
            Subcategory acsubcategory = new Subcategory(titleSub[i], priceSub[i], photoSub[i]);
            list.add(acsubcategory);
        }
        return list;
    }

    // AC service list
    private static ArrayList<Subcategory> getListAc() {
        String[] titleSub = {
                "AC Check-up",
                "AC Regular Service",
                "AC Installation",
                "AC Uninstallation"
        };
        long[] priceSub = {
                100000,
                350000,
                150000,
                150000
        };
        int[] photoSub = {
                R.drawable.accheckup,
                R.drawable.acregular,
                R.drawable.acinstallation,
                R.drawable.acuninstallation
        };
        ArrayList<Subcategory> list = new ArrayList<>();
        for (int i = 0; i < titleSub.length; i++) {
            Subcategory acsubcategory = new Subcategory(titleSub[i], priceSub[i], photoSub[i]);
            list.add(acsubcategory);
        }
        return list;
    }

    private static ArrayList<Subcategory> getListLaundry() {
        String[] titleSub = {
                "Reguler (2-3 days) ",
                "Express (1 day)",
                "Flash (4-6 hours)"
        };
        long[] priceSub = {
                8000,
                12000,
                16000,
        };
        int[] photoSub = {
                R.drawable.laundry1,
                R.drawable.laundry2,
                R.drawable.laundry3,
        };
        ArrayList<Subcategory> list = new ArrayList<>();
        for (int i = 0; i < titleSub.length; i++) {
            Subcategory acsubcategory = new Subcategory(titleSub[i], priceSub[i], photoSub[i]);
            list.add(acsubcategory);
        }
        return list;
    }

    private static ArrayList<Subcategory> getListShipping() {
        String[] titleSub = {
                "Pick up bak (800 kg)",
                "Pick up box (1000 kg)"
        };
        long[] priceSub = {
                100000,
                150000,
        };
        int[] photoSub = {
                R.drawable.shipping1,
                R.drawable.shipping2,
        };
        ArrayList<Subcategory> list = new ArrayList<>();
        for (int i = 0; i < titleSub.length; i++) {
            Subcategory acsubcategory = new Subcategory(titleSub[i], priceSub[i], photoSub[i]);
            list.add(acsubcategory);
        }
        return list;
    }
}
