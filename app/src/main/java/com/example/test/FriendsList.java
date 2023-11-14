package com.example.test;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class FriendsList extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View contentview = inflater.inflate(R.layout.fragment_friends_list, container, false);
        ListView listView = contentview.findViewById(R.id.friendslist);

        String usernames[] = {"Friend 1", "Friend 2", "Friend 3"};

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(contentview.getContext(), R.layout.textview_layout, R.id.textView, usernames);

        return contentview;

    }
}