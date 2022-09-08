// Generated by view binder compiler. Do not edit!
package com.tencent.nanodetncnn.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.tencent.nanodetncnn.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ListAdapter2Binding implements ViewBinding {
  @NonNull
  private final FrameLayout rootView;

  @NonNull
  public final TextView AmountTextview;

  @NonNull
  public final CardView d2Card;

  @NonNull
  public final TextView d2ExpireddateDate;

  @NonNull
  public final EditText d2ExpireddateDate2;

  @NonNull
  public final ImageView d2ImageView;

  @NonNull
  public final EditText d2NumberPlaintext;

  @NonNull
  public final Spinner d2PositionSpinner;

  @NonNull
  public final TextView d2PositionTextview;

  @NonNull
  public final EditText d2RemarkPlaintext;

  @NonNull
  public final TextView d2RemarkPlaintext2;

  @NonNull
  public final TextView d2TextView;

  @NonNull
  public final TextView expireddateTextview;

  @NonNull
  public final TextView remarkTextview;

  private ListAdapter2Binding(@NonNull FrameLayout rootView, @NonNull TextView AmountTextview,
      @NonNull CardView d2Card, @NonNull TextView d2ExpireddateDate,
      @NonNull EditText d2ExpireddateDate2, @NonNull ImageView d2ImageView,
      @NonNull EditText d2NumberPlaintext, @NonNull Spinner d2PositionSpinner,
      @NonNull TextView d2PositionTextview, @NonNull EditText d2RemarkPlaintext,
      @NonNull TextView d2RemarkPlaintext2, @NonNull TextView d2TextView,
      @NonNull TextView expireddateTextview, @NonNull TextView remarkTextview) {
    this.rootView = rootView;
    this.AmountTextview = AmountTextview;
    this.d2Card = d2Card;
    this.d2ExpireddateDate = d2ExpireddateDate;
    this.d2ExpireddateDate2 = d2ExpireddateDate2;
    this.d2ImageView = d2ImageView;
    this.d2NumberPlaintext = d2NumberPlaintext;
    this.d2PositionSpinner = d2PositionSpinner;
    this.d2PositionTextview = d2PositionTextview;
    this.d2RemarkPlaintext = d2RemarkPlaintext;
    this.d2RemarkPlaintext2 = d2RemarkPlaintext2;
    this.d2TextView = d2TextView;
    this.expireddateTextview = expireddateTextview;
    this.remarkTextview = remarkTextview;
  }

  @Override
  @NonNull
  public FrameLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ListAdapter2Binding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ListAdapter2Binding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.list_adapter2, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ListAdapter2Binding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.Amount_textview;
      TextView AmountTextview = ViewBindings.findChildViewById(rootView, id);
      if (AmountTextview == null) {
        break missingId;
      }

      id = R.id.d2_card;
      CardView d2Card = ViewBindings.findChildViewById(rootView, id);
      if (d2Card == null) {
        break missingId;
      }

      id = R.id.d2_expireddate_date;
      TextView d2ExpireddateDate = ViewBindings.findChildViewById(rootView, id);
      if (d2ExpireddateDate == null) {
        break missingId;
      }

      id = R.id.d2_expireddate_date_2;
      EditText d2ExpireddateDate2 = ViewBindings.findChildViewById(rootView, id);
      if (d2ExpireddateDate2 == null) {
        break missingId;
      }

      id = R.id.d2_imageView;
      ImageView d2ImageView = ViewBindings.findChildViewById(rootView, id);
      if (d2ImageView == null) {
        break missingId;
      }

      id = R.id.d2_Number_plaintext;
      EditText d2NumberPlaintext = ViewBindings.findChildViewById(rootView, id);
      if (d2NumberPlaintext == null) {
        break missingId;
      }

      id = R.id.d2_Position_spinner;
      Spinner d2PositionSpinner = ViewBindings.findChildViewById(rootView, id);
      if (d2PositionSpinner == null) {
        break missingId;
      }

      id = R.id.d2_Position_textview;
      TextView d2PositionTextview = ViewBindings.findChildViewById(rootView, id);
      if (d2PositionTextview == null) {
        break missingId;
      }

      id = R.id.d2_remark_plaintext;
      EditText d2RemarkPlaintext = ViewBindings.findChildViewById(rootView, id);
      if (d2RemarkPlaintext == null) {
        break missingId;
      }

      id = R.id.d2_remark_plaintext_2;
      TextView d2RemarkPlaintext2 = ViewBindings.findChildViewById(rootView, id);
      if (d2RemarkPlaintext2 == null) {
        break missingId;
      }

      id = R.id.d2_textView;
      TextView d2TextView = ViewBindings.findChildViewById(rootView, id);
      if (d2TextView == null) {
        break missingId;
      }

      id = R.id.expireddate_textview;
      TextView expireddateTextview = ViewBindings.findChildViewById(rootView, id);
      if (expireddateTextview == null) {
        break missingId;
      }

      id = R.id.remark_textview;
      TextView remarkTextview = ViewBindings.findChildViewById(rootView, id);
      if (remarkTextview == null) {
        break missingId;
      }

      return new ListAdapter2Binding((FrameLayout) rootView, AmountTextview, d2Card,
          d2ExpireddateDate, d2ExpireddateDate2, d2ImageView, d2NumberPlaintext, d2PositionSpinner,
          d2PositionTextview, d2RemarkPlaintext, d2RemarkPlaintext2, d2TextView,
          expireddateTextview, remarkTextview);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
