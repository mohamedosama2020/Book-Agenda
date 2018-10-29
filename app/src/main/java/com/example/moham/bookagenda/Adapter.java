package com.example.moham.bookagenda;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class Adapter extends ArrayAdapter<BookData> {

    //widgets
    ImageView imageView;
    TextView title,author;

    public Adapter(@NonNull Context context, int resource, ArrayList<BookData> books) {
        super(context, resource,books);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = convertView;
        if(view == null){
            view = LayoutInflater.from(getContext()).inflate(R.layout.list_item,parent,false);
        }

        Animation animation = AnimationUtils.loadAnimation(getContext(),R.anim.scale);
        view.startAnimation(animation);

        BookData bookData = getItem(position);
        imageView = view.findViewById(R.id.bookImage);
        title = view.findViewById(R.id.book_title);
        author = view.findViewById(R.id.book_author);

        title.setText(bookData.getTitle());
        author.setText(bookData.getAuthor());

        if(bookData.getImageURL().length() != 0){

            Glide.with(getContext()).load(bookData.getImageURL()).into(imageView);

        }else{
            imageView.setImageResource(R.drawable.notebook);
        }



        return view;

    }
}
