package com.tencent.nanodetncnn;

import android.app.AlertDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;

import androidx.fragment.app.Fragment;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Calendar;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link cust3Fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class cust3Fragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    //
    TextView mode, name, alertTime, recipeTime;
    String pmode, pname, alerttime, recipetime,result, uid,modename,moderesult=null,sso,veg,hate;
    //

    public static JSONObject obj = null;
    public static JSONArray table = null;
    public static JSONObject data;
    public static JSONArray vegtable = null;
    public static JSONObject vegdata;
    public static String did=null;
    public static String vegname=null;


    public cust3Fragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment cust3Fragment.
     */
    // TODO: Rename and change types and number of parameters
    public static cust3Fragment newInstance(String param1, String param2) {
        cust3Fragment fragment = new cust3Fragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_cust3, container, false);

        Button info = (Button) view.findViewById(R.id.info);
        Button btn2 = (Button) view.findViewById(R.id.button2);
        mode = view.findViewById(R.id.mode);
        name = view.findViewById(R.id.name);
        alertTime = view.findViewById(R.id.alertTime);
        recipeTime = view.findViewById(R.id.recipeTime);

        if(getArguments()!=null){
            result=getArguments().getString("key");//userid;sugar糖/salt鹽/oil油;菜名,菜名...
            updateUserData(data.toString());
            String[] split = result.split(";");
            uid = split[0];//userid
            sso = split[1];//sugar糖/salt鹽/oil油
            veg = split[2];//菜名,菜名...
            dialog();
        }
        String sugar,salt,oil;
        String[] splitsso = sso.split("/");
        sugar=splitsso[0];
        salt=splitsso[1];
        oil=splitsso[2];


        hate="[";//要傳資料庫的陣列
        String[] splituserhateveg = veg.split(",");//user不喜歡的食材
        for (int i = 0; i < splituserhateveg.length; i++) {
            if(i==splituserhateveg.length-1){
                hate=hate+"\""+splituserhateveg[i]+"\"";
                break;
            }
            hate=hate+"\""+splituserhateveg[i]+"\""+",";
        }
//        String[] splitdid = did.split(";");//食材大表所有蔬菜id
//        String[] splitvegname = vegname.split(";");//食材大表所有蔬菜名字
//        for (int i = 0; i < splituserhateveg.length; i++) {
//            if(i==splituserhateveg.length-1){
//                for (int j = 1; j < splitvegname.length; j++) {
//                    if(splituserhateveg[i].equals(splitvegname[j])){
//                        hate=hate+splitdid[j];
//                        break;
//                    }if(j==splitvegname.length-1){
//                        hate=hate+"\""+splituserhateveg[i]+"\"";
//                    }
//                }
//                break;
//            }
//            for (int j = 1; j < splitvegname.length; j++) {
//                if(splituserhateveg[i].equals(splitvegname[j])){
//                    hate=hate+splitdid[j]+",";
//                    break;
//                }if(j==splitvegname.length-1){
//                    hate=hate+"\""+splituserhateveg[i]+"\""+",";
//                }
//            }
//        }
        hate=hate+"]";

        //alertTime.setText(did.toString());

        //設定模式
        mode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String mix = uid + ";" + pname+";"+recipeTime.getText().toString() + ";" + alertTime.getText().toString();
                Bundle bundle = new Bundle();
                bundle.putString("key", mix);
                //別的變數型態傳送
                //bundle.putBoolean("key1",true);
                //bundle.putFloat("float",3.44f);
                AlertDialog.Builder dumb = new AlertDialog.Builder(view.getContext());
                dumb.setTitle(Html.fromHtml("<font color='#00455F'>注意"));
                dumb.setMessage(Html.fromHtml("<font color='#00455F'>選擇其他模式將導致客製化內容失效\n" + "(包含飲食習慣及討厭的食材選擇)"));
                dumb.setPositiveButton("確定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        EditFragment fragment = new EditFragment();
                        fragment.setArguments(bundle);
                        getFragmentManager().beginTransaction().replace(R.id.frame_layout, fragment).commit();
                    }
                });
                AlertDialog dialog = dumb.create();
                dialog.show();
            }
        });

//        info.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
////                AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());
////                builder.setTitle("不喜歡食物："+result);
////                builder.show();
//                mode.setText(result);
//            }
//        });


        //傳資料庫
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //pass to settimedb
                String mix = uid +";"+pname+ ";" + recipeTime.getText().toString() + ";" + alertTime.getText().toString()+";客製化"+";"+sugar+";"+salt+";"+oil+";"+hate;
                Intent intent = new Intent(getActivity(), passvaluecustActivity.class);
                Bundle bundlle1 = new Bundle();
                bundlle1.putString("result", mix); //放入所需要傳遞的值
                intent.putExtras(bundlle1);
                getActivity().startActivity(intent); //這里一定要獲取到所在Activity再startActivity()；
                //end
            }
        });

        //設定食譜時間
        recipeTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar c = Calendar.getInstance();
                int hour = c.get(Calendar.HOUR);
                int minute = c.get(Calendar.MINUTE);
                new TimePickerDialog(getActivity(), new TimePickerDialog.OnTimeSetListener() {

                    @Override
                    public void onTimeSet(TimePicker view, int hour, int minute) {
                        if(hour<10){
                            if (minute == 0) {
                                recipeTime.setText("0"+hour + ":" + minute + "0");
                            } else if (minute < 10) {
                                recipeTime.setText("0"+hour + ":" + "0" + minute);
                            } else {
                                recipeTime.setText("0"+hour + ":" + minute);
                            }
                        }else {
                            if (minute == 0) {
                                recipeTime.setText(hour + ":" + minute + "0");
                            } else if (minute < 10) {
                                recipeTime.setText(hour + ":" + "0" + minute);
                            } else {
                                recipeTime.setText(hour + ":" + minute);
                            }
                        }
                    }
                }, hour, minute, true).show();

            }
        });

        //設定提醒時間
        alertTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar c = Calendar.getInstance();
                int hour = c.get(Calendar.HOUR);
                int minute = c.get(Calendar.MINUTE);
                new TimePickerDialog(getActivity(), new TimePickerDialog.OnTimeSetListener() {

                    @Override
                    public void onTimeSet(TimePicker view, int hour, int minute) {
                        if(hour<10){
                            if (minute == 0) {
                                alertTime.setText("0"+hour + ":" + minute + "0");
                            } else if (minute < 10) {
                                alertTime.setText("0"+hour + ":" + "0" + minute);
                            } else {
                                alertTime.setText("0"+hour + ":" + minute);
                            }
                        }else {
                            if (minute == 0) {
                                alertTime.setText(hour + ":" + minute + "0");
                            } else if (minute < 10) {
                                alertTime.setText(hour + ":" + "0" + minute);
                            } else {
                                alertTime.setText(hour + ":" + minute);
                            }
                        }
                    }
                }, hour, minute, true).show();

            }
        });

        return view;
    }

    private void updateUserData(String result) {
        try {
            pmode = data.getString("mid");
            pname= data.getString("name");
            alerttime = data.getString("alertTime");
            recipetime = data.getString("recipeTime");
            modename=data.getString("mname");
            name.setText(pname);
            //mode.setText(modename);
            mode.setText("客製化");

            String[] split = alerttime.split(":");
            String atime=split[0]+":"+split[1];
            alertTime.setText(atime);
            String[] split1 = recipetime.split(":");
            String rtime=split1[0]+":"+split1[1];
            recipeTime.setText(rtime);
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }


    public static void user(String result,String uid){
        String id;

        try {
            obj = new JSONObject(result);
            table = obj.getJSONArray("user");
            vegtable=obj.getJSONArray("food_dic");
            for (int i = 0; i < vegtable.length(); i++) {
                vegdata=vegtable.getJSONObject(i);
                did=did+";"+vegdata.getString("did");
                vegname=vegname+";"+vegdata.getString("name");
            }
            for (int i =0; i< table.length();++i){
                data = table.getJSONObject(i);
                id=data.getString("uid");
                if(id.equals(uid)){
                    break;
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    private void dialog() {
        android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(getContext());//只需要context
        //builder.setTitle("失敗！");  //設置標題
        //builder.setIcon(R.mipmap.ic_launcher_round); //標題前面那個小圖示
        builder.setMessage("飲食習慣:"+sso+"\n討厭食材:"+veg); //提示訊息
        builder.setPositiveButton("確定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        builder.create().show();
    }
}