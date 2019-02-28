package com.example.ass1;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.ass1.data.Course;

import java.util.ArrayList;

public class CourseAdapter extends RecyclerView.Adapter<CourseAdapter.CourseViewHolder> /*implements Filterable*/ {

    private  ArrayList<Course> mCourseList;
    private  ArrayList<Course> fCourseList;
    private final LayoutInflater mInflater;

    public CourseAdapter(Context context, ArrayList<Course> mCourseList) {

        this.mCourseList = mCourseList;
        this.fCourseList = new ArrayList<>();
        for(Course c: mCourseList){
            fCourseList.add(new Course(c));
        }


        this.mInflater = LayoutInflater.from(context);;

    }



    @NonNull
    @Override
    public CourseViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View mItemView = mInflater.inflate(R.layout.row_layout, viewGroup, false);
        return new CourseViewHolder(mItemView, this);

    }

    @Override
    public void onBindViewHolder(@NonNull CourseViewHolder courseViewHolder, int i) {
        courseViewHolder.tvCourseName.setText(mCourseList.get(i).getName());
        courseViewHolder.tvNum.setText(String.valueOf(mCourseList.get(i).getProgress()));
        courseViewHolder.pbBar.setProgress((int) Math.round(mCourseList.get(i).getProgress()),true);
    }

    @Override
    public int getItemCount() {
        return mCourseList.size();
    }

    // For filtering
//    @Override
//    public Filter getFilter() {
//        return myFilter;
//    }
//    private Filter myFilter = new Filter() {
//        @Override
//        protected FilterResults performFiltering(CharSequence constraint) {
//           ArrayList <Course> filteredList = new ArrayList<>();
//           if (constraint == null || constraint.length() == 0)  {
//               filteredList.addAll(fCourseList);
//           }
//           else {
//               String filterPattern = constraint.toString().toLowerCase().trim();
//               for(Course c : fCourseList)  {
//                   if(c.getName().toLowerCase().contains(filterPattern))    {
//                       filteredList.add(c);
//                   }
//               }
//           }
//            FilterResults results = new FilterResults();
//            results.values = filteredList;
//           return results;
//        }
//
//        @Override
//        protected void publishResults(CharSequence constraint, FilterResults results) {
//            mCourseList.clear();
//            mCourseList.addAll((ArrayList)results.values);
//            notifyDataSetChanged();
//        }
//    };
    // View holder
    public class CourseViewHolder extends RecyclerView.ViewHolder {

        final CourseAdapter mAdapter;
        final TextView tvCourseName;
        final TextView tvNum;
        final ProgressBar pbBar;

        public CourseViewHolder(@NonNull View itemView,CourseAdapter mAdapter) {
            super(itemView);
            this.mAdapter = mAdapter;
            tvCourseName = itemView.findViewById(R.id.tvCourseName);
            tvNum = itemView.findViewById(R.id.tvNum);
            pbBar = itemView.findViewById(R.id.pbBar);

        }


    }

}
