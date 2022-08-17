package recycler;

//import android.support.v7.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
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

public class ListAdapter_2 extends RecyclerView.Adapter<ListAdapter_2.ListHolder> {

    String[] list;
    Context context;

    public ListAdapter_2(Context context, String[] list){
        this.context = context;
        this.list=list;
    }

    @Override
    public ListHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_adapter,parent,false);
        ListHolder holder = new ListHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ListHolder holder, @SuppressLint("RecyclerView") int position) {
//        holder.textView.setText(list[position]);
        holder.d1_textView.setText(list[position]);
        holder.d1_imageView.setImageResource(R.drawable.pic1);
//        holder.d1_imageView.setImageResource(context.getApplicationContext().getResources().getIdentifier(String.valueOf(imgName[position]),"drawable", context.getPackageName()));
        holder.d1_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(MainActivity.class_list_checked[position] != String.valueOf(1)) {
                    holder.d1_textView.setTextColor(Color.parseColor("#F2F2F3"));
                    holder.d1_card.setBackgroundColor(Color.parseColor("#A100455F"));
                    MainActivity.class_list_checked[position] = String.valueOf(1);
                }else{
                    holder.d1_textView.setTextColor(Color.parseColor("#00455F"));
                    holder.d1_card.setBackgroundColor(Color.parseColor("#F2F2F3"));
                    MainActivity.class_list_checked[position] = String.valueOf(0);
                }
                System.out.println(MainActivity.class_list_checked[position]);
                System.out.println(holder.d1_textView.getText());
            }
        });
    }


    @Override
    public int getItemCount() {
        return list.length;
    }

    static class ListHolder extends RecyclerView.ViewHolder {

        CardView d1_card;
        TextView d1_textView;
        ImageView d1_imageView;
        public ListHolder(@NonNull View itemView){
            super(itemView);
            d1_card = (CardView) itemView.findViewById(R.id.d1_card);
            d1_textView= (TextView) itemView.findViewById(R.id.d1_textView);
            d1_imageView = (ImageView) itemView.findViewById(R.id.d1_imageView);

        }

    }
}
