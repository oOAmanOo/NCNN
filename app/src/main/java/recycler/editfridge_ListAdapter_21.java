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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.tencent.nanodetncnn.MainActivity;
import com.tencent.nanodetncnn.R;
import com.tencent.nanodetncnn.editfridge_dfragment2;


public class editfridge_ListAdapter_21 extends RecyclerView.Adapter<editfridge_ListAdapter_21.editfridge_ListHolder_21> {

    String[] editfridge_did;
    String[] editfridge_name;
    String[] editfridge_imgName;
    public static int[] editfridgeAmountText;

    Context context;

    public editfridge_ListAdapter_21(Context context, String[] editfridge_did, String[] editfridge_name, String[] editfridge_imgName){
        this.editfridge_did = editfridge_did;
        this.editfridge_name = editfridge_name;
        this.editfridge_imgName = editfridge_imgName;
        this.editfridgeAmountText = new int[editfridge_did.length];
        for (int i = 0; i < editfridge_did.length; i++) {
            this.editfridgeAmountText[i] = 1;
        }
        this.context = context;
    }

    @Override
    public editfridge_ListHolder_21 onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(MainActivity.mContext2).inflate(R.layout.editfridge_adapter21,parent,false);
        editfridge_ListHolder_21 holder = new editfridge_ListHolder_21(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull editfridge_ListHolder_21 holder, @SuppressLint("RecyclerView") int position) {
        holder.ef21_imageView.setImageResource(context.getApplicationContext().getResources().getIdentifier(String.valueOf(editfridge_imgName[position]),"drawable", context.getPackageName()));
        holder.ef21_textView.setText(editfridge_name[position]);
//        holder.d2_imageView.setImageResource(R.drawable.pic1);
        String[] editfridgedb_fid = new String[MainActivity.editfridge_count[position]];
        String[] editfridgedb_did = new String[MainActivity.editfridge_count[position]];
        String[] editfridgedb_position = new String[MainActivity.editfridge_count[position]];
        String[] editfridgedb_insertDate = new String[MainActivity.editfridge_count[position]];
        String[] editfridgedb_expireDate = new String[MainActivity.editfridge_count[position]];
        String[] editfridgedb_uid = new String[MainActivity.editfridge_count[position]];
        int[] editfridgedb_amount = new int[MainActivity.editfridge_count[position]];
        String[] editfridgedb_memo = new String[MainActivity.editfridge_count[position]];
        int[] editfridgedb_editnum = new int[MainActivity.editfridge_count[position]];
        int count_index = 0;
        for (int i = 0; i < position; i++) {
            count_index += MainActivity.editfridge_count[i];
        }
        for (int i = 0; i < MainActivity.editfridge_count[position]  ; i++) {
            editfridgedb_fid[i] = MainActivity.editfridgedb_fid[count_index + i];
            editfridgedb_did[i] = MainActivity.editfridgedb_did[count_index + i];
            editfridgedb_position[i] = MainActivity.editfridgedb_position[count_index + i];
            editfridgedb_insertDate[i] = MainActivity.editfridgedb_insertDate[count_index + i];
            editfridgedb_expireDate[i] = MainActivity.editfridgedb_expireDate[count_index + i];
            editfridgedb_uid[i] = MainActivity.editfridgedb_uid[count_index + i];
            editfridgedb_amount[i] = MainActivity.editfridgedb_amount[count_index + i];
            editfridgedb_memo[i] = MainActivity.editfridgedb_memo[count_index + i];
            editfridgedb_editnum[i] = MainActivity.editfridgedb_editnum[count_index + i];
        }
        editfridge_ListAdapter_22 editfridge_listAdapter_22 = new editfridge_ListAdapter_22(editfridge_dfragment2.nested_f, position, editfridge_name[position], editfridge_imgName[position], count_index, editfridgedb_fid, editfridgedb_did, editfridgedb_position, editfridgedb_insertDate, editfridgedb_expireDate, editfridgedb_uid, editfridgedb_amount, editfridgedb_memo, editfridgedb_editnum);
        holder.ef21_recyclerview.setLayoutManager(new LinearLayoutManager(holder.ef21_recyclerview.getContext()));
        holder.ef21_recyclerview.setAdapter(editfridge_listAdapter_22);
    }


    @Override
    public int getItemCount() {
        return editfridge_did.length;
    }

    static class editfridge_ListHolder_21 extends RecyclerView.ViewHolder {
        ImageView ef21_imageView;
        TextView ef21_textView;
        RecyclerView ef21_recyclerview;
        public editfridge_ListHolder_21(@NonNull View itemView){
            super(itemView);
            ef21_imageView = (ImageView) itemView.findViewById(R.id.ef21_imageView);
            ef21_textView= (TextView) itemView.findViewById(R.id.ef21_textView);
            ef21_recyclerview = (RecyclerView) itemView.findViewById(R.id.ef21_recyclerview);
        }

    }
}
