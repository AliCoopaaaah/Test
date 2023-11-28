package com.example.test;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;

public class ListFragment extends Fragment {

    ListView listView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        ArrayList list = new ArrayList<String>();
        list.add("item 1");
        list.add("item 2");
        list.add("item 3");

        View result = inflater.inflate(R.layout.fragment_list, container, false);

        listView = (ListView) result.findViewById(R.id.list);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(result.getContext(), R.layout.list_items, list);
        listView.setAdapter(adapter);

        return result;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }
}