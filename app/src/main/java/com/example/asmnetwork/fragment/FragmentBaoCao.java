package com.example.asmnetwork.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import datdvph44632.fpoly.duan1_appbanhang_dinhvandat.R;


public class FragmentBaoCao extends Fragment {


    public FragmentBaoCao() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_bao_cao, container, false);
    }
}