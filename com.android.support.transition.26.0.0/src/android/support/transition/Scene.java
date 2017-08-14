package android.support.transition;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.transition.R.id;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class Scene {
   private Context mContext;
   private int mLayoutId = -1;
   private ViewGroup mSceneRoot;
   private View mLayout;
   private Runnable mEnterAction;
   private Runnable mExitAction;

   @NonNull
   public static Scene getSceneForLayout(@NonNull ViewGroup sceneRoot, @LayoutRes int layoutId, @NonNull Context context) {
      SparseArray scenes = (SparseArray)sceneRoot.getTag(id.transition_scene_layoutid_cache);
      if (scenes == null) {
         scenes = new SparseArray();
         sceneRoot.setTag(id.transition_scene_layoutid_cache, scenes);
      }

      Scene scene = (Scene)scenes.get(layoutId);
      if (scene != null) {
         return scene;
      } else {
         scene = new Scene(sceneRoot, layoutId, context);
         scenes.put(layoutId, scene);
         return scene;
      }
   }

   public Scene(@NonNull ViewGroup sceneRoot) {
      this.mSceneRoot = sceneRoot;
   }

   private Scene(ViewGroup sceneRoot, int layoutId, Context context) {
      this.mContext = context;
      this.mSceneRoot = sceneRoot;
      this.mLayoutId = layoutId;
   }

   public Scene(@NonNull ViewGroup sceneRoot, @NonNull View layout) {
      this.mSceneRoot = sceneRoot;
      this.mLayout = layout;
   }

   @NonNull
   public ViewGroup getSceneRoot() {
      return this.mSceneRoot;
   }

   public void exit() {
      if (getCurrentScene(this.mSceneRoot) == this && this.mExitAction != null) {
         this.mExitAction.run();
      }

   }

   public void enter() {
      if (this.mLayoutId > 0 || this.mLayout != null) {
         this.getSceneRoot().removeAllViews();
         if (this.mLayoutId > 0) {
            LayoutInflater.from(this.mContext).inflate(this.mLayoutId, this.mSceneRoot);
         } else {
            this.mSceneRoot.addView(this.mLayout);
         }
      }

      if (this.mEnterAction != null) {
         this.mEnterAction.run();
      }

      setCurrentScene(this.mSceneRoot, this);
   }

   static void setCurrentScene(View view, Scene scene) {
      view.setTag(id.transition_current_scene, scene);
   }

   static Scene getCurrentScene(View view) {
      return (Scene)view.getTag(id.transition_current_scene);
   }

   public void setEnterAction(@Nullable Runnable action) {
      this.mEnterAction = action;
   }

   public void setExitAction(@Nullable Runnable action) {
      this.mExitAction = action;
   }

   boolean isCreatedFromLayoutResource() {
      return this.mLayoutId > 0;
   }
}
