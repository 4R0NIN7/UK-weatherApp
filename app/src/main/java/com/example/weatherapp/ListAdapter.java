package com.example.weatherapp;

import android.app.Activity;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Klasa adapteraz do listView
 */
public class ListAdapter extends ArrayAdapter<String> {

    private final Activity context;
    private final ArrayList<String> city;
    private final ArrayList<String> weather;
    private final ArrayList<Double> maxTemp;
    private final String cityWithLowestAvgTemperature;

    /**
     * @param context context aplikacji (stan)
     * @param city lista miast
     * @param weather lista z informacjami o pogdzie
     * @param maxTemp lista z max temperatura
     * @param cityWithLowestTemperature miasto z najmniejsza srednia temperatura
     */
    public ListAdapter(Activity context, ArrayList<String> city, ArrayList<String> weather, ArrayList<Double> maxTemp, String cityWithLowestTemperature) {
        super(context, R.layout.cities_list_element, city);
        this.context = context;
        this.city = city;
        this.weather = weather;
        this.maxTemp = maxTemp;
        this.cityWithLowestAvgTemperature = cityWithLowestTemperature;
    }


    /**
     * @param position pozycja w listVIew
     * @param view widok
     * @param parent view group
     * @return
     */
    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView = inflater.inflate(R.layout.cities_list_element, null,true);

        TextView cityText = (TextView) rowView.findViewById(R.id.city);
        ImageView weatherImage = (ImageView) rowView.findViewById(R.id.weather_icon);
        TextView weatherText = (TextView) rowView.findViewById(R.id.weather);
        TextView maxText = (TextView) rowView.findViewById(R.id.max_temp);

        cityText.setText(city.get(position));
        if(cityWithLowestAvgTemperature.equals(cityText.getText().toString())) {
            cityText.setTextColor(Color.RED);
        }
        weatherText.setText(weather.get(position));
        maxText.setText(maxTemp.get(position).toString());

        String s = weatherText.getText().toString();
        switch (s) {
            case "rainy":
                weatherImage.setImageResource(R.mipmap.ic_rainy);
                Log.d("AdapterTest", "rainy" + weather.get(position));
                break;
            case "sunny":
                weatherImage.setImageResource(R.mipmap.ic_sunny);
                Log.d("AdapterTest", "sunny" + weather.get(position));
                break;
            case "cloudy":
                weatherImage.setImageResource(R.mipmap.ic_cloudy);
                Log.d("AdapterTest", "Cloudy" + weather.get(position));
                break;
        }

        return rowView;

    };
}
