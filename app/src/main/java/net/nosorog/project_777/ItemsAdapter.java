package net.nosorog.project_777;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.squareup.picasso.Picasso;

public class ItemsAdapter extends RecyclerView.Adapter<ItemViewHolder> {

    private Item[] items;
    private TapListener tapListener;

    public ItemsAdapter(Item[] items, TapListener tapListener) {
        this.items = items;
        this.tapListener = tapListener;
    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.element_of_list, parent, false);
        return new ItemViewHolder(itemView, tapListener);
    }

    @Override
    public void onBindViewHolder(ItemViewHolder holder, int position) {
        Item item = items[position];
        holder.descriptionTextView.setText(item.getFirstName() + " " + item.getLastName());
        Picasso.with(holder.pictureImageView.getContext()).load(item.getPhoto()).into(holder.pictureImageView);
    }

    @Override
    public int getItemCount() {
        return items.length;
    }

    public Item getItem(int position){
        return items[position];
    }
}

