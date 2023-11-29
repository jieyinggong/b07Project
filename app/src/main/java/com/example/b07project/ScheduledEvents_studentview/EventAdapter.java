package com.example.b07project.ScheduledEvents_studentview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.b07project.R;
import com.example.b07project.main.Event;
import com.example.b07project.main.Information;

import java.util.List;

public class EventAdapter extends ArrayAdapter<Event> {
    public EventAdapter(Context context, int resource, List<Event> items) {
        super(context, resource, items);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;

        if (view == null) {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            view = inflater.inflate(R.layout.item_image_and_text, parent, false);
        }

        Event event = getItem(position);

        if (event != null) {
            TextView subjectText = view.findViewById(R.id.itemTextView);
            ImageView imageView = view.findViewById(R.id.itemImageView);

            if (event.eventInProgress()) {
                imageView.setImageResource(R.drawable.in_progress_icon);
            } else if (event.eventHasEnded()) {
                imageView.setImageResource(R.drawable.end_icon);
            } else {
                imageView.setImageResource(R.drawable.event_icon);
            }

            if (subjectText != null && event.subject != null) {
                subjectText.setText(event.subject);
            }
        }
        return view;
    }
}

