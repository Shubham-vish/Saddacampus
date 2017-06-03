/*
 * Copyright (C) 2016 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.android.saddacampus;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;


public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.NumberViewHolder> {

    private static final String TAG = CategoryAdapter.class.getSimpleName();

    private ArrayList<Category> mCategoryList;
    private ArrayList<Word> wordlist;
    int activity=0;
    private int mColorResourceId;


    public CategoryAdapter(ArrayList<Category> c) {
        mCategoryList=c;
    }

    public CategoryAdapter(ArrayList<Word> w, int m)
    {
        wordlist=w;
        activity=m;
    }

    @Override
    public NumberViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        Context context = viewGroup.getContext();
        if(activity==1)
        {
            int layoutIdForListItem = R.layout.list_item;
            LayoutInflater inflater = LayoutInflater.from(context);
            boolean shouldAttachToParentImmediately = false;

            View view = inflater.inflate(layoutIdForListItem, viewGroup, shouldAttachToParentImmediately);
            NumberViewHolder viewHolder = new NumberViewHolder(view);

            return viewHolder;
        }
        else {

            int layoutIdForListItem = R.layout.s;
            LayoutInflater inflater = LayoutInflater.from(context);
            boolean shouldAttachToParentImmediately = false;

            View view = inflater.inflate(layoutIdForListItem, viewGroup, shouldAttachToParentImmediately);
            NumberViewHolder viewHolder = new NumberViewHolder(view);

            return viewHolder;
        }
    }


    @Override
    public void onBindViewHolder(NumberViewHolder holder, int position) {
        Log.d(TAG, "#" + position);
        holder.bind(position);
    }


    @Override
    public int getItemCount() {
        if(activity==1){
            return wordlist.size();
        }
        return mCategoryList.size();
    }


    class NumberViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        // Will display the position in the list, ie 0 through getItemCount() - 1
        TextView listItemNumberView;
        ImageView image;
        TextView too;

        public NumberViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            if(activity==1){
                image = (ImageView) itemView.findViewById(R.id.i) ;
                listItemNumberView = (TextView) itemView.findViewById(R.id.tv_item_number);
                too = (TextView) itemView.findViewById(R.id.default_text_view);
            }
            else
            {
            image = (ImageView) itemView.findViewById(R.id.i) ;
            listItemNumberView = (TextView) itemView.findViewById(R.id.tv_item_number);
            too = (TextView) itemView.findViewById(R.id.default_text_view);
         }
        }

        @Override
        public void onClick(View view) {
            Toast.makeText(view.getContext(), "position = " + getPosition(), Toast.LENGTH_SHORT).show();
        }
        void bind(int listIndex) {
            if(activity==1){
                image.setImageResource(wordlist.get(listIndex).getImageResourceId());
                listItemNumberView.setText(wordlist.get(listIndex).getDefaultTranslation());
                too.setText(wordlist.get(listIndex).getMiwokTranslation());
            }
            image.setImageResource(mCategoryList.get(listIndex).getImageId());
            listItemNumberView.setText(mCategoryList.get(listIndex).getCategoryName());
        }
    }
}
