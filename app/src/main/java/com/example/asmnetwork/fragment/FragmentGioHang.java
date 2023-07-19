package com.example.asmnetwork.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import datdvph44632.fpoly.duan1_appbanhang_dinhvandat.Model.GioHangItem;
import datdvph44632.fpoly.duan1_appbanhang_dinhvandat.R;
import datdvph44632.fpoly.duan1_appbanhang_dinhvandat.adapter.CartAdapter;
import datdvph44632.fpoly.duan1_appbanhang_dinhvandat.database.GioHangDAO;


public class FragmentGioHang extends Fragment {

    private RecyclerView recyclerView;
    private CartAdapter cartAdapter;
    private List<GioHangItem> gioHangItems;
    public FragmentGioHang() {
        // Required empty public constructor
    }

    public static FragmentGioHang newInstance() {
        FragmentGioHang fragment = new FragmentGioHang();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_gio_hang, container, false);

        recyclerView = view.findViewById(R.id.cartRecyclerView);
        gioHangItems = new ArrayList<>();
        cartAdapter = new CartAdapter(getContext(), gioHangItems);
        recyclerView.setAdapter(cartAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        // Lấy danh sách sản phẩm trong giỏ hàng từ database
        GioHangDAO gioHangDAO = new GioHangDAO(getContext());
        gioHangItems = gioHangDAO.getAllProductsInCart();
        cartAdapter.updateCartItems(gioHangItems);


        return view;
    }
}