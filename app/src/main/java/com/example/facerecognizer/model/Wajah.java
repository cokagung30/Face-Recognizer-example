package com.example.facerecognizer.model;

import android.util.Log;

import com.google.firebase.ml.vision.common.FirebaseVisionPoint;

import java.util.List;

public class Wajah {

    public static int jarakMataKananKeHidung(float hidungX, float hidungY, float mataKananX, float mataKananY) {
        int hasil = 0;
        double perhitungan1 = ((hidungX - mataKananX) * (hidungX - mataKananX)) + ((hidungY - mataKananY) * (hidungY - mataKananY));
        int mataKananKeHidung = (int) Math.sqrt(perhitungan1);
        Log.i("Hasil", String.valueOf(mataKananKeHidung));
        if (mataKananKeHidung > 100 && mataKananKeHidung < 120) {
            hasil = mataKananKeHidung / 2;
        } else if (mataKananKeHidung >= 120) {
            hasil = mataKananKeHidung / 4;
        } else {
            hasil = mataKananKeHidung;
        }

        return hasil;
    }

    public static int jarakMataKiriKeHidung(float hidungX, float hidungY, float mataKiriX, float mataKiriY) {
        int hasil = 0;
        double perhitungan1 = (hidungX - mataKiriX) * (hidungX - mataKiriX) + (hidungY - mataKiriY) * (hidungY - mataKiriY);
        int mataKiriKeHidung = (int) Math.sqrt(perhitungan1);
        if (mataKiriKeHidung > 100 && mataKiriKeHidung < 120) {
            hasil = mataKiriKeHidung / 2;
        } else if (mataKiriKeHidung >= 120) {
            hasil = mataKiriKeHidung / 4;
        } else {
            hasil = mataKiriKeHidung;
        }

        return hasil;
    }

    public static int lebarHidungBawah(List<FirebaseVisionPoint> bawahHidung) {
        int hasil = 0;

        float titik0X = bawahHidung.get(0).getX();
        float titik0Y = bawahHidung.get(0).getY();

        float titik1X = bawahHidung.get(1).getX();
        float titik1Y = bawahHidung.get(1).getY();

        float titik2X = bawahHidung.get(2).getX();
        float titik2Y = bawahHidung.get(2).getY();

        double perhitungan1 = ((titik0X - titik1X) * (titik0X - titik1X)) + ((titik0Y - titik1Y) * (titik0Y - titik1Y));
        int titik0KeTitik1 = (int) Math.sqrt(perhitungan1);

        double perhitungan2 = ((titik1X - titik2X) * (titik1X - titik2X)) + ((titik1Y - titik2Y) * (titik1Y - titik2Y));
        int titik1KeTitik2 = (int) Math.sqrt(perhitungan2);

        int jarak = titik0KeTitik1 + titik1KeTitik2;

        Log.i("Lebar Hidung", String.valueOf(jarak));

        if (jarak > 100 && jarak < 120) {
            hasil = jarak / 2;
        } else if (jarak >= 120) {
            hasil = jarak / 4;
        } else {
            hasil = jarak;
        }

        return hasil;
    }

    public static int panjangHidung(List<FirebaseVisionPoint> hidung) {
        int hasil = 0;
        float hidungAtasX = hidung.get(0).getX();
        float hidungAtasY = hidung.get(0).getY();

        float hidungBawahX = hidung.get(1).getX();
        float hidungBawahY = hidung.get(1).getY();

        double perhitungan = ((hidungAtasX - hidungBawahX) * (hidungAtasX - hidungBawahX)) + ((hidungAtasY - hidungBawahY) * (hidungAtasY - hidungBawahY));
        int panjangHidung = (int) Math.sqrt(perhitungan);

        if (panjangHidung > 100 && panjangHidung < 120) {
            hasil = panjangHidung / 2;
        } else if (panjangHidung >= 120) {
            hasil = panjangHidung / 4;
        } else {
            hasil = panjangHidung;
        }

        return hasil;
    }

    public static int jaraMataKiriKeBibir(List<FirebaseVisionPoint> mataKiri, List<FirebaseVisionPoint> bibir) {

        float mataKiriX = mataKiri.get(12).getX();
        float mataKiriY = mataKiri.get(12).getY();

        float bibirX = bibir.get(0).getX();
        float bibirY = bibir.get(0).getY();

        double perhitungan = ((mataKiriX - bibirX) * (mataKiriX - bibirX)) + ((mataKiriY - bibirY) * (mataKiriY - bibirY));
        int jarakMataKiriKeBibir = (int) Math.sqrt(perhitungan);
        int hasil = 0;

        if (jarakMataKiriKeBibir > 100 && jarakMataKiriKeBibir < 120) {
            hasil = jarakMataKiriKeBibir / 2;
        } else if (jarakMataKiriKeBibir >= 120) {
            hasil = jarakMataKiriKeBibir / 4;
        } else {
            hasil = jarakMataKiriKeBibir;
        }

        return hasil;
    }

    public static int jarakHidungBawahKeBibirKiri(List<FirebaseVisionPoint> bawahHidung, List<FirebaseVisionPoint> bibir) {
        float titik0X = bawahHidung.get(0).getX();
        float titik0Y = bawahHidung.get(0).getY();

        float bibirX = bibir.get(0).getX();
        float bibirY = bibir.get(0).getY();

        double perhitungan = ((titik0X - bibirX) * (titik0X - bibirX)) + ((titik0Y - bibirY) * (titik0Y - bibirY));
        int jarakHidungBawahKeBibir = (int) Math.sqrt(perhitungan);
        int hasil = 0;

        if (jarakHidungBawahKeBibir > 100 && jarakHidungBawahKeBibir < 120) {
            hasil = jarakHidungBawahKeBibir / 2;
        } else if (jarakHidungBawahKeBibir >= 120) {
            hasil = jarakHidungBawahKeBibir / 4;
        } else {
            hasil = jarakHidungBawahKeBibir;
        }

        return hasil;
    }

    public static int jarakHidungBawahKeBibirKanan(List<FirebaseVisionPoint> bawahHidung, List<FirebaseVisionPoint> bibir) {
        float titik0X = bawahHidung.get(2).getX();
        float titik0Y = bawahHidung.get(2).getY();

        float bibirX = bibir.get(10).getX();
        float bibirY = bibir.get(10).getY();

        double perhitungan = ((titik0X - bibirX) * (titik0X - bibirX)) + ((titik0Y - bibirY) * (titik0Y - bibirY));
        int jarakHidungBawahKeBibir = (int) Math.sqrt(perhitungan);
        int hasil = 0;

        if (jarakHidungBawahKeBibir > 100 && jarakHidungBawahKeBibir < 120) {
            hasil = jarakHidungBawahKeBibir / 2;
        } else if (jarakHidungBawahKeBibir >= 120) {
            hasil = jarakHidungBawahKeBibir / 4;
        } else {
            hasil = jarakHidungBawahKeBibir;
        }

        return hasil;
    }

    public static int jarakBibirAtasKiriKeBibirBawah(List<FirebaseVisionPoint> bibirAtas, List<FirebaseVisionPoint> bibirBawah) {
        float atasX = bibirAtas.get(0).getX();
        float atasY = bibirAtas.get(0).getY();

        float bawahX = bibirBawah.get(4).getX();
        float bawahY = bibirBawah.get(4).getY();

        double perhitungan = ((atasX - bawahX) * (atasX - bawahX)) + ((atasY - bawahY) * (atasY - bawahY));
        int jarakBibirAtasKiriKeBibirBawah = (int) Math.sqrt(perhitungan);
        int hasil = 0;

        if (jarakBibirAtasKiriKeBibirBawah > 100 && jarakBibirAtasKiriKeBibirBawah < 120) {
            hasil = jarakBibirAtasKiriKeBibirBawah / 2;
        } else if (jarakBibirAtasKiriKeBibirBawah >= 120) {
            hasil = jarakBibirAtasKiriKeBibirBawah / 4;
        } else {
            hasil = jarakBibirAtasKiriKeBibirBawah;
        }

        return hasil;
    }

    public static int jarakBibirAtasKananKeBibirBawah(List<FirebaseVisionPoint> bibirAtas, List<FirebaseVisionPoint> bibirBawah) {
        float atasX = bibirAtas.get(10).getX();
        float atasY = bibirAtas.get(10).getY();

        float bawahX = bibirBawah.get(4).getX();
        float bawahY = bibirBawah.get(4).getY();

        double perhitungan = ((atasX - bawahX) * (atasX - bawahX)) + ((atasY - bawahY) * (atasY - bawahY));
        int jarakBibirAtasKananKeBibirBawah = (int) Math.sqrt(perhitungan);
        int hasil = 0;

        if (jarakBibirAtasKananKeBibirBawah > 100 && jarakBibirAtasKananKeBibirBawah < 120) {
            hasil = jarakBibirAtasKananKeBibirBawah / 2;
        } else if (jarakBibirAtasKananKeBibirBawah >= 120) {
            hasil = jarakBibirAtasKananKeBibirBawah / 4;
        } else {
            hasil = jarakBibirAtasKananKeBibirBawah;
        }

        return hasil;
    }

    public static int jaraMataKananKeBibir(List<FirebaseVisionPoint> mataKanan, List<FirebaseVisionPoint> bibir) {

        float mataKiriX = mataKanan.get(12).getX();
        float mataKiriY = mataKanan.get(12).getY();

        float bibirX = bibir.get(0).getX();
        float bibirY = bibir.get(0).getY();

        double perhitungan = ((mataKiriX - bibirX) * (mataKiriX - bibirX)) + ((mataKiriY - bibirY) * (mataKiriY - bibirY));
        int jaraMataKananKeBibir = (int) Math.sqrt(perhitungan);
        int hasil = 0;

        if (jaraMataKananKeBibir > 100 && jaraMataKananKeBibir < 120) {
            hasil = jaraMataKananKeBibir / 2;
        } else if (jaraMataKananKeBibir >= 120) {
            hasil = jaraMataKananKeBibir / 4;
        } else {
            hasil = jaraMataKananKeBibir;
        }

        return hasil;
    }

    public static int ukuranMataKiri(List<FirebaseVisionPoint> mataKiri){
        int output = 0;
        for (int i = 0; i < 15; i++){
            double perhitungan = ((mataKiri.get(i).getX() - mataKiri.get(i+1).getX())*(mataKiri.get(i).getX() - mataKiri.get(i+1).getX())) + ((mataKiri.get(i).getY() - mataKiri.get(i+1).getY())*(mataKiri.get(i).getY() - mataKiri.get(i+1).getY()));
            int ukuranMataKanan = (int) Math.sqrt(perhitungan);

            if (i == 0){
                output = ukuranMataKanan;
            } else {
                output += ukuranMataKanan;
            }

            Log.i("Ukuran Mata Kiri"+i, String.valueOf(ukuranMataKanan));
        }

        int hasil = 0;
        if (output > 100 && output < 120) {
            hasil = output / 2;
        } else if (output >= 120) {
            hasil = output / 4;
        } else {
            hasil = output;
        }

        return hasil;
    }

    public static int jarakMataKiriKeAlis1(List<FirebaseVisionPoint> mataKiri, List<FirebaseVisionPoint> alisKiri) {
        float mataKiriX = mataKiri.get(8).getX();
        float mataKiriY = mataKiri.get(8).getY();

        float alisKiriX = alisKiri.get(4).getX();
        float alisKiriY = alisKiri.get(4).getY();

        double perhitungan = ((mataKiriX - alisKiriX) * (mataKiriX - alisKiriX)) + ((mataKiriY - alisKiriY) * (mataKiriY - alisKiriY));
        int jarakMataKiriKeAlis1 = (int) Math.sqrt(perhitungan);
        int hasil = 0;

        if (jarakMataKiriKeAlis1 > 100 && jarakMataKiriKeAlis1 < 120) {
            hasil = jarakMataKiriKeAlis1 / 2;
        } else if (jarakMataKiriKeAlis1 >= 120) {
            hasil = jarakMataKiriKeAlis1 / 4;
        } else {
            hasil = jarakMataKiriKeAlis1;
        }

        return hasil;

    }

    public static int panjangAlisKiri(List<FirebaseVisionPoint> alisKiri){
        int output = 0;
        for (int i = 0; i < 4; i++){
            double perhitungan = ((alisKiri.get(i).getX() - alisKiri.get(i+1).getX())*(alisKiri.get(i).getX() - alisKiri.get(i+1).getX())) + ((alisKiri.get(i).getY() - alisKiri.get(i+1).getY())*(alisKiri.get(i).getY()- alisKiri.get(i+1).getY()));
            int panjangAlisKiri = (int) Math.sqrt(perhitungan);
            if (i == 0){
                output = panjangAlisKiri;
            } else {
                output += panjangAlisKiri;
            }
            Log.i("Panjang Alis", String.valueOf(panjangAlisKiri));
        }

        int hasil = 0;
        if (output > 100 && output < 120) {
            hasil = output / 2;
        } else if (output >= 120) {
            hasil = output / 4;
        } else {
            hasil = output;
        }

        return hasil;
    }

    public static int panjangALisKanan(List<FirebaseVisionPoint> alisKanan){
        int output = 0;
        for (int i = 0; i < 4; i++){
            double perhitungan = ((alisKanan.get(i).getX() - alisKanan.get(i+1).getX())*(alisKanan.get(i).getX() - alisKanan.get(i+1).getX())) + ((alisKanan.get(i).getY() - alisKanan.get(i+1).getY())*(alisKanan.get(i).getY()- alisKanan.get(i+1).getY()));
            int panjangAlisKanan = (int) Math.sqrt(perhitungan);
            if (i == 0){
                output = panjangAlisKanan;
            } else {
                output += panjangAlisKanan;
            }
            Log.i("Panjang Alis Kanan", String.valueOf(panjangAlisKanan));
        }

        int hasil = 0;
        if (output > 100 && output < 120) {
            hasil = output / 2;
        } else if (output >= 120) {
            hasil = output / 4;
        } else {
            hasil = output;
        }

        return hasil;
    }

    public static int jarakMataKananKeAlis(List<FirebaseVisionPoint> mataKanan, List<FirebaseVisionPoint> alisKanan) {
        float mataKananX = mataKanan.get(0).getX();
        float mataKananY = mataKanan.get(0).getY();

        float alisKananX = alisKanan.get(4).getX();
        float alisKananY = alisKanan.get(4).getY();

        double perhitungan = ((mataKananX - alisKananX) * (mataKananX - alisKananX)) + ((mataKananY - alisKananY) * (mataKananY - alisKananY));
        int jarakMataKananKeAlis = (int) Math.sqrt(perhitungan);
        int hasil = 0;

        if (jarakMataKananKeAlis > 100 && jarakMataKananKeAlis < 120) {
            hasil = jarakMataKananKeAlis / 2;
        } else if (jarakMataKananKeAlis >= 120) {
            hasil = jarakMataKananKeAlis / 4;
        } else {
            hasil = jarakMataKananKeAlis;
        }

        return hasil;
    }

    public static int ukuranMataKanan(List<FirebaseVisionPoint> mataKanan){
        int output = 0;
        for (int i = 0; i < 15; i++){
            double perhitungan = ((mataKanan.get(i).getX() - mataKanan.get(i+1).getX())*(mataKanan.get(i).getX() - mataKanan.get(i+1).getX())) + ((mataKanan.get(i).getY() - mataKanan.get(i+1).getY())*(mataKanan.get(i).getY() - mataKanan.get(i+1).getY()));
            int ukuranMataKanan = (int) Math.sqrt(perhitungan);

            if (i == 0){
                output = ukuranMataKanan;
            } else {
                output += ukuranMataKanan;
            }

            Log.i("Ukuran Mata Kanan"+i, String.valueOf(ukuranMataKanan));
        }

        int hasil = 0;
        if (output > 100 && output < 120) {
            hasil = output / 2;
        } else if (output >= 120) {
            hasil = output / 4;
        } else {
            hasil = output;
        }

        return hasil;
    }
}
