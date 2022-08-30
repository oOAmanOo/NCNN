package recycler;


import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.tencent.nanodetncnn.R;

public class dialog_fragment_ListHolder_1 extends RecyclerView.ViewHolder {
    ImageView ef11_imageView;
    TextView ef11_textView;
    ImageView checkedbox;
    public dialog_fragment_ListHolder_1(@NonNull View itemView) {
        super(itemView);
        ImageView ef11_imageView;
        TextView ef11_textView;
        ImageView checkedbox;
        ef11_imageView = (ImageView) itemView.findViewById(R.id.dialog_food_img);
        ef11_textView= (TextView) itemView.findViewById(R.id.dialog_food_name);
        checkedbox = (ImageView) itemView.findViewById(R.id.uncheckedbox);
    }

}
