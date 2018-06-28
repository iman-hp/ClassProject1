package test.application.classproject;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;

import android.provider.MediaStore;
import android.support.annotation.NonNull;

import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import android.widget.ListView;

import com.bumptech.glide.Glide;


import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewsById;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import test.application.classproject.Adapters.DrawerLiastAdapter;
import test.application.classproject.DataModels.DrawerDataModel;

public class MainActivity extends BaseActivity implements View.OnClickListener {
    Intent intent;
    ListView listview;
    Button btn_camera;
    Button btn_gallery;
    CircleImageView profile_image;
    int CAMERA_PERMISSION_CODE = 1000;
    int CAPTURE_CODE = 2000;
    private static int RESULT_LOAD_IMAGE = 1;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_main);
        super.onCreate(savedInstanceState);

        bind();
        createListDrawer();
        btncamera();
        btn_gallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(
                        Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

                startActivityForResult(i, RESULT_LOAD_IMAGE);
            }
        });

    }


    void bind() {
        btn_camera = findViewById(R.id.btn_camera);
        btn_gallery = findViewById(R.id.btn_gallery);
        listview = findViewById(R.id.listview);
        profile_image = findViewById(R.id.profile_image);
    }

    void createListDrawer() {
        List<DrawerDataModel> datalist = new ArrayList<>();
        DrawerDataModel home = new DrawerDataModel("Home", getResources().getDrawable(R.drawable.homeicon));
        DrawerDataModel aboutus = new DrawerDataModel("About Us", getResources().getDrawable(R.drawable.abouticon));
        DrawerDataModel callus = new DrawerDataModel("Call Us", getResources().getDrawable(R.drawable.callicon));
        DrawerDataModel like = new DrawerDataModel("Support us", getResources().getDrawable(R.drawable.handicon));
        datalist.add(home);
        datalist.add(aboutus);
        datalist.add(callus);
        datalist.add(like);
        DrawerLiastAdapter adapter = new DrawerLiastAdapter(mContext, datalist);
        listview.setAdapter(adapter);

    }

    void btncamera() {
        btn_gallery.setOnClickListener(this);
        btn_camera.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_camera) {
            permission();
        }


    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == CAMERA_PERMISSION_CODE) {
            super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }

    void permission() {
        if (ContextCompat.checkSelfPermission(mContext,
                Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(mActivity, new String[]{Manifest.permission.CAMERA}, CAMERA_PERMISSION_CODE);
        } else {
            capture();
        }
    }


    void capture() {
        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(cameraIntent, CAPTURE_CODE);

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CAPTURE_CODE) {
            if (resultCode == Activity.RESULT_OK) {
                Bitmap photo = (Bitmap) data.getExtras().get("data");
                Glide.with(mContext).load(photo).into(profile_image);

                //gallery

                if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && null != data) {
                    Uri selectedImage = data.getData();
                    String[] filePathColumn = {MediaStore.Images.Media.DATA};

                    Cursor cursor = getContentResolver().query(selectedImage,
                            filePathColumn, null, null, null);
                    cursor.moveToFirst();

                    int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                    String picturePath = cursor.getString(columnIndex);
                    cursor.close();

                    Glide.with(mContext).load(BitmapFactory.decodeFile(picturePath)).into(profile_image);
                }
            }

        }


    }



    }

