package com.journaldev.recyclerviewcardview;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewAdapter> {

    private ArrayList<DataModel> data;

    public CustomAdapter(ArrayList<DataModel> data) {
        this.data = data;
    }

    public class MyViewAdapter extends RecyclerView.ViewHolder{

        TextView textViewName;
        TextView textViewVersion;
        ImageView imageView;
        CardView cardView;

        public MyViewAdapter(@NonNull View itemView) {
            super(itemView);
            textViewName = itemView.findViewById(R.id.textViewName);
            textViewVersion = itemView.findViewById(R.id.textViewVersion);
            imageView = itemView.findViewById(R.id.imageView);
            cardView = itemView.findViewById(R.id.card_view);
        }
    }

    @NonNull
    @Override
    public MyViewAdapter onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.cards_layout,
                viewGroup, false);

//       view.setOnClickListener(MainActivity.myOnClickListener);

        MyViewAdapter myViewAdapter = new MyViewAdapter(view);
        return myViewAdapter;
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewAdapter holder, final int indexInArray) {

        TextView textViewName = holder.textViewName;
        TextView textViewVer = holder.textViewVersion;
        ImageView imageView = holder.imageView;
        CardView cardView = holder.cardView;

        textViewName.setText(data.get(indexInArray).getName());
        textViewVer.setText(data.get(indexInArray).getVersion());
        imageView.setImageResource(data.get(indexInArray).getImage());

        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                remove(holder.getAdapterPosition());
            }
        });
    }

    public void remove(int position)
    {
        data.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position,data.size());

    }

    @Override
    public int getItemCount() {
        return data.size();
    }
}
