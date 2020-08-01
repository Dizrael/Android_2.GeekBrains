package ru.dizraelapps.msgtodscts;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.textview.MaterialTextView;

import java.util.Date;
import java.util.List;

import ru.dizraelapps.msgtodscts.weather.Current;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder> {
    private List<String> data;
    private Activity activity;

    public ListAdapter(List<String> data, Activity activity){
        this.data = data;
        this.activity = activity;
    }

    //region Переопределение методов адаптера
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {

        // Заполнение элементов холдера
        fillHolderItems(holder, position);

    }

    private void fillHolderItems(@NonNull ViewHolder holder, int position) {
        MaterialTextView tvDay = holder.getTvDay();
        MaterialTextView tvData = holder.getTvData();
        MaterialTextView tvTempDay = holder.getTvTempDay();
        MaterialTextView tvTempNight = holder.getTvTempNight();


        tvDay.setText(String.valueOf(data.get(position)));
        tvData.setText(String.valueOf(data.get(position)));
        tvTempDay.setText(String.valueOf(data.get(position)));
        tvTempNight.setText(String.valueOf(data.get(position)));
    }

    @Override
    public int getItemCount() {
        return data == null ? 0 : data.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        private MaterialTextView tvDay;
        private MaterialTextView tvData;
        private MaterialTextView tvTempDay;
        private MaterialTextView tvTempNight;
        private ImageView ivWeatherIcon;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvDay = itemView.findViewById(R.id.li_textView_day);
            tvData = itemView.findViewById(R.id.li_textView_data);
            tvTempDay = itemView.findViewById(R.id.li_textView_day);
            tvTempNight = itemView.findViewById(R.id.li_textView_temp_night);
            ivWeatherIcon = itemView.findViewById(R.id.li_icon_weather);
        }

        public MaterialTextView getTvDay() {
            return tvDay;
        }

        public MaterialTextView getTvData() {
            return tvData;
        }

        public MaterialTextView getTvTempDay() {
            return tvTempDay;
        }

        public MaterialTextView getTvTempNight() {
            return tvTempNight;
        }

        public ImageView getIvWeatherIcon() {
            return ivWeatherIcon;
        }
    }

}
