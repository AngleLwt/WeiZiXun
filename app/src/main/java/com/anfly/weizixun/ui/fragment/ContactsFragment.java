package com.anfly.weizixun.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.anfly.weizixun.R;
import com.anfly.weizixun.adapter.UsersAdapter;
import com.anfly.weizixun.common.Constants;
import com.anfly.weizixun.ui.activity.ChatActivity;
import com.hyphenate.chat.EMContact;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class ContactsFragment extends Fragment {

    @BindView(R.id.rv)
    RecyclerView rv;
    private ArrayList<String> list;
    private UsersAdapter adapter;

    public ContactsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_contacts, container, false);
        ButterKnife.bind(this, view);
        initView();
        initData();
        return view;
    }

    private void initData() {
        ArrayList<String> names = new ArrayList<>();

        names.add("a");
        names.add("b");
        names.add("c");

        if (list.size() <= 0) {
            list.addAll(names);
        }
        adapter.notifyDataSetChanged();
    }

    private void initView() {
        rv.setLayoutManager(new LinearLayoutManager(getActivity()));
        list = new ArrayList<>();
        rv.addItemDecoration(new DividerItemDecoration(getActivity(), RecyclerView.VERTICAL));
        adapter = new UsersAdapter(list, getActivity());
        rv.setAdapter(adapter);
        adapter.setOnItemClickListener(new UsersAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position, String name) {
                Intent intent = new Intent(getActivity(), ChatActivity.class);
                intent.putExtra(Constants.NAME, name);
                startActivity(intent);
            }
        });
    }
}
