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
   (let [children (filter (fn [c]
                            (let [ca (.getArtifact c)]
                              (pred ca)))
                          (.getChildren node))]
     (-> (.getArtifact node)
         (artifact/artifact->vector)
         (hash-map (mapv dependency-graph children))))))

(defmulti summary type)

(defmethod summary DependencyResult
  [result]
  (dependency-graph (.getRoot result)))

(defmethod summary CollectResult
  [result]
  (dependency-graph (.getRoot result)))

