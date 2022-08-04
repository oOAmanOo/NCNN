// Tencent is pleased to support the open source community by making ncnn available.
//
// Copyright (C) 2021 THL A29 Limited, a Tencent company. All rights reserved.
//
// Licensed under the BSD 3-Clause License (the "License"); you may not use this file except
// in compliance with the License. You may obtain a copy of the License at
//
// https://opensource.org/licenses/BSD-3-Clause
//
// Unless required by applicable law or agreed to in writing, software distributed
// under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR
// CONDITIONS OF ANY KIND, either express or implied. See the License for the
// specific language governing permissions and limitations under the License.

package com.tencent.nanodetncnn;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.graphics.PixelFormat;
import android.os.Bundle;
import android.util.Log;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MainActivity extends Activity implements SurfaceHolder.Callback
{
    public static final int REQUEST_CAMERA = 100;

    private NcnnYolov5 ncnnyolov5 = new NcnnYolov5();
    private int facing = 0;

    private Spinner spinnerModel;
    private Spinner spinnerCPUGPU;
    private int current_model = 0;
    private int current_cpugpu = 0;
    private char result_java = '1';
    private int duplicate_class = 0;

    private SurfaceView cameraView;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        cameraView = (SurfaceView) findViewById(R.id.cameraview);

        cameraView.getHolder().setFormat(PixelFormat.RGBA_8888);
        cameraView.getHolder().addCallback(this);

        Button buttonSwitchCamera = (Button) findViewById(R.id.buttonSwitchCamera);
        buttonSwitchCamera.setOnClickListener(arg0 -> {

            int new_facing = 1 - facing;

            ncnnyolov5.closeCamera();

            ncnnyolov5.openCamera(new_facing);

            facing = new_facing;
        });

        spinnerModel = (Spinner) findViewById(R.id.spinnerModel);
        spinnerModel.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1, int position, long id)
            {
                if (position != current_model)
                {
                    current_model = position;
                    reload();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0)
            {
            }
        });

        spinnerCPUGPU = (Spinner) findViewById(R.id.spinnerCPUGPU);
        spinnerCPUGPU.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1, int position, long id)
            {
                if (position != current_cpugpu)
                {
                    current_cpugpu = position;
                    reload();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0)
            {
            }
        });

        Button VarButton = (Button) findViewById(R.id.endVarify);
        VarButton.setOnClickListener(view -> {
            if(result_java == '1'){
                result_java = '2';
            }else{
                result_java = '1';
            }
//            Toast toast = Toast.makeText( MainActivity.this, "點了按鈕"+result_java, Toast.LENGTH_SHORT);
//            toast.show();
            NcnnYolov5.varifyCheck(result_java);
    try{
        FileInputStream fis  = new FileInputStream("/data/data/com.tencent.nanodetncnn/result.txt");
        BufferedReader in= new BufferedReader(new InputStreamReader((fis)));
//        BufferedReader in = new BufferedReader(new FileReader("result.txt"));
        Scanner read = new Scanner(in);
        read.useDelimiter("\n");
        String title, category, runningTime, year, price;
        ArrayList<String> class_list = new ArrayList<String>(500);

        while(read.hasNext()){
            String next_class;
            next_class = read.next();
            class_list.forEach((e)->{
                System.out.println(e);
                System.out.println(next_class);
                if(e.equals(next_class)){
                    duplicate_class = 1;
                }
                System.out.println(duplicate_class);
            });
            if(duplicate_class == 0){
                class_list.add(next_class);
            }
            duplicate_class = 0;
        }

        Toast toast = Toast.makeText( MainActivity.this, "-"+class_list+"-", Toast.LENGTH_SHORT);
        toast.show();
        class_list.forEach(t -> System.out.println(t));
//        for(String element : class_list){
//            System.out.println(element);
//        }
    }
    catch (Exception ex){}



        });

        reload();
    }

    private void reload()
    {
        boolean ret_init = ncnnyolov5.loadModel(getAssets(), current_model, current_cpugpu);
        if (!ret_init)
        {
            Log.e("MainActivity", "ncnnyolv5 loadModel failed");
        }
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height)
    {
        ncnnyolov5.setOutputWindow(holder.getSurface());
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder)
    {
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder)
    {
    }

    @Override
    public void onResume()
    {
        super.onResume();

        if (ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CAMERA) == PackageManager.PERMISSION_DENIED)
        {
            ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.CAMERA}, REQUEST_CAMERA);
        }

        ncnnyolov5.openCamera(facing);
    }

    @Override
    public void onPause()
    {
        super.onPause();

        ncnnyolov5.closeCamera();
    }


}