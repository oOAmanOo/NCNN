package recycler;

//import android.support.v7.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.tencent.nanodetncnn.MainActivity;
import com.tencent.nanodetncnn.R;

import java.util.Calendar;

public class ListAdapter_2 extends RecyclerView.Adapter<ListAdapter_2.ListHolder_2> {

    String[] fridge_did;
    String[] fridge_name;
    String[] fridge_position;
    String[] fridge_expiredate;
    String[] fridge_imgName;
    String[] fridge_amount;
    String[] fridge_memo;

    Context context;

    public ListAdapter_2(Context context, String[] fridge_did, String[] fridge_name, String[] fridge_position, String[] fridge_expiredate, String[] fridge_imgName, String[] fridge_amount, String[] fridge_memo){
        this.fridge_did = fridge_did;
        this.fridge_name = fridge_name;
        this.fridge_position = fridge_position;
        this.fridge_expiredate = fridge_expiredate;
        this.fridge_imgName = fridge_imgName;
        this.fridge_amount = fridge_amount;
        this.fridge_memo = fridge_memo;
        this.context = context;
    }

    @Override
    public ListHolder_2 onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_adapter2,parent,false);
        ListHolder_2 holder = new ListHolder_2(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ListHolder_2 holder, @SuppressLint("RecyclerView") int position) {

        if(position >= MainActivity.fridge_index){
            return;
        }
        System.out.println(1);
        holder.d2_textView.setText(fridge_name[position]);
        System.out.println("fridge_amount[position]"+fridge_amount[position]);
        holder.d2_Number_plaintext.setText(fridge_amount[position].toString());
        System.out.println(3);
        holder.d2_position_plaintext.setText(fridge_position[position]);
        System.out.println(4);
        holder.d2_expireddate_date.setText(fridge_expiredate[position].toString());
        System.out.println(5);
        holder.d2_expireddate_date.setOnClickListener(new View.OnClickListener(){
            //在點了按鈕後才跳出日曆
            @Override
            public void onClick(View view) {
                Calendar calendar = Calendar.getInstance();
                //取得Calender物件資訊
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int day = calendar.get(Calendar.DAY_OF_MONTH);
                //取出年月日
                new DatePickerDialog(MainActivity.mContext2, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int day) {

                        String dateTime = String.valueOf(year)+"-"+String.valueOf(month+1)+"-"+String.valueOf(day);  //這是希望它選取後顯示上去的文字格式
                        System.out.println(dateTime);
                        holder.d2_expireddate_date.setText(dateTime);//setText上去editText~

                    }
                },year,month,day).show();
            }
        });;
        System.out.println(6);
        holder.d2_remark_plaintext.setText(fridge_memo[position]);
        System.out.println(7);
        holder.d2_imageView.setImageResource(R.drawable.pic1);
        System.out.println(8);
//        holder.d2_imageView.setImageResource(context.getApplicationContext().getResources().getIdentifier(String.valueOf(fridge_imgName[position]),"drawable", context.getPackageName()));
//        holder.d2_card.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
////                if(MainActivity.class_list_checked[position] != String.valueOf(1)) {
////                    holder.d2_textView.setTextColor(Color.parseColor("#F2F2F3"));
////                    holder.d2_card.setBackgroundColor(Color.parseColor("#A100455F"));
////                    MainActivity.class_list_checked[position] = String.valueOf(1);
////                }else{
////                    holder.d2_textView.setTextColor(Color.parseColor("#00455F"));
////                    holder.d2_card.setBackgroundColor(Color.parseColor("#F2F2F3"));
////                    MainActivity.class_list_checked[position] = String.valueOf(0);
////                }
////                System.out.println(MainActivity.class_list_checked[position]);
////                System.out.println(holder.d2_textView.getText());
//            }
//        });
        System.out.println(9);
    }


    @Override
    public int getItemCount() {
        return fridge_did.length;
    }

    static class ListHolder_2 extends RecyclerView.ViewHolder {

        CardView d2_card;
        TextView d2_textView;
        ImageView d2_imageView;

        EditText d2_Number_plaintext;
        EditText d2_position_plaintext;
        TextView d2_expireddate_date;
        EditText d2_remark_plaintext;


        public ListHolder_2(@NonNull View itemView){
            super(itemView);
            d2_textView= (TextView) itemView.findViewById(R.id.d2_textView);
            d2_imageView = (ImageView) itemView.findViewById(R.id.d2_imageView);
            d2_Number_plaintext = (EditText) itemView.findViewById(R.id.d2_Number_plaintext);
            d2_position_plaintext = (EditText) itemView.findViewById(R.id.d2_position_plaintext);
            d2_expireddate_date = (TextView) itemView.findViewById(R.id.d2_expireddate_date);
            d2_remark_plaintext = (EditText) itemView.findViewById(R.id.d2_remark_plaintext);

            d2_card = (CardView) itemView.findViewById(R.id.d2_card);


        }

    }
}
