package com.tencent.nanodetncnn;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.Objects;

//            obj = new JSONObject(result2);
//            recipe = obj.getJSONArray("recipe");
//
//            int j = 0;
//            int z = 0;
//            recipeindex = 0;
//            if(searchtext3 == null){
//                allRecipeId[j] = null;
//                imgName[j] = null;
//                allRecipeSugar[j] = null;
//                allRecipeSalt[j] = null;
//                allRecipeOil[j] = null;
//                allRecipeNames[j] = null;
//                allRecipeFood[j] = null;
//            }
//
//
//            for (int i =0; i< 30;++i){ // 從第一筆食譜看
//                recipedata = recipe.getJSONObject(i); //得到單筆食譜資料
//
//                if(recipedata.getString("name").contains(searchtext3)){ //當前搜尋字與該筆食譜名稱相同
//
//                    allRecipeId[j] = recipedata.getString("rid");
//                    imgName[j] = recipedata.getString("imgName");
//                    allRecipeSugar[j] = recipedata.getString("sugar");
//                    allRecipeSalt[j] = recipedata.getString("salt");
//                    allRecipeOil[j] = recipedata.getString("oil");
//                    allRecipeNames[j] = recipedata.getString("name");
//                    allRecipeSteps[j] =recipedata.getString("step");
//                    ++j;
//                    ++recipeindex;
//                }
//                else{
//                    continue;
//                }
//            }
//            recipe_fetch(allRecipeId);

public class AllRecipeList {//    setText要用的
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
    public static String result1 = null;
    public static String result2 = null;
    public static String searchtext2 = null;

//    public static void search(String searchtext) {
//        recipe_food_num = 0;
//        searchtext2 = searchtext;
//        recipe();
//    }

//    public static void result(String result) {
//        result2 = result;
//    }
//    public static void result1(String result) {
//        result1 = result;
//    }
    public static void recipe(String result) {
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

        try{
            recipe = new JSONArray(result);
            int j = 0;
            int z = 0;
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
            for (int i = 0; i < recipefoodRid.length; i++) {
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
//            ++recipeindex;
            recipefood(allfoodName,allfoodDid,allfoodImg);
            allfoodName = new String[800];
            allfoodDid = new String[800];
            allfoodImg = new String[800];
        }catch (Exception e){
            e.printStackTrace();
        }

    }
    public static void recipe_fetch(String[] allRecipeId1){
        JSONArray recipe = null;
        JSONObject recipedata = null;
        String tempRid = null;//現在進來的RID
        String currentRid = null;//正在使用的RID
        int count = 0;
        int z = 0;
        try{
            recipe = new JSONArray(result1);
            for (int i = 0; i < 200; i++) {
                recipedata = recipe.getJSONObject(i); //得到單筆食譜資料
                for (int j = 0; j < allRecipeId1.length; j++) {
                    if(recipedata.getString("rid").equals(allRecipeId1[j])){
                        recipefoodRid[count] = recipedata.getString("rid");
                        recipefoodDid[count] = recipedata.getString("did");
                        recipefoodName[count] = recipedata.getString("foodName");
                        recipefoodImg[count] = recipedata.getString("foodimgName");
                        ++count;
                        break;
                    }
                }
            }
            for (int i = 0; i < 200; i++) {
                tempRid = recipefoodRid[i];
                if(currentRid == null || currentRid.equals(tempRid)){
                    currentRid = tempRid;
                    allfoodName[z] = recipefoodName[i];
                    allfoodDid[z] = recipefoodDid[i];
                    allfoodImg[z] = recipefoodImg[i];

                    ++z;
                }
                else if(!Objects.equals(tempRid, currentRid)){
                    recipefood(allfoodName,allfoodDid,allfoodImg);
                    allfoodName = new String[800];
                    allfoodDid = new String[800];
                    allfoodImg = new String[800];
                    currentRid = tempRid;

                    z = 0;
                    allfoodName[z] = recipefoodName[i];
                    allfoodDid[z] = recipefoodDid[i];
                    allfoodImg[z] = recipefoodImg[i];
                    ++z;
                }
            }
            recipefood(allfoodName,allfoodDid,allfoodImg);
            allfoodName = new String[800];
            allfoodDid = new String[800];
            allfoodImg = new String[800];


        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public static void recipefood(String[] allfoodName, String[] allfoodDid, String[] allfoodImg){

        int tag = 0;

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
        MainActivity.allmode_loop = 0;
        recipe_food_num++;
    }

    public static void getallfoodhistory(String result){
        JSONObject obj;
        JSONArray  foodArray= null;
        JSONObject  foodObj= null;

        try {
            obj = new JSONObject(result);
            foodArray = obj.getJSONArray("food_dic");
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

//    public static void foodDic(String[] recipefoodDid){
//
//        System.out.println("into foodDic");
//
//        JSONObject obj;
//        JSONArray  foodDic= null;
//        JSONObject  fooddata= null;
//
//        try{
//            int x = 0;
//            obj = new JSONObject(result2);
//            foodDic = obj.getJSONArray("food_dic");
//
//            for(int i = 0; i < foodDic.length(); ++i){
//                fooddata = foodDic.getJSONObject(i);   //遍尋foodDic的did
//                allfoodDid[i] = fooddata.getString("did");
//
//                System.out.println("check all foodDid");
//                for(int z = 0; z < recipefoodDid.length; ++z){   //目前食譜的食材did數量
//
//                    if(allfoodDid[i].equals(recipefoodDid[z])){
//                        allfoodName[x] = fooddata.getString("name");
//                        System.out.println("allfoodName add:"+ allfoodName[x]);
//
//                        x++;
//
//                    }
//                }
//
//            }
//
//            recipefood(allfoodName);
//
//
//
//        }
//        catch (Exception e){
//            e.printStackTrace();
//        }
//
//    }
//
//    public static void recipefood(String[] allfoodName){
//
//        System.out.println("output");
//
//        int tag = 0;
////        System.out.println("allRecipeFood:"+ Arrays.toString(allRecipeFood));
//
//        for(int y = 0; y<allfoodName.length; ++y){
////            System.out.println("enter");
//            if(allfoodName[y] != null){
//                if(y==0){
//                    allRecipeFood[recipe_food_num] = allfoodName[y];
//                    System.out.println("allRecipeFood+"+allRecipeFood[recipe_food_num]);
//                    System.out.println("recipe_food_num"+recipe_food_num);
//                    if(allfoodName[y+1] != null){
//                        allRecipeFood[recipe_food_num] += "、";
//                    }
////                    System.out.println("First"+allRecipeFood[recipe_food_num]);
//                }
//                else if(allfoodName[y+1] != null){
//                    allRecipeFood[recipe_food_num] += (allfoodName[y]+"、");
//                    System.out.println("allRecipeFood+"+allRecipeFood[recipe_food_num]+"and");
//                    System.out.println("recipe_food_num"+recipe_food_num);
////                    System.out.println("and"+allRecipeFood[recipe_food_num]);
//                }
//                else if(allfoodName[y] != null){
//                    allRecipeFood[recipe_food_num] += allfoodName[y];
//                    System.out.println("end allRecipeFood+"+allRecipeFood[recipe_food_num]);
//                    System.out.println("recipe_food_num"+recipe_food_num);
////                    System.out.println("end"+allRecipeFood[recipe_food_num]);
//                }
//                else{
//                    continue;
//                }
//            }
//            else{
//                break;
//            }
//
//
//        }
//        recipe_food_num++;
////        System.out.println("recipe_food_num"+recipe_food_num);
////        System.out.println("allRecipeFood4:"+ Arrays.toString(allRecipeFood));
//
//
//    }

//

}