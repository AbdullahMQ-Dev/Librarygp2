package com.example.librarygp2.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.librarygp2.R;

import java.util.ArrayList;

public class PickCategoreyActivity extends AppCompatActivity {
    ListView listViewCat ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pick_categorey);

        setTitle("Pick Categorey");

        listViewCat = findViewById(R.id.catlist);

        final ArrayList<String> arrayList = new ArrayList<>();

        arrayList.add("Design");
        arrayList.add("Database");
        arrayList.add("Network");
        arrayList.add("Security");
        arrayList.add("Programing");

        ArrayAdapter arrayAdapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,arrayList);
        listViewCat.setAdapter(arrayAdapter);

        listViewCat.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Bundle bundle = new Bundle();
                bundle.putString("Catname",arrayList.get(position));

                Intent intent = new Intent(PickCategoreyActivity.this, CategoryPageActivity.class);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });


    }
}
