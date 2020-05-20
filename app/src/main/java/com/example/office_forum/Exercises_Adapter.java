package com.example.office_forum;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.office_forum.Exercises_Data;
import com.example.office_forum.Exercises_PPWindow;
import com.example.office_forum.Post_Date.PS_Post_Data;
import com.example.office_forum.R;

import java.util.ArrayList;
import java.util.List;

public class Exercises_Adapter extends BaseAdapter {
  public static int Sum;
    private Context context ;
    private List<Exercises_Data> list;
    public static List<String> answer=new ArrayList<>();

    public Exercises_Adapter(Context context,List<Exercises_Data> list){
        this.context=context;
        this.list=list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(context);
        convertView = inflater.inflate(R.layout.exercises_item, null);
        TextView title=convertView.findViewById(R.id.ex_title);
        RadioGroup radioGroup=convertView.findViewById(R.id.radio_option);
        final RadioButton opA=convertView.findViewById(R.id.op_a);
        RadioButton opB=convertView.findViewById(R.id.op_b);
        RadioButton opC=convertView.findViewById(R.id.op_c);
        RadioButton opD=convertView.findViewById(R.id.op_d);
        title.setText(list.get(position).getTitle());
        opA.setText(list.get(position).getOptionA());
        opB.setText(list.get(position).getOptionB());
        opC.setText(list.get(position).getOptionC());
        opD.setText(list.get(position).getOptionD());
        for(int i=0;i<10;i++){
            answer.add(" ");
        }
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(checkedId==R.id.op_a){
                    answer.set(position,"A");
                }

                if(checkedId==R.id.op_b){
                    answer.set(position,"B");
                }
                if(checkedId==R.id.op_c){
                    answer.set(position,"C");
                }
                if(checkedId==R.id.op_d){
                    answer.set(position,"D");
                }
            }
        });

        return convertView;



    }
}
