// Generated by view binder compiler. Do not edit!
package com.tencent.nanodetncnn.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.tencent.nanodetncnn.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class FragmentCust2Binding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final EditText add;

  @NonNull
  public final Button btnaddsp;

  @NonNull
  public final Button btnback;

  @NonNull
  public final Button btndel;

  @NonNull
  public final Button btnnext;

  @NonNull
  public final TextView food;

  @NonNull
  public final Spinner sptest;

  @NonNull
  public final TextView textView6;

  @NonNull
  public final TextView textView7;

  private FragmentCust2Binding(@NonNull ConstraintLayout rootView, @NonNull EditText add,
      @NonNull Button btnaddsp, @NonNull Button btnback, @NonNull Button btndel,
      @NonNull Button btnnext, @NonNull TextView food, @NonNull Spinner sptest,
      @NonNull TextView textView6, @NonNull TextView textView7) {
    this.rootView = rootView;
    this.add = add;
    this.btnaddsp = btnaddsp;
    this.btnback = btnback;
    this.btndel = btndel;
    this.btnnext = btnnext;
    this.food = food;
    this.sptest = sptest;
    this.textView6 = textView6;
    this.textView7 = textView7;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static FragmentCust2Binding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static FragmentCust2Binding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.fragment_cust2, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static FragmentCust2Binding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.add;
      EditText add = ViewBindings.findChildViewById(rootView, id);
      if (add == null) {
        break missingId;
      }

      id = R.id.btnaddsp;
      Button btnaddsp = ViewBindings.findChildViewById(rootView, id);
      if (btnaddsp == null) {
        break missingId;
      }

      id = R.id.btnback;
      Button btnback = ViewBindings.findChildViewById(rootView, id);
      if (btnback == null) {
        break missingId;
      }

      id = R.id.btndel;
      Button btndel = ViewBindings.findChildViewById(rootView, id);
      if (btndel == null) {
        break missingId;
      }

      id = R.id.btnnext;
      Button btnnext = ViewBindings.findChildViewById(rootView, id);
      if (btnnext == null) {
        break missingId;
      }

      id = R.id.food;
      TextView food = ViewBindings.findChildViewById(rootView, id);
      if (food == null) {
        break missingId;
      }

      id = R.id.sptest;
      Spinner sptest = ViewBindings.findChildViewById(rootView, id);
      if (sptest == null) {
        break missingId;
      }

      id = R.id.textView6;
      TextView textView6 = ViewBindings.findChildViewById(rootView, id);
      if (textView6 == null) {
        break missingId;
      }

      id = R.id.textView7;
      TextView textView7 = ViewBindings.findChildViewById(rootView, id);
      if (textView7 == null) {
        break missingId;
      }

      return new FragmentCust2Binding((ConstraintLayout) rootView, add, btnaddsp, btnback, btndel,
          btnnext, food, sptest, textView6, textView7);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}