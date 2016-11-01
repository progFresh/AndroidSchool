package net.nosorog.project_777;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class ItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    TextView descriptionTextView;
    ImageView pictureImageView;
    private TapListener tapListener;

    public ItemViewHolder(View itemView, TapListener tapListener) {
        super(itemView);
        itemView.setOnClickListener(this);
        this.tapListener = tapListener;

        descriptionTextView = (TextView) itemView.findViewById(R.id.description_text_view);
        pictureImageView = (ImageView) itemView.findViewById(R.id.picture_text_view);
    }

    @Override
    public void onClick(View view) {
        tapListener.onTap(getAdapterPosition());
    }
}
