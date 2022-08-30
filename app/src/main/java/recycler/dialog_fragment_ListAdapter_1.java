package recycler;

//import android.support.v7.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.tencent.nanodetncnn.MainActivity;
import com.tencent.nanodetncnn.R;
import com.tencent.nanodetncnn.dialog_fragment;


public class dialog_fragment_ListAdapter_1 extends RecyclerView.Adapter<dialog_fragment_ListAdapter_1.dialog_fragment_ListHolder_1> {

    String[] listDid;
    String[] listName;
    String[] listImg;
    boolean[] allfoodDid;
//    boolean[] original;
    int percentage;
    Context context;

    public dialog_fragment_ListAdapter_1(Context context, String[] listDids, String[] listNames, String[] listImgs, boolean[] allfoodDids){
        this.listDid = listDids;
        this.listName = listNames;
        this.listImg = listImgs;
        this.context = context;
        this.allfoodDid = allfoodDids;
    }

    @Override
    public dialog_fragment_ListHolder_1 onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(MainActivity.mContext2).inflate(R.layout.dialog_food_adapter,parent,false);
        dialog_fragment_ListHolder_1 holder = new dialog_fragment_ListHolder_1(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull dialog_fragment_ListHolder_1 holder, @SuppressLint("RecyclerView") int position) {
        holder.ef11_imageView.setImageResource(context.getApplicationContext().getResources().getIdentifier(String.valueOf(listImg[position]),"drawable", context.getPackageName()));
        holder.ef11_textView.setText(listName[position]);
//        holder.ef11_imageView.setImageResource(R.drawable.pic1);


        if(allfoodDid[position]){   // 預先勾選已有食材
            holder.checkedbox.setImageResource(R.drawable.checked);   // checked item == true
            dialog_fragment.checkeditems[position] = true;

        }else{
            holder.checkedbox.setImageResource(R.drawable.unchecked);
            dialog_fragment.checkeditems[position] = false;

        }


        holder.dialog_food_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!allfoodDid[position]){
                    if(dialog_fragment.checkeditems[position] == false){
                        holder.checkedbox.setImageResource(R.drawable.checked);
                        dialog_fragment.checkeditems[position] = true;
                    }
                    else{
                        holder.checkedbox.setImageResource(R.drawable.unchecked);
                        dialog_fragment.checkeditems[position] = false;
                    }
                    dialog_fragment.update_percentage.callOnClick();
                }



            }
        });
        dialog_fragment.update_percentage.callOnClick();

    }


    @Override
    public int getItemCount() {
        return listDid.length;
    }

    static class dialog_fragment_ListHolder_1 extends RecyclerView.ViewHolder {
        ImageView ef11_imageView;
        TextView ef11_textView;
        ImageView checkedbox;
        CardView dialog_food_card;

        public dialog_fragment_ListHolder_1(@NonNull View itemView){
            super(itemView);
            ef11_imageView = (ImageView) itemView.findViewById(R.id.dialog_food_img);
            ef11_textView= (TextView) itemView.findViewById(R.id.dialog_food_name);
            checkedbox = (ImageView) itemView.findViewById(R.id.uncheckedbox);
            dialog_food_card = (CardView) itemView.findViewById(R.id.dialog_food_card);
        }

    }
}
