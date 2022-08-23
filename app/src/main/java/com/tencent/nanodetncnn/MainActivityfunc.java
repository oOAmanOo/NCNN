package com.tencent.nanodetncnn;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.tencent.nanodetncnn.databinding.ActivityMainActivityfuncBinding;

public class MainActivityfunc extends AppCompatActivity implements View.OnClickListener{

    Button scanBtn;
    ActivityMainActivityfuncBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainActivityfuncBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        scanBtn =findViewById(R.id.scanBtn);
        scanBtn.setOnClickListener(this);
        replaceFragment(new MyFridgeFragment());


        binding.bottomNavigationView.setOnNavigationItemSelectedListener(item -> {

            switch (item.getItemId()) {

                case R.id.myFridge:
                    replaceFragment(new MyFridgeFragment());
                    break;
                case R.id.search:
                    replaceFragment(new SearchFragment());
                    break;
                case R.id.expiration:
                    replaceFragment(new ExpirationFragment());
                    break;
                case R.id.profile:
                    replaceFragment(new ProfileFragment());
                    break;
            }
            return true;
        });
    }

    @Override
    public void onClick(View v){
        scanCode();
    }

    private void scanCode(){

        IntentIntegrator integrator = new IntentIntegrator(this);
        integrator.setCaptureActivity(CaptureAct.class);
        integrator.setOrientationLocked(false);
        integrator.setDesiredBarcodeFormats(IntentIntegrator.ALL_CODE_TYPES);
        integrator.setPrompt("Scanning Code");
        integrator.initiateScan();
    }

    @Override
    protected void  onActivityResult(int requestCode, int resultCode, Intent data){
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode,resultCode,data);
        if(result != null){
            if(result.getContents() != null){
                AlertDialog.Builder builder =new AlertDialog.Builder(this);
                builder.setMessage(result.getContents());
                builder.setTitle("Scan result");
                builder.setPositiveButton("Scan Again", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        scanCode();
                    }
                }).setNegativeButton("finish", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
//                        finish();
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
            }
            else{
                Toast.makeText(this,"No Result",Toast.LENGTH_LONG).show();
            }
        }else{
            super.onActivityResult(requestCode,resultCode,data);
        }

    }
    private void replaceFragment(Fragment fragment){

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout, fragment);
        fragmentTransaction.commit();
    }




}