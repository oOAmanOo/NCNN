// Generated by view binder compiler. Do not edit!
package com.tencent.nanodetncnn.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
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

public final class Editfridge3LayoutBinding implements ViewBinding {
  @NonNull
  private final FrameLayout rootView;

  @NonNull
  public final CardView d2Card;

  @NonNull
  public final TextView ef3AmountTextview4;

  @NonNull
  public final TextView ef3ExpireDateTextview;

  @NonNull
  public final ImageView ef3ImageView;

  @NonNull
  public final TextView ef3InsertDateTextview;

  @NonNull
  public final Button ef3NextButton;

  @NonNull
  public final TextView ef3OwnerTextview;

  @NonNull
  public final TextView ef3PositionTextview;

  @NonNull
  public final EditText ef3RemarkEdittext;

  @NonNull
  public final TextView ef3TextView;

  private Editfridge3LayoutBinding(@NonNull FrameLayout rootView, @NonNull CardView d2Card,
      @NonNull TextView ef3AmountTextview4, @NonNull TextView ef3ExpireDateTextview,
      @NonNull ImageView ef3ImageView, @NonNull TextView ef3InsertDateTextview,
      @NonNull Button ef3NextButton, @NonNull TextView ef3OwnerTextview,
      @NonNull TextView ef3PositionTextview, @NonNull EditText ef3RemarkEdittext,
      @NonNull TextView ef3TextView) {
    this.rootView = rootView;
    this.d2Card = d2Card;
    this.ef3AmountTextview4 = ef3AmountTextview4;
    this.ef3ExpireDateTextview = ef3ExpireDateTextview;
    this.ef3ImageView = ef3ImageView;
    this.ef3InsertDateTextview = ef3InsertDateTextview;
    this.ef3NextButton = ef3NextButton;
    this.ef3OwnerTextview = ef3OwnerTextview;
    this.ef3PositionTextview = ef3PositionTextview;
    this.ef3RemarkEdittext = ef3RemarkEdittext;
    this.ef3TextView = ef3TextView;
  }

  @Override
  @NonNull
  public FrameLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static Editfridge3LayoutBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static Editfridge3LayoutBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.editfridge3_layout, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static Editfridge3LayoutBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.d2_card;
      CardView d2Card = ViewBindings.findChildViewById(rootView, id);
      if (d2Card == null) {
        break missingId;
      }

      id = R.id.ef3_amount_textview4;
      TextView ef3AmountTextview4 = ViewBindings.findChildViewById(rootView, id);
      if (ef3AmountTextview4 == null) {
        break missingId;
      }

      id = R.id.ef3_expireDate_textview;
      TextView ef3ExpireDateTextview = ViewBindings.findChildViewById(rootView, id);
      if (ef3ExpireDateTextview == null) {
        break missingId;
      }

      id = R.id.ef3_imageView;
      ImageView ef3ImageView = ViewBindings.findChildViewById(rootView, id);
      if (ef3ImageView == null) {
        break missingId;
      }

      id = R.id.ef3_insertDate_textview;
      TextView ef3InsertDateTextview = ViewBindings.findChildViewById(rootView, id);
      if (ef3InsertDateTextview == null) {
        break missingId;
      }

      id = R.id.ef3_next_button;
      Button ef3NextButton = ViewBindings.findChildViewById(rootView, id);
      if (ef3NextButton == null) {
        break missingId;
      }

      id = R.id.ef3_owner_textview;
      TextView ef3OwnerTextview = ViewBindings.findChildViewById(rootView, id);
      if (ef3OwnerTextview == null) {
        break missingId;
      }

      id = R.id.ef3_position_textview;
      TextView ef3PositionTextview = ViewBindings.findChildViewById(rootView, id);
      if (ef3PositionTextview == null) {
        break missingId;
      }

      id = R.id.ef3_remark_edittext;
      EditText ef3RemarkEdittext = ViewBindings.findChildViewById(rootView, id);
      if (ef3RemarkEdittext == null) {
        break missingId;
      }

      id = R.id.ef3_textView;
      TextView ef3TextView = ViewBindings.findChildViewById(rootView, id);
      if (ef3TextView == null) {
        break missingId;
      }

      return new Editfridge3LayoutBinding((FrameLayout) rootView, d2Card, ef3AmountTextview4,
          ef3ExpireDateTextview, ef3ImageView, ef3InsertDateTextview, ef3NextButton,
          ef3OwnerTextview, ef3PositionTextview, ef3RemarkEdittext, ef3TextView);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}