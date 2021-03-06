package edu.neu.numad21su_jefftaylor;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

public class LinkCollector extends AppCompatActivity {

    private ArrayList<ItemCard> itemList = new ArrayList<>();

    private RecyclerView recyclerView;
    private ListAdapter rviewAdapter;
    private RecyclerView.LayoutManager rLayoutManger;
    private EditText et_UrlName;
    private EditText et_Url;
    private Button addURL;
    private FloatingActionButton addButton;

    private static final String KEY_OF_INSTANCE = "KEY_OF_INSTANCE";
    private static final String NUMBER_OF_ITEMS = "NUMBER_OF_ITEMS";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_link_collector2);
        init(savedInstanceState);

        et_UrlName = findViewById(R.id.et_UrlName);
        et_Url = findViewById(R.id.et_url);
        addURL = findViewById(R.id.btn_add);
        addURL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int pos = 0;
                addItem(pos, v);
            }
        });
        setInitialInvisibility();
    }


    // Handling Orientation Changes on Android
    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {

        int size = itemList == null ? 0 : itemList.size();
        outState.putInt(NUMBER_OF_ITEMS, size);

        for (int i = 0; i < size; i++) {
            outState.putString(KEY_OF_INSTANCE + i + "0", itemList.get(i).getUrlName());
            outState.putString(KEY_OF_INSTANCE + i + "1", itemList.get(i).getUrl());
        }
        super.onSaveInstanceState(outState);
    }

    private void init(Bundle savedInstanceState) {

        initialItemData(savedInstanceState);
        createRecyclerView();
    }


    private void initialItemData(Bundle savedInstanceState) {

        // Not the first time to open this Activity
        if (savedInstanceState != null && savedInstanceState.containsKey(NUMBER_OF_ITEMS)) {
            if (itemList == null || itemList.size() == 0) {

                int size = savedInstanceState.getInt(NUMBER_OF_ITEMS);

                // Retrieve keys we stored in the instance
                for (int i = 0; i < size; i++) {
                    String urlName = savedInstanceState.getString(KEY_OF_INSTANCE + i + "0");
                    String url = savedInstanceState.getString(KEY_OF_INSTANCE + i + "1");

                    ItemCard itemCard = new ItemCard(urlName, url);

                    itemList.add(itemCard);
                }
            }
        }
        // The first time to open this Activity
        else {
//            itemList.add(new ItemCard("Google", "https://www.google.com/"));
//            itemList.add(new ItemCard("ESPN", "https://www.espn.com/"));
//            itemList.add(new ItemCard("Northeastern", "https://www.northeastern.edu/"));
        }

    }

    private void createRecyclerView() {

        rLayoutManger = new LinearLayoutManager(this);
        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);

        rviewAdapter = new ListAdapter(itemList);
        ItemClickListener itemClickListener = new ItemClickListener() {
            @Override
            public void onItemClick(int position, View v) {
                //attributions bond to the item has been changed
                itemList.get(position).onItemClick(position, v);

//                rviewAdapter.notifyItemChanged(position);
            }

        };
        rviewAdapter.setOnItemClickListener(itemClickListener);

        recyclerView.setAdapter(rviewAdapter);
        recyclerView.setLayoutManager(rLayoutManger);
    }

    private void addItem(int position, View v) {
        if (validateInput()) {
            itemList.add(position, new ItemCard(et_UrlName.getText().toString(), et_Url.getText().toString()));
//        Toast.makeText(MainActivity.this, "Add an item", Toast.LENGTH_SHORT).show();

            rviewAdapter.notifyItemInserted(position);
            displaySuccessSnackBar(v);
        }
    }

    private boolean validateInput() {

        String enteredUrl = et_Url.getText().toString().trim();
        if (et_UrlName.getText().toString().trim().equals("")) {
            et_UrlName.setError("Please enter a URL display name");
            return false;
        }
        else if (enteredUrl.equals("")) {
            et_Url.setError("Please enter a URL");
            return false;
        }
        else if (enteredUrl.length() < 11 || (!enteredUrl.substring(0,10).equals( "http://www") &&
                        !enteredUrl.substring(0,11).equals( "https://www"))) {
            et_Url.setError("Please enter a properly formatted URL");
            return false;
        }
        return true;
    }

    //Sets the two text entries and add URL button initially to visible
    //Users will click the FAB to set these to visible
    private void setInitialInvisibility() {
        et_UrlName.setVisibility(View.INVISIBLE);
        et_Url.setVisibility(View.INVISIBLE);
        addURL.setVisibility(View.INVISIBLE);
    }

    public void enableURLEntry(View view) {
        et_UrlName.setVisibility(View.VISIBLE);
        et_Url.setVisibility(View.VISIBLE);
        addURL.setVisibility(View.VISIBLE);
    }

    private void displaySuccessSnackBar(View v) {
        Snackbar.make(v, "URL added to list", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();
    }
}