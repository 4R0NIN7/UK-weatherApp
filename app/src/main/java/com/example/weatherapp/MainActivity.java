package com.example.weatherapp;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;

/**
 * TODO
 * 1. Find the smallest temperature across all cities and print it - OK
 * 2. For each city find its highest temperatures and print the results in format "city: max_temp", - OK
 * 3. Find the city with the smallest average daily temperature and print its name, - OK
 * 4. Show JSON data as a list in the Android app. There is no requirement on how the UI should look.
 * It's up to you what and how you show it. - OK
 */
public class MainActivity extends Activity {

    /**
     * Srednia temperatura
     */
    private double finalAvg = 100;
    /**
     * Minimalna temperatura
     */
    private double finalMin = 100;
    /**
     * Miasto ktore ma najmniejsza srednia temperature
     */
    private String cityWithLowestAvgTemperature = "";

    /**
     * Lista miast
     */
    private ArrayList<String> cities;
    /**
     * Lista z informacjami o pogodzie
     */
    private ArrayList<String> weather;
    /**
     * Lista z informacjami o temperaturze
     */
    private ArrayList<Double> temp;
    /**
     * Lista z informacjami o max temperaturze w miscie
     */
    private ArrayList<Double> maxTemp;

    /**
     * Rozmiar textu na wykresie
     */
    private final int TEXT_SIZE = 12;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        cities = new ArrayList<>();
        weather = new ArrayList<>();
        maxTemp = new ArrayList<>();

        setData();
        ListView citiesListView = (ListView) findViewById(R.id.citiestList);
        ListAdapter listAdapter = new ListAdapter(this, cities, weather,maxTemp, cityWithLowestAvgTemperature);

        LineChart lineChart = (LineChart)findViewById(R.id.temperatureChart);
        lineChart.setBackgroundColor(Color.WHITE);
        lineChart.setTouchEnabled(true);
        lineChart.setDragEnabled(true);
        lineChart.setScaleEnabled(true);
        citiesListView.setAdapter(listAdapter);

        Button buttonShowMinimal = (Button) findViewById(R.id.buttonShowMinimal);
        buttonShowMinimal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(),"The minimal temperature across all cities is: " + finalMin,Toast.LENGTH_LONG).show();
            }
        });

        ArrayList<Entry> temp = getTemp(0);
        LineDataSet dataSet = new LineDataSet(temp, "Temperature");
        dataSet.setColor(Color.BLUE);
        LineData data = new LineData(dataSet);
        data.setValueTextSize(TEXT_SIZE);
        data.setValueTextColor(Color.RED);
        lineChart.setData(data);
        lineChart.invalidate();

        citiesListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i) {
                    case 0: {
                        ArrayList<Entry> temp = getTemp(0);
                        LineDataSet dataSet = new LineDataSet(temp, "Temperature");
                        dataSet.setHighlightEnabled(true);
                        dataSet.setColor(Color.BLUE);
                        LineData data = new LineData(dataSet);
                        data.setValueTextSize(TEXT_SIZE);
                        data.setValueTextColor(Color.RED);
                        lineChart.setData(data);
                        lineChart.invalidate();
                        break;
                    }
                    case 1: {
                        ArrayList<Entry> temp = getTemp(1);
                        LineDataSet dataSet = new LineDataSet(temp, "Temperature");
                        dataSet.setColor(Color.BLUE);
                        LineData data = new LineData(dataSet);
                        data.setValueTextSize(TEXT_SIZE);
                        data.setValueTextColor(Color.MAGENTA);
                        lineChart.setData(data);
                        lineChart.invalidate();
                        break;
                    }
                    case 2: {
                        ArrayList<Entry> temp = getTemp(2);
                        LineDataSet dataSet = new LineDataSet(temp, "Temperature");
                        dataSet.setColor(Color.BLUE);
                        LineData data = new LineData(dataSet);
                        data.setValueTextSize(TEXT_SIZE);
                        data.setValueTextColor(Color.BLUE);
                        lineChart.setData(data);
                        lineChart.invalidate();
                        break;
                    }
                    case 3: {
                        ArrayList<Entry> temp = getTemp(3);
                        LineDataSet dataSet = new LineDataSet(temp, "Temperature");
                        dataSet.setColor(Color.BLUE);
                        LineData data = new LineData(dataSet);
                        data.setValueTextSize(TEXT_SIZE);
                        data.setValueTextColor(Color.BLACK);
                        lineChart.setData(data);
                        lineChart.invalidate();
                        break;
                    }
                }
            }
        });


    }

    /**
     * Zwraca listę z obiektami potrzebynmi do narysowania wykresu
     * @param city - numer miasta
     * @return lista elementów do wykresu
     */
    private ArrayList<Entry> getTemp(int city){
        String jsonString = Data.getJson();
        try{
            JSONArray jsonArray = new JSONArray(jsonString);
            JSONArray temperature = jsonArray.getJSONObject(city).getJSONArray("hourly_temp");
            ArrayList<Entry> entries = new ArrayList<>();
            for(int j=0;j<temperature.length();j++) {
                String temp = temperature.getJSONObject(j).getString("temp");
                float f = Float.parseFloat(temp);
                int hour = temperature.getJSONObject(j).getInt("hour");
                entries.add(new BarEntry(hour,f));
            }
            return entries;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Pobiera dane oraz znajduje wszystkie potrzebne temperatury max_temp, avg_temp i min_temp
     */
    private void setData(){
        String jsonString = Data.getJson();
        try{
            JSONArray jsonArray = new JSONArray(jsonString);
            for(int i=0;i<jsonArray.length();i++){
                JSONArray temperature = jsonArray.getJSONObject(i).getJSONArray("hourly_temp");
                temp = new ArrayList();
                for(int j=0;j<temperature.length();j++){
                    Double d = temperature.getJSONObject(j).getDouble("temp");
                    temp.add(d);
                }
                double max_temp = temp.stream()
                        .mapToDouble((x)->x)
                        .max()
                        .getAsDouble();
                double avg_temp = temp.stream()
                        .mapToDouble((x)->x)
                        .average()
                        .getAsDouble();
                double min_temp = temp.stream()
                        .mapToDouble((x) -> x)
                        .min()
                        .getAsDouble();
                if(finalAvg > avg_temp){
                    finalAvg = avg_temp;
                    cityWithLowestAvgTemperature = jsonArray.getJSONObject(i).getString("city");
                }
                if(finalMin > min_temp){
                    finalMin = min_temp;
                }
                cities.add(jsonArray.getJSONObject(i).getString("city"));
                weather.add(jsonArray.getJSONObject(i).getString("weather"));
                maxTemp.add(max_temp);
                Log.d("DebugTest","For each city find its highest temperatures and print the results in format \"city: max_temp\" : "+jsonArray.getJSONObject(i).getString("city") + ": "+max_temp);

            }
            Log.d("DebugTest","Find the smallest temperature across all cities and print it: "+finalMin);
            Log.d("DebugTest","Find the city with the smallest average daily temperature and print its name: "+ cityWithLowestAvgTemperature);

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}