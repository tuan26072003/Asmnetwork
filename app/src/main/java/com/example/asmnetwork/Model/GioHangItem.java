package com.example.asmnetwork.Model;

import java.util.Arrays;

public class GioHangItem {
    private int id;
    private String tenSanPham;
    private double giaSanPham;
    private byte[] imageSanPham;

    public GioHangItem() {
    }

    public GioHangItem(int id, String tenSanPham, double giaSanPham, byte[] imageSanPham) {
        this.id = id;
        this.tenSanPham = tenSanPham;
        this.giaSanPham = giaSanPham;
        this.imageSanPham = imageSanPham;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTenSanPham() {
        return tenSanPham;
    }

    public void setTenSanPham(String tenSanPham) {
        this.tenSanPham = tenSanPham;
    }

    public double getGiaSanPham() {
        return giaSanPham;
    }

    public void setGiaSanPham(double giaSanPham) {
        this.giaSanPham = giaSanPham;
    }

    public byte[] getImageSanPham() {
        return imageSanPham;
    }

    public void setImageSanPham(byte[] imageSanPham) {
        this.imageSanPham = imageSanPham;
    }

    @Override
    public String toString() {
        return "GioHangItem{" +
                "id=" + id +
                ", tenSanPham='" + tenSanPham + '\'' +
                ", giaSanPham=" + giaSanPham +
                ", imageSanPham=" + Arrays.toString(imageSanPham) +
                '}';
    }
}




