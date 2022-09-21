// Generated by view binder compiler. Do not edit!
package com.tencent.nanodetncnn.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.tencent.nanodetncnn.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ActivityToDoListBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final ImageView listBackstackButton;

  @NonNull
  public final RecyclerView todolistRecylerview;

  private ActivityToDoListBinding(@NonNull ConstraintLayout rootView,
      @NonNull ImageView listBackstackButton, @NonNull RecyclerView todolistRecylerview) {
    this.rootView = rootView;
    this.listBackstackButton = listBackstackButton;
    this.todolistRecylerview = todolistRecylerview;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivityToDoListBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivityToDoListBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_to_do_list, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivityToDoListBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.list_backstack_button;
      ImageView listBackstackButton = ViewBindings.findChildViewById(rootView, id);
      if (listBackstackButton == null) {
        break missingId;
      }

      id = R.id.todolist_recylerview;
      RecyclerView todolistRecylerview = ViewBindings.findChildViewById(rootView, id);
      if (todolistRecylerview == null) {
        break missingId;
      }

      return new ActivityToDoListBinding((ConstraintLayout) rootView, listBackstackButton,
          todolistRecylerview);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
