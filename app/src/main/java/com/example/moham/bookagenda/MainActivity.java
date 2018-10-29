package com.example.moham.bookagenda;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.Pair;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.moham.bookagenda.RetroFitAPI.BookAPI;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    //Widgets
    private EditText titleEditTxt;
    private ListView listView;
    private ImageView imageView;
    private ProgressBar progressBar;

    //vars
    private Adapter adapter;
    private String bookTitle;
    private ArrayList<BookData> books;
    private String GOOGLE_BOOKS_API = "https://www.googleapis.com/books/v1/";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        titleEditTxt = findViewById(R.id.field_title);
        Button searchBtn = findViewById(R.id.serch_button);
        listView = findViewById(R.id.listView);
        imageView = findViewById(R.id.bookImage);
        progressBar = findViewById(R.id.progress_bar);

        progressBar.setVisibility(View.GONE);

        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                progressBar.setVisibility(View.VISIBLE);

                Retrofit retrofit = new  Retrofit.Builder()
                        .baseUrl(GOOGLE_BOOKS_API)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

                BookAPI bookAPI = retrofit.create(BookAPI.class);

                bookTitle = titleEditTxt.getText().toString();

                if(bookTitle.length() == 0){
                    Toast.makeText(MainActivity.this, "Please Enter Book Title", Toast.LENGTH_SHORT).show();
                    progressBar.setVisibility(View.GONE);
                    Animation animation = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.shake);
                    titleEditTxt.startAnimation(animation);
                }else{
                    imageView.setVisibility(View.GONE);

                    Call<BaseObject> call = bookAPI.getBaseObject("volumes?q="+bookTitle);
                    call.enqueue(new Callback<BaseObject>() {
                        @Override
                        public void onResponse(@NonNull Call<BaseObject> call, @NonNull Response<BaseObject> response) {
                            books = new ArrayList<>();
                            ArrayList<BookItem> items = null;
                            if (response.body() != null) items = response.body().getItems();
                            if(items != null){
                                for(BookItem item:items){
                                    BookData book = new BookData();
                                    book.setTitle(item.getVolumeInfo().getTitle());
                                    if(item.getVolumeInfo().getAuthors() == null){
                                        book.setAuthor("Author not found");
                                    }else
                                        book.setAuthor(item.getVolumeInfo().getAuthors().get(0));

                                    if(item.getVolumeInfo().getPublisher() == null){
                                        book.setPublisher("Publisher not found");
                                    }else
                                        book.setPublisher(item.getVolumeInfo().getPublisher());

                                    if(item.getVolumeInfo().getPublishedDate() == null){
                                        book.setDate("Date not found");
                                    }else
                                        book.setDate(item.getVolumeInfo().getPublishedDate());

                                    if(item.getVolumeInfo().getDescription() == null){
                                        book.setDescription("No description found :( ");
                                    }else
                                        book.setDescription(item.getVolumeInfo().getDescription());

                                    if(item.getVolumeInfo().getImageLinks() == null){
                                        book.setImageURL("");
                                    }else{
                                        book.setImageURL(item.getVolumeInfo().getImageLinks().getThumbnail());
                                    }

                                    books.add(book);
                                }

                            }
                            else{
                                Toast.makeText(MainActivity.this, "No Matching Search Result", Toast.LENGTH_SHORT).show();
                            }

                            adapter = new Adapter(getApplicationContext(),0,books);
                            listView.setAdapter(adapter);
                            progressBar.setVisibility(View.GONE);


                            for(int i=0;i<books.size();i++){
                                Log.e(TAG, "onResponse: "+books.get(i).toString());
                            }
                        }

                        @Override
                        public void onFailure(@NonNull Call<BaseObject> call, @NonNull Throwable t) {

                        }
                    });

                    InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
                    if (inputMethodManager != null) {
                        inputMethodManager.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY,0);
                    }


                }

            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                String bookTitle = books.get(i).getTitle();
                String bookAuthor = books.get(i).getAuthor();
                String bookPublisher = books.get(i).getPublisher();
                String bookPublishedDate = books.get(i).getDate();
                String bookDescription = books.get(i).getDescription();
                String bookImageUrl = books.get(i).getImageURL();


                Intent intent = new Intent(MainActivity.this,BookDetailsActivity.class);

                //Extras
                intent.putExtra("bookTitle",bookTitle);
                intent.putExtra("bookAuthor",bookAuthor);
                intent.putExtra("bookPublisher",bookPublisher);
                intent.putExtra("bookPublishedDate",bookPublishedDate);
                intent.putExtra("bookDescription",bookDescription);
                intent.putExtra("bookImageUrl",bookImageUrl);

                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {

                    ImageView listItemImgView = view.findViewById(R.id.bookImage);
                    TextView listItemTitle = view.findViewById(R.id.book_title);
                    TextView listItemAuthor = view.findViewById(R.id.book_author);

                    Pair[] pairs = new Pair[3];
                    pairs[0] = new Pair<View,String>(listItemImgView,"imageTransition");
                    pairs[1] = new Pair<View,String>(listItemTitle,"titleTransition");
                    pairs[2] = new Pair<View,String>(listItemAuthor,"authorTransition");



                    //Shared Animation
                    ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(MainActivity.this,pairs);
                    startActivity(intent,options.toBundle());
                }else{

                    startActivity(intent);
                }

            }
        });


    }


}
