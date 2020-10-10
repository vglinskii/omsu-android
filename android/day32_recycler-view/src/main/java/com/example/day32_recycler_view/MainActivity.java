package com.example.day32_recycler_view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        List<String> list = fillList();
        list.addAll(getCatArray());
        recyclerView.setAdapter(new RecyclerAdapter3000(list));
    }

    private List<String> fillList() {
        return Stream
                .iterate(0, (i) -> i + 1)
                .map(i -> i + " element").limit(30)
                .collect(Collectors.toList());
    }

    private List<String> getCatArray() {
        return Arrays.asList(getResources().getStringArray(R.array.cat_names));
    }
}
