package com.example.facerecognizer.database;

import com.example.facerecognizer.model.User;

import java.util.List;

public class DbUser {

    public static void insertUser(String nama, String nim, int mataKananKeHidung, int mataKiriKeHidung, int lebarHidungBawah,
                                  int panjangHidung, int mataKiriKeBibir, int mataKananKeBibir, int hidungBawahKeBibirKiri,
                                  int hidungBawahKeBibirKanan, int bibirAtasKananKeBibirBawah, int bibirAtasKiriKeBibirBawah,
                                  int ukuranMataKiri, int mataKiriKeAlis, int panjangAlisKiri, int panjangAlisKanan, int mataKananKeAlis,
                                  int ukuranMataKanan){
        User user = new User(nama, nim, mataKananKeHidung, mataKiriKeHidung, lebarHidungBawah, panjangHidung, mataKiriKeBibir, mataKananKeBibir,
                hidungBawahKeBibirKiri, hidungBawahKeBibirKanan, bibirAtasKananKeBibirBawah, bibirAtasKiriKeBibirBawah, ukuranMataKiri, mataKiriKeAlis,
                panjangAlisKiri, panjangAlisKanan, mataKananKeAlis, ukuranMataKanan);
        user.save();
    }

    public static List<User> users (String nim){
        return User.findWithQuery(User.class, "SELECT * FROM USER WHERE NIM ="+nim);
    }

}
