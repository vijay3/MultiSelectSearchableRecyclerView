package com.vijayhiremath.multiselectsearchablerecyclerview;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.widget.EditText;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity
{
    Context mContext;
    MultiSelectAdapter multi_select_adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RecyclerView rl_list = (RecyclerView) findViewById(R.id.rl_labtest);
        EditText     et_search = (EditText)   findViewById(R.id.et_search);

        ArrayList<DummyModel> labTestNameList = new ArrayList<>();
        labTestNameList.add( new DummyModel( 1  , "David De Gea"       , false ) );
        labTestNameList.add( new DummyModel( 2  , "Antonio Valencia"   , false ) );
        labTestNameList.add( new DummyModel( 3  , "Eric Baily"         , false ) );
        labTestNameList.add( new DummyModel( 4  , "Chris Smalling"     , false ) );
        labTestNameList.add( new DummyModel( 5  , "Daley Blind"        , false ) );
        labTestNameList.add( new DummyModel( 6  , "Ander Harera"       , false ) );
        labTestNameList.add( new DummyModel( 7  , "Paul Pogba"         , false ) );
        labTestNameList.add( new DummyModel( 8  , "Jesse Lingard"      , false ) );
        labTestNameList.add( new DummyModel( 9  , "Juan Mata"          , false ) );
        labTestNameList.add( new DummyModel( 10 , "Marcus Rashford"    , false ) );
        labTestNameList.add( new DummyModel( 11 , "Zlatan Ibrahimovic" , false ) );
        labTestNameList.add( new DummyModel( 12 , "Wayne Rooney"       , false ) );
        labTestNameList.add( new DummyModel( 13 , "Anthony Martial"    , false ) );
        labTestNameList.add(new DummyModel(  14 , "Memphis Depay"      , false ) );
        labTestNameList.add(new DummyModel(  15 , "Phil Jones"         , false ) );



        final LinearLayoutManager mLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rl_list.setLayoutManager(mLayoutManager);
        multi_select_adapter = new MultiSelectAdapter(labTestNameList, this);
        rl_list.setAdapter(multi_select_adapter);

        et_search.addTextChangedListener(new TextWatcher()
        {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after)
            {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count)
            {
                if (TextUtils.isEmpty(s))
                {
                    multi_select_adapter.getFilter().filter("");
                } else
                {
                    multi_select_adapter.getFilter().filter(s.toString());
                }
            }

            @Override
            public void afterTextChanged(Editable s)
            {

            }
        });


        multi_select_adapter.setOnItemClickListener(new MultiSelectAdapter.OptionClickListener()
        {
            @Override
            public void onItemClick(int positions, boolean isChecked)
            {
                if(isChecked)
                {
                    multi_select_adapter.addThisItem( positions);
                }
                else
                {
                    multi_select_adapter.removeThisItem( positions);
                }
            }
        });
    }
}
