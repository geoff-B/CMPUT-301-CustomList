package com.example.simpleparadox.listycity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    // Declare the variables so that you will be able to reference it later.
    ListView cityList;
    ArrayAdapter<City> cityAdapter;
    ArrayList<City> cityDataList;

    CustomList customList;
    final String TAG = "Sample";
    Button addCityButton;
    EditText addCityEditText;
    EditText addProvinceEditText;
    FirebaseFirestore db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addCityButton = findViewById(R.id.add_city_button);
        addCityEditText = findViewById(R.id.add_city_field);
        addProvinceEditText = findViewById(R.id.add_province_edit_text);

        cityList = findViewById(R.id.city_list);

        String []cities ={};
        String []provinces = {};


        cityDataList = new ArrayList<>();

        for(int i=0;i<cities.length;i++){
            cityDataList.add((new City(cities[i], provinces[i])));
        }

        cityAdapter = new CustomList(this, cityDataList);

        cityList.setAdapter(cityAdapter);
        // Access a Cloud Firestore instance from your Activity

        db = FirebaseFirestore.getInstance();
        final CollectionReference collectionReference = db.collection("Cities");
        addCityButton.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Retrieving the city name and the province name from the EditText fields
                final String cityName = addCityEditText.getText().toString();
                final String provinceName = addProvinceEditText.getText().toString();
                HashMap<String,String> data = new HashMap<>();
                if (cityName.length() > 0 && provinceName.length() > 0) {
                    data.put("Province Name",provinceName);
                    collectionReference.document(cityName).set(data).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void unused) {
                            Log.d(TAG,"Data has been added successfully.");
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Log.d(TAG,"Data could not be added!");
                        }
                    });
                    addCityEditText.setText("");
                    addProvinceEditText.setText("");
                }
            }
        });
        collectionReference.addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable
                    FirebaseFirestoreException error) {
                cityDataList.clear();
                for (QueryDocumentSnapshot doc : queryDocumentSnapshots) {
                    Log.d(TAG, String.valueOf(doc.getData().get("Province Name")));
                    String city = doc.getId();
                    String province = (String) doc.getData().get("Province Name");
                    cityDataList.add(new City(city, province)); // Adding the cities and provinces from FireStor
                }
                cityAdapter.notifyDataSetChanged();
            }
        });
        cityList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                // show the delete button
                FloatingActionButton deleteFAB = findViewById(R.id.deleteFAB);
                deleteFAB.setVisibility(View.VISIBLE);
                cityList.getSelector().setAlpha(255); // color background
                deleteFAB.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {


                        String currentCityName = cityDataList.get(i).getCityName();
                        cityAdapter.remove(cityAdapter.getItem(i));
                        collectionReference.document(currentCityName).delete();


                        //hide button
                        deleteFAB.setVisibility(View.INVISIBLE);
                        // clear selection
                        cityList.clearChoices();
                        cityList.getSelector().setAlpha(0);
                    }
                });
            }
        });

//        dataList = new ArrayList<>();
//        dataList.addAll(Arrays.asList(cities));
//
//        cityAdapter = new ArrayAdapter<>(this, R.layout.content, dataList);
//
//        cityList.setAdapter(cityAdapter);



    }


}
