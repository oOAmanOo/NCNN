// Generated by view binder compiler. Do not edit!
package com.tencent.nanodetncnn.databinding;

import android.view.LayoutInflater;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.tencent.nanodetncnn.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class MainBinding implements ViewBinding {
  @NonNull
  private final LinearLayout rootView;

  @NonNull
  public final Button buttonSwitchCamera;

  @NonNull
  public final SurfaceView cameraview;

  @NonNull
  public final TextView click;

  @NonNull
  public final Button endVarify;

  @NonNull
  public final Spinner spinnerCPUGPU;

  @NonNull
  public final Spinner spinnerModel;

  @NonNull
  public final Button uploadButton;

  private MainBinding(@NonNull LinearLayout rootView, @NonNull Button buttonSwitchCamera,
      @NonNull SurfaceView cameraview, @NonNull TextView click, @NonNull Button endVarify,
      @NonNull Spinner spinnerCPUGPU, @NonNull Spinner spinnerModel, @NonNull Button uploadButton) {
    this.rootView = rootView;
    this.buttonSwitchCamera = buttonSwitchCamera;
    this.cameraview = cameraview;
    this.click = click;
    this.endVarify = endVarify;
    this.spinnerCPUGPU = spinnerCPUGPU;
    this.spinnerModel = spinnerModel;
    this.uploadButton = uploadButton;
  }

  @Override
  @NonNull
  public LinearLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static MainBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static MainBinding inflate(@NonNull LayoutInflater inflater, @Nullable ViewGroup parent,
      boolean attachToParent) {
    View root = inflater.inflate(R.layout.main, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static MainBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.buttonSwitchCamera;
      Button buttonSwitchCamera = ViewBindings.findChildViewById(rootView, id);
      if (buttonSwitchCamera == null) {
        break missingId;
      }

      id = R.id.cameraview;
      SurfaceView cameraview = ViewBindings.findChildViewById(rootView, id);
      if (cameraview == null) {
        break missingId;
      }

      id = R.id.click;
      TextView click = ViewBindings.findChildViewById(rootView, id);
      if (click == null) {
        break missingId;
      }

      id = R.id.endVarify;
      Button endVarify = ViewBindings.findChildViewById(rootView, id);
      if (endVarify == null) {
        break missingId;
      }

      id = R.id.spinnerCPUGPU;
      Spinner spinnerCPUGPU = ViewBindings.findChildViewById(rootView, id);
      if (spinnerCPUGPU == null) {
        break missingId;
      }

      id = R.id.spinnerModel;
      Spinner spinnerModel = ViewBindings.findChildViewById(rootView, id);
      if (spinnerModel == null) {
        break missingId;
      }

      id = R.id.upload_button;
      Button uploadButton = ViewBindings.findChildViewById(rootView, id);
      if (uploadButton == null) {
        break missingId;
      }

      return new MainBinding((LinearLayout) rootView, buttonSwitchCamera, cameraview, click,
          endVarify, spinnerCPUGPU, spinnerModel, uploadButton);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}