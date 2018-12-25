package com.example.android.databasebasic;
/*;
 * Copyright (C) 2017 Google Inc.
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
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.TextView;

import java.util.List;


public class WordListAdapter extends RecyclerView.Adapter<WordListAdapter.WordViewHolder> {
   /* public class RecycleViewAdapter extends
            RecyclerView.Adapter<RecycleViewHolder> {// Recyclerview will extend to
        private List<FileName> fileNames;
        private Context context;

        //declare interface
        private OnItemClicked onClick;

        //make interface like this
        public interface OnItemClicked {
            void onItemClick(int position);
        }

        public RecycleViewAdapter(Context context,List<FileName> fileNames) {
            this.context = context;
            this.fileNames = fileNames;*/

    class WordViewHolder extends RecyclerView.ViewHolder {
        private final TextView wordItemView;
        private final TextView catItemView;
        private final TextView numItemView;

        private WordViewHolder(View itemView) {
            super(itemView);
            wordItemView = itemView.findViewById(R.id.textView);
            catItemView = itemView.findViewById(R.id.edit_cat_TextView);
            numItemView = itemView.findViewById(R.id.prise_textView);
        }
    }

    private final LayoutInflater mInflater;
    private List<Word> mWords; // Cached copy of words
    private Context context;
    private OnItemClicked onClick;

    //make interface like this
    public interface OnItemClicked {void onItemClick(int position);}

    WordListAdapter(Context context) {
        this.context = context;
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public WordViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.recyclerview_item, parent, false);
        return new WordViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final WordViewHolder holder, int position) {
        Word current = mWords.get(position);
        holder.wordItemView.setText(current.getWord());
        holder.wordItemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClick.onItemClick(holder.getAdapterPosition());
            }
        });

        holder.catItemView.setText(current.getCategory());
        holder.catItemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClick.onItemClick(holder.getAdapterPosition());
            }
        });

        int nu = current.getNumber();
        holder.numItemView.setText(Integer.toString(nu));
        holder.numItemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClick.onItemClick(holder.getAdapterPosition());
            }
        });


    }

    void setWords(List<Word> words){
        mWords = words;
        notifyDataSetChanged();
    }

    // getItemCount() is called many times, and when it is first called,
    // mWords has not been updated (means initially, it's null, and we can't return null).
    @Override
    public int getItemCount() {
        if (mWords != null)
            return mWords.size();
        else return 0;
    }

    public void setOnClick(OnItemClicked onClick)
    {this.onClick=onClick; }
}

