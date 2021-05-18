package com.example.aahaarapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;

public class myadapter extends RecyclerView.Adapter<myadapter.myviewholder>
{
    ArrayList<model> datalist;
    FirebaseAuth fAuth= FirebaseAuth.getInstance();
    public String userID = fAuth.getCurrentUser().getUid();
    public String uid;


    public myadapter(ArrayList<model> datalist) {
        this.datalist = datalist;
    }

    @NonNull
    @Override
    public myviewholder onCreateViewHolder (@NonNull ViewGroup parent,int viewType){
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.singlerow, parent, false);
        return new myviewholder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull myviewholder holder, int position) {
        holder.tname.setText(datalist.get(position).getName());
        holder.ttype.setText(datalist.get(position).getType());
        holder.tdescription.setText(datalist.get(position).getDescription());
    }

    public void deleteItem(int position){
        //getSnapshots().getSnapshot(position).getReference().delete();
        //notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return datalist.size();
    }

    class myviewholder extends RecyclerView.ViewHolder
    {
        TextView tname,ttype,tdescription;
        public myviewholder(@NonNull View itemView) {
            super(itemView);
            tname = itemView.findViewById(R.id.name);
            ttype = itemView.findViewById(R.id.type);
            tdescription = itemView.findViewById(R.id.description);
        }
    }
}
