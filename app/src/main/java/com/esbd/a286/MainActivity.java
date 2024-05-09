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

import com.google.android.ads.nativetemplates.NativeTemplateStyle;
import com.google.android.ads.nativetemplates.TemplateView;
import com.google.android.gms.ads.AdLoader;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.gms.ads.nativead.NativeAd;
import com.google.android.material.button.MaterialButton;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    HashMap<String, String> hashMap;
    ArrayList<HashMap<String, String>> arrayList;
    ArrayList<HashMap<String, String>> finalArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        ----------------------------------
        recyclerView = findViewById(R.id.recyclerview);
//        -----------------------
        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });
//        ----------------------------------
        createMainItems();
        createFinalItems();
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
    int Native_Ad_Item = 2;
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
        }
    }
    private class nativeAdViewHolder extends RecyclerView.ViewHolder{
        TemplateView templateView;

        public nativeAdViewHolder(@NonNull View itemView) {
            super(itemView);
//            ----------------
            templateView = itemView.findViewById(R.id.templateView);
//            ----------------
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
        } else if (viewType == Video_Item) {
            View myView = inflater.inflate(R.layout.item_video, parent, false);
            return  new videoViewHolder(myView);
        } else {
            View myView = inflater.inflate(R.layout.item_native_ad, parent, false);
            return  new nativeAdViewHolder(myView);
        }
//        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (getItemViewType(position)==Book_Item){
            bookViewHolder bViewHolder = (bookViewHolder) holder;
            hashMap = finalArrayList.get(position);
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

        } else if (getItemViewType(position)==Video_Item) {
            videoViewHolder vViewHolder = (videoViewHolder) holder;
            hashMap = finalArrayList.get(position);
            String videoTitle = hashMap.get("videoTitle");
            String videoId = hashMap.get("videoId");

            String imgUrl = "https://img.youtube.com/vi/"+videoId+"/0.jpg";
            vViewHolder.tvVideoTitle.setText(videoTitle);
            Picasso.get().load(imgUrl).into(vViewHolder.imgThumbnail);
        } else {
            nativeAdViewHolder adViewHolder = (nativeAdViewHolder) holder;

            AdLoader adLoader = new AdLoader.Builder(MainActivity.this, "ca-app-pub-3940256099942544/2247696110")
                    .forNativeAd(new NativeAd.OnNativeAdLoadedListener() {
                        @Override
                        public void onNativeAdLoaded(NativeAd nativeAd) {

                            adViewHolder.templateView.setNativeAd(nativeAd);
                        }
                    })
                    .build();

            adLoader.loadAd(new AdRequest.Builder().build());
        }
    }

    @Override
    public int getItemCount() {
        return finalArrayList.size();
    }

    @Override
    public int getItemViewType(int position) {
        hashMap = finalArrayList.get(position);
        String itemType = hashMap.get("itemType");
        if (itemType.contains("BOOK")) return Book_Item;
        else if (itemType.contains("VIDEO"))  return Video_Item;
        else return Native_Ad_Item;

//        return super.getItemViewType(position);
    }
}

    private void createMainItems(){
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

//        hashMap = new HashMap<>();
//        hashMap.put("itemType","NATIVE_AD");
//        arrayList.add(hashMap);

        hashMap = new HashMap<>();
        hashMap.put("itemType","BOOK");
        hashMap.put("bookName","স্মার্ট মার্কেটিং");
        hashMap.put("writerName","মার্ক অনুপম মল্লিক");
        hashMap.put("bookLink","https://www.rokomari.com/book/366556/smart-marketing");
        hashMap.put("bookImage","https://ds.rokomari.store/rokomari110/ProductNew20190903/260X372/Smart_Marketing-Mark_Anupam_Mallick-e7693-366556.jpg");
        arrayList.add(hashMap);

        hashMap = new HashMap<>();
        hashMap.put("itemType","BOOK");
        hashMap.put("bookName","স্মার্ট মার্কেটিং");
        hashMap.put("writerName","মার্ক অনুপম মল্লিক");
        hashMap.put("bookLink","https://www.rokomari.com/book/366556/smart-marketing");
        hashMap.put("bookImage","https://ds.rokomari.store/rokomari110/ProductNew20190903/260X372/Smart_Marketing-Mark_Anupam_Mallick-e7693-366556.jpg");
        arrayList.add(hashMap);

        hashMap = new HashMap<>();
        hashMap.put("itemType","BOOK");
        hashMap.put("bookName","স্মার্ট মার্কেটিং");
        hashMap.put("writerName","মার্ক অনুপম মল্লিক");
        hashMap.put("bookLink","https://www.rokomari.com/book/366556/smart-marketing");
        hashMap.put("bookImage","https://ds.rokomari.store/rokomari110/ProductNew20190903/260X372/Smart_Marketing-Mark_Anupam_Mallick-e7693-366556.jpg");
        arrayList.add(hashMap);

        hashMap = new HashMap<>();
        hashMap.put("itemType","BOOK");
        hashMap.put("bookName","স্মার্ট মার্কেটিং");
        hashMap.put("writerName","মার্ক অনুপম মল্লিক");
        hashMap.put("bookLink","https://www.rokomari.com/book/366556/smart-marketing");
        hashMap.put("bookImage","https://ds.rokomari.store/rokomari110/ProductNew20190903/260X372/Smart_Marketing-Mark_Anupam_Mallick-e7693-366556.jpg");
        arrayList.add(hashMap);

        hashMap = new HashMap<>();
        hashMap.put("itemType","BOOK");
        hashMap.put("bookName","স্মার্ট মার্কেটিং");
        hashMap.put("writerName","মার্ক অনুপম মল্লিক");
        hashMap.put("bookLink","https://www.rokomari.com/book/366556/smart-marketing");
        hashMap.put("bookImage","https://ds.rokomari.store/rokomari110/ProductNew20190903/260X372/Smart_Marketing-Mark_Anupam_Mallick-e7693-366556.jpg");
        arrayList.add(hashMap);

        hashMap = new HashMap<>();
        hashMap.put("itemType","BOOK");
        hashMap.put("bookName","স্মার্ট মার্কেটিং");
        hashMap.put("writerName","মার্ক অনুপম মল্লিক");
        hashMap.put("bookLink","https://www.rokomari.com/book/366556/smart-marketing");
        hashMap.put("bookImage","https://ds.rokomari.store/rokomari110/ProductNew20190903/260X372/Smart_Marketing-Mark_Anupam_Mallick-e7693-366556.jpg");
        arrayList.add(hashMap);

        hashMap = new HashMap<>();
        hashMap.put("itemType","BOOK");
        hashMap.put("bookName","স্মার্ট মার্কেটিং");
        hashMap.put("writerName","মার্ক অনুপম মল্লিক");
        hashMap.put("bookLink","https://www.rokomari.com/book/366556/smart-marketing");
        hashMap.put("bookImage","https://ds.rokomari.store/rokomari110/ProductNew20190903/260X372/Smart_Marketing-Mark_Anupam_Mallick-e7693-366556.jpg");
        arrayList.add(hashMap);

        hashMap = new HashMap<>();
        hashMap.put("itemType","BOOK");
        hashMap.put("bookName","স্মার্ট মার্কেটিং");
        hashMap.put("writerName","মার্ক অনুপম মল্লিক");
        hashMap.put("bookLink","https://www.rokomari.com/book/366556/smart-marketing");
        hashMap.put("bookImage","https://ds.rokomari.store/rokomari110/ProductNew20190903/260X372/Smart_Marketing-Mark_Anupam_Mallick-e7693-366556.jpg");
        arrayList.add(hashMap);

        hashMap = new HashMap<>();
        hashMap.put("itemType","BOOK");
        hashMap.put("bookName","স্মার্ট মার্কেটিং");
        hashMap.put("writerName","মার্ক অনুপম মল্লিক");
        hashMap.put("bookLink","https://www.rokomari.com/book/366556/smart-marketing");
        hashMap.put("bookImage","https://ds.rokomari.store/rokomari110/ProductNew20190903/260X372/Smart_Marketing-Mark_Anupam_Mallick-e7693-366556.jpg");
        arrayList.add(hashMap);

        hashMap = new HashMap<>();
        hashMap.put("itemType","BOOK");
        hashMap.put("bookName","স্মার্ট মার্কেটিং");
        hashMap.put("writerName","মার্ক অনুপম মল্লিক");
        hashMap.put("bookLink","https://www.rokomari.com/book/366556/smart-marketing");
        hashMap.put("bookImage","https://ds.rokomari.store/rokomari110/ProductNew20190903/260X372/Smart_Marketing-Mark_Anupam_Mallick-e7693-366556.jpg");
        arrayList.add(hashMap);
    }
    private void createFinalItems(){
        finalArrayList = new ArrayList<>();

        for (int x=0; x<arrayList.size(); x++){

            if (x>1 && x%3==0){
                hashMap = new HashMap<>();
                hashMap.put("itemType", "NATIVE_AD");
                finalArrayList.add(hashMap);
            }

            hashMap = arrayList.get(x);
            finalArrayList.add(hashMap);
        }
    }

//        ----------------------------------
}