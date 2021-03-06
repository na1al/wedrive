package com.dev.wedrive.adapters;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.dev.wedrive.R;
import com.dev.wedrive.entity.ApiCar;
import com.dev.wedrive.helpers.CarHelper;

import java.util.ArrayList;

import lombok.Setter;

public class CarListAdapter extends RecyclerView.Adapter<CarListAdapter.ViewHolder> {

    private LayoutInflater inflater;

    private Drawable activeRadioShape;

    @Setter
    private OnItemClickListener listener;

    private ArrayList<ApiCar> cars;

    // data is passed into the constructor
    public CarListAdapter(Context context, ArrayList<ApiCar> cars) {
        this.inflater = LayoutInflater.from(context);
        activeRadioShape = context.getDrawable(R.drawable.btn_radio_shape_checked);
        this.cars = cars;
    }

    // total number of rows
    @Override
    public int getItemCount() {
        return cars.size();
    }

    // convenience method for getting data at click position
    public ApiCar getItem(int id) {
        return cars.get(id);
    }

    // inflates the row layout from xml when needed
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.adapter_car_list_item, parent, false);
        return new ViewHolder(view);
    }

    // binds the data to the TextView in each row
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        ApiCar car = cars.get(position);

        CarHelper.setCarImage(car, holder.image);
        holder.model.setText(car.model);
        holder.brand.setText(car.brand);
        holder.number.setText(car.number);

        if (car.active)
            holder.checkbox.setBackground(activeRadioShape);
    }

    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder {

        RelativeLayout content;
        ImageView image;
        TextView model;
        TextView brand;
        TextView number;
        ImageView checkbox;
        ImageButton editBtn;
        ImageButton deleteBtn;

        ViewHolder(View view) {
            super(view);
            content = view.findViewById(R.id.smContentView);
            image = view.findViewById(R.id.car_image);
            model = view.findViewById(R.id.car_model);
            brand = view.findViewById(R.id.car_brand);
            number = view.findViewById(R.id.car_number);
            checkbox = view.findViewById(R.id.list_status);
            editBtn = view.findViewById(R.id.list_item_edit);
            deleteBtn = view.findViewById(R.id.list_item_delete);

            content.setOnClickListener((v) -> listener.onItemClick(v, getAdapterPosition()));
            editBtn.setOnClickListener((v) -> listener.onItemClick(v, getAdapterPosition()));
            deleteBtn.setOnClickListener((v) -> listener.onItemClick(v, getAdapterPosition()));
        }
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }
}
