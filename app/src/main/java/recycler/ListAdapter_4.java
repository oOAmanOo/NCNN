package recycler;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.tencent.nanodetncnn.MainActivity;
import com.tencent.nanodetncnn.R;

import java.time.LocalDate;

public class ListAdapter_4 extends RecyclerView.Adapter<ListAdapter_4.ListHolder_4>{

    int[] addIndex;
    Context context;
    public static int[] foodNameText;

    public ListAdapter_4(Context context, int[] addIndex){
        this.context = context;
        this.addIndex = addIndex;
        this.foodNameText = addIndex;
        for (int i = 0; i < foodNameText.length; i++) {
            this.foodNameText[i] = 0;
        }
    }

    @Override
    public ListHolder_4 onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_adapter4,parent,false);
        ListHolder_4 holder = new ListHolder_4(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ListHolder_4 holder, @SuppressLint("RecyclerView") int position) {
        holder.d4_editTextName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void afterTextChanged(Editable s) {
                if(!(holder.d4_editTextName.getText().toString().matches(""))){
                    foodNameText[position] = 1;
                    int index = MainActivity.fridge_index + position;
                    MainActivity.fridge_did[index] = holder.d4_editTextName.getText().toString();
                    MainActivity.fridge_name[index] = holder.d4_editTextName.getText().toString();
                    MainActivity.fridge_expiredate[index] = LocalDate.now().plusDays(2).toString();
                    MainActivity.fridge_imgName[index] ="question";
                    MainActivity.fridge_position[index] = "2";
                    MainActivity.fridge_amount[index] = "1";
                    MainActivity.fridge_memo[index] = "#";
                }
            }
        });
    }


    @Override
    public int getItemCount() {
        return addIndex.length;
    }

    static class ListHolder_4 extends RecyclerView.ViewHolder {

        EditText d4_editTextName;

        public ListHolder_4(@NonNull View itemView){
            super(itemView);
            d4_editTextName = (EditText) itemView.findViewById(R.id.d4_editText);
        }
    }
}
