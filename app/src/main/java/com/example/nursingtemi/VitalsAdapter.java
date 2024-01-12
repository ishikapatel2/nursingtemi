package com.example.nursingtemi;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


public class VitalsAdapter extends ArrayAdapter<String> implements Filterable {

    private List<Record> records;
    private Context context;
    private List<String> objects;
    private List<Record> allRecords;


    public VitalsAdapter(Context context, List<String> objects) {
        super(context, 0, objects);
        this.records = new ArrayList<>();
        this.allRecords = new ArrayList<>();
        this.objects = objects;


        for (String s : objects) {
            Record temp = new Record(s);
            allRecords.add(temp);
            records.add(temp);
        }

        this.context = context;
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                FilterResults results = new FilterResults();
                if (constraint == null || constraint.length() == 0) {
                    results.values = records;
                    results.count = records.size();
                } else {
                    List<Record> filteredList = new ArrayList<>();
                    String filterPattern = constraint.toString().toLowerCase().trim();

                    for (Record record : records) {
                        if (record.getPatientName().toLowerCase().contains(filterPattern)) {
                            filteredList.add(record);
                        }
                    }

                    results.values = filteredList;
                    results.count = filteredList.size();
                }
                return results;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                records = (ArrayList<Record>) results.values;
                notifyDataSetChanged();
            }
        };
    }

    public void resetData() {
        records.clear();
        records.addAll(allRecords);
        notifyDataSetChanged();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (position < 0 || position >= records.size()) {
            return new View(context); // Return an empty view or handle appropriately
        }

        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item_vital, parent, false);
        }



        // Get data item for this position
        Record record = records.get(position);
        if (record == null) {
            return new View(context); // Handle null record
        }

        // Populates data into template view using data object
        TextView patientNameView = convertView.findViewById(R.id.patientName);
        if (patientNameView == null) {
            Log.e("VitalsAdapter", "patientNameView is null");
            return convertView;
        }
        //TextView patientNameView = convertView.findViewById(R.id.patientName);
        TextView heartRateView = convertView.findViewById(R.id.heartRate);
        TextView tempView = convertView.findViewById(R.id.temperature);
        TextView bpView = convertView.findViewById(R.id.bloodPressure);
        TextView oxySatView = convertView.findViewById(R.id.oxySat);

        patientNameView.setText(record.getPatientName());
        heartRateView.setText(record.getHeartRate());
        tempView.setText(record.getTemp());
        bpView.setText(record.getBloodPressure());
        oxySatView.setText(record.getOxySat());

        return convertView;
    }

    public void removeItem(int position) {

        // Get the record at position
        Record r = allRecords.get(position);

        Log.d("VitalsAdapter", "Deleting this patient: " + r.getPatientName() + " " + position);

        // Remove the item from the database
        DatabaseHelper dbHelper = DatabaseHelper.getInstance(context);
        dbHelper.deleteRecord(r);
        allRecords.remove(position);
        records.remove(r);
        //objects.remove(position);
        notifyDataSetChanged();
    }
}

