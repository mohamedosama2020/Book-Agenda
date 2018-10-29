package com.example.moham.bookagenda;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestOptions;

public class BookDetailsActivity extends AppCompatActivity {
    private static final String TAG = "BookDetailsActivity";

    //Widgets
    private ImageView imageView;
    private TextView book_title;
    private TextView book_author;
    private TextView publishByTv;
    private TextView publishedDateTv;
    private TextView description;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_details);

        Intent intent = getIntent();
        String bookTitle = intent.getStringExtra("bookTitle");
        String bookAuthor = intent.getStringExtra("bookAuthor");
        String bookPublisher = intent.getStringExtra("bookPublisher");
        String bookPublishedDate = intent.getStringExtra("bookPublishedDate");
        String bookDescription = intent.getStringExtra("bookDescription");
        String bookImageUrl = intent.getStringExtra("bookImageUrl");

        imageView = findViewById(R.id.bookImageDetails);
        book_title = findViewById(R.id.book_title);
        book_author = findViewById(R.id.book_author);
        publishByTv = findViewById(R.id.publishByTv);
        publishedDateTv = findViewById(R.id.publishedDateTv);
        description = findViewById(R.id.description);




        if(bookImageUrl.length() == 0){
            Glide.with(getApplicationContext()).load(R.drawable.notebook).into(imageView);
        }
        else
        Glide.with(getApplicationContext()).load(bookImageUrl).into(imageView);

        book_title.setText(bookTitle);
        book_author.setText(bookAuthor);
        publishByTv.setText(publishByTv.getText()+bookPublisher);
        publishedDateTv.setText(publishedDateTv.getText()+bookPublishedDate);
        description.setText(bookDescription);


    }
}
