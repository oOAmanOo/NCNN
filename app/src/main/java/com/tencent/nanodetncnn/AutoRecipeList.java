package com.tencent.nanodetncnn;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class AutoRecipeList {

    public static int[] allRecipeFoodIndex = new int[500];
    public static String[] allRecipeId = new String[500];
    public static String[] imgName = new String[500];
    public static String[] allRecipeNames = new String[500];
    public static String[] allRecipeSteps = new String[500];
    public static String[] allRecipeSugar = new String[500];
    public static String[] allRecipeSalt = new String[500];
    public static String[] allRecipeOil = new String[500];
    public static String[] allRecipeFood = new String[500];
    public static String[] tempallRecipeFood = new String[500];

    public static String[] tempallRecipeId = new String[500];
    public static String[] recipefoodRid = new String[500];
    public static String[] recipefoodDid = new String[500];
    public static int[] detail_allRecipeFoodIndex = new int[500];

    public static String[] allfoodDid = new String[800];
    public static String[] allfoodName = new String[800];
    public static String[] allfoodImg = new String[800];


    public static String[] allRecipeDid= new String[500];
    public static String[] allRecipeFoodImg = new String[500];

    public static String[] finalallRecipeFood= new String[500];
    public static int recipe_food_num = 0;
    public static int recipeindex = 0;
    public static int count = 0;

    public static String[] allfoodhistoryDid = new String[1000];
    public static String[] allfoodhistoryName = new String[1000];



    public static String result2 = null;
    public static boolean match;


    public static void search(String searchtext) {
        autosearch(searchtext);
    }


//    public static void result(String result) {
//        result2 = result;
//    }



    public static void recipe(String result) {
        allRecipeId = new String[500];
        imgName = new String[500];
        allRecipeNames = new String[500];
        allRecipeSteps = new String[500];

        allRecipeSugar = new String[500];
        allRecipeSalt = new String[500];
        allRecipeOil = new String[500];

        recipefoodRid = new String[500];
        recipefoodDid = new String[500];

        allfoodDid = new String[800];
        allfoodName = new String[800];
        allfoodImg = new String[800];

        allRecipeFood= new String[500];
        allRecipeDid= new String[500];
        allRecipeFoodImg = new String[500];

        recipe_food_num = 0;
        recipeindex = 0;

        allfoodhistoryDid = new String[1000];
        allfoodhistoryName = new String[1000];
        result2 = result;

        JSONObject obj = null;
        JSONArray recipe = null;
        JSONObject recipedata = null;

        JSONArray recipe_food = null;
        JSONObject recipefooddata = null;
        String tempRid = null;//現在進來的RID
        String currentRid = null;//正在使用的RID

        try{
            obj = new JSONObject(result2);
            recipe = obj.getJSONArray("recipe");
            recipe_food = obj.getJSONArray("recipe_food");
            int j = 0;
            int z = 0;
            recipeindex = 0;

            for (int i =0; i< recipe.length();++i){ // 從第一筆食譜看
                recipedata = recipe.getJSONObject(i); //得到單筆食譜資料

                tempallRecipeId[i] = recipedata.getString("rid");
                ++recipeindex;


            }
            for(int a = 0; a < recipe_food.length(); ++a){       // 從第一筆食譜食材看
                recipefooddata = recipe_food.getJSONObject(a);  //得到單筆食譜食材資料
                recipefoodRid[a] = recipefooddata.getString("rid");  //紀錄所有食譜食材的rid
                tempRid = recipefoodRid[a];   //紀錄現在進入的食譜食材rid
                if(currentRid == null ||currentRid.equals(tempRid)){   //第一次紀錄或目前進入的食譜食材rid未改變，則currentRid為現在進入的食譜食材rid
                    currentRid = tempRid;
                }
                else{
                    foodDic(recipefoodDid);
                    allfoodName = new String[800];
                    recipefoodDid = new String[500];
                    currentRid = tempRid;
                    z = 0;
                }
                for(int i = 0; i < recipeindex; ++i){
                    if(recipefoodRid[a].equals(tempallRecipeId[i]) && recipefoodRid[a].equals(currentRid)){
                        recipefoodDid[z] = recipefooddata.getString("did");
                        ++z;
                    }
                }
            }
            foodDic(recipefoodDid);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void foodDic(String[] foodDid) {
        JSONObject obj;
        JSONArray  foodArray= null;
        JSONObject  foodObj= null;
        try {
            obj = new JSONObject(result2);
            foodArray = obj.getJSONArray("food_dic");
            int x = 0;
//            System.out.println("allRecipeFood2:"+ Arrays.toString(allRecipeFood));
            for(int i = 0; i< foodArray.length(); ++i){
                foodObj = foodArray.getJSONObject(i);
                allfoodDid[i] = foodObj.getString("did");
                for(int z = 0; z < foodDid.length; ++z){
                    if(allfoodDid[i].equals(foodDid[z])){
//                        System.out.println("allfoodDid[i]:"+allfoodDid[i]+"foodDid[z]:"+foodDid[z]);
                        allfoodName[x] = foodObj.getString("name");
                        allfoodDid[x] = foodObj.getString("did");
                        allfoodImg[x] = foodObj.getString("imgName");
//                        System.out.println("add:"+allfoodName[x]);
                        x++;
                    }
                }

            }
            for(int clear = 0; clear<foodDid.length; ++clear){
                if(foodDid[clear]!=null){
                    foodDid[clear] = null;
                }

//                System.out.println("clearfoodDid");
            }

            recipefood(allfoodName,allfoodDid,allfoodImg);
//            System.out.println("pass over2");



        }
        catch (Exception e){
            e.printStackTrace();
        }


    }

    public static void recipefood(String[] allfoodName, String[] allfoodDid, String[] allfoodImg){

        int tag = 0;
//        System.out.println("allRecipeFood:"+ Arrays.toString(allRecipeFood));

        for(int y = 0; y < allfoodName.length; ++y){
//            System.out.println("enter");
            if(allfoodName[y] != null){
                if(y==0){
                    tempallRecipeFood[recipe_food_num] = allfoodName[y];
                    allRecipeDid[recipe_food_num] = allfoodDid[y];
                    allRecipeFoodImg[recipe_food_num] = allfoodImg[y];
                    if(allfoodName[y+1] != null){
                        tempallRecipeFood[recipe_food_num] += ",";
                        allRecipeDid[recipe_food_num] += ",";
                        allRecipeFoodImg[recipe_food_num]+= ",";
                    }
//                    System.out.println("First"+allRecipeFood[recipe_food_num]);
                }
                else if(allfoodName[y+1] != null){
                    tempallRecipeFood[recipe_food_num] += (allfoodName[y]+",");
                    allRecipeDid[recipe_food_num] += (allfoodDid[y]+",");
                    allRecipeFoodImg[recipe_food_num] += (allfoodImg[y]+",");

//                    System.out.println("and"+allRecipeFood[recipe_food_num]);
                }
                else if(allfoodName[y] != null){
                    tempallRecipeFood[recipe_food_num] += allfoodName[y];
                    allRecipeDid[recipe_food_num] += allfoodDid[y];
                    allRecipeFoodImg[recipe_food_num] += allfoodImg[y];
//                    System.out.println("end"+allRecipeFood[recipe_food_num]);
                }
                else{
                    continue;
                }
            }
            else{
                break;
            }


        }
        recipe_food_num++;
//        System.out.println("recipe_food_num"+recipe_food_num);
//        System.out.println("allRecipeFood4:"+ Arrays.toString(allRecipeFood));
    }
    public static void autosearch(String searchtext){

        JSONObject obj = null;
        JSONArray recipe = null;
        JSONObject recipedata = null;

        String[] splitdata;

        try{
            obj = new JSONObject(result2);
            recipe = obj.getJSONArray("recipe");
        }catch (Exception e) {
            e.printStackTrace();
        }

        if(searchtext != null && tempallRecipeFood != null){
            count = 0;
            for (int i = 0; i < recipeindex; i++) {
                System.out.println("tempallRecipeFood.length:"+recipeindex);
                splitdata = tempallRecipeFood[i].split(",");
                for (int j = 0; j < splitdata.length; j++) {
                    System.out.println("splitdata[j]:"+splitdata[j]);
                    if(searchtext.equals(splitdata[j])){
                        System.out.println("match:"+searchtext+"="+splitdata[j]);
                        try {

                            recipedata = recipe.getJSONObject(i);
                            allRecipeFoodIndex[count] = i;
                            allRecipeId[count] = recipedata.getString("rid");
                            imgName[count] = recipedata.getString("imgName");
                            allRecipeSugar[count] = recipedata.getString("sugar");
                            allRecipeSalt[count] = recipedata.getString("salt");
                            allRecipeOil[count] = recipedata.getString("oil");
                            allRecipeNames[count] = recipedata.getString("name");
                            allRecipeSteps[count] =recipedata.getString("step");
                            allRecipeFood[count] = tempallRecipeFood[i];

                            detail_allRecipeFoodIndex[i] = count;

                            System.out.println("count"+count);
                            System.out.println("allRecipeId[count]"+allRecipeId[count]);
                            System.out.println("imgName[count]"+imgName[count]);
                            System.out.println("allRecipeSugar[count]"+allRecipeSugar[count]);
                            System.out.println("allRecipeSalt[count]"+allRecipeSalt[count]);
                            System.out.println("allRecipeOil[count]"+allRecipeOil[count]);
                            System.out.println("allRecipeNames[count]"+allRecipeNames[count]);
                            System.out.println("allRecipeSteps[count]"+allRecipeSteps[count]);
                            System.out.println("allRecipeFood[count]"+allRecipeFood[count]);

                            ++count;
                            break;
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        System.out.println("count"+count);

                    }
                    System.out.println("out if");

                }
                System.out.println("end one recipe");

            }
            System.out.println("end all recipe");
        }



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