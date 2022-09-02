package recycler;

//import android.support.v7.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.tencent.nanodetncnn.MainActivity;
import com.tencent.nanodetncnn.R;


public class editfridge_ListAdapter_22 extends RecyclerView.Adapter<editfridge_ListAdapter_22.editfridge_ListHolder_22> {
    int editfridge_index21;
    int count_index;
    String editfridge_name;
    String editfridge_imgName;
    String[] editfridgedb_fid;
    String[] editfridgedb_did;
    String[] editfridgedb_position;
    String[] editfridgedb_insertDate;
    String[] editfridgedb_expireDate;
    String[] editfridgedb_uid;
    int[] editfridgedb_amount;
    String[] editfridgedb_memo;
    int[] editfridgedb_editnum;

    Context context;

    public editfridge_ListAdapter_22(FragmentActivity context, int position, String editfridge_name, String editfridge_imgName, int count_index, String[] editfridgedb_fid, String[] editfridgedb_did, String[] editfridgedb_position, String[] editfridgedb_insertDate, String[] editfridgedb_expireDate, String[] editfridgedb_uid, int[] editfridgedb_amount, String[] editfridgedb_memo, int[] editfridgedb_editnum){
        this.editfridge_index21 = position;
        this.editfridge_name = editfridge_name;
        this.editfridge_imgName = editfridge_imgName;
        this.count_index = count_index;
        this.editfridgedb_fid = editfridgedb_fid;
        this.editfridgedb_did = editfridgedb_did;
        this.editfridgedb_position = editfridgedb_position;
        this.editfridgedb_insertDate = editfridgedb_insertDate;
        this.editfridgedb_expireDate = editfridgedb_expireDate;
        this.editfridgedb_uid = editfridgedb_uid;
        this.editfridgedb_amount = editfridgedb_amount;
        this.editfridgedb_memo = editfridgedb_memo;
        this.editfridgedb_editnum = editfridgedb_editnum;
        this.context = context;
    }

    @Override
    public editfridge_ListHolder_22 onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(MainActivity.mContext2).inflate(R.layout.editfridge_adapter22,parent,false);
        editfridge_ListHolder_22 holder = new editfridge_ListHolder_22(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull editfridge_ListHolder_22 holder, @SuppressLint("RecyclerView") int position) {
        holder.ef22_imageButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                MainActivity.info_editfridge_name = editfridge_name;
                MainActivity.info_editfridge_imgName = editfridge_imgName;
                MainActivity.info_editfridgedb_position = editfridgedb_position[position];
                MainActivity.info_editfridgedb_insertDate = editfridgedb_insertDate[position];
                MainActivity.info_editfridgedb_expireDate = editfridgedb_expireDate[position];
                MainActivity.info_editfridgedb_uid = editfridgedb_uid[position];
                MainActivity.info_editfridgedb_amount = String.valueOf(editfridgedb_amount[position]);
                MainActivity.info_editfridgedb_memo = editfridgedb_memo[position];
                MainActivity.current_editdialog = 3;
                MainActivity.editdialog_change(MainActivity.current_editdialog, MainActivity.origin_editdialog, MainActivity.fm_p);
            }
        });
        holder.ef22_date_textview.setText(editfridgedb_expireDate[position]);
//        holder.d2_imageView.setImageResource(R.drawable.pic1);
        holder.ef22_amount_textview.setText(String.valueOf(editfridgedb_amount[position]));
        holder.ef22_editTextNumber.setText(String.valueOf(editfridgedb_editnum[position]));
        holder.ef22_editTextNumber.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(!(holder.ef22_editTextNumber.getText().toString().matches(""))){
                    if(Integer.parseInt(holder.ef22_editTextNumber.getText().toString()) > editfridgedb_amount[position]){
                        MainActivity.editfridge_fault[editfridge_index21] = -2;
                    }else{
                        MainActivity.editfridge_fault[editfridge_index21] = 0;
                        MainActivity.editfridgedb_editnum[count_index + position] = Integer.parseInt(holder.ef22_editTextNumber.getText().toString());
                    }
                }else{
//                    holder.ef22_editTextNumber.setText(String.valueOf(0));
                    MainActivity.editfridgedb_editnum[count_index + position] = 0;
                }
            }
        });

    }


    @Override
    public int getItemCount() {
        return editfridgedb_fid.length;
    }

    static class editfridge_ListHolder_22 extends RecyclerView.ViewHolder {
        TextView ef22_date_textview;
        TextView ef22_amount_textview;
        final EditText ef22_editTextNumber;
        ImageButton ef22_imageButton;
        public editfridge_ListHolder_22(@NonNull View itemView){
            super(itemView);
            ef22_date_textview= (TextView) itemView.findViewById(R.id.ef22_date_textview);
            ef22_amount_textview= (TextView) itemView.findViewById(R.id.ef22_amount_textview);
            ef22_editTextNumber= (EditText) itemView.findViewById(R.id.ef22_editTextNumber);
            ef22_imageButton= (ImageButton) itemView.findViewById(R.id.ef22_imageButton);
        }
    }
}
