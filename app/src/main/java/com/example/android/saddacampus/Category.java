package com.example.android.saddacampus;

/**
 * Created by hp on 03-06-2017.
 */

public class Category {


    private String category_name;
    private int mImageResourceId;

    public Category(String name, int image)
    {
        category_name=name;
        mImageResourceId=image;
    }
    public String getCategoryName(){return category_name;}
    public int getImageId(){return mImageResourceId;}
}
