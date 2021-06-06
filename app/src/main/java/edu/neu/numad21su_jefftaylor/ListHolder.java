package edu.neu.numad21su_jefftaylor;

import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ListHolder extends RecyclerView.ViewHolder {

    public TextView itemName;


    public ListHolder(View itemView, final ItemClickListener listener) {
        super(itemView);

        itemName = itemView.findViewById(R.id.urlDisplay);


        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    int position = getLayoutPosition();
                    if (position != RecyclerView.NO_POSITION) {

                        listener.onItemClick(position, v);
                    }
                }
            }
        });

//        checkBox.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (listener != null) {
//                    int position = getLayoutPosition();
//                    if (position != RecyclerView.NO_POSITION) {
//                        listener.onCheckBoxClick(position);
//                    }
//                }
//            }
//        });
    }
}
