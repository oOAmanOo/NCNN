package recycler;


import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.tencent.nanodetncnn.R;

public class ListHolder_4 extends RecyclerView.ViewHolder {
    EditText d4_editTextName;
    public ListHolder_4(@NonNull View itemView) {
        super(itemView);
        EditText d4_editTextName;
        d4_editTextName = (EditText) itemView.findViewById(R.id.d4_editText);
    }
}
