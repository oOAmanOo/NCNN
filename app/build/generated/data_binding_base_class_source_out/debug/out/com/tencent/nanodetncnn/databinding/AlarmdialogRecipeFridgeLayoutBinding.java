// Generated by view binder compiler. Do not edit!
package com.tencent.nanodetncnn.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
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

public final class AlarmdialogRecipeFridgeLayoutBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final LinearLayout alarmRecipeFridge;

  @NonNull
  public final CheckBox alarmRecipeFridgeCheckBox;

  @NonNull
  public final ImageButton alarmRecipeFridgeClose;

  @NonNull
  public final ImageView alarmRecipeFridgeImg;

  @NonNull
  public final TextView alarmRecipeFridgeText;

  @NonNull
  public final ScrollView alarmRf;

  private AlarmdialogRecipeFridgeLayoutBinding(@NonNull ConstraintLayout rootView,
      @NonNull LinearLayout alarmRecipeFridge, @NonNull CheckBox alarmRecipeFridgeCheckBox,
      @NonNull ImageButton alarmRecipeFridgeClose, @NonNull ImageView alarmRecipeFridgeImg,
      @NonNull TextView alarmRecipeFridgeText, @NonNull ScrollView alarmRf) {
    this.rootView = rootView;
    this.alarmRecipeFridge = alarmRecipeFridge;
    this.alarmRecipeFridgeCheckBox = alarmRecipeFridgeCheckBox;
    this.alarmRecipeFridgeClose = alarmRecipeFridgeClose;
    this.alarmRecipeFridgeImg = alarmRecipeFridgeImg;
    this.alarmRecipeFridgeText = alarmRecipeFridgeText;
    this.alarmRf = alarmRf;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static AlarmdialogRecipeFridgeLayoutBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static AlarmdialogRecipeFridgeLayoutBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.alarmdialog_recipe_fridge_layout, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static AlarmdialogRecipeFridgeLayoutBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.alarm_recipe_fridge;
      LinearLayout alarmRecipeFridge = ViewBindings.findChildViewById(rootView, id);
      if (alarmRecipeFridge == null) {
        break missingId;
      }

      id = R.id.alarm_recipe_fridge_checkBox;
      CheckBox alarmRecipeFridgeCheckBox = ViewBindings.findChildViewById(rootView, id);
      if (alarmRecipeFridgeCheckBox == null) {
        break missingId;
      }

      id = R.id.alarm_recipe_fridge_close;
      ImageButton alarmRecipeFridgeClose = ViewBindings.findChildViewById(rootView, id);
      if (alarmRecipeFridgeClose == null) {
        break missingId;
      }

      id = R.id.alarm_recipe_fridge_img;
      ImageView alarmRecipeFridgeImg = ViewBindings.findChildViewById(rootView, id);
      if (alarmRecipeFridgeImg == null) {
        break missingId;
      }

      id = R.id.alarm_recipe_fridge_text;
      TextView alarmRecipeFridgeText = ViewBindings.findChildViewById(rootView, id);
      if (alarmRecipeFridgeText == null) {
        break missingId;
      }

      id = R.id.alarm_rf;
      ScrollView alarmRf = ViewBindings.findChildViewById(rootView, id);
      if (alarmRf == null) {
        break missingId;
      }

      return new AlarmdialogRecipeFridgeLayoutBinding((ConstraintLayout) rootView,
          alarmRecipeFridge, alarmRecipeFridgeCheckBox, alarmRecipeFridgeClose,
          alarmRecipeFridgeImg, alarmRecipeFridgeText, alarmRf);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
