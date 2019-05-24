package com.example.facerecognizer;

import android.app.AlertDialog;
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
import com.example.facerecognizer.model.Pengenalan;
import com.example.facerecognizer.model.User;
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

public class PengenalanActivity extends AppCompatActivity {

    private CameraView cameraView;
    private Button pengenalan;
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
        setContentView(R.layout.activity_pengenalan);

        cameraView = findViewById(R.id.cm_pengenalan);
        pengenalan = findViewById(R.id.btn_pengenalan);
        graphicOverlay = findViewById(R.id.graphic_overlay_kenali);
        alertDialog = new SpotsDialog.Builder().setContext(this)
                .setMessage("Plesae Wait ...")
                .setCancelable(false)
                .build();

        pengenalan.setOnClickListener(new View.OnClickListener() {
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

                faceRecognize(bitmap);
            }

            @Override
            public void onVideo(CameraKitVideo cameraKitVideo) {

            }
        });
    }

    private void faceRecognize(Bitmap bitmap) {
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
                        prosesPengenalan(firebaseVisionFaces);
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(PengenalanActivity.this, "" + e.getMessage(), Toast.LENGTH_SHORT).show();
                        alertDialog.dismiss();
                    }
                });

    }

    private void prosesPengenalan(List<FirebaseVisionFace> firebaseVisionFaces) {
        int jumlah = 0;

        for (FirebaseVisionFace face : firebaseVisionFaces) {
            Rect bounds = face.getBoundingBox();
            RectOverlay rectOverlay = new RectOverlay(graphicOverlay, bounds);
            graphicOverlay.add(rectOverlay);

            kenaliWajah(face);

            jumlah++;
        }

        if (jumlah != 0) {
            Toast.makeText(this, "Jumlah wajah: " + jumlah, Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Wajah tidak terdeteksi", Toast.LENGTH_SHORT).show();
        }

        alertDialog.dismiss();
    }

    private void kenaliWajah(FirebaseVisionFace face) {
        float rotY = face.getHeadEulerAngleY();

        List<FirebaseVisionPoint> hidung = face.getContour(FirebaseVisionFaceContour.NOSE_BRIDGE).getPoints();
        List<FirebaseVisionPoint> bibir = face.getContour(FirebaseVisionFaceContour.UPPER_LIP_TOP).getPoints();
        List<FirebaseVisionPoint> bibirBawah = face.getContour(FirebaseVisionFaceContour.LOWER_LIP_BOTTOM).getPoints();
        List<FirebaseVisionPoint> mataKanan = face.getContour(FirebaseVisionFaceContour.RIGHT_EYE).getPoints();
        List<FirebaseVisionPoint> mataKiri = face.getContour(FirebaseVisionFaceContour.LEFT_EYE).getPoints();
        List<FirebaseVisionPoint> hidungBawah = face.getContour(FirebaseVisionFaceContour.NOSE_BOTTOM).getPoints();
        List<FirebaseVisionPoint> alisKiri = face.getContour(FirebaseVisionFaceContour.LEFT_EYEBROW_BOTTOM).getPoints();
        List<FirebaseVisionPoint> alisKanan = face.getContour(FirebaseVisionFaceContour.RIGHT_EYEBROW_BOTTOM).getPoints();

        String nim = getIntent().getStringExtra("nim");
        List<User> users = DbUser.users(nim);

        int jarakMataKananKeHidung = Pengenalan.jarakMataKananKeHidung(mataKanan, hidung, nim);
        int jarakMataKiriKeHidung = Pengenalan.jarakMataKiriKeHidung(mataKiri, hidung, nim);
        int lebarHidungBawah = Pengenalan.lebarHidungBawah(hidungBawah, nim);
        int panjangHidung = Pengenalan.panjangHidung(hidung, nim);

        int hasil =jarakMataKananKeHidung + jarakMataKiriKeHidung + lebarHidungBawah + panjangHidung;

        for (User user : users){
            recognizeOutput(hasil, user);
        }


    }

    private void recognizeOutput(int hasil, User user) {
        Log.i("Hasil", String.valueOf(hasil));
        if (hasil < 3) {
            Toast.makeText(this, "Wajah Tidak Dikenali", Toast.LENGTH_SHORT).show();
        } else if (hasil > 3){
            Toast.makeText(this, "Orang ini adalah" + user.getNama(), Toast.LENGTH_SHORT).show();
        }
    }


}
