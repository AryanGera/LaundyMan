package com.example.laundryman;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class ListAdapterReady extends RecyclerView.Adapter<MyListAdapter.ViewHolder>{


    private ArrayList<MyListData> listdata;
    AlertDialog.Builder builder;
    // RecyclerView recyclerView;
    AlertDialog diag;
    ViewGroup par;
    DatabaseReference mDatabase;

    public ListAdapterReady( ArrayList<MyListData> listdata) {

        this.listdata = listdata;
    }

    @Override
    public MyListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem= layoutInflater.inflate(R.layout.list_item, parent, false);
        // holder.setIsRecyclable(false);
        MyListAdapter.ViewHolder viewHolder = new MyListAdapter.ViewHolder(listItem);
        par = parent;
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(MyListAdapter.ViewHolder holder, int position) {

        mDatabase = FirebaseDatabase.getInstance().getReference();


        MyListData order= listdata.get(position);
        holder.name.setText(order.getName());
        holder.date.setText("Date: " +order.getDate());
        holder.phone.setText("Phone Num. : " +order.getPhone());
        holder.upper.setText("Upper: " +String.valueOf(order.getUpper()));
        holder.lower.setText("Lower: " +String.valueOf(order.getLower()));
        holder.price.setText("Price: " +String.valueOf(order.getPrice()));
        holder.small.setText("Small: " +String.valueOf(order.getSmall()));
        if(order.isPaid()==true)
        {
            holder.paid.setText("Payment Completed");
        }
        else
        {
            holder.paid.setText("Payment Pending....");

        }
        builder = new AlertDialog.Builder(par.getContext());


        builder.setTitle("Remove Image");


        builder.setMessage("Is the order for "+order.getName()+" Ready ?");


        //Button One : Yes
        builder.setPositiveButton("Ready", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                order.setReady(true);
                mDatabase.child("orders").child(order.getId()).setValue(order);

            }
        });


        //Button Two : No
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        diag = builder.create();
        holder.cr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(view.getContext(),"click on item: "+order.getName(), Toast.LENGTH_LONG).show();

            }
        });
    }


    @Override
    public int getItemCount() {
        return listdata.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView name,date,phone,upper,lower,small,price,paid;
        public CardView cr;
        public ViewHolder(View itemView) {
            super(itemView);

            this.name = (TextView) itemView.findViewById(R.id.name);
            this.date = (TextView) itemView.findViewById(R.id.date);
            this.phone = (TextView) itemView.findViewById(R.id.phone);
            this.upper = (TextView) itemView.findViewById(R.id.upper);
            this.lower = (TextView) itemView.findViewById(R.id.lower);
            this.small = (TextView) itemView.findViewById(R.id.small);
            this.price = (TextView) itemView.findViewById(R.id.price);
            this.paid=   (TextView) itemView.findViewById(R.id.paid);
            cr = (CardView) itemView.findViewById(R.id.relativeLayout);
        }
    }
}
