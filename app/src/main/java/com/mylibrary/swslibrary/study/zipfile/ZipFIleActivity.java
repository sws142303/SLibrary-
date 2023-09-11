package com.mylibrary.swslibrary.study.zipfile;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;

import com.mylibrary.swslibrary.R;

public class ZipFIleActivity extends AppCompatActivity {

    private Button btnStartZip,btnStopZip;
    //压缩前文件地址
    private String zipStartFilePath = Environment.getExternalStorageDirectory().getAbsolutePath() + "/测试文件压缩/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zip_f_ile);

        btnStartZip = findViewById(R.id.btnStartZip);
        btnStartZip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FileCompressionTool.getInstance().compressFile();
            }
        });

        btnStopZip = findViewById(R.id.btnStopZip);
        btnStopZip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FileCompressionTool.getInstance().unZip();
            }
        });
    }







}
