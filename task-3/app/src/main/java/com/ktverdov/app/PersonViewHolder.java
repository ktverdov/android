package com.ktverdov.app;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

class PersonViewHolder extends RecyclerView.ViewHolder {
    private TextView personName;
    private ImageView personImage;
    private long id;

    PersonViewHolder(final View itemView) {
        super(itemView);
        LinearLayout personItemView = itemView.findViewById(R.id.person_item);
        personName = itemView.findViewById(R.id.person_name);
        personImage = itemView.findViewById(R.id.person_image);
        personItemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                final Context context = v.getContext();
                context.startActivity(ProfileActivity.getIntent(context, id));
            }
        });
    }

    void bind(final Person person) {
        personName.setText(person.getName());
        personImage.setImageResource(person.getImageRes());
        id = person.getId();
    }
}