package com.tencent.nanodetncnn;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;
import java.util.Objects;



public class AllRecipeList {

    public static String[] allRecipeId = new String[500];
    public static String[] imgName = new String[500];
    public static String[] allRecipeNames = new String[500];
    public static String[] allRecipeSteps = new String[500];

    public static String[] allRecipeSugar = new String[500];
    public static String[] allRecipeSalt = new String[500];
    public static String[] allRecipeOil = new String[500];



    public static String[] recipefoodRid = new String[500];
    public static String[] recipefoodDid = new String[500];

    public static String[] allrecipefoodDid = new String[500];

    public static String[] allfoodDid = new String[800];
    public static String[] allfoodName = new String[800];
    public static String[] allfoodImg = new String[800];

    public static String[] allRecipeFood= new String[500];
    public static String[] allRecipeDid= new String[500];
    public static String[] allRecipeFoodImg = new String[500];

    public static String[] finalallRecipeFood= new String[500];
    public static int recipe_food_num = 0;
    public static int recipeindex = 0;


    public static String[] allfoodhistoryDid = new String[1000];
    public static String[] allfoodhistoryName = new String[1000];

    public static String[] tempRecipeId = new String[500];


    public static String result2 = null;
    public static String searchtext2 = null;



    public static void search(String searchtext) {


        recipe_food_num = 0;
        searchtext2 = searchtext;



        recipe(searchtext2);


    }


    public static void result(String result) {
        result2 = result;
    }



    public static void recipe(String searchtext3) {



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
            if(searchtext3 == null){
                allRecipeId[j] = null;
                imgName[j] = null;
                allRecipeSugar[j] = null;
                allRecipeSalt[j] = null;
                allRecipeOil[j] = null;
                allRecipeNames[j] = null;
                allRecipeFood[j] = null;
            }

            for (int i =0; i< recipe.length();++i){ // 從第一筆食譜看
                recipedata = recipe.getJSONObject(i); //得到單筆食譜資料

                if(recipedata.getString("name").contains(searchtext3)){ //當前搜尋字與該筆食譜名稱相同


                    allRecipeId[j] = recipedata.getString("rid");
                    imgName[j] = recipedata.getString("imgName");
                    allRecipeSugar[j] = recipedata.getString("sugar");
                    allRecipeSalt[j] = recipedata.getString("salt");
                    allRecipeOil[j] = recipedata.getString("oil");
                    allRecipeNames[j] = recipedata.getString("name");
                    allRecipeSteps[j] =recipedata.getString("step");

                    ++j;
                    ++recipeindex;


                }
                else{
                   continue;
                }


            }
            for(int a=0; a < recipe_food.length(); ++a){       // 從第一筆食譜食材看


                recipefooddata = recipe_food.getJSONObject(a);  //得到單筆食譜食材資料
                recipefoodRid[a] = recipefooddata.getString("rid");  //紀錄所有食譜食材的rid
                tempRid = recipefoodRid[a];   //紀錄現在進入的食譜食材rid

                if(currentRid == null ||currentRid.equals(tempRid)){   //第一次紀錄或目前進入的食譜食材rid未改變，則currentRid為現在進入的食譜食材rid

                    currentRid = tempRid;

                }
                else{
                    foodDic(recipefoodDid);
                    for(int clear = 0; clear<allfoodName.length; ++clear){
                        if(allfoodName[clear]!=null){
                            allfoodName[clear] = null;
                        }

                    }
                    for(int clear = 0; clear<recipefoodDid.length; ++clear){
                        if(recipefoodDid[clear]!=null){
                            recipefoodDid[clear] = null;
                        }

                    }

                    currentRid = tempRid;
                    z = 0;
                }


                for(int i = 0; i < recipeindex; ++i){
                    if(recipefoodRid[a].equals(allRecipeId[i]) && recipefoodRid[a].equals(currentRid)){


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
            int tag = 0;
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

        for(int y = 0; y<allfoodName.length; ++y){
//            System.out.println("enter");
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
//                    System.out.println("First"+allRecipeFood[recipe_food_num]);
                }
                else if(allfoodName[y+1] != null){
                    allRecipeFood[recipe_food_num] += (allfoodName[y]+",");
                    allRecipeDid[recipe_food_num] += (allfoodDid[y]+",");
                    allRecipeFoodImg[recipe_food_num] += (allfoodImg[y]+",");

//                    System.out.println("and"+allRecipeFood[recipe_food_num]);
                }
                else if(allfoodName[y] != null){
                    allRecipeFood[recipe_food_num] += allfoodName[y];
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