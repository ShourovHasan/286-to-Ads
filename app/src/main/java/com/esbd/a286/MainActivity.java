package com.esbd.a286;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    HashMap<String, String> hashMap;
    ArrayList<HashMap<String, String>> arrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        ----------------------------------
        recyclerView = findViewById(R.id.recyclerview);
//        ----------------------------------
        arrayList = new ArrayList<>();

        hashMap = new HashMap<>();
        hashMap.put("itemType","BOOK");
        hashMap.put("bookName","স্মার্ট মার্কেটিং");
        hashMap.put("writerName","মার্ক অনুপম মল্লিক");
        hashMap.put("bookLink","https://www.rokomari.com/book/366556/smart-marketing");
        hashMap.put("bookImage","https://ds.rokomari.store/rokomari110/ProductNew20190903/260X372/Smart_Marketing-Mark_Anupam_Mallick-e7693-366556.jpg");
        arrayList.add(hashMap);

        hashMap = new HashMap<>();
        hashMap.put("itemType","VIDEO");
        hashMap.put("videoTitle","মিল্টন সমাদ্দারের কিডনি পাচার নিয়ে যা বললেন ডা. লেলিন");
        hashMap.put("videoId","u7oWLgLtlTc");
        arrayList.add(hashMap);

        hashMap = new HashMap<>();
        hashMap.put("itemType","BOOK");
        hashMap.put("bookName","স্মার্ট মার্কেটিং");
        hashMap.put("writerName","মার্ক অনুপম মল্লিক");
        hashMap.put("bookLink","https://www.rokomari.com/book/366556/smart-marketing");
        hashMap.put("bookImage","https://ds.rokomari.store/rokomari110/ProductNew20190903/260X372/Smart_Marketing-Mark_Anupam_Mallick-e7693-366556.jpg");
        arrayList.add(hashMap);

        hashMap = new HashMap<>();
        hashMap.put("itemType","VIDEO");
        hashMap.put("videoTitle","মিল্টন সমাদ্দারের কিডনি পাচার নিয়ে যা বললেন ডা. লেলিন");
        hashMap.put("videoId","u7oWLgLtlTc");
        arrayList.add(hashMap);

        hashMap = new HashMap<>();
        hashMap.put("itemType","BOOK");
        hashMap.put("bookName","স্মার্ট মার্কেটিং");
        hashMap.put("writerName","মার্ক অনুপম মল্লিক");
        hashMap.put("bookLink","https://www.rokomari.com/book/366556/smart-marketing");
        hashMap.put("bookImage","https://ds.rokomari.store/rokomari110/ProductNew20190903/260X372/Smart_Marketing-Mark_Anupam_Mallick-e7693-366556.jpg");
        arrayList.add(hashMap);
//        ----------------------------------
        XAdapter adapter = new XAdapter();
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
//        ----------------------------------
//        ----------------------------------
    }
//        ----------------------------------
private class XAdapter extends RecyclerView.Adapter {
    int Book_Item = 0;
    int Video_Item = 1;
    private class videoViewHolder extends RecyclerView.ViewHolder{
        ImageView imgThumbnail;
        TextView tvVideoTitle;

        public videoViewHolder(@NonNull View itemView) {
            super(itemView);
            imgThumbnail = itemView.findViewById(R.id.imgThumbnail);
            tvVideoTitle = itemView.findViewById(R.id.tvVideoTitle);


        }
    }

    private class bookViewHolder extends RecyclerView.ViewHolder{
        ImageView imgBook;
        TextView tvBookName, tvWritterName;
        MaterialButton buttonGetBook;
        public bookViewHolder(@NonNull View itemView) {
            super(itemView);
//            ----------------------
            imgBook = itemView.findViewById(R.id.imgBook);
            tvBookName = itemView.findViewById(R.id.tvBookName);
            tvWritterName = itemView.findViewById(R.id.tvWritterName);
            buttonGetBook = itemView.findViewById(R.id.buttonGetBook);
//            ----------------------
//            ----------------------
        }
    }

//    -------------------------

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = getLayoutInflater();
        if (viewType == Book_Item){
            View myView = inflater.inflate(R.layout.item_book, parent, false);
            return  new bookViewHolder(myView);
        } else {
            View myView = inflater.inflate(R.layout.item_video, parent, false);
            return  new videoViewHolder(myView);
        }

//        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (getItemViewType(position)==Book_Item){
            bookViewHolder bViewHolder = (bookViewHolder) holder;
            hashMap = arrayList.get(position);
            String bookName = hashMap.get("bookName");
            String writerName = hashMap.get("writerName");
            String bookLink = hashMap.get("bookLink");
            String bookImage = hashMap.get("bookImage");

            bViewHolder.tvBookName.setText(bookName);
            bViewHolder.tvWritterName.setText(writerName);
//            bViewHolder.imgBook.setim
            bViewHolder.buttonGetBook.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(MainActivity.this, bookLink, Toast.LENGTH_SHORT).show();
                }
            });
            Picasso.get().load(bookImage).into(bViewHolder.imgBook);


        } else {
            videoViewHolder vViewHolder = (videoViewHolder) holder;
            hashMap = arrayList.get(position);
            String videoTitle = hashMap.get("videoTitle");
            String videoId = hashMap.get("videoId");

            String imgUrl = "https://img.youtube.com/vi/"+videoId+"/0.jpg";
            vViewHolder.tvVideoTitle.setText(videoTitle);
            Picasso.get().load(imgUrl).into(vViewHolder.imgThumbnail);

        }
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    @Override
    public int getItemViewType(int position) {
        hashMap = arrayList.get(position);
        String itemType = hashMap.get("itemType");
        if (itemType.contains("BOOK")) return Book_Item;
        else return Video_Item;

//        return super.getItemViewType(position);
    }
}




//        ----------------------------------
}