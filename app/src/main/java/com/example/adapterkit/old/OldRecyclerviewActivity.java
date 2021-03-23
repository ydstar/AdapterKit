package com.example.adapterkit.old;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;


import com.example.adapterkit.R;

import java.util.ArrayList;
import java.util.List;

public class OldRecyclerviewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_old_recyclerview);
        final List<OldModel> dataList = mockDataSets();

        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setAdapter(new OldAdapter(this, dataList));

        GridLayoutManager manager = new GridLayoutManager(this, 2);
        manager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                return dataList.get(position).itemType < OldModel.TYPE_VIDEO ? 2 : 1;
            }
        });
        recyclerView.setLayoutManager(manager);

    }

    private List<OldModel> mockDataSets() {
        List<OldModel> dataList = new ArrayList<>();
        for (int index = 0; index < 20; index++) {
            OldModel itemData = new OldModel();
            if (index < 6) {
                itemData.itemType = index;
            } else {
                itemData.itemType = index % 2 == 0 ? OldModel.TYPE_VIDEO : OldModel.TYPE_IMAGE;
            }
            dataList.add(itemData);
        }
        return dataList;
    }
}