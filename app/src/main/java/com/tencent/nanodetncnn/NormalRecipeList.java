package com.tencent.nanodetncnn;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Objects;

public class NormalRecipeList {

    //    setText要用的
    public static String[] allRecipeId = new String[500];
    public static String[] imgName = new String[500];
    public static String[] allRecipeNames = new String[500];
    public static String[] allRecipeSteps = new String[500];
    public static String[] allRecipeSugar = new String[500];
    public static String[] allRecipeSalt = new String[500];
    public static String[] allRecipeOil = new String[500];
    public static String[] allRecipeFood= new String[500];
    public static String[] allRecipeDid= new String[500];
    public static String[] allRecipeFoodImg = new String[500];

    //    內部迴圈要用的
    public static String[] recipefoodRid = new String[800];
    public static String[] recipefoodDid = new String[800];
    public static String[] recipefoodName = new String[800];
    public static String[] recipefoodImg = new String[800];
    public static String[] recipeName = new String[800];
    public static String[] recipeSteps = new String[800];
    public static String[] recipeImg = new String[800];
    public static String[] recipeSugar = new String[800];
    public static String[] recipeSalt = new String[800];
    public static String[] recipeOil = new String[800];

    public static String[] allfoodDid = new String[800];
    public static String[] allfoodName = new String[800];
    public static String[] allfoodImg = new String[800];


    public static int recipe_food_num = 0;
    public static int recipeindex = 0;
    public static int lastnum = 0;

    public static String[] allfoodhistoryDid = new String[1000];
    public static String[] allfoodhistoryName = new String[1000];


    public static void recipe(String result){
        allRecipeId = new String[500];
        imgName = new String[500];
        allRecipeNames = new String[500];
        allRecipeSteps = new String[500];
        allRecipeSugar = new String[500];
        allRecipeSalt = new String[500];
        allRecipeOil = new String[500];
        allRecipeFood= new String[500];
        allRecipeDid= new String[500];
        allRecipeFoodImg = new String[500];
        recipefoodRid = new String[800];
        recipefoodDid = new String[800];
        recipefoodName = new String[800];
        recipefoodImg = new String[800];
        recipeName = new String[800];
        recipeSteps = new String[800];
        recipeImg = new String[800];
        recipeSugar = new String[800];
        recipeSalt = new String[800];
        recipeOil = new String[800];
        allfoodDid = new String[800];
        allfoodName = new String[800];
        allfoodImg = new String[800];
        recipe_food_num = 0;
        recipeindex = 0;
        lastnum = 0;
        //Don't new allfoodhistoryDid and allfoodhistoryName
//        allfoodhistoryDid = new String[1000];
//        allfoodhistoryName = new String[1000];

        JSONObject obj = null;
        JSONArray recipe = null;
        JSONObject recipedata = null;
        String tempRid = null;//現在進來的RID
        String currentRid = null;//正在使用的RID

    try {
        recipe = new JSONArray(result);
        int j = 0;
        int z = 0;
        ////////////////  O<~~~~~~~~~~
        String temp = "-1";
        for (int i =0; i< recipe.length();++i){ // 從第一筆食譜看
            recipedata = recipe.getJSONObject(i); //得到單筆食譜資料
            if(temp.equals(recipedata.getString("rid"))){
                // 單筆食譜其他筆
                allRecipeFood[i] += "," + recipedata.getString("foodName");
                allRecipeDid[i] += "," + recipedata.getString("did");
                allRecipeFoodImg[i] += "," + recipedata.getString("foodimgName");
            }else{
                // 單筆食譜第一筆
                temp = recipedata.getString("rid");
                recipefoodRid[i] = recipedata.getString("rid");
                recipefoodDid[i] = recipedata.getString("did");
                recipeName[i] = recipedata.getString("name");
                recipeSteps[i] =recipedata.getString("step");
                recipeImg[i] = recipedata.getString("imgName");
                // 看你覺得改哪邊方便
                //recipetag[i] = recipedata.getString("sugar") + "糖,";
                //recipetag[i] += recipedata.getString("salt") + "鹽,";
                //recipetag[i] += recipedata.getString("oil") + "油";
                allRecipeFood[i] = recipedata.getString("foodName");
                allRecipeDid[i] = recipedata.getString("did");
                allRecipeFoodImg[i] = recipedata.getString("foodimgName");
                // 本來想說這樣會不會方便點，但是發現這樣可能要存更多資料? 看你覺得需不需要
                //allRecipeFoodAmount[i] = recipedata.getString("amount");

            }
        }
        MainActivity.normal_loop = 0;
        ////////////////  O<~~~~~~~~~~


        for (int i =0; i< recipe.length();++i){ // 從第一筆食譜看
            recipedata = recipe.getJSONObject(i); //得到單筆食譜資料
            recipefoodRid[i] = recipedata.getString("rid");
            recipefoodDid[i] = recipedata.getString("did");
            recipefoodName[i] = recipedata.getString("foodName");
            recipefoodImg[i] = recipedata.getString("foodimgName");
            recipeName[i] = recipedata.getString("name");
            recipeSteps[i] =recipedata.getString("step");
            recipeImg[i] = recipedata.getString("imgName");
            recipeSugar[i] = recipedata.getString("sugar");
            recipeSalt[i] = recipedata.getString("salt");
            recipeOil[i] = recipedata.getString("oil");
        }
        for (int i = 0; i < recipe.length(); i++) {
//            System.out.println("recipedata = "+recipedata);
            tempRid = recipefoodRid[i];
            if(currentRid == null || currentRid.equals(tempRid)){
                currentRid = tempRid;

                allfoodName[j] = recipefoodName[i];
                allfoodDid[j] = recipefoodDid[i];
                allfoodImg[j] = recipefoodImg[i];

                ++j;
            }
            else if(!Objects.equals(tempRid, currentRid)){
                allRecipeId[recipeindex] = recipefoodRid[i-1];
                allRecipeNames[recipeindex] = recipeName[i-1];
                allRecipeSteps[recipeindex] =recipeSteps[i-1];
                imgName[recipeindex] = recipeImg[i-1];
                allRecipeSugar[recipeindex] = recipeSugar[i-1];
                allRecipeSalt[recipeindex] = recipeSalt[i-1];
                allRecipeOil[recipeindex] = recipeOil[i-1];
                lastnum = i;
                ++recipeindex;


                recipefood(allfoodName,allfoodDid,allfoodImg);
                allfoodName = new String[800];
                allfoodDid = new String[800];
                allfoodImg = new String[800];
                currentRid = tempRid;

                j = 0;
                allfoodName[j] = recipefoodName[i];
                allfoodDid[j] = recipefoodDid[i];
                allfoodImg[j] = recipefoodImg[i];
                ++j;
            }
        }
        allRecipeId[recipeindex] = recipefoodRid[lastnum];
        allRecipeNames[recipeindex] = recipeName[lastnum];
        allRecipeSteps[recipeindex] =recipeSteps[lastnum];
        imgName[recipeindex] = recipeImg[lastnum];
        allRecipeSugar[recipeindex] = recipeSugar[lastnum];
        allRecipeSalt[recipeindex] = recipeSalt[lastnum];
        allRecipeOil[recipeindex] = recipeOil[lastnum];
        ++recipeindex;
        recipefood(allfoodName,allfoodDid,allfoodImg);
        allfoodName = new String[800];
        allfoodDid = new String[800];
        allfoodImg = new String[800];

    } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public static void recipefood(String[] allfoodName, String[] allfoodDid,String[] allfoodImg){


        for(int y = 0; y<allfoodName.length; ++y){
            if(allfoodName[y] != null){
                if(y==0){
                    allRecipeFood[recipe_food_num] = allfoodName[y];
                    allRecipeDid[recipe_food_num] = allfoodDid[y];
                    allRecipeFoodImg[recipe_food_num] = allfoodImg[y];
                    if(allfoodName[y+1] != null){
                        allRecipeFood[recipe_food_num] += ",";
                        allRecipeDid[recipe_food_num] += ",";
                        allRecipeFoodImg[recipe_food_num]+= ",";
                    }
                }
                else if(allfoodName[y+1] != null){
                    allRecipeFood[recipe_food_num] += (allfoodName[y]+",");
                    allRecipeDid[recipe_food_num] += (allfoodDid[y]+",");
                    allRecipeFoodImg[recipe_food_num] += (allfoodImg[y]+",");
                }
                else if(allfoodName[y] != null){
                    allRecipeFood[recipe_food_num] += allfoodName[y];
                    allRecipeDid[recipe_food_num] += allfoodDid[y];
                    allRecipeFoodImg[recipe_food_num] += allfoodImg[y];
                }
                else{
                    continue;
                }
            }
            else{
                break;
            }
        }
        MainActivity.normal_loop = 0;
        recipe_food_num++;

    }

    public static void getallfoodhistory(String result){
        JSONObject obj;
        JSONArray  foodArray= null;
        JSONObject  foodObj= null;

        try {
            obj = new JSONObject(result);
            foodArray = obj.getJSONArray("food_dic");
            ////////////////  O<~~~~~~~~~~
            // food_dic只顯示 > 0的資料時
//            for(int i = 0; i < foodArray.length(); ++i){
//                foodObj = foodArray.getJSONObject(i);
//                allfoodhistoryDid[i] = foodObj.getString("did");
//            }
            ////////////////  O<~~~~~~~~~~

// 糾結糾結糾結糾結糾結糾結糾結糾結糾結糾結糾結糾結糾結糾結糾結糾結糾結糾結糾結糾結糾結糾結糾結糾結糾結糾結糾結糾結糾結糾結

            ////////////////  O<~~~~~~~~~~
            // food_dic為整個資料表時
//            for(int i = 0; i < foodArray.length(); ++i){
//                foodObj = foodArray.getJSONObject(i);
//                // 用did當index直接判斷，減少迴圈
//                if(!foodObj.getString("amount").equals("0")){
//                    allfoodhistoryName[Integer.parseInt(foodObj.getString("did"))] = "1";
//                }else{
//                    allfoodhistoryName[Integer.parseInt(foodObj.getString("did"))] = "0";
//                }
//            }
            ////////////////  O<~~~~~~~~~~
            for(int i = 0; i < foodArray.length(); ++i){
                foodObj = foodArray.getJSONObject(i);
                if(!foodObj.getString("amount").equals("0")){
                    allfoodhistoryDid[i] = foodObj.getString("did");
                    allfoodhistoryName[i] = foodObj.getString("name");
                }
            }

        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
