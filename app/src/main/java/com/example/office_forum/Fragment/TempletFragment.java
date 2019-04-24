package com.example.office_forum.Fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.office_forum.Activity.PPT_TemplateActivity;
import com.example.office_forum.Activity.PS_TemplateActivity;
import com.example.office_forum.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class TempletFragment extends Fragment {
    View mPPT_Template;
    View mPS_Template;
    public TempletFragment() {
        // Required empty public constructor

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_templet, container, false);
        mPPT_Template=view.findViewById(R.id.ppt_templet);
        mPS_Template=view.findViewById(R.id.ps_templet);
        mPPT_Template.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), PPT_TemplateActivity.class);
                startActivity(intent);
            }
        });
        mPS_Template.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), PS_TemplateActivity.class));
            }
        });
        return view;
    }

}
