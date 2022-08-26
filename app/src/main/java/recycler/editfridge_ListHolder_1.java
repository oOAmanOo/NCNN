package recycler;


import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.tencent.nanodetncnn.R;

public class editfridge_ListHolder_1 extends RecyclerView.ViewHolder {
    ImageView ef11_imageView;
    TextView ef11_textView;
    EditText ef11_editTExt;
    public editfridge_ListHolder_1(@NonNull View itemView) {
        super(itemView);
        ImageView ef11_imageView;
        TextView ef11_textView;
        EditText ef11_editTExt;
        ef11_imageView = (ImageView) itemView.findViewById(R.id.ef11_imageView);
        ef11_textView= (TextView) itemView.findViewById(R.id.ef11_textView);
        ef11_editTExt = (EditText) itemView.findViewById(R.id.ef11_editTExt);
    }

}
