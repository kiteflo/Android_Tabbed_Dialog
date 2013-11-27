package com.jooik.tabbeddialog.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jooik.tabbeddialog.R;

/**
 * A simple counterpart for tab1 layout...
 */
public class Fragment_Tab_1 extends Fragment
{
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        return inflater.inflate(R.layout.fragment_tab_1, container, false);
    }
}