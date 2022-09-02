package com.tencent.nanodetncnn.model;

import android.util.Log;

import com.tencent.nanodetncnn.MyApplication;
import com.tencent.nanodetncnn.Consts;
import com.tencent.nanodetncnn.utils.MyUtils;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class FoodModelPraise {

    public ArrayList<FoodDicModel> foodDicModel_List = new ArrayList<FoodDicModel>();
    public ArrayList<FridgeModel> fridgeModel_List = new ArrayList<FridgeModel>();
    public ArrayList<FridgeHistoryModel> fridgeHistoryModel_List = new ArrayList<FridgeHistoryModel>();
    public ArrayList<RecipeModel> recipeModel_List = new ArrayList<RecipeModel>();
    public ArrayList<RecipeFoodModel> recipeFoodModel_List = new ArrayList<RecipeFoodModel>();


    public void praiseFoodJsonString(String jsonStr) {
        //
        try {
            JSONObject jSONObject = new JSONObject(jsonStr);
            {
                foodDicModel_List.clear();
                JSONArray rootJSONArray = jSONObject.getJSONArray("food_dic");
                for (int i = 0; i < rootJSONArray.length(); i++) {
                    JSONObject jsonO = rootJSONArray.getJSONObject(i);
                    FoodDicModel f = new FoodDicModel();
                    f.did = MyUtils.str2Long(jsonO.getString("did"));
                    f.name = jsonO.getString("name");
                    f.engName = jsonO.getString("engName");
                    f.expireDay = jsonO.getString("expireDay");
                    f.position = MyUtils.str2int(jsonO.getString("position"));
                    f.amount = MyUtils.str2int(jsonO.getString("amount"));
                    f.imgName = jsonO.getString("imgName");
                    foodDicModel_List.add(f);

                    //  Log.d("debug001", "praiseFoodJsonString ===>" + f.name+"  ,  "+f.imgName);
                }
            }

            {
                fridgeModel_List.clear();
                JSONArray rootJSONArray = jSONObject.getJSONArray("fridge");
                for (int i = 0; i < rootJSONArray.length(); i++) {
                    JSONObject jsonO = rootJSONArray.getJSONObject(i);
                    FridgeModel f = new FridgeModel();

                    f.fid = MyUtils.str2Long(jsonO.getString("fid"));
                    f.did = MyUtils.str2Long(jsonO.getString("did"));
//                    f.position = jsonO.getString("position");
                    f.position = MyUtils.str2int(jsonO.getString("position"));
                    f.insertDate = jsonO.getString("insertDate");
                    f.alertDate = jsonO.getString("alertDate");
                    f.expireDate = jsonO.getString("expireDate");
                    f.uid = jsonO.getString("uid");
                    f.amount = MyUtils.str2int(jsonO.getString("amount"));
                    f.memo = jsonO.getString("memo");

                    fridgeModel_List.add(f);

                    // Log.d("debug001", "praiseFridgeJsonString ===>" + f.fid);
                }
            }


            {
                fridgeHistoryModel_List.clear();
                JSONArray rootJSONArray = jSONObject.getJSONArray("fridge_history");
                for (int i = 0; i < rootJSONArray.length(); i++) {
                    JSONObject jsonO = rootJSONArray.getJSONObject(i);
                    FridgeHistoryModel f = new FridgeHistoryModel();
                    f.hid = MyUtils.str2Long(jsonO.getString("hid"));
                    f.fid = MyUtils.str2Long(jsonO.getString("fid"));
//                    f.uid = MyUtils.str2Long(jsonO.getString("uid"));
                    f.uid = jsonO.getString("uid");
                    f.updateDate = jsonO.getString("updateDate");
                    f.updateNum = MyUtils.str2int(jsonO.getString("updateNum"));
                    f.updateAmount = MyUtils.str2int(jsonO.getString("updateAmount"));

                    fridgeHistoryModel_List.add(f);
                    // Log.d("debug001", "praiseFridgeJsonString ===>" + f.fid);
                }
            }




            {
                recipeModel_List.clear();
                JSONArray rootJSONArray = jSONObject.getJSONArray("recipe");
                for (int i = 0; i < rootJSONArray.length(); i++) {
                    JSONObject jsonO = rootJSONArray.getJSONObject(i);
                    RecipeModel f = new RecipeModel();
                    f.rid = MyUtils.str2Long(jsonO.getString("rid"));
                    f.name = jsonO.getString("name");
                    f.step = jsonO.getString("step");
                    f.imgName = jsonO.getString("imgName");
                    f.sugar = jsonO.getString("sugar");
                    f.salt = jsonO.getString("salt");
                    f.oil = jsonO.getString("oil");
                    recipeModel_List.add(f);

                    // Log.d("debug001", "praiseFridgeJsonString ===>" + f.fid);
                }
            }



            {
                recipeFoodModel_List.clear();
                JSONArray rootJSONArray = jSONObject.getJSONArray("recipe_food");
                for (int i = 0; i < rootJSONArray.length(); i++) {
                    JSONObject jsonO = rootJSONArray.getJSONObject(i);
                    RecipeFoodModel f = new RecipeFoodModel();
                    f.rid = MyUtils.str2Long(jsonO.getString("rid"));
                    f.rdid = MyUtils.str2Long(jsonO.getString("rdid"));
                    f.did = MyUtils.str2Long(jsonO.getString("did"));
                    recipeFoodModel_List.add(f);

                    // Log.d("debug001", "praiseFridgeJsonString ===>" + f.fid);
                }
            }


        } catch (Exception e) {
            Log.e("debug", "--CityModelPraise -Error parser ----: " + e.toString());
        }
    }



    public ArrayList<FridgeHistoryModel> getFridgeHistoryByDid(long fid) {
        ArrayList<FridgeHistoryModel> list = new ArrayList<FridgeHistoryModel>();
        for (FridgeHistoryModel fg : fridgeHistoryModel_List) {
            if (fid == fg.fid) {

                list.add(fg);
//                FoodDicModel fd = getFoodNameById(fg.did); // 找出食物字典
//                if (fd != null) {
//                    FridgeFoodSumModel ff = new FridgeFoodSumModel();
//                    ff.fid = fg.fid;
//                    ff.did = fd.did;
//                    ff.name = fd.name;
//                    ff.position = fd.position;
//                    ff.imgName = fd.imgName;
//                    ff.alertDate = fg.alertDate;
//                    ff.expireDate = fg.expireDate;
//                    ff.userName = fg.uid;
//                    ff.amount = fg.amount;
//                    fridgeFoodSum_List.add(ff);
//                } else {
//                    Log.d("debug001", "can not find food dic!!!");
//                }
            }
        }
        return list;
    }



    //依did查詢食材在冰箱中的資料
    public ArrayList<FridgeFoodSumModel> getAllFridgeFoodByDid(long did) {
        ArrayList<FridgeFoodSumModel> fridgeFoodSum_List = new ArrayList<FridgeFoodSumModel>();
        for (FridgeModel fg : fridgeModel_List) {
            if (did == fg.did) {
                FoodDicModel fd = getFoodNameById(fg.did); // 找出食物字典
                if (fd != null) {
                    FridgeFoodSumModel ff = new FridgeFoodSumModel();
                    ff.fid = fg.fid;
                    ff.did = fd.did;
                    ff.name = fd.name;
                    ff.position = fd.position;
                    ff.imgName = fd.imgName;
                    ff.insertDate = fg.insertDate;
                    ff.alertDate = fg.alertDate;
                    ff.expireDate = fg.expireDate;
                    ff.userName = fg.uid;
                    ff.amount = fg.amount;
                    fridgeFoodSum_List.add(ff);
                } else {
                    Log.d("debug001", "can not find food dic!!!");
                }
            }
        }
        return fridgeFoodSum_List;
    }


    public ArrayList<FridgeFoodSumModel> getAllFridgeFood(int searchType) {
        ArrayList<FridgeFoodSumModel> fridgeFoodSum_List = new ArrayList<FridgeFoodSumModel>();

        for (FridgeModel fg : fridgeModel_List) {
            boolean goAddItem = (searchType == Consts.Fridge_Food_all) ||  // 全部
                    (searchType == Consts.Fridge_Food_Ready_to_eat && MyUtils.getTwoDay(fg.alertDate, MyUtils.getNowDateShortString()) < 0 && MyUtils.getTwoDay(fg.expireDate, MyUtils.getNowDateShortString()) >= 0) ||
                    (searchType == Consts.Fridge_Food_Expired && MyUtils.getTwoDay(fg.expireDate, MyUtils.getNowDateShortString()) < 0);
            if (goAddItem) {
                FoodDicModel fd = getFoodNameById(fg.did); // 找出食物字典
                if (fd != null) {
                    FridgeFoodSumModel ff = new FridgeFoodSumModel();
                    ff.fid = fg.fid;
                    ff.did = fd.did;
                    ff.name = fd.name;
                    ff.position = fd.position;
                    ff.imgName = fd.imgName;
                    ff.alertDate = fg.alertDate;
                    ff.expireDate = fg.expireDate;
                    ff.userName = fg.uid;
                    ff.amount = fg.amount;
                    fridgeFoodSum_List.add(ff);
                } else {
                    Log.d("debug001", "can not find food dic!!!");
                }
            }

        }
        return fridgeFoodSum_List;
    }


    //計算統計項目總量
    public ArrayList<FridgeFoodSumModel> getAllFridgeFoodSum(int foodType) {
        ArrayList<FridgeFoodSumModel> fridgeFoodSum_List = new ArrayList<FridgeFoodSumModel>();
        int food_color = foodType;
        //統計冰箱全部數量
        for (FridgeModel fg : fridgeModel_List) {

            boolean goAddItem = false;

            switch (foodType) {
                case Consts.Fridge_Food_all:
                    goAddItem = true;
                    break;
                case Consts.Fridge_Food_Ready_to_eat:
                    goAddItem = MyUtils.getTwoDay(fg.alertDate, MyUtils.getNowDateShortString()) < 0 &&
                            MyUtils.getTwoDay(fg.expireDate, MyUtils.getNowDateShortString()) >= 0;
                    //  Log.d("debug001", "xxxxx-----");
                    //  Log.d("debug001", "alertDate===>" + fg.alertDate + ":" + MyUtils.getTwoDay(fg.alertDate, MyUtils.getNowDateShortString()));
                    //  Log.d("debug001", "222expireDate===>" + fg.expireDate + ":" + MyUtils.getTwoDay(fg.expireDate, MyUtils.getNowDateShortString()));
                    break;
                case Consts.Fridge_Food_Expired:
                    goAddItem = MyUtils.getTwoDay(fg.expireDate, MyUtils.getNowDateShortString()) < 0;
                    break;
            }


            FoodDicModel fd = getFoodNameById(fg.did); // 找出食物字典
            if (fd == null) {
                fd = new FoodDicModel();
                fd.name = "did:" + fg.did + ":無food字典";

                Log.d("debug001", "Error===>" + fd.name);
                goAddItem = false;
            }
            //  Log.d("debug001", foodType+"-- "+goAddItem +" ===> "+"品項："+fg.position +",alertDate:"+fg.alertDate +",expireDate"+fg.expireDate +",amount:"+fg.amount +",");

            if (goAddItem) {
                boolean isNewOne = true;
                for (FridgeFoodSumModel fdgg : fridgeFoodSum_List) {
                    if (fdgg.did == fg.did) { //找出是否有項目
                        fdgg.amount += fg.amount;


                        //全部資料時 - 是以最差的當顏色
                        if (foodType == Consts.Fridge_Food_all) {
                            int tt_color =   MyUtils.checkColorByType(fdgg.alertDate,fdgg.expireDate);
                            if (tt_color > fdgg.food_color)
                                fdgg.food_color = tt_color;
                        }
//                        if (fdgg.did == 14) {
//                            Log.d("debug001", "---color:" + fdgg.food_color + "===" + "===>expire:"
//                                    + fdgg.expireDate + "-->" + MyUtils.getTwoDay(fdgg.expireDate, MyUtils.getNowDateShortString()) +
//                                    ",alert:" + fdgg.alertDate + "-->" + MyUtils.getTwoDay(fdgg.alertDate, MyUtils.getNowDateShortString()));
//                        }

                        isNewOne = false;
                    }
                }

                //沒有品項去新增
                if (isNewOne) {
                    FridgeFoodSumModel ff = new FridgeFoodSumModel();
                    ff.fid = fg.fid;
                    ff.did = fd.did;
                    ff.position = fd.position;
                    ff.name = fd.name;
                    ff.imgName = fd.imgName;
                    ff.alertDate = fg.alertDate;
                    ff.expireDate = fg.expireDate;
                    ff.userName = fg.uid;
                    ff.amount = fg.amount;//在原始 food_dic 後台計算的總量
//                    ff.amount_backend = fg.amount;//在原始 food_dic 後台計算的總量
                    ff.food_type = foodType;
                    ff.food_color = food_color;

                    if (foodType == Consts.Fridge_Food_all) {


                        ff.food_color =  MyUtils.checkColorByType(ff.alertDate,ff.expireDate);
//                        if (ff.did == 14) {
//                            Log.d("debug001", "---color:" + ff.food_color + "===" + "===>expire:"
//                                    + ff.expireDate + "-->" + MyUtils.getTwoDay(ff.expireDate, MyUtils.getNowDateShortString()) +
//                                    ",alert:" + ff.alertDate + "-->" + MyUtils.getTwoDay(ff.alertDate, MyUtils.getNowDateShortString()));
//                        }
                    }

                    fridgeFoodSum_List.add(ff);

//                    Log.d("debug001", "fridge===>" + ff.name + ":" + ff.did);
                } else {
                    Log.d("debug001", "can not find food dic!!!");

                }
            }
        }

        return fridgeFoodSum_List;
    }





    public ArrayList<RecipeModel> searchRecipeByFood(long did) {
        ArrayList<RecipeModel> tList = new ArrayList<>();
        for (RecipeFoodModel fd : recipeFoodModel_List) {
            if (fd.did == did) {
                RecipeModel rr = getRecipeByRid(fd.rid);
                if(rr != null)
                tList.add(rr);
            }
        }
        return tList;
    }

    private RecipeModel getRecipeByRid(long rid){
        for (RecipeModel fd : recipeModel_List) {
            if (fd.rid == rid) {
                return fd;
            }

        }
        return null;
    }

    private FoodDicModel getFoodNameById(long did) {
        for (FoodDicModel fd : foodDicModel_List) {
            if (fd.did == did) {
                return fd;
            }

        }
        return null;
    }


    public void addHistoryList(FridgeFoodSumModel ffs){
        FridgeHistoryModel fh = new FridgeHistoryModel();
        fh.updateNum = -1 * ffs.amount;
        fh.fid = ffs.fid;
        fh.uid = MyApplication.nowUser;
        fh.updateDate = MyUtils.getNowDateTimeString();

        fridgeHistoryModel_List .add(fh);
//        fh.hid = ffs.
    }

}

