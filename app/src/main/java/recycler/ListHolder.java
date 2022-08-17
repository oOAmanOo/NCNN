package recycler;


import android.view.View;
import android.widget.CheckBox;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.tencent.nanodetncnn.R;

public class ListHolder extends RecyclerView.ViewHolder {
//    TextView textView;
    CheckBox checkBox;
    public ListHolder(@NonNull View itemView) {
        super(itemView);
        //TextView textView = (TextView) itemView.findViewById(R.id.textView);
        CheckBox checkBox = (CheckBox) itemView.findViewById(R.id.checkBox);
//        itemView.setOnClickListener(checkBox);
    }
}
