package com.example.facerecognizer.model;

import android.util.Log;

import com.example.facerecognizer.database.DbUser;
import com.google.firebase.ml.vision.common.FirebaseVisionPoint;

import java.util.List;

public class Pengenalan {

    //1
    public static int jarakMataKananKeHidung(List<FirebaseVisionPoint> mataKanan, List<FirebaseVisionPoint> hidung, String nim) {
        float hidungX = hidung.get(1).getX();
        float hidungY = hidung.get(1).getY();

        float mataKananX = mataKanan.get(0).getX();
        float mataKananY = mataKanan.get(0).getY();

        double perhitungan1 = (hidungX - mataKananX) * (hidungX - mataKananX) + (hidungY - mataKananY) * (hidungY - mataKananY);
        int mataKananKeHidung = (int) Math.sqrt(perhitungan1);
        int output = 0;

        if (mataKananKeHidung >= 80 && mataKananKeHidung < 120) {
            output = mataKananKeHidung / 3;
        } else if (mataKananKeHidung >= 120) {
            output = mataKananKeHidung / 5;
        } else {
            output = mataKananKeHidung / 2;
        }
        Log.i("Output", String.valueOf(output));

        List<User> users = DbUser.users(nim);
        int hasil = 0;
        for (User user : users) {
            int persentase = (int) (0.6 * user.getMataKananKeHidung());
            Log.i("Output1", String.valueOf(persentase));
            if (output > persentase) {
                hasil = 0;
            } else if (persentase > output || persentase == output) {
                hasil = 1;
            }
        }

        return hasil;
    }

    //2
    public static int jarakMataKiriKeHidung(List<FirebaseVisionPoint> mataKiri, List<FirebaseVisionPoint> hidung, String nim) {
        float hidungX = hidung.get(1).getX();
        float hidungY = hidung.get(1).getY();

        float mataKiriX = mataKiri.get(0).getX();
        float mataKiriY = mataKiri.get(0).getY();

        double perhitungan = ((mataKiriX - hidungX) * (mataKiriX - hidungX)) + ((mataKiriY - hidungY) * (mataKiriY - hidungY));
        int mataKiriKeHidung = (int) Math.sqrt(perhitungan);
        int output = 0;

        if (mataKiriKeHidung >= 80 && mataKiriKeHidung < 120) {
            output = mataKiriKeHidung / 3;
        } else if (mataKiriKeHidung >= 120) {
            output = mataKiriKeHidung / 5;
        } else {
            output = mataKiriKeHidung;
        }

        Log.i("Output", String.valueOf(output));

        List<User> users = DbUser.users(nim);
        int hasil = 0;

        for (User user : users) {
            int persentase = (int) (0.6 * user.getMataKiriKeHidung());
            Log.i("Output1", String.valueOf(persentase));
            if (output > persentase) {
                hasil = 0;
            } else if (persentase > output || persentase == output) {
                hasil = 1;
            }
        }

        return hasil;
    }

    //3
    public static int lebarHidungBawah(List<FirebaseVisionPoint> hidungBawah, String nim) {
        int output = 0;
        int hasil = 0;
        int hasilReturn = 0;
        for (int i = 0; i < 2; i++) {
            double perhitungan = ((hidungBawah.get(i).getX() - hidungBawah.get(i + 1).getX()) * (hidungBawah.get(i).getX() - hidungBawah.get(i + 1).getX())) + ((hidungBawah.get(i).getY() - hidungBawah.get(i + 1).getY()) * (hidungBawah.get(i).getY() - hidungBawah.get(i + 1).getY()));
            int lebaHidungBawah = (int) Math.sqrt(perhitungan);

            if (i == 0) {
                output = lebaHidungBawah;
            } else {
                output += lebaHidungBawah;
            }
            Log.i("Output" + i, String.valueOf(lebaHidungBawah));
        }

        if (output >= 80 && output < 120) {
            hasil = output / 3;
        } else if (output >= 120) {
            hasil = output / 5;
        } else {
            hasil = output;
        }

        List<User> users = DbUser.users(nim);

        for (User user : users) {
            int persentase = (int) (0.6 * user.getLebarHidungBawah());
            if (hasil > persentase) {
                hasilReturn = 0;
            } else if (persentase > hasil || persentase == hasil) {
                hasilReturn = 1;
            }
        }

        return hasilReturn;
    }

    //4
    public static int panjangHidung(List<FirebaseVisionPoint> hidung, String nim) {

        int output = 0;
        int hasil;
        int hasilReturn = 0;
        for (int i = 0; i < 1; i++) {
            double perhitungan = ((hidung.get(i).getX() - hidung.get(i + 1).getX()) * (hidung.get(i).getX() - hidung.get(i + 1).getX())) + ((hidung.get(i).getY() - hidung.get(i + 1).getY()) * (hidung.get(i).getY() - hidung.get(i + 1).getY()));
            int panjangHidung = (int) Math.sqrt(perhitungan);
            output = panjangHidung;
        }

        if (output >= 80 && output < 120) {
            hasil = output / 3;
        } else if (output >= 120){
            hasil = output/5;
        } else {
            hasil = output;
        }

        List<User> users = DbUser.users(nim);

        for (User user : users) {
            int persentase = (int) (0.6 * user.getPanjangHidung());
            if (hasil > persentase) {
                hasilReturn = 0;
            } else if (persentase > hasil || persentase == hasil) {
                hasilReturn = 1;
            }
        }

        return hasilReturn;
    }

}
