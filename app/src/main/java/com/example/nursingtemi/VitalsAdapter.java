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


public class VitalsAdapter extends ArrayAdapter<Record> implements Filterable {

    private List<Record> records;
    private Context context;
    private List<Record> allRecords;
    private Filter filter;



    public VitalsAdapter(Context context, List<Record> data) {
        super(context, R.layout.list_header_vital, data);
        this.allRecords = new ArrayList<>(data);
        this.records = data;


//        for (String s : objects) {
//            Record temp = new Record(s);
//            allRecords.add(temp);
//            records.add(temp);
//        }

        this.context = context;
    }

    @Override
    public int getCount() {
        return records.size();
    }

    @Override
    public Record getItem(int position) {
        return records.get(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item_vital, parent, false);
        }

        Record record = getItem(position);

        TextView patientName = convertView.findViewById(R.id.patientName);
        TextView heartRate = convertView.findViewById(R.id.heartRate);
        TextView temperature = convertView.findViewById(R.id.temperature);
        TextView bloodPressure = convertView.findViewById(R.id.bloodPressure);
        TextView oxygenSaturation = convertView.findViewById(R.id.oxySat);

        patientName.setText(record.getPatientName());
        heartRate.setText(record.getHeartRate());
        temperature.setText(record.getTemp());
        bloodPressure.setText(record.getBloodPressure());
        oxygenSaturation.setText(record.getOxySat());

        return convertView;
    }

    @Override
    public Filter getFilter() {
        if (filter == null) {
            filter = new Filter() {
                @Override
                protected FilterResults performFiltering(CharSequence constraint) {
                    FilterResults results = new FilterResults();
                    if (constraint == null || constraint.length() == 0) {
                        results.values = new ArrayList<>(allRecords);
                        results.count = allRecords.size();
                    } else {
                        List<Record> filterResultsData = new ArrayList<>();
                        for (Record record : allRecords) {
                            if (record.getPatientName().toLowerCase().contains(constraint.toString().toLowerCase())) {
                                filterResultsData.add(record);
                            }
                        }
                        results.values = filterResultsData;
                        results.count = filterResultsData.size();
                    }
                    return results;
                }

                @Override
                protected void publishResults(CharSequence constraint, FilterResults results) {
                    records = (List<Record>) results.values;
                    notifyDataSetChanged();
                }
            };
        }
        return filter;
    }

    public void resetData() {
        records = new ArrayList<>(allRecords);
        notifyDataSetChanged();
    }

//    @Override
//    public Filter getFilter() {
//        if (filter == null) {
//            filter = new Filter() {
//                @Override
//                protected FilterResults performFiltering(CharSequence constraint) {
//                    FilterResults results = new FilterResults();
//                    if (constraint == null || constraint.length() == 0) {
//                        results.values = allRecords;
//                        results.count = allRecords.size();
//                    } else {
//                        List<Record> filterResultsData = new ArrayList<>();
//                        for (Record record : allRecords) {
//                            if (record.getPatientName().toLowerCase().contains(constraint.toString().toLowerCase())) {
//                                filterResultsData.add(record);
//                            }
//                        }
//                        results.values = filterResultsData;
//                        results.count = filterResultsData.size();
//                    }
//                    return results;
//                }
//
//                @Override
//                protected void publishResults(CharSequence constraint, FilterResults results) {
//                    results = (FilterResults) results.values;
//                    notifyDataSetChanged();
//                }
//            };
//        }
//        return filter;
//    }
//
//    public void resetData() {
//        records = new ArrayList<>(allRecords);
//        notifyDataSetChanged();
//    }
//
//    @Override
//    public View getView(int position, View convertView, ViewGroup parent) {
//        if (position < 0 || position >= records.size()) {
//            return new View(context); // Return an empty view or handle appropriately
//        }
//
//        // Check if an existing view is being reused, otherwise inflate the view
//        if (convertView == null) {
//            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item_vital, parent, false);
//        }
//
//
//
//        // Get data item for this position
//        Record record = records.get(position);
//        if (record == null) {
//            return new View(context); // Handle null record
//        }
//
//        // Populates data into template view using data object
//        TextView patientNameView = convertView.findViewById(R.id.patientName);
//        if (patientNameView == null) {
//            Log.e("VitalsAdapter", "patientNameView is null");
//            return convertView;
//        }
//        TextView heartRateView = convertView.findViewById(R.id.heartRate);
//        TextView tempView = convertView.findViewById(R.id.temperature);
//        TextView bpView = convertView.findViewById(R.id.bloodPressure);
//        TextView oxySatView = convertView.findViewById(R.id.oxySat);
//
//        patientNameView.setText(record.getPatientName());
//        heartRateView.setText(record.getHeartRate());
//        tempView.setText(record.getTemp());
//        bpView.setText(record.getBloodPressure());
//        oxySatView.setText(record.getOxySat());
//
//        return convertView;
//    }

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

