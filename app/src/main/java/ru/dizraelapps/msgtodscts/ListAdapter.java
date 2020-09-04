package ru.dizraelapps.msgtodscts;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.graphics.Color;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.textview.MaterialTextView;
import com.squareup.picasso.Picasso;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import ru.dizraelapps.msgtodscts.weather.Current;
import ru.dizraelapps.msgtodscts.weather.Daily;
import ru.dizraelapps.msgtodscts.weather.Weather_;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder> {
    private List<Daily> data;

    public ListAdapter(List<Daily> data, Activity activity){
        this.data = data;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_layout, parent, false);
        return new ViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {

        //Получаем данные из data
        Long dt = (data.get(position).getDt()) * 1000L;
        Double tempMaxDay = data.get(position).getTemp().getMax();
        Double tempNight = data.get(position).getTemp().getNight();

        Weather_ weather_ = data.get(position).getWeather().get(0);
        String iconWeather = weather_.getIcon();

        // Заполнение элементов холдера
        holder.bind(dt, tempMaxDay, tempNight, iconWeather);

        holder.itemView.setBackgroundColor(Color.GRAY);
        if (position == 0){
            holder.getTvDay().setText("Today");
        } else if(position == 1){
            holder.getTvDay().setText("Tomorrow");
        }

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
            tvTempDay = itemView.findViewById(R.id.li_textView_temp_day);
            tvTempNight = itemView.findViewById(R.id.li_textView_temp_night);
            ivWeatherIcon = itemView.findViewById(R.id.li_icon_weather);
        }

        @SuppressLint("SetTextI18n")
        private void bind(Long date, Double tempMaxDay, Double tempNight, String iconWeather) {

            @SuppressLint("SimpleDateFormat") DateFormat dfWeekDay = new SimpleDateFormat("EEEE");
            @SuppressLint("SimpleDateFormat") DateFormat dfDate = new SimpleDateFormat("dd MMMM");

            String dateFormat = dfDate.format(date);
            String weekDayFormat = dfWeekDay.format(date);
            String temperatureDay = String.valueOf(Math.round(tempMaxDay));
            String temperatureNight = String.valueOf(Math.round(tempNight));


            tvDay.setText(weekDayFormat);
            tvData.setText(dateFormat);
            tvTempDay.setText("+" + temperatureDay + "°");
            tvTempNight.setText("+" + temperatureNight + "°");


            String iconUrl = "http://openweathermap.org/img/wn/" + iconWeather + "@2x.png";
            Uri iconUri = Uri.parse(iconUrl);
            Picasso.get()
                    .load(iconUri)
                    .into(ivWeatherIcon);
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
