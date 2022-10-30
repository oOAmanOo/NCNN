package com.tencent.nanodetncnn;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MergeListAdapter extends RecyclerView.Adapter<MergeListAdapter.MyViewHolder>{

    private final RecyclerViewInterface recyclerViewInterface;

    Context context;
    public static String mode;
    ArrayList Recipeimages, Recipenames,Recipefood, Recipesugar, Recipesalt, Recipeoil;


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(final ViewGroup parent, int viewType) {

        return new MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.merge_list_adapter,parent,false),recyclerViewInterface);

    }

    public MergeListAdapter(Context context, ArrayList Recipeimages, ArrayList Recipenames, ArrayList Recipefood, ArrayList Recipesugar, ArrayList Recipesalt, ArrayList Recipeoil, RecyclerViewInterface recyclerViewInterface, String currentMode){

        this.context = context;
        this.Recipeimages = Recipeimages;
        this.Recipenames = Recipenames;
        this.Recipefood = Recipefood;
        this.Recipesugar = Recipesugar;
        this.Recipesalt = Recipesalt;
        this.Recipeoil = Recipeoil;
        this.recyclerViewInterface = recyclerViewInterface;
        mode = currentMode;


        }




    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        if(mode == "normal"){
            holder.recipe_name.setText(NormalRecipeList.allRecipeNames[position]);
            holder.recipe_food.setText(NormalRecipeList.allRecipeFood[position]);
            if(NormalRecipeList.allRecipeSugar[position] == null){
                holder.recipe_tag.setText(null);
            }
            else{
                holder.recipe_tag.setText(NormalRecipeList.allRecipeSugar[position]+"糖"+"/"+NormalRecipeList.allRecipeSalt[position]+"鹽"+"/"+NormalRecipeList.allRecipeOil[position]+"油");
            }
            holder.recipe_img.setImageResource(context.getApplicationContext().getResources().getIdentifier(String.valueOf(NormalRecipeList.imgName[position]),"drawable", context.getPackageName()));

        }
        else if(mode == "manage"){
            holder.recipe_name.setText(ManageRecipeList.allRecipeNames[position]);
            holder.recipe_food.setText(ManageRecipeList.allRecipeFood[position]);
            if(ManageRecipeList.allRecipeSugar[position] == null){
                holder.recipe_tag.setText(null);
            }
            else{
                holder.recipe_tag.setText(ManageRecipeList.allRecipeSugar[position]+"糖"+"/"+ManageRecipeList.allRecipeSalt[position]+"鹽"+"/"+ManageRecipeList.allRecipeOil[position]+"油");
            }

            holder.recipe_img.setImageResource(context.getApplicationContext().getResources().getIdentifier(String.valueOf(ManageRecipeList.imgName[position]),"drawable", context.getPackageName()));
        }
        else if(mode == "fitness"){
            holder.recipe_name.setText(FitnessRecipeList.allRecipeNames[position]);
            holder.recipe_food.setText(FitnessRecipeList.allRecipeFood[position]);
            if(FitnessRecipeList.allRecipeSugar[position] == null){
                holder.recipe_tag.setText(null);
            }
            else{
                holder.recipe_tag.setText(FitnessRecipeList.allRecipeSugar[position]+"糖"+"/"+FitnessRecipeList.allRecipeSalt[position]+"鹽"+"/"+FitnessRecipeList.allRecipeOil[position]+"油");
            }

            holder.recipe_img.setImageResource(context.getApplicationContext().getResources().getIdentifier(String.valueOf(FitnessRecipeList.imgName[position]),"drawable", context.getPackageName()));
        }
        else if(mode == "relax"){
            holder.recipe_name.setText(RelaxRecipeList.allRecipeNames[position]);
            holder.recipe_food.setText(RelaxRecipeList.allRecipeFood[position]);
            if(RelaxRecipeList.allRecipeSugar[position] == null){
                holder.recipe_tag.setText(null);
            }
            else{
                holder.recipe_tag.setText(RelaxRecipeList.allRecipeSugar[position]+"糖"+"/"+RelaxRecipeList.allRecipeSalt[position]+"鹽"+"/"+RelaxRecipeList.allRecipeOil[position]+"油");
            }

            holder.recipe_img.setImageResource(context.getApplicationContext().getResources().getIdentifier(String.valueOf(RelaxRecipeList.imgName[position]),"drawable", context.getPackageName()));
        }
        else if(mode == "search"){
            if(AllRecipeList.allRecipeNames[position] == null){
                holder.recipe_name.setText(null);
            }
            else{
                holder.recipe_name.setText(AllRecipeList.allRecipeNames[position]);
            }
            if(AllRecipeList.allRecipeFood[position] == null){
                holder.recipe_food.setText(null);
            }
            else{
                holder.recipe_food.setText(AllRecipeList.allRecipeFood[position]);
            }

            if(AllRecipeList.allRecipeSugar[position] == null){
                holder.recipe_tag.setText(null);
            }
            else{
                holder.recipe_tag.setText(AllRecipeList.allRecipeSugar[position]+"糖"+"/"+AllRecipeList.allRecipeSalt[position]+"鹽"+"/"+AllRecipeList.allRecipeOil[position]+"油");
            }
            if(AllRecipeList.imgName[position] ==null){
                holder.recipe_img.setImageResource(R.drawable.ic_baseline_access_time_24);
            }
            else{
                holder.recipe_img.setImageResource(context.getApplicationContext().getResources().getIdentifier(String.valueOf(AllRecipeList.imgName[position]),"drawable", context.getPackageName()));
            }
        }else if(mode == "autosearch"){
            if(AutoRecipeList.allRecipeNames[position] == null){
                holder.recipe_name.setText(null);
            }
            else{
                holder.recipe_name.setText(AutoRecipeList.allRecipeNames[position]);
            }
            if(AutoRecipeList.allRecipeFood[position] == null){
                holder.recipe_food.setText(null);
            }
            else{
                holder.recipe_food.setText(AutoRecipeList.allRecipeFood[position]);
            }

            if(AutoRecipeList.allRecipeSugar[position] == null){
                holder.recipe_tag.setText(null);
            }
            else{
                holder.recipe_tag.setText(AutoRecipeList.allRecipeSugar[position]+AutoRecipeList.allRecipeSalt[position]+AutoRecipeList.allRecipeOil[position]);
            }

            if(AutoRecipeList.imgName[position] ==null){
                holder.recipe_img.setImageResource(R.drawable.ic_baseline_access_time_24);
            }
            else{
                holder.recipe_img.setImageResource(context.getApplicationContext().getResources().getIdentifier(String.valueOf(AutoRecipeList.imgName[position]),"drawable", context.getPackageName()));
            }
        }
    }



    @Override
    public int getItemCount() {
        return Recipenames.size();
    }

    static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView recipe_name;
        TextView recipe_food;
        TextView recipe_tag;
        ImageView recipe_img;



        public MyViewHolder(@NonNull View itemView,RecyclerViewInterface recyclerViewInterface) {

            super(itemView);

            recipe_name = itemView.findViewById(R.id.merge_recipe_name);
            recipe_food = itemView.findViewById(R.id.merge_recipe_food);
            recipe_img = (ImageView)itemView.findViewById(R.id.merge_recipe_img);
            recipe_tag = itemView.findViewById(R.id.merge_recipe_tag);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(recyclerViewInterface != null){
                        int pos = getAdapterPosition();

                        if(pos != RecyclerView.NO_POSITION){
                            recyclerViewInterface.onItemClick(pos);
//                            NormalDetailFragment.postion(pos);
//                            MergeDetailFragment.postion(AutoRecipeList.allRecipeFoodIndex[pos]);
                            MergeDetailFragment.postion(pos);
                            MergeDetailFragment.getmode(mode);
                        }
                    }
                }
            });

        }
    }




}
