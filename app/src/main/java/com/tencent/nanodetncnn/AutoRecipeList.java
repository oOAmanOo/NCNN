package com.tencent.nanodetncnn;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.Objects;


public class AutoRecipeList {
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

    public static String[] tempallRecipeFood = new String[500];

    public static String[] tempallRecipeId = new String[500];

    public static int count = 0;
    public static int[] allRecipeFoodIndex = new int[800];
    public static int[] detail_allRecipeFoodIndex = new int[800];

    public static String result2 = null;
    public static String searchtext2 = null;
    public static boolean match;


//    public static void search(String searchtext1) {
//        searchtext2 = searchtext1;
//        System.out.println("searchtext2 ="+searchtext2);
//        recipe();
//    }

    public static void recipe(String result) {
        result2 = null;
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
        allfoodhistoryDid = new String[1000];
        allfoodhistoryName = new String[1000];
        count = 0;
        allRecipeFoodIndex = new int[800];
        result2 = result;


        JSONObject obj = null;
        JSONArray recipe = null;
        JSONObject recipedata = null;
        String tempRid = null;//現在進來的RID
        String currentRid = null;//正在使用的RID

        try{
            recipe = new JSONArray(result);
            System.out.println("recipe6  "+recipe);
            int j = 0;
            int z = 0;
            recipeindex = 0;
//            if(result.length()>50)
            for (int i = 0; i < recipe.length(); i++) {
                recipedata = recipe.getJSONObject(i); //得到單筆食譜資料
                allRecipeFoodIndex[count] = i;
                recipefoodRid[i] = recipedata.getString("rid");
                recipeImg[i] = recipedata.getString("imgName");
                recipeSugar[i] = recipedata.getString("sugar");
                recipeSalt[i] = recipedata.getString("salt");
                recipeOil[i] = recipedata.getString("oil");
                recipeName[i] = recipedata.getString("name");
                recipeSteps[i] =recipedata.getString("step");
//                detail_allRecipeFoodIndex[i] = j;
            }
            recipe_fetch(recipefoodRid);


        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public static void recipe_fetch(String[] recipefoodRid1){
        JSONArray recipe = null;
        JSONObject recipedata = null;
        String tempRid = null;//現在進來的RID
        String currentRid = null;//正在使用的RID
        int count = 0;
        int z = 0;
        try{
            recipe = new JSONArray(result2);
            for (int i = 0; i < recipe.length(); i++) {
                recipedata = recipe.getJSONObject(i); //得到單筆食譜資料
                for (int j = 0; j < recipefoodRid1.length; j++) {
                    if(recipedata.getString("rid").equals(recipefoodRid1[j])){
                        recipefoodRid[count] = recipedata.getString("rid");
                        recipefoodDid[count] = recipedata.getString("did");
                        recipefoodName[count] = recipedata.getString("foodName");
                        recipefoodImg[count] = recipedata.getString("foodimgName");
                        ++count;
                        break;
                    }
                }
            }
            for (int i = 0; i < recipefoodRid.length; i++) {
                tempRid = recipefoodRid[i];
                if(currentRid == null || currentRid.equals(tempRid)){
                    currentRid = tempRid;
                    allfoodName[z] = recipefoodName[i];
                    allfoodDid[z] = recipefoodDid[i];
                    allfoodImg[z] = recipefoodImg[i];
                    ++z;
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
                    z = 0;
                    allfoodName[z] = recipefoodName[i];
                    allfoodDid[z] = recipefoodDid[i];
                    allfoodImg[z] = recipefoodImg[i];
                    ++z;
                }
            }
            allRecipeId[recipeindex] = recipefoodRid[lastnum];
            allRecipeNames[recipeindex] = recipeName[lastnum];
            allRecipeSteps[recipeindex] =recipeSteps[lastnum];
            imgName[recipeindex] = recipeImg[lastnum];
            allRecipeSugar[recipeindex] = recipeSugar[lastnum];
            allRecipeSalt[recipeindex] = recipeSalt[lastnum];
            allRecipeOil[recipeindex] = recipeOil[lastnum];
            recipefood(allfoodName,allfoodDid,allfoodImg);
            allfoodName = new String[800];
            allfoodDid = new String[800];
            allfoodImg = new String[800];


        }catch(Exception e){
            e.printStackTrace();
        }
    }


    public static void recipefood(String[] allfoodName, String[] allfoodDid, String[] allfoodImg){


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
        MainActivity.auto_loop = 0;
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

//    public static void autosearch(String searchtext){
//
//        JSONObject obj = null;
//        JSONArray recipe = null;
//        JSONObject recipedata = null;
//
//        String[] splitdata;
//
//        try{
//            obj = new JSONObject(result2);
//            recipe = obj.getJSONArray("recipe");
//        }catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        if(searchtext != null && tempallRecipeFood != null){
//            count = 0;
//            for (int i = 0; i < recipeindex; i++) {
//                System.out.println("tempallRecipeFood.length:"+recipeindex);
//                splitdata = tempallRecipeFood[i].split(",");
//                for (int j = 0; j < splitdata.length; j++) {
//                    System.out.println("splitdata[j]:"+splitdata[j]);
//                    if(searchtext.equals(splitdata[j])){
//                        System.out.println("match:"+searchtext+"="+splitdata[j]);
//                        try {
//
//                            recipedata = recipe.getJSONObject(i);
//                            allRecipeFoodIndex[count] = i;
//                            allRecipeId[count] = recipedata.getString("rid");
//                            imgName[count] = recipedata.getString("imgName");
//                            allRecipeSugar[count] = recipedata.getString("sugar");
//                            allRecipeSalt[count] = recipedata.getString("salt");
//                            allRecipeOil[count] = recipedata.getString("oil");
//                            allRecipeNames[count] = recipedata.getString("name");
//                            allRecipeSteps[count] =recipedata.getString("step");
//                            allRecipeFood[count] = tempallRecipeFood[i];
//
//                            detail_allRecipeFoodIndex[i] = count;
//
//                            System.out.println("count"+count);
//                            System.out.println("allRecipeId[count]"+allRecipeId[count]);
//                            System.out.println("imgName[count]"+imgName[count]);
//                            System.out.println("allRecipeSugar[count]"+allRecipeSugar[count]);
//                            System.out.println("allRecipeSalt[count]"+allRecipeSalt[count]);
//                            System.out.println("allRecipeOil[count]"+allRecipeOil[count]);
//                            System.out.println("allRecipeNames[count]"+allRecipeNames[count]);
//                            System.out.println("allRecipeSteps[count]"+allRecipeSteps[count]);
//                            System.out.println("allRecipeFood[count]"+allRecipeFood[count]);
//
//                            ++count;
//                            break;
//                        } catch (JSONException e) {
//                            e.printStackTrace();
//                        }
//                        System.out.println("count"+count);
//
//                    }
//                    System.out.println("out if");
//
//                }
//                System.out.println("end one recipe");
//
//            }
//            System.out.println("end all recipe");
//        }
//
//
//
//    }


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