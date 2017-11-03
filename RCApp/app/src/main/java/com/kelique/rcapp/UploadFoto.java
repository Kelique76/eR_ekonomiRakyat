package com.kelique.rcapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class UploadFoto extends AppCompatActivity {

    @InjectView(R.id.button1)
    Button button1;
    @InjectView(R.id.button2)
    Button button2;
    //    private Button mSelectbtn;
//    private Button mSelectCam;
    private ImageView mImage;
    private StorageReference mStoreRef;
    private FirebaseStorage mFireStore;
    private static final int GALLERY_INTENT = 2;
    private static final int CAMERA_REQUEST_CODE = 1;
    private ProgressDialog mProgDiag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_foto);
        ButterKnife.inject(this);

        button1 = (Button) findViewById(R.id.button1);
        button2 = (Button) findViewById(R.id.button2);
        mImage = (ImageView) findViewById(R.id.imageView3);
        mStoreRef = FirebaseStorage.getInstance().getReference();
        mProgDiag = new ProgressDialog(this);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent, GALLERY_INTENT);
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//                intent.setType("image/*");
                startActivityForResult(intent, CAMERA_REQUEST_CODE);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        try {
            switch (requestCode) {
                case GALLERY_INTENT:
                    if (requestCode == GALLERY_INTENT && resultCode == RESULT_OK){
                        mProgDiag.setMessage("Sedang Mengunggah Foto...");
                        mProgDiag.show();
                        Uri uri = data.getData();
                        StorageReference tempatfile = mStoreRef.child("Foto").child(uri.getLastPathSegment());
                        tempatfile.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                            @Override
                            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                                @SuppressWarnings("VisibleForTests") Uri downloadUri = taskSnapshot.getUploadSessionUri();
                                Log.d("DEBUUG",String.valueOf(downloadUri));
                                Picasso.with(UploadFoto.this).load(downloadUri).fit().centerCrop().error(R.mipmap.ic_launcher).into(mImage);
                                Toast.makeText(UploadFoto.this, "Unggahan Foto Berhasil", Toast.LENGTH_SHORT).show();
                                mProgDiag.dismiss();

                            }
                        });

                    }
                    break;
                case CAMERA_REQUEST_CODE:
                    if (requestCode == CAMERA_REQUEST_CODE && resultCode == RESULT_OK){
                        mProgDiag.setMessage("Sedang Mengunggah Hasil Jepretan Camera...");
                        mProgDiag.show();
                        Uri uril = data.getData();
                        StorageReference wadahfile = mStoreRef.child("Fotokamera").child(uril.getLastPathSegment());
                        wadahfile.putFile(uril).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                                    @Override
                                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                                        @SuppressWarnings("VisibleForTests") Uri downloadUri = taskSnapshot.getUploadSessionUri();
                                        Picasso.with(UploadFoto.this).load(downloadUri).fit().centerCrop().into(mImage);
                                        Toast.makeText(UploadFoto.this, "Hasil Jepretan Camera Berhasil disimpan", Toast.LENGTH_SHORT).show();
                                        mProgDiag.dismiss();
                                    }
                                });

                    }
            }

        } catch (Exception e) {
            Log.i("kalau salah piye", e.getMessage());
        }
        }




//        try {
//            switch (requestCode) {
//                case GALLERY_INTENT:
//
//                    if (requestCode == GALLERY_INTENT && resultCode == RESULT_OK) {
//
//                        //your code
//                        mProgDiag.setMessage("Sedang Mengunggah Foto...");
//                        mProgDiag.show();
//                        Uri uri = data.getData();
//                        StorageReference tempatfile = mStoreRef.child("Foto").child(uri.getLastPathSegment());
//                        tempatfile.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
//                            @Override
//                            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
//
//                                Toast.makeText(UploadFoto.this, "Unggahan Foto Berhasil", Toast.LENGTH_SHORT).show();
//                                mProgDiag.dismiss();
//
//                            }
//                        });
//                        break;

//                        case CAMERA_REQUEST_CODE:
//                            if (requestCode == CAMERA_REQUEST_CODE && resultCode == RESULT_OK) {
//                                //your code
//                                mProgDiag.setMessage("Sedang Mengunggah Hasil Jepretan Camera...");
//                                mProgDiag.show();
//                                Uri uril = data.getData();
//                                StorageReference wadahfile = mStoreRef.child("Foto").child(uril.getLastPathSegment());
//                                wadahfile.putFile(uril).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
//                                    @Override
//                                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
//                                        @SuppressWarnings("VisibleForTests") Uri downloadUri = taskSnapshot.getUploadSessionUri();
//                                        Picasso.with(UploadFoto.this).load(downloadUri).fit().centerCrop().into(mImage);
//                                        Toast.makeText(UploadFoto.this, "Hasil Jepretan Camera Berhasil disimpan", Toast.LENGTH_SHORT).show();
//                                        mProgDiag.dismiss();
//                                    }
//                                });
//                            }

//                    }
//
//                    break;
//            }
//
//
//        } catch (Exception e) {
//            Log.d("kalausalah tampilkan", e.getMessage());
//        }
//    }


//
//
//                (requestCode == GALLERY_INTENT && resultCode == RESULT_OK) {
//            mProgDiag.setMessage("Sedang Mengunggah Foto...");
//            mProgDiag.show();
//            Uri uri = data.getData();
//            StorageReference tempatfile = mStoreRef.child("Foto").child(uri.getLastPathSegment());
//            tempatfile.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
//                @Override
//                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
//
//                    Toast.makeText(UploadFoto.this, "Unggahan Foto Berhasil", Toast.LENGTH_SHORT).show();
//                    mProgDiag.dismiss();
//
//                }
//            });
//        } else if (requestCode == CAMERA_REQUEST_CODE && resultCode == RESULT_OK) {
//            mProgDiag.setMessage("Sedang Mengunggah Hasil Jepretan Camera...");
//            mProgDiag.show();
//            Uri uri = data.getData();
//            StorageReference wadahfile = mStoreRef.child("Foto").child(uri.getLastPathSegment());
//            wadahfile.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
//                @Override
//                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
//                    @SuppressWarnings("VisibleForTests") Uri downloadUri = taskSnapshot.getUploadSessionUri();
//                    Picasso.with(UploadFoto.this).load(downloadUri).fit().centerCrop().into(mImage);
//                    Toast.makeText(UploadFoto.this, "Hasil Jepretan Camera Berhasil disimpan", Toast.LENGTH_SHORT).show();
//                    mProgDiag.dismiss();
//                }
//            });
//        }
//    }

//    @OnClick({R.id.button1, R.id.button2})
//    public void onViewClicked(View view) {
//        switch (view.getId()) {
//            case R.id.button1:
//                Intent intent = new Intent(Intent.ACTION_PICK);
//                intent.setType("image/*");
//                startActivityForResult(intent, GALLERY_INTENT);
//                (requestCode == GALLERY_INTENT && resultCode == RESULT_OK) {
//                    mProgDiag.setMessage("Sedang Mengunggah Foto...");
//                    mProgDiag.show();
//                    Uri uri = data.getData();
//                    StorageReference tempatfile = mStoreRef.child("Foto").child(uri.getLastPathSegment());
//                    tempatfile.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
//                        @Override
//                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
//
//                            Toast.makeText(UploadFoto.this, "Unggahan Foto Berhasil", Toast.LENGTH_SHORT).show();
//                            mProgDiag.dismiss();
//
//                        }
//                    });
//                    break;
//                    case R.id.button2:
//                        if (requestCode == CAMERA_REQUEST_CODE && resultCode == RESULT_OK) {
//                            mProgDiag.setMessage("Sedang Mengunggah Hasil Jepretan Camera...");
//                            mProgDiag.show();
//                            Uri uri = data.getData();
//                            StorageReference wadahfile = mStoreRef.child("Foto").child(uri.getLastPathSegment());
//                            wadahfile.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
//                                @Override
//                                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
//                                    @SuppressWarnings("VisibleForTests") Uri downloadUri = taskSnapshot.getUploadSessionUri();
//                                    Picasso.with(UploadFoto.this).load(downloadUri).fit().centerCrop().into(mImage);
//                                    Toast.makeText(UploadFoto.this, "Hasil Jepretan Camera Berhasil disimpan", Toast.LENGTH_SHORT).show();
//                                    mProgDiag.dismiss();
//                                }
//                            });
//                            break;
//                        }
//                }
//        }
//    }
}