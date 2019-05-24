package com.example.facerecognizer;

import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.facerecognizer.database.DbUser;
import com.example.facerecognizer.helper.GraphicOverlay;
import com.example.facerecognizer.helper.RectOverlay;
import com.example.facerecognizer.model.Wajah;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.ml.vision.FirebaseVision;
import com.google.firebase.ml.vision.common.FirebaseVisionImage;
import com.google.firebase.ml.vision.common.FirebaseVisionPoint;
import com.google.firebase.ml.vision.face.FirebaseVisionFace;
import com.google.firebase.ml.vision.face.FirebaseVisionFaceContour;
import com.google.firebase.ml.vision.face.FirebaseVisionFaceDetector;
import com.google.firebase.ml.vision.face.FirebaseVisionFaceDetectorOptions;
import com.wonderkiln.camerakit.CameraKitError;
import com.wonderkiln.camerakit.CameraKitEvent;
import com.wonderkiln.camerakit.CameraKitEventListener;
import com.wonderkiln.camerakit.CameraKitImage;
import com.wonderkiln.camerakit.CameraKitVideo;
import com.wonderkiln.camerakit.CameraView;

import java.util.List;

import dmax.dialog.SpotsDialog;
import jpegkit.JpegImageView;

public class DeteksiActivity extends AppCompatActivity {

    private CameraView cameraView;
    private Button register;
    private JpegImageView jpegView;
    private GraphicOverlay graphicOverlay;

    AlertDialog alertDialog;

    @Override
    protected void onResume() {
        super.onResume();
        cameraView.start();
    }

    @Override
    protected void onPause() {
        super.onPause();
        cameraView.stop();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deteksi);

        cameraView = findViewById(R.id.camera_view);
        register = findViewById(R.id.btn_detect);
        graphicOverlay = findViewById(R.id.graphic_overlay);

        alertDialog = new SpotsDialog.Builder().setContext(this)
                .setMessage("Plesae Wait ...")
                .setCancelable(false)
                .build();


        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cameraView.start();
                cameraView.captureImage();
                graphicOverlay.clear();
            }
        });

        cameraView.addCameraKitListener(new CameraKitEventListener() {
            @Override
            public void onEvent(CameraKitEvent cameraKitEvent) {

            }

            @Override
            public void onError(CameraKitError cameraKitError) {

            }

            @Override
            public void onImage(CameraKitImage cameraKitImage) {
                alertDialog.show();
                Bitmap bitmap = cameraKitImage.getBitmap();
                bitmap = Bitmap.createScaledBitmap(bitmap, cameraView.getWidth(), cameraView.getHeight(), false);
                cameraView.stop();


                faceDetct(bitmap);
            }

            @Override
            public void onVideo(CameraKitVideo cameraKitVideo) {

            }
        });

    }

    private void faceDetct(final Bitmap bitmap) {
        FirebaseVisionImage image = FirebaseVisionImage.fromBitmap(bitmap);
        FirebaseVisionFaceDetectorOptions options =
                new FirebaseVisionFaceDetectorOptions.Builder()
                        .setPerformanceMode(FirebaseVisionFaceDetectorOptions.ACCURATE)
                        .setLandmarkMode(FirebaseVisionFaceDetectorOptions.ALL_LANDMARKS)
                        .setContourMode(FirebaseVisionFaceDetectorOptions.ALL_CONTOURS)
                        .setClassificationMode(FirebaseVisionFaceDetectorOptions.ALL_CLASSIFICATIONS)
                        .enableTracking()
                        .build();

        FirebaseVisionFaceDetector detector = FirebaseVision.getInstance()
                .getVisionFaceDetector(options);

        detector.detectInImage(image)
                .addOnSuccessListener(new OnSuccessListener<List<FirebaseVisionFace>>() {
                    @Override
                    public void onSuccess(List<FirebaseVisionFace> firebaseVisionFaces) {
                        prosesPendeteksi(firebaseVisionFaces, bitmap);
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(DeteksiActivity.this, "" + e.getMessage(), Toast.LENGTH_SHORT).show();
                        alertDialog.dismiss();
                    }
                });

    }

    private void prosesPendeteksi(List<FirebaseVisionFace> firebaseVisionFaces, Bitmap bitmap) {
        int jumlah = 0;

        for (FirebaseVisionFace face : firebaseVisionFaces) {
            Rect bounds = face.getBoundingBox();
            RectOverlay rectOverlay = new RectOverlay(graphicOverlay, bounds);

            graphicOverlay.add(rectOverlay);

            tambahWajah(face);

            jumlah++;
        }

        if (jumlah != 0) {
            Toast.makeText(this, "Jumlah wajah: " + jumlah, Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Wajah tidak terdeteksi", Toast.LENGTH_SHORT).show();
        }

        alertDialog.dismiss();
    }

    private void tambahWajah(FirebaseVisionFace face) {
        float rotY = face.getHeadEulerAngleY();
        int rotasiY = (int) rotY;

        List<FirebaseVisionPoint> hidung = face.getContour(FirebaseVisionFaceContour.NOSE_BRIDGE).getPoints();
        List<FirebaseVisionPoint> bibir = face.getContour(FirebaseVisionFaceContour.UPPER_LIP_TOP).getPoints();
        List<FirebaseVisionPoint> bibirBawah = face.getContour(FirebaseVisionFaceContour.LOWER_LIP_BOTTOM).getPoints();
        List<FirebaseVisionPoint> mataKanan = face.getContour(FirebaseVisionFaceContour.RIGHT_EYE).getPoints();
        List<FirebaseVisionPoint> mataKiri = face.getContour(FirebaseVisionFaceContour.LEFT_EYE).getPoints();
        List<FirebaseVisionPoint> hidungBawah = face.getContour(FirebaseVisionFaceContour.NOSE_BOTTOM).getPoints();
        List<FirebaseVisionPoint> alisKiri = face.getContour(FirebaseVisionFaceContour.LEFT_EYEBROW_BOTTOM).getPoints();
        List<FirebaseVisionPoint> alisKanan = face.getContour(FirebaseVisionFaceContour.RIGHT_EYEBROW_BOTTOM).getPoints();

        float hidungX = hidung.get(1).getX();
        float hidungY = hidung.get(1).getY();

        float mataKananX = mataKanan.get(0).getX();
        float mataKananY = mataKanan.get(0).getY();

        float mataKiriX = mataKiri.get(8).getX();
        float mataKiriY = mataKiri.get(8).getY();

        int jarakMataKananKeHidung = Wajah.jarakMataKananKeHidung(hidungX, hidungY, mataKananX, mataKananY);
        int lebarHidungBawah = Wajah.lebarHidungBawah(hidungBawah);
        int jarakMataKiriKeHidung = Wajah.jarakMataKiriKeHidung(hidungX, hidungY, mataKiriX, mataKiriY);
        int panjangHidung = Wajah.panjangHidung(hidung);
        int jarakMataKiriKeBibir = Wajah.jaraMataKiriKeBibir(mataKiri, bibir);
        int jarakHidungBawahKeBibirKiri = Wajah.jarakHidungBawahKeBibirKiri(hidungBawah, bibir);
        int jarakHidungBawahKeBibirKanan = Wajah.jarakHidungBawahKeBibirKanan(hidungBawah, bibir);
        int jarakBibirAtasKiriKeBibirBawah = Wajah.jarakBibirAtasKiriKeBibirBawah(bibir, bibirBawah);
        int jarakBibirAtasKananKeBibirBawah = Wajah.jarakBibirAtasKananKeBibirBawah(bibir, bibirBawah);
        int jaraMataKananKeBibir = Wajah.jaraMataKananKeBibir(mataKanan, bibir);
        int ukuranMataKiri = Wajah.ukuranMataKiri(mataKiri);
        int jarakMataKiriKeAlis = Wajah.jarakMataKiriKeAlis1(mataKiri, alisKiri);
        int panjangAlisKiri = Wajah.panjangAlisKiri(alisKiri);
        int panjangAlisKanan = Wajah.panjangALisKanan(alisKanan);
        int jarakMataKananKeALis = Wajah.jarakMataKananKeAlis(mataKanan, alisKanan);
        int ukuranMataKanan = Wajah.ukuranMataKanan(mataKanan);

        String nama = getIntent().getStringExtra("nama");
        String nim = getIntent().getStringExtra("nim");

        DbUser.insertUser(nama, nim, jarakMataKananKeHidung, jarakMataKiriKeHidung, lebarHidungBawah, panjangHidung,
                jarakMataKiriKeBibir, jarakHidungBawahKeBibirKiri, jarakHidungBawahKeBibirKanan, jarakBibirAtasKiriKeBibirBawah,
                jarakBibirAtasKananKeBibirBawah, jaraMataKananKeBibir, ukuranMataKiri, jarakMataKiriKeAlis, panjangAlisKiri, panjangAlisKanan,
                jarakMataKananKeALis, ukuranMataKanan);
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();

    }
}
