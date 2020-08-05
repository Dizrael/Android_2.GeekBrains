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
    private Activity activity;

    public ListAdapter(List<Daily> data, Activity activity){
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
        holder.bind(holder, position );

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
        private void bind(@NonNull ViewHolder holder, int position) {

            @SuppressLint("SimpleDateFormat") DateFormat dfWeekDay = new SimpleDateFormat("EEEE");
            @SuppressLint("SimpleDateFormat") DateFormat dfDate = new SimpleDateFormat("dd MMMM");

            //Получаем наши вью из Холдера
            MaterialTextView tvDay = holder.getTvDay();
            MaterialTextView tvData = holder.getTvData();
            MaterialTextView tvTempDay = holder.getTvTempDay();
            MaterialTextView tvTempNight = holder.getTvTempNight();
            ImageView ivWeatherIcon = holder.getIvWeatherIcon();

            Long dt = (data.get(position).getDt()) * 1000L;
            String dateFormat = dfDate.format(dt);
            String weekDayFormat = dfWeekDay.format(dt);
            String temperatureDay = String.valueOf(Math.round(data.get(position).getTemp().getMax()));
            String temperatureNight = String.valueOf(Math.round(data.get(position).getTemp().getNight()));

            Log.d("DATEEEEEEE", String.valueOf(dt));
            Log.d("DATEEEEEEE", dateFormat);
            Log.d("DATEEEEEEE", weekDayFormat);


            tvDay.setText(weekDayFormat);
            tvData.setText(dateFormat);
            tvTempDay.setText("+" + temperatureDay + "°");
            tvTempNight.setText("+" + temperatureNight + "°");

            Weather_ weather_ = data.get(position).getWeather().get(0);

            String iconUrl = "http://openweathermap.org/img/wn/" + weather_.
                    getIcon() + "@2x.png";
            Uri iconUri = Uri.parse(iconUrl);
            Picasso.get()
                    .load(iconUri)
                    .into(ivWeatherIcon);

            itemView.setBackgroundColor(Color.GRAY);
            if (position == 0){
                tvDay.setText("Today");
            } else if(position == 1){
                tvDay.setText("Tomorrow");
            }
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
