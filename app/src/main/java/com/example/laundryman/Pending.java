package com.example.laundryman;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Pending#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Pending extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    TextView tv;
    ArrayList<MyListData> orders;

    public Pending() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Pending.
     */
    // TODO: Rename and change types and number of parameters
    public static Pending newInstance(String param1, String param2) {
        Pending fragment = new Pending();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }



    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {




        View view = inflater.inflate(R.layout.fragment_pending, container, false);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("orders");

        orders=new ArrayList<>();
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.rv_pending);
        MyListAdapter adapter = new MyListAdapter(orders);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        recyclerView.setAdapter(adapter);
        databaseReference.addValueEventListener(new ValueEventListener() {


            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                orders.clear();
                for( DataSnapshot dataSnapshot : snapshot.getChildren())
                {
                    MyListData order=dataSnapshot.getValue(MyListData.class);

                    order.setId(dataSnapshot.getKey());
                    if(order.isReady()==false){
                        orders.add(order);
                        Toast.makeText(view.getContext(), "order key:"+order.getId(), Toast.LENGTH_SHORT).show();


                    }




                }

                adapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // calling on cancelled method when we receive
                // any error or we are not able to get the data.
            }
        });

        Log.d("order:","order.toString()");

//        MyListData[] myListData = new MyListData[] {
//                new MyListData("Aryan Gera","123456","1849543",1,2,3,5.43),
//                new MyListData("Aryan Gera","123456","1849543",1,2,3,5.43),
//                new MyListData("Aryan Gera","123456","1849543",1,2,3,5.43),
//                new MyListData("Aryan Gera","123456","1849543",1,2,3,5.43),
//
//        };





        return view;




        //return inflater.inflate(R.layout.fragment_pending, container, false);
    }


}