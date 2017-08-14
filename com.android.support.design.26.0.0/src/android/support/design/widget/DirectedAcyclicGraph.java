package android.support.design.widget;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.util.SimpleArrayMap;
import android.support.v4.util.Pools.Pool;
import android.support.v4.util.Pools.SimplePool;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

final class DirectedAcyclicGraph {
   private final Pool mListPool = new SimplePool(10);
   private final SimpleArrayMap mGraph = new SimpleArrayMap();
   private final ArrayList mSortResult = new ArrayList();
   private final HashSet mSortTmpMarked = new HashSet();

   void addNode(@NonNull Object node) {
      if (!this.mGraph.containsKey(node)) {
         this.mGraph.put(node, (Object)null);
      }

   }

   boolean contains(@NonNull Object node) {
      return this.mGraph.containsKey(node);
   }

   void addEdge(@NonNull Object node, @NonNull Object incomingEdge) {
      if (this.mGraph.containsKey(node) && this.mGraph.containsKey(incomingEdge)) {
         ArrayList edges = (ArrayList)this.mGraph.get(node);
         if (edges == null) {
            edges = this.getEmptyList();
            this.mGraph.put(node, edges);
         }

         edges.add(incomingEdge);
      } else {
         throw new IllegalArgumentException("All nodes must be present in the graph before being added as an edge");
      }
   }

   @Nullable
   List getIncomingEdges(@NonNull Object node) {
      return (List)this.mGraph.get(node);
   }

   @Nullable
   List getOutgoingEdges(@NonNull Object node) {
      ArrayList result = null;
      int i = 0;

      for(int size = this.mGraph.size(); i < size; ++i) {
         ArrayList edges = (ArrayList)this.mGraph.valueAt(i);
         if (edges != null && edges.contains(node)) {
            if (result == null) {
               result = new ArrayList();
            }

            result.add(this.mGraph.keyAt(i));
         }
      }

      return result;
   }

   boolean hasOutgoingEdges(@NonNull Object node) {
      int i = 0;

      for(int size = this.mGraph.size(); i < size; ++i) {
         ArrayList edges = (ArrayList)this.mGraph.valueAt(i);
         if (edges != null && edges.contains(node)) {
            return true;
         }
      }

      return false;
   }

   void clear() {
      int i = 0;

      for(int size = this.mGraph.size(); i < size; ++i) {
         ArrayList edges = (ArrayList)this.mGraph.valueAt(i);
         if (edges != null) {
            this.poolList(edges);
         }
      }

      this.mGraph.clear();
   }

   @NonNull
   ArrayList getSortedList() {
      this.mSortResult.clear();
      this.mSortTmpMarked.clear();
      int i = 0;

      for(int size = this.mGraph.size(); i < size; ++i) {
         this.dfs(this.mGraph.keyAt(i), this.mSortResult, this.mSortTmpMarked);
      }

      return this.mSortResult;
   }

   private void dfs(Object node, ArrayList result, HashSet tmpMarked) {
      if (!result.contains(node)) {
         if (tmpMarked.contains(node)) {
            throw new RuntimeException("This graph contains cyclic dependencies");
         } else {
            tmpMarked.add(node);
            ArrayList edges = (ArrayList)this.mGraph.get(node);
            if (edges != null) {
               int i = 0;

               for(int size = edges.size(); i < size; ++i) {
                  this.dfs(edges.get(i), result, tmpMarked);
               }
            }

            tmpMarked.remove(node);
            result.add(node);
         }
      }
   }

   int size() {
      return this.mGraph.size();
   }

   @NonNull
   private ArrayList getEmptyList() {
      ArrayList list = (ArrayList)this.mListPool.acquire();
      if (list == null) {
         list = new ArrayList();
      }

      return list;
   }

   private void poolList(@NonNull ArrayList list) {
      list.clear();
      this.mListPool.release(list);
   }
}
