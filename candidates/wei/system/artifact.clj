(ns wu.wei.system.artifact
  (:import [org.eclipse.aether.artifact Artifact DefaultArtifact]))

(defn to-coordinates [artifact]
  [(symbol (str (.getGroupId artifact)
                "/"
                (.getArtifactId artifact)))
   (.getVersion artifact)])

(defmethod print-method Artifact
  [v w]
  (.write w (str (to-coordinates v)))
