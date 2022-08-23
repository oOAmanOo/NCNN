package recycler;

//import android.support.v7.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Build;
import android.text.Editable;
import android.text.Html;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.tencent.nanodetncnn.Verify_Activity;
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
    public static int[] fridgeAmountText;

    Context context;

    public ListAdapter_2(Context context, String[] fridge_did, String[] fridge_name, String[] fridge_position, String[] fridge_expiredate, String[] fridge_imgName, String[] fridge_amount, String[] fridge_memo){
        this.fridge_did = fridge_did;
        this.fridge_name = fridge_name;
        this.fridge_position = fridge_position;
        this.fridge_expiredate = fridge_expiredate;
        this.fridge_imgName = fridge_imgName;
        this.fridge_amount = fridge_amount;
        this.fridge_memo = fridge_memo;
        this.fridgeAmountText = new int[fridge_did.length];
        for (int i = 0; i < fridge_did.length; i++) {
            this.fridgeAmountText[i] = 1;
        }

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
        holder.d2_textView.setText(fridge_name[position]);
//        holder.d2_imageView.setImageResource(R.drawable.pic1);
        holder.d2_imageView.setImageResource(context.getApplicationContext().getResources().getIdentifier(String.valueOf(fridge_imgName[position]),"drawable", context.getPackageName()));
        if(fridge_position[position].equals("1")){
            holder.d2_Position_spinner.setSelection(0);
        }else{
            holder.d2_Position_spinner.setSelection(1);
        }
        holder.d2_Position_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Verify_Activity.fridge_position[position] = Integer.toString(position);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        holder.d2_Number_plaintext.setText(fridge_amount[position]);
        holder.d2_Number_plaintext.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(holder.d2_Number_plaintext.getText().toString().matches("") || Integer.parseInt(holder.d2_Number_plaintext.getText().toString()) <= 0){
                    fridgeAmountText[position] = 0;
                }else{
                    fridgeAmountText[position] = 1;
                    Verify_Activity.fridge_amount[position] = holder.d2_Number_plaintext.getText().toString();
                }
            }
        });
        holder.d2_expireddate_date.setText(fridge_expiredate[position]);
        holder.d2_expireddate_date.setOnClickListener(new View.OnClickListener(){
            //在點了按鈕後才跳出日曆
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View view) {
                Calendar calendar = Calendar.getInstance();
                //取得Calender物件資訊
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int day = calendar.get(Calendar.DAY_OF_MONTH);
                //取出年月日
                DatePickerDialog  datePickerDialog = new DatePickerDialog(Verify_Activity.mContext2, new DatePickerDialog.OnDateSetListener() {
                    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                        String dateTime = String.valueOf(year)+"-"+String.valueOf(month)+"-"+String.valueOf(day);  //這是希望它選取後顯示上去的文字格式
                        holder.d2_expireddate_date.setText(dateTime);//setText上去editText~
                        Verify_Activity.fridge_expiredate[position] = dateTime;
                    }
                },year,month,day);
                datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis()-10000);
                datePickerDialog.show();

            }
        });
        if(!(fridge_memo[position].equals("#"))){
            if(fridge_memo[position].length() > 4){
                String substring = fridge_memo[position].substring(0,4);
                substring += "...";
                holder.d2_remark_plaintext.setText(substring);
            }else{
                holder.d2_remark_plaintext.setText(fridge_memo[position]);
            }
        }
        holder.d2_remark_plaintext_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder memotext = new AlertDialog.Builder(v.getContext());
                final EditText editText_memo = new EditText(v.getContext());
                memotext.setView(editText_memo);
                if(!(Verify_Activity.fridge_memo[position].equals("#"))){
                    editText_memo.setText(Verify_Activity.fridge_memo[position]);
                }
                memotext.setTitle(Html.fromHtml("<font color='#00455F'>備註"));
                memotext.setPositiveButton("確定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Verify_Activity.fridge_memo[position] = editText_memo.getText().toString();
                        if(editText_memo.getText().toString().length() > 4){
                            String substring = editText_memo.getText().toString().substring(0,4);
                            substring += "...";
                            holder.d2_remark_plaintext.setText(substring);
                            editText_memo.setText(Verify_Activity.fridge_memo[position]);
                            dialog.dismiss();
                        }else{
                            holder.d2_remark_plaintext.setText(editText_memo.getText().toString());
                            editText_memo.setText(Verify_Activity.fridge_memo[position]);
                            dialog.dismiss();
                        }
                    }
                });
                AlertDialog dialog = memotext.create();
                dialog.setView(editText_memo, 60, 0, 60, 0);
                dialog.show();
            }
        });

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
        Spinner d2_Position_spinner;
        TextView d2_expireddate_date;
        EditText d2_remark_plaintext;
        TextView d2_remark_plaintext_2;


        public ListHolder_2(@NonNull View itemView){
            super(itemView);
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
}
