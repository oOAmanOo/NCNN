package recycler;


import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.tencent.nanodetncnn.R;

public class ListHolder extends RecyclerView.ViewHolder {
    TextView textView;
    public ListHolder(@NonNull View itemView) {
        super(itemView);
        TextView textView;
        textView = (TextView) itemView.findViewById(R.id.d1_textView);
    }
}
