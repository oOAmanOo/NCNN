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
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.tencent.nanodetncnn.MainActivity;
import com.tencent.nanodetncnn.R;


public class editfridge_ListAdapter_1 extends RecyclerView.Adapter<editfridge_ListAdapter_1.editfridge_ListHolder_1> {

    String[] editfridge_did;
    String[] editfridge_name;
    String[] editfridge_imgName;
    int[] editfridge_num;
    public static int[] editfridgeAmountText;

    Context context;

    public editfridge_ListAdapter_1(Context context, String[] editfridge_did, String[] editfridge_name, String[] editfridge_imgName, int[] editfridge_num){
        this.editfridge_did = editfridge_did;
        this.editfridge_name = editfridge_name;
        this.editfridge_imgName = editfridge_imgName;
        this.editfridge_num = editfridge_num;
        this.editfridgeAmountText = new int[editfridge_did.length];
        for (int i = 0; i < editfridge_did.length; i++) {
            this.editfridgeAmountText[i] = 1;
        }
        this.context = context;
    }

    @Override
    public editfridge_ListHolder_1 onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.editfridge_adapter1,parent,false);
        editfridge_ListHolder_1 holder = new editfridge_ListHolder_1(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull editfridge_ListHolder_1 holder, @SuppressLint("RecyclerView") int position) {
        holder.ef11_imageView.setImageResource(context.getApplicationContext().getResources().getIdentifier(String.valueOf(editfridge_imgName[position]),"drawable", context.getPackageName()));
        holder.ef11_textView.setText(editfridge_name[position]);
//        holder.ef11_imageView.setImageResource(R.drawable.pic1);
        holder.ef11_editTExt.setText("1");
        holder.ef11_editTExt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(holder.ef11_editTExt.getText().toString().matches("") || Integer.parseInt(holder.ef11_editTExt.getText().toString()) <= 0){
                    editfridgeAmountText[position] = 0;
                }else{
                    editfridgeAmountText[position] = 1;
                    MainActivity.editfridge_num[position] = Integer.parseInt(holder.ef11_editTExt.getText().toString());
                }
            }
        });
    }


    @Override
    public int getItemCount() {
        return editfridge_did.length;
    }

    static class editfridge_ListHolder_1 extends RecyclerView.ViewHolder {
        ImageView ef11_imageView;
        TextView ef11_textView;
        EditText ef11_editTExt;
        public editfridge_ListHolder_1(@NonNull View itemView){
            super(itemView);
            ef11_imageView = (ImageView) itemView.findViewById(R.id.ef11_imageView);
            ef11_textView= (TextView) itemView.findViewById(R.id.ef11_textView);
            ef11_editTExt = (EditText) itemView.findViewById(R.id.ef11_editTExt);
        }

    }
}
