// Generated by view binder compiler. Do not edit!
package com.tencent.nanodetncnn.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

public final class AdapterFoodFridgeListItemBinding implements ViewBinding {
  @NonNull
  private final RelativeLayout rootView;

  @NonNull
  public final TextView adapterFoodFridgeListItemAmount;

  @NonNull
  public final View adapterFoodFridgeListItemBg;

  @NonNull
  public final TextView adapterFoodFridgeListItemDate;

  @NonNull
  public final TextView adapterFoodFridgeListItemOwn;

  @NonNull
  public final TextView adapterFoodFridgeListItemPosition;

  @NonNull
  public final RelativeLayout viewVideoLargeGridListItemAll;

  private AdapterFoodFridgeListItemBinding(@NonNull RelativeLayout rootView,
      @NonNull TextView adapterFoodFridgeListItemAmount, @NonNull View adapterFoodFridgeListItemBg,
      @NonNull TextView adapterFoodFridgeListItemDate,
      @NonNull TextView adapterFoodFridgeListItemOwn,
      @NonNull TextView adapterFoodFridgeListItemPosition,
      @NonNull RelativeLayout viewVideoLargeGridListItemAll) {
    this.rootView = rootView;
    this.adapterFoodFridgeListItemAmount = adapterFoodFridgeListItemAmount;
    this.adapterFoodFridgeListItemBg = adapterFoodFridgeListItemBg;
    this.adapterFoodFridgeListItemDate = adapterFoodFridgeListItemDate;
    this.adapterFoodFridgeListItemOwn = adapterFoodFridgeListItemOwn;
    this.adapterFoodFridgeListItemPosition = adapterFoodFridgeListItemPosition;
    this.viewVideoLargeGridListItemAll = viewVideoLargeGridListItemAll;
  }

  @Override
  @NonNull
  public RelativeLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static AdapterFoodFridgeListItemBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static AdapterFoodFridgeListItemBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.adapter_food_fridge_list_item, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static AdapterFoodFridgeListItemBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.adapter_food_fridge_list_item_amount;
      TextView adapterFoodFridgeListItemAmount = ViewBindings.findChildViewById(rootView, id);
      if (adapterFoodFridgeListItemAmount == null) {
        break missingId;
      }

      id = R.id.adapter_food_fridge_list_item_bg;
      View adapterFoodFridgeListItemBg = ViewBindings.findChildViewById(rootView, id);
      if (adapterFoodFridgeListItemBg == null) {
        break missingId;
      }

      id = R.id.adapter_food_fridge_list_item_date;
      TextView adapterFoodFridgeListItemDate = ViewBindings.findChildViewById(rootView, id);
      if (adapterFoodFridgeListItemDate == null) {
        break missingId;
      }

      id = R.id.adapter_food_fridge_list_item_own;
      TextView adapterFoodFridgeListItemOwn = ViewBindings.findChildViewById(rootView, id);
      if (adapterFoodFridgeListItemOwn == null) {
        break missingId;
      }

      id = R.id.adapter_food_fridge_list_item_position;
      TextView adapterFoodFridgeListItemPosition = ViewBindings.findChildViewById(rootView, id);
      if (adapterFoodFridgeListItemPosition == null) {
        break missingId;
      }

      RelativeLayout viewVideoLargeGridListItemAll = (RelativeLayout) rootView;

      return new AdapterFoodFridgeListItemBinding((RelativeLayout) rootView,
          adapterFoodFridgeListItemAmount, adapterFoodFridgeListItemBg,
          adapterFoodFridgeListItemDate, adapterFoodFridgeListItemOwn,
          adapterFoodFridgeListItemPosition, viewVideoLargeGridListItemAll);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
