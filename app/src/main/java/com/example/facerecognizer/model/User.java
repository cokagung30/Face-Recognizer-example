package com.example.facerecognizer.model;

import com.orm.SugarRecord;

public class User extends SugarRecord {

    private String nama, nim;
    private int mataKananKeHidung, mataKiriKeHidung;
    private int lebarHidungBawah, panjangHidung, mataKiriKeBibir, mataKananKeBibir;
    private int hidungBawahKeBibirKiri, hidungBawahKeBibirKanan, bibirAtasKananKeBibirBawah, bibirAtasKiriKeBibirBawah;
    private int ukuranMataKiri, mataKiriKeAlis, panjangAlisKiri, panjangAlisKanan, mataKananKeAlis, ukuranMataKanan;

    public User() {
    }

    public User(String nama, String nim, int mataKananKeHidung, int mataKiriKeHidung, int lebarHidungBawah, int panjangHidung, int mataKiriKeBibir, int mataKananKeBibir, int hidungBawahKeBibirKiri, int hidungBawahKeBibirKanan, int bibirAtasKananKeBibirBawah, int bibirAtasKiriKeBibirBawah, int ukuranMataKiri, int mataKiriKeAlis, int panjangAlisKiri, int panjangAlisKanan, int mataKananKeAlis, int ukuranMataKanan) {
        this.nama = nama;
        this.nim = nim;
        this.mataKananKeHidung = mataKananKeHidung;
        this.mataKiriKeHidung = mataKiriKeHidung;
        this.lebarHidungBawah = lebarHidungBawah;
        this.panjangHidung = panjangHidung;
        this.mataKiriKeBibir = mataKiriKeBibir;
        this.mataKananKeBibir = mataKananKeBibir;
        this.hidungBawahKeBibirKiri = hidungBawahKeBibirKiri;
        this.hidungBawahKeBibirKanan = hidungBawahKeBibirKanan;
        this.bibirAtasKananKeBibirBawah = bibirAtasKananKeBibirBawah;
        this.bibirAtasKiriKeBibirBawah = bibirAtasKiriKeBibirBawah;
        this.ukuranMataKiri = ukuranMataKiri;
        this.mataKiriKeAlis = mataKiriKeAlis;
        this.panjangAlisKiri = panjangAlisKiri;
        this.panjangAlisKanan = panjangAlisKanan;
        this.mataKananKeAlis = mataKananKeAlis;
        this.ukuranMataKanan = ukuranMataKanan;
    }

    public String getNama() {
        return nama;
    }

    public String getNim() {
        return nim;
    }

    public int getMataKananKeHidung() {
        return mataKananKeHidung;
    }

    public int getMataKiriKeHidung() {
        return mataKiriKeHidung;
    }

    public int getLebarHidungBawah() {
        return lebarHidungBawah;
    }

    public int getPanjangHidung() {
        return panjangHidung;
    }

    public int getMataKiriKeBibir() {
        return mataKiriKeBibir;
    }

    public int getMataKananKeBibir() {
        return mataKananKeBibir;
    }

    public int getHidungBawahKeBibirKiri() {
        return hidungBawahKeBibirKiri;
    }

    public int getHidungBawahKeBibirKanan() {
        return hidungBawahKeBibirKanan;
    }

    public int getBibirAtasKananKeBibirBawah() {
        return bibirAtasKananKeBibirBawah;
    }

    public int getBibirAtasKiriKeBibirBawah() {
        return bibirAtasKiriKeBibirBawah;
    }

    public int getUkuranMataKiri() {
        return ukuranMataKiri;
    }

    public int getMataKiriKeAlis() {
        return mataKiriKeAlis;
    }

    public int getPanjangAlisKiri() {
        return panjangAlisKiri;
    }

    public int getPanjangAlisKanan() {
        return panjangAlisKanan;
    }

    public int getMataKananKeAlis() {
        return mataKananKeAlis;
    }

    public int getUkuranMataKanan() {
        return ukuranMataKanan;
    }
}
