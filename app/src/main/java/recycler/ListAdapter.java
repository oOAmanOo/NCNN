package recycler;

//import android.support.v7.widget.RecyclerView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.tencent.nanodetncnn.MainActivity;
import com.tencent.nanodetncnn.R;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ListHolder> {

    String[] list;
    Context context;

    public ListAdapter(Context context, String[] list){
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
    public void onBindViewHolder(@NonNull ListHolder holder, int position) {
//        holder.textView.setText(list[position]);
        holder.checkBox.setText(list[position]);
        holder.checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(holder.checkBox.isChecked()) {
                    MainActivity.class_list_checked[position] = String.valueOf(1);
                }else{
                    MainActivity.class_list_checked[position] = String.valueOf(0);
                }
                System.out.println(MainActivity.class_list_checked[position]);
                System.out.println(holder.checkBox.getText());
            }
        });
    }


    @Override
    public int getItemCount() {
        return list.length;
    }

    static class ListHolder extends RecyclerView.ViewHolder {

//        TextView textView;
        CheckBox checkBox;

        public ListHolder(@NonNull View itemView){
            super(itemView);
            checkBox = (CheckBox) itemView.findViewById(R.id.checkBox);
//            textView= (TextView) itemView.findViewById(R.id.textView);
        }

    }
}
