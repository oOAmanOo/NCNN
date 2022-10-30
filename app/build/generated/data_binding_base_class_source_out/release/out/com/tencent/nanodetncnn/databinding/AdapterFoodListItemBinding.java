// Generated by view binder compiler. Do not edit!
package com.tencent.nanodetncnn.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
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

public final class AdapterFoodListItemBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final TextView adapterFoodListItemAmount;

  @NonNull
  public final View adapterFoodListItemBg;

  @NonNull
  public final View adapterFoodListItemChose;

  @NonNull
  public final TextView adapterFoodListItemDate;

  @NonNull
  public final TextView adapterFoodListItemOwn;

  @NonNull
  public final ImageView adapterFoodListItemPic;

  @NonNull
  public final TextView adapterFoodListItemTitle;

  @NonNull
  public final ConstraintLayout constraintLayout;

  @NonNull
  public final ConstraintLayout viewVideoLargeGridListItemAll;

  private AdapterFoodListItemBinding(@NonNull ConstraintLayout rootView,
      @NonNull TextView adapterFoodListItemAmount, @NonNull View adapterFoodListItemBg,
      @NonNull View adapterFoodListItemChose, @NonNull TextView adapterFoodListItemDate,
      @NonNull TextView adapterFoodListItemOwn, @NonNull ImageView adapterFoodListItemPic,
      @NonNull TextView adapterFoodListItemTitle, @NonNull ConstraintLayout constraintLayout,
      @NonNull ConstraintLayout viewVideoLargeGridListItemAll) {
    this.rootView = rootView;
    this.adapterFoodListItemAmount = adapterFoodListItemAmount;
    this.adapterFoodListItemBg = adapterFoodListItemBg;
    this.adapterFoodListItemChose = adapterFoodListItemChose;
    this.adapterFoodListItemDate = adapterFoodListItemDate;
    this.adapterFoodListItemOwn = adapterFoodListItemOwn;
    this.adapterFoodListItemPic = adapterFoodListItemPic;
    this.adapterFoodListItemTitle = adapterFoodListItemTitle;
    this.constraintLayout = constraintLayout;
    this.viewVideoLargeGridListItemAll = viewVideoLargeGridListItemAll;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static AdapterFoodListItemBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static AdapterFoodListItemBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.adapter_food_list_item, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static AdapterFoodListItemBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.adapter_food_list_item_amount;
      TextView adapterFoodListItemAmount = ViewBindings.findChildViewById(rootView, id);
      if (adapterFoodListItemAmount == null) {
        break missingId;
      }

      id = R.id.adapter_food_list_item_bg;
      View adapterFoodListItemBg = ViewBindings.findChildViewById(rootView, id);
      if (adapterFoodListItemBg == null) {
        break missingId;
      }

      id = R.id.adapter_food_list_item_chose;
      View adapterFoodListItemChose = ViewBindings.findChildViewById(rootView, id);
      if (adapterFoodListItemChose == null) {
        break missingId;
      }

      id = R.id.adapter_food_list_item_date;
      TextView adapterFoodListItemDate = ViewBindings.findChildViewById(rootView, id);
      if (adapterFoodListItemDate == null) {
        break missingId;
      }

      id = R.id.adapter_food_list_item_own;
      TextView adapterFoodListItemOwn = ViewBindings.findChildViewById(rootView, id);
      if (adapterFoodListItemOwn == null) {
        break missingId;
      }

      id = R.id.adapter_food_list_item_pic;
      ImageView adapterFoodListItemPic = ViewBindings.findChildViewById(rootView, id);
      if (adapterFoodListItemPic == null) {
        break missingId;
      }

      id = R.id.adapter_food_list_item_title;
      TextView adapterFoodListItemTitle = ViewBindings.findChildViewById(rootView, id);
      if (adapterFoodListItemTitle == null) {
        break missingId;
      }

      id = R.id.constraintLayout;
      ConstraintLayout constraintLayout = ViewBindings.findChildViewById(rootView, id);
      if (constraintLayout == null) {
        break missingId;
      }

      ConstraintLayout viewVideoLargeGridListItemAll = (ConstraintLayout) rootView;

      return new AdapterFoodListItemBinding((ConstraintLayout) rootView, adapterFoodListItemAmount,
          adapterFoodListItemBg, adapterFoodListItemChose, adapterFoodListItemDate,
          adapterFoodListItemOwn, adapterFoodListItemPic, adapterFoodListItemTitle,
          constraintLayout, viewVideoLargeGridListItemAll);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}