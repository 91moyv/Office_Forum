package com.example.office_forum;


import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import static com.example.office_forum.PostHelper.Excel_NAME;
import static com.example.office_forum.PostHelper.Post_FROM;


/**
 * A simple {@link Fragment} subclass.
 */
public class ExcelFragment extends Fragment {
    private ListView mPost_List;
    PostHelper mPostHelper;
    SQLiteDatabase db;
    Cursor Post_cur;
    SimpleCursorAdapter mPost_adapter;


    public ExcelFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       View view= inflater.inflate(R.layout.fragment_excel, container, false);
        mPost_List=view.findViewById(R.id.post_excle_list);
        mPostHelper=new PostHelper(getActivity());
        db=mPostHelper.getWritableDatabase();

        Post_cur=db.rawQuery("SELECT*FROM "+Excel_NAME+" ORDER BY _id DESC",null);
        mPost_adapter=new SimpleCursorAdapter(getActivity(),
                R.layout.home_list_item,Post_cur,
                Post_FROM,
                new int[]{R.id.post_title,R.id.post_content,},0);
        mPost_List.setAdapter(mPost_adapter);

        mPost_List.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });
        requery();
       return view;
    }

    private void requery(){
        Post_cur=db.rawQuery("SELECT*FROM "+Excel_NAME+" ORDER BY _id DESC",null);
        mPost_adapter.changeCursor(Post_cur);

    }

}
