package ru.krivorukie.foodcountpp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class ListOfProducts extends RecyclerView.Adapter<ListOfProducts.ListHolder> {
    ArrayList<UserJSONSerializer.Product> products;

    void load(List data){
        products.addAll(data);
        notifyDataSetChanged();
    }


    public ListOfProducts (ArrayList<UserJSONSerializer.Product> products) {
        this.products = products;
    }

    public class ListHolder extends RecyclerView.ViewHolder {
        TextView name1, name2, name3;
        public ListHolder (View view) {
            super(view);
            name1 = (TextView) view.findViewById(R.id.name31);
            name2 = (TextView) view.findViewById(R.id.name32);
            name3 = (TextView) view.findViewById(R.id.name33);
        }
    }

    @NonNull
    @Override
    public ListHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_3, parent, false);
        return new ListHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListHolder holder, int position) {
        holder.name1.setText(products.get(position).getName() + ", " + products.get(position).getSize());
        holder.name2.setText(Double.toString(products.get(position).getPrice()) + "₽");
        holder.name3.setText("В 100г продукта: энергетическая ценность - " + products.get(position).getEnergy_value() + "ККал, " + "белки - " + products.get(position).getProteins() + "г, " + "жиры - " + products.get(position).getFats() + "г, " + "углеводы - " + products.get(position).getCarbohydrates() + "г");
    }

    @Override
    public int getItemCount() {
        return products.size();
    }
}