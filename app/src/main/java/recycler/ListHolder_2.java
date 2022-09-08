package recycler;


import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.tencent.nanodetncnn.R;

public class ListHolder_2 extends RecyclerView.ViewHolder {
    CardView d2_card;
    TextView d2_textView;
    ImageView d2_imageView;

    EditText d2_Number_plaintext;
    Spinner d2_Position_spinner;
    TextView d2_expireddate_date;
    EditText d2_remark_plaintext;
    TextView d2_remark_plaintext_2;
    public ListHolder_2(@NonNull View itemView) {
        super(itemView);
        CardView d2_card;
        TextView d2_textView;
        ImageView d2_imageView;

        EditText d2_Number_plaintext;
        Spinner d2_position_plaintext;
        TextView d2_expireddate_date;
        EditText d2_remark_plaintext;
        TextView d2_remark_plaintext_2;
        d2_textView= (TextView) itemView.findViewById(R.id.d2_textView);
        d2_imageView = (ImageView) itemView.findViewById(R.id.d2_imageView);
        d2_Number_plaintext = (EditText) itemView.findViewById(R.id.d2_Number_plaintext);
        d2_Position_spinner = (Spinner) itemView.findViewById(R.id.d2_Position_spinner);
        d2_expireddate_date = (TextView) itemView.findViewById(R.id.d2_expireddate_date);
        d2_remark_plaintext = (EditText) itemView.findViewById(R.id.d2_remark_plaintext);
        d2_remark_plaintext_2 =(TextView) itemView.findViewById(R.id.d2_remark_plaintext_2);

        d2_card = (CardView) itemView.findViewById(R.id.d2_card);
    }

}
