package com.example.islam.travelbird.weather.view;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.example.islam.travelbird.R;
import com.example.islam.travelbird.models.Forecast;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by islam on 8/19/16.
 */
public class WeatherAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final int TYPE_HEADER = 0;
    private static final int TYPE_ITEM = 1;
    List<Forecast> forecasts;
    Context context;

    public WeatherAdapter(Context context,List<Forecast> forecasts) {
        this.forecasts = forecasts;
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_ITEM) {
            View item = LayoutInflater.from(parent.getContext()).inflate(R.layout.weather_item, parent, false);
            return new VHItem(item);
        } else if (viewType == TYPE_HEADER) {
            View titleView = LayoutInflater.from(parent.getContext()).inflate(R.layout.weather_list_title, parent, false);
            return new VHHeader(titleView);
        }
        throw new RuntimeException("there is no type that matches the type " + viewType + " + make sure your using types correctly");
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof VHItem) {
            Forecast forecast = forecasts.get(position - 1);
            ((VHItem) holder).maxTemp.setText("High " + forecast.getHigh() + " F");
            ((VHItem) holder).minTemp.setText("Low " + forecast.getLow() + " F");
            ((VHItem) holder).date.setText(forecast.getDate());
        } else
        {
            //static header so no data will be here
        }
    }

    @Override
    public int getItemCount() {
        return forecasts.size()
                + 1;
    }

    @Override
    public int getItemViewType(int position) {
        if (isPositionHeader(position))
            return TYPE_HEADER;

        return TYPE_ITEM;
    }

    private boolean isPositionHeader(int position) {
        return position == 0;
    }


    class VHItem extends RecyclerView.ViewHolder {
        @Bind(R.id.max_temp_textview)
        TextView maxTemp;
        @Bind(R.id.min_temp_textview)
        TextView minTemp;
        @Bind(R.id.weather_imageview)
        ImageView weatherImage;
        @Bind(R.id.date_textview)
        TextView date;

        public VHItem(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    class VHHeader extends RecyclerView.ViewHolder {
        @Bind(R.id.title_textview)
        TextView title;

        public VHHeader(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

}