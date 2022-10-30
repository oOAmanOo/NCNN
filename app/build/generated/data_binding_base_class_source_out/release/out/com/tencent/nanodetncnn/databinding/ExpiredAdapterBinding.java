// Generated by view binder compiler. Do not edit!
package com.tencent.nanodetncnn.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.tencent.nanodetncnn.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ExpiredAdapterBinding implements ViewBinding {
  @NonNull
  private final RelativeLayout rootView;

  @NonNull
  public final ImageView expiredImage;

  @NonNull
  public final TextView expiredName;

  @NonNull
  public final TextView expiredNum;

  private ExpiredAdapterBinding(@NonNull RelativeLayout rootView, @NonNull ImageView expiredImage,
      @NonNull TextView expiredName, @NonNull TextView expiredNum) {
    this.rootView = rootView;
    this.expiredImage = expiredImage;
    this.expiredName = expiredName;
    this.expiredNum = expiredNum;
  }

  @Override
  @NonNull
  public RelativeLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ExpiredAdapterBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ExpiredAdapterBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.expired_adapter, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ExpiredAdapterBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.expiredImage;
      ImageView expiredImage = ViewBindings.findChildViewById(rootView, id);
      if (expiredImage == null) {
        break missingId;
      }

      id = R.id.expiredName;
      TextView expiredName = ViewBindings.findChildViewById(rootView, id);
      if (expiredName == null) {
        break missingId;
      }

      id = R.id.expiredNum;
      TextView expiredNum = ViewBindings.findChildViewById(rootView, id);
      if (expiredNum == null) {
        break missingId;
      }

      return new ExpiredAdapterBinding((RelativeLayout) rootView, expiredImage, expiredName,
          expiredNum);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}