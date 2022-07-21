package MyAssistHub.App.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Subcategory implements Parcelable {
    private String title;
    private long price;
    private int photo;

    public Subcategory(String title, long price, int photo){
        this.title = title;
        this.price = price;
        this.photo = photo;
    }

    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }

    public int getPhoto() {
        return photo;
    }

    public void setPhoto(int photo) {
        this.photo = photo;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(title);
        parcel.writeLong(price);
        parcel.writeInt(photo);
    }

    protected Subcategory(Parcel in){
        title = in.readString();
        price = in.readLong();
        photo = in.readInt();
    }
    public static final Creator<Subcategory> CREATOR = new Creator<Subcategory>() {
        @Override
        public Subcategory createFromParcel(Parcel in) {
            return new Subcategory(in);
        }

        @Override
        public Subcategory[] newArray(int size) {
            return new Subcategory[size];
        }
    };
}
