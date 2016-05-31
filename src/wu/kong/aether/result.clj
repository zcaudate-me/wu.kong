(ns wu.kong.aether.result
  (:require [wu.kong.aether.artifact :as artifact])
  (:import org.eclipse.aether.collection.CollectResult
           org.eclipse.aether.resolution.DependencyResult
           org.eclipse.aether.graph.DependencyNode))

(defn dependency-graph
  ([node]
   (dependency-graph node
                     (fn [ca]
                       (not (= (first (artifact/artifact->vector ca))
                               'org.clojure/clojure)))))
  ([^DependencyNode node pred]
   (let [artifact (.getArtifact node)
         children (filter (fn [c]
                            (let [ca (.getArtifact c)]
                              (pred ca)))
                          (.getChildren node))
         coords (artifact/artifact->vector artifact)]
     (if (empty? children)
       coords
       {coords (mapv dependency-graph children)}))))

(defmulti summary type)

(defmethod summary DependencyResult
  [result]
  (dependency-graph (.getRoot result)))

(defmethod summary CollectResult
  [result]
  (dependency-graph (.getRoot result)))

