package com.example.office_forum.Fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.example.office_forum.R;
import com.example.office_forum.Activity.SkillActivity;


/**
 * A simple {@link Fragment} subclass.
 */
public class SkillFragment extends Fragment {

    private View mButton;

    public SkillFragment() {
        // Required empty public constructor

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
      View view= inflater.inflate(R.layout.fragment_skill, container, false);
        mButton=view.findViewById(R.id.view_word_skill);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), SkillActivity.class));
            }
        });

        return view;
    }





    }











