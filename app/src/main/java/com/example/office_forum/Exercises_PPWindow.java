package com.example.office_forum;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.example.office_forum.Post_Date.Examine_Post_Data;

import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

import static com.example.office_forum.Exercises_Adapter.Sum;
import static com.example.office_forum.Exercises_Adapter.answer;

public class Exercises_PPWindow extends PopupWindow {
    private  Context context;
    private TextView mGrade;
    private ListView mExercises_List;
    public static int grade=0;
    private Button mSubmit;
    private View mView;
    public Exercises_PPWindow(Context context){
        super(context);
        this.context = context;
        setBackgroundDrawable(null);
        Initialize_Layout();

    }


    private void Initialize_Layout(){
        LayoutInflater inflater = LayoutInflater.from(context);
        View view=inflater.inflate(R.layout.exercises_ppwindow,null);
        mExercises_List=view.findViewById(R.id.exercise_list);
        mSubmit=view.findViewById(R.id.bt_submit);
        mGrade=view.findViewById(R.id.exercise_grade);
        mView=view.findViewById(R.id.exercises_top_view);
        mGrade.setVisibility(mGrade.GONE);
        query();

        mSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                BmobQuery<Exercises_Data> query = new BmobQuery<>();
                query.order("createdAt");
                query.findObjects(new FindListener<Exercises_Data>() {  //按行查询，查到的数据放到List<Goods>的集合
                    @Override
                    public void done(List<Exercises_Data> list, BmobException e) {
                        if (e == null){
                                 int sum = 0;
                            //System.out.println("查询成功"+list.get(0).getName()+list.get(0).getPrice()+list.get(0).getDesc());
                            for(int i = 0;i<10;i++){
                                if(list.get(i).getAnswer().equals(answer.get(i))){
                                    sum++;
                                }
                            }
                            mView.setVisibility(mView.GONE);
                            mExercises_List.setVisibility(mExercises_List.GONE);
                            mGrade.setText("你的分数为:"+sum*10+"分"+"\n点击重新练习");
                            mGrade.setVisibility(mGrade.VISIBLE);
                        }

                    }
                });
            }
        });
        mGrade.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mView.setVisibility(mView.VISIBLE);
                mGrade.setVisibility(mGrade.GONE);
                mExercises_List.setVisibility(mExercises_List.VISIBLE);
            }
        });
        setContentView(view);
        initWindow();

    }
    private void initWindow() {
        DisplayMetrics d = context.getResources().getDisplayMetrics();
        /* this.setWidth((int) (d.widthPixels * 0.6));*/
        this.setWidth(ViewGroup.LayoutParams.WRAP_CONTENT);
        this.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        this.setFocusable(true);
        this.setOutsideTouchable(true);
        this.update();



    }


    public void showAtBottom(View view) {
        //弹窗位置设置
        showAtLocation(view, Gravity.TOP | Gravity.CENTER, 10, 500);
        //showAtLocation(view, Gravity.TOP | Gravity.CENTER, 10, 1000);
        //showAtLocation(view, Gravity.TOP | Gravity.RIGHT, 10, 110);//有偏差
        //showAsDropDown(view, Math.abs((view.getWidth() - getWidth()) / 2), 0);

    }


    private void query(){
        BmobQuery<Exercises_Data> query = new BmobQuery<>();
        query.order("createdAt");
        query.findObjects(new FindListener<Exercises_Data>() {  //按行查询，查到的数据放到List<Goods>的集合
            @Override
            public void done(List<Exercises_Data> list, BmobException e) {
                if (e == null){

                    //System.out.println("查询成功"+list.get(0).getName()+list.get(0).getPrice()+list.get(0).getDesc());
                    mExercises_List.setAdapter(new Exercises_Adapter(context, list));

                }

            }
        });

    }




}
