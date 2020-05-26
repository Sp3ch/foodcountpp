package ru.krivorukie.foodcountpp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class ListOfProducts extends RecyclerView.Adapter<ListOfProducts.ListHolder> {
    ArrayList<UserJSONSerializer.Product> products;

    public ListOfProducts (ArrayList<UserJSONSerializer.Product> products) {
        this.products = products;
    }

    public class ListHolder extends RecyclerView.ViewHolder {
        TextView name;
        public ListHolder (View view) {
            super(view);
            name = (TextView) view.findViewById(R.id.name);
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
        holder.name.setText(products.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return products.size();
    }
}
