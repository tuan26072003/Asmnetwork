package com.example.asmnetwork.fragment;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.List;

import datdvph44632.fpoly.duan1_appbanhang_dinhvandat.Model.SanPham;
import datdvph44632.fpoly.duan1_appbanhang_dinhvandat.R;
import datdvph44632.fpoly.duan1_appbanhang_dinhvandat.adapter.SanPhamAdapter;
import datdvph44632.fpoly.duan1_appbanhang_dinhvandat.database.SanPhamDAO;


public class FragmentBanHang extends Fragment {
    NavigationView navigationView;
    androidx.appcompat.widget.Toolbar toolbar;
    DrawerLayout drawerLayout;
    EditText edTimKiem;
    ListView lvList;
    Spinner spnLocDanhSach;
    final String[] danhSachLC = {"Theo tên", "Giá ↑", "Giá ↓"};
    ImageView imageView;
    List<SanPham> list;
    SanPhamDAO sanPhamDAO;
    SanPhamAdapter sanPhamAdapter;
    TextView tvNull;
    int soLuong;
    static int tong = 0;
    TextView tvSoLuongBanHang;
    ImageView cart;


    public FragmentBanHang() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_ban_hang, container, false);
        toolbar = view.findViewById(R.id.toolbar_ban_hang);
        imageView = view.findViewById(R.id.imgBanHang);
        navigationView = view.findViewById(R.id.NavigationViewBanHang);
        drawerLayout = view.findViewById(R.id.drawerLayoutBanHang);
        cart = view.findViewById(R.id.cartshoppe);

        cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentGioHang fragmentGioHang = FragmentGioHang.newInstance();
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.container, fragmentGioHang);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();

            }
        });
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent intent = new Intent(getActivity(), DonHangActivity.class);
//                startActivity(intent);
            }
        });
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        anhXaView(view);
        sanPhamDAO = new SanPhamDAO(getActivity());
        list = new ArrayList<>();
        setHasOptionsMenu(true);
        ArrayAdapter adapter = new ArrayAdapter(getActivity(), android.R.layout.simple_spinner_item, danhSachLC);
        spnLocDanhSach.setAdapter(adapter);
        doDuLieuTheoSpinner();
        timKiem();

    }


    private void timKiem() {
        edTimKiem.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                List<SanPham> list = sanPhamDAO.getAllSanPhamTheoMa(edTimKiem.getText().toString());
                SanPhamAdapter sanPhamAdapter = new SanPhamAdapter(getActivity(), list);
                lvList.setAdapter(sanPhamAdapter);
                tvNull.setVisibility(View.INVISIBLE);
                if (edTimKiem.getText().toString().equalsIgnoreCase("")) {
                    doDuLieuTheoSpinner();
                }
                if (list.size() <= 0) {
                    tvNull.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    public void anhXaView(View view) {
        edTimKiem = view.findViewById(R.id.edTimKiemSanPham);
        lvList = view.findViewById(R.id.lvListMatHang);
        spnLocDanhSach = view.findViewById(R.id.spnLocTimKiem);
        tvNull = view.findViewById(R.id.tvNull);
        tvSoLuongBanHang = view.findViewById(R.id.tvSoLuongBanHang);
        cart = view.findViewById(R.id.cartshoppe);
    }

    //    public void doDuLieu(){
//        list = sanPhamDAO.getAllSanPham();
//        sanPhamAdapter = new SanPhamAdapter(getContext(), list);
//        lvList.setAdapter(sanPhamAdapter);
//    }
    public void doDuLieuTheoSpinner() {
        spnLocDanhSach.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (adapterView.getItemAtPosition(i) == adapterView.getItemAtPosition(0)) {
                    list = sanPhamDAO.getAllSanPhamTheoTen();
                } else if (adapterView.getItemAtPosition(i) == adapterView.getItemAtPosition(1)) {
                    list = sanPhamDAO.getAllSanPhamTheoGiaTangDan();
                } else {
                    list = sanPhamDAO.getAllSanPhamTheoGiaGiamDan();
                }
                sanPhamAdapter = new SanPhamAdapter(getContext(), list);
                lvList.setAdapter(sanPhamAdapter);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }
}