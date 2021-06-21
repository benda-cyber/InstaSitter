package com.example.instasitter.classes;

import android.os.Parcel;
import android.os.Parcelable;

import com.example.instasitter.R;

public class MyData implements Parcelable {
     public static String[] nameArray = {"Cupcake", "Donut", "Eclair", "Froyo", "Gingerbread", "Honeycomb", "Ice Cream Sandwich","JellyBean", "Kitkat", "Lollipop", "Marshmallow"};
     public static String[] locationArray = {"אילת", "באר שבע", "תל אביב", "ראשון לציון", "חולון", "ראשון לציון, חולון, בת ים", "רחובות", "נתניה", "באר יעקב", "ראש העין","תל אביב והסביבה"};
     public static String[] serviceTypeArray = {"Dog Walker", "Babysitter", "Babysitter, Dog Walker", "Dog Walker", "Dog Walker", "Babysitter", "Dog Walker","Babysitter", "Babysitter, Dog Walker", "Babysitter", "Dog Walker"};

     public static Integer[] drawableArray = {R.drawable.cupcake, R.drawable.donut, R.drawable.eclair,
            R.drawable.froyo, R.drawable.gingerbread, R.drawable.honeycomb, R.drawable.ics,
            R.drawable.jellybean, R.drawable.kitkat, R.drawable.lollipop,R.drawable.marsh};

     public static Integer[] id_ = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10};

     protected MyData(Parcel in) {
     }

     public static final Creator<MyData> CREATOR = new Creator<MyData>() {
          @Override
          public MyData createFromParcel(Parcel in) {
               return new MyData(in);
          }

          @Override
          public MyData[] newArray(int size) {
               return new MyData[size];
          }
     };


     public static Integer[] getId_() {
          return id_;
     }

     @Override
     public int describeContents() {
          return 0;
     }

     @Override
     public void writeToParcel(Parcel dest, int flags) {
     }
}
