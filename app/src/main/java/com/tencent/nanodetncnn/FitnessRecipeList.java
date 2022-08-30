package com.tencent.nanodetncnn;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Objects;


public class FitnessRecipeList {
    public static String[] allRecipeId = new String[500];
    public static String[] imgName = new String[500];
    public static String[] allRecipeNames = new String[500];
    public static String[] allRecipeSteps = new String[500];

    public static String[] allRecipeSugar = new String[500];
    public static String[] allRecipeSalt = new String[500];
    public static String[] allRecipeOil = new String[500];



    public static String[] recipefoodRid = new String[500];
    public static String[] recipefoodDid = new String[500];

    public static String[] allfoodDid = new String[800];
    public static String[] allfoodName = new String[800];
    public static String[] allfoodImg = new String[800];

    public static String[] allRecipeFood= new String[500];
    public static String[] allRecipeDid= new String[500];
    public static String[] allRecipeFoodImg = new String[500];

    public static int recipe_food_num = 0;
    public static int recipeindex = 0;


    public static String[] allfoodhistoryDid = new String[1000];
    public static String[] allfoodhistoryName = new String[1000];


    public static void recipe(String result){

        JSONObject obj = null;
        JSONArray recipe = null;
        JSONArray recipe_food = null;
        JSONObject recipedata = null;
        JSONObject recipefooddata = null;
        int count = 0;
        String tempRid = null;//現在進來的RID
        String currentRid = null;//正在使用的RID


        try {
            obj = new JSONObject(result);
            recipe = obj.getJSONArray("recipe");
            recipe_food = obj.getJSONArray("recipe_food");


            recipeindex = 0;
            int z = 0;
            for (int i =0; i< recipe.length();++i){ // 從第一筆食譜看
                recipedata = recipe.getJSONObject(i); //得到單筆食譜資料

                if(!recipedata.getString("sugar").equals("多糖")){
                    imgName[recipeindex] = recipedata.getString("imgName");
                    allRecipeSugar[recipeindex] = recipedata.getString("sugar");
                    allRecipeSalt[recipeindex] = recipedata.getString("salt");
                    allRecipeOil[recipeindex] = recipedata.getString("oil");
                    allRecipeNames[recipeindex] = recipedata.getString("name");
                    allRecipeId[recipeindex] = recipedata.getString("rid");
                    allRecipeSteps[recipeindex] =recipedata.getString("step");
                    ++recipeindex;

                }
                else{
                    continue;
                }



            }


            for(int a = 0; a < recipe_food.length(); ++a){

                recipefooddata = recipe_food.getJSONObject(a);

                recipefoodRid[a] = recipefooddata.getString("rid").toString();

                tempRid = recipefoodRid[a];


                if(Objects.equals(currentRid, null) || currentRid.equals(tempRid)){
                    currentRid = tempRid;

                }
                else{

                    foodDic(result,recipefoodDid);

                    for(int clear = 0; clear<allfoodName.length; ++clear){
                        if(allfoodName[clear]!=null){
                            allfoodName[clear] = null;
                        }

                    }
                    currentRid = tempRid;
                    z = 0;

                }



                for(int x=0; x<allRecipeId.length; x++){


                    if (recipefoodRid[a].equals(allRecipeId[x]) && recipefoodRid[a].equals(currentRid) ){


                        recipefoodDid[z] = recipefooddata.getString("did");


                        ++z;
                    }
                    else{
                        continue;
                    }



                }




            }
            foodDic(result,recipefoodDid);

            recipefoodDid = null;





        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    public static void findDic(){

    }

    public static void foodDic(String result, String[] foodDid) {
        JSONObject obj;
        JSONArray  foodArray= null;
        JSONObject  foodObj= null;


        try {

            obj = new JSONObject(result);
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
//    public static void foodDic(String result){
//
//
}