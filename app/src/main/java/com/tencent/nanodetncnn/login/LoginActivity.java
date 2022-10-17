package com.tencent.nanodetncnn.login;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Looper;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.tencent.nanodetncnn.MainActivityreg;
import com.tencent.nanodetncnn.databinding.ActivityLoginBinding;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.json.JSONArray;
import org.json.JSONException;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class LoginActivity extends AppCompatActivity {

    private LoginViewModel loginViewModel;
    private ActivityLoginBinding binding;
    private EditText usernameEditText,passwordEditText;
    private TextView ff;
    String result,pname,pw;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        loginViewModel = new ViewModelProvider(this, new LoginViewModelFactory())
                .get(LoginViewModel.class);

        usernameEditText = binding.uid;
        passwordEditText = binding.password;
        final Button loginButton = binding.login;
        final Button registerButton = binding.register;//
        final ProgressBar loadingProgressBar = binding.loading;

        loginViewModel.getLoginFormState().observe(this, new Observer<LoginFormState>() {
            @Override
            public void onChanged(@Nullable LoginFormState loginFormState) {
                if (loginFormState == null) {
                    return;
                }
                loginButton.setEnabled(loginFormState.isDataValid());
                if (loginFormState.getUsernameError() != null) {
                    usernameEditText.setError(getString(loginFormState.getUsernameError()));
                }
                if (loginFormState.getPasswordError() != null) {
                    passwordEditText.setError(getString(loginFormState.getPasswordError()));
                }
            }
        });

        TextWatcher afterTextChangedListener = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // ignore
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // ignore
            }

            @Override
            public void afterTextChanged(Editable s) {
                loginViewModel.loginDataChanged(usernameEditText.getText().toString(),
                        passwordEditText.getText().toString());
            }
        };
        usernameEditText.addTextChangedListener(afterTextChangedListener);
        passwordEditText.addTextChangedListener(afterTextChangedListener);
        passwordEditText.setOnEditorActionListener(new TextView.OnEditorActionListener() {

            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    loginViewModel.login(usernameEditText.getText().toString(),
                            passwordEditText.getText().toString());
                }
                return false;
            }
        });

        //
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Thread thread = new Thread(mutiThread);
                thread.start(); // 開始執行
            }

        });

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadingProgressBar.setVisibility(View.VISIBLE);
                loginViewModel.login(usernameEditText.getText().toString(),
                        passwordEditText.getText().toString());
                Intent intent=new Intent(LoginActivity.this, MainActivityreg.class); startActivity(intent);
            }
        });
    }

    private void dialogw() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("錯誤！");  //設置標題
        //builder.setIcon(R.mipmap.ic_launcher_round); //標題前面那個小圖示
        builder.setMessage("帳號密碼輸入錯誤"); //提示訊息

        builder.setPositiveButton("確定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
                }
        });
        builder.create().show();
    }


    //new add
    /* ======================================== */


    // 建立一個執行緒執行的事件取得網路資料
    // Android 有規定，連線網際網路的動作都不能再主線程做執行
    // 畢竟如果使用者連上網路結果等太久整個系統流程就卡死了
    private Runnable mutiThread = new Runnable() {
        public void run() {
            Looper.prepare();
            // TODO Auto-generated method stub
            HttpClient client = new DefaultHttpClient();

            HttpPost myPost = new HttpPost("http://140.117.71.11/user_login.php");
            try {
                List<NameValuePair> params = new ArrayList<NameValuePair>();
                params.add(new BasicNameValuePair("uid",usernameEditText.getText().toString()));
                params.add(new BasicNameValuePair("pw",passwordEditText.getText().toString()));
                myPost.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
                HttpResponse response = new DefaultHttpClient().execute(myPost);
                //開始宣告HTTP連線需要的物件
                HttpClient httpClient = new DefaultHttpClient();//宣告網路連線物件
                HttpResponse httpResponse = httpClient.execute(myPost);//宣告HTTP回應物件
                HttpEntity httpEntity = httpResponse.getEntity();//宣告HTTP實體化物件
                InputStream inputStream = httpEntity.getContent();//宣告輸入串流

                //讀取輸入船劉並存到字串
                //取得資料後可在此處理
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "utf-8"), 8);
                String box = "";
                String line = null;
                while ((line = bufferedReader.readLine()) != null) {
                    box += line;
                    box += "\n";
                }
                inputStream.close();
                result = box;
            } catch (Exception e) {
                result = e.toString();
            }

            // 當這個執行緒完全跑完後執行
            runOnUiThread(new Runnable() {
                public void run() {
                    updateUserData(result);
                }
            });
        }
        private void updateUserData(String result) {
            try {
                JSONArray parent = new JSONArray(result);

                String text =parent.getString(0);
                if(text.equals("登入成功")){
                    Toast.makeText(LoginActivity.this, "登入成功", Toast.LENGTH_SHORT).show();//new
//                    Intent intent=new Intent(LoginActivity.this, MainActivity.class);
                    Intent intent=new Intent(LoginActivity.this, AlarmActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putString("data_uid",usernameEditText.getText().toString());
                    intent.putExtras(bundle);   // put進去
                    startActivity(intent);
                    finish();
                }

            } catch (JSONException e) {
                dialogw();
                passwordEditText.setText("");
                e.printStackTrace();
            }
        }
    };
//new end
}