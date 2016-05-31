(ns wu.kong.aether.artifact
  (:import [org.eclipse.aether.artifact Artifact  DefaultArtifact]))

(defn artifact->vector
  [artifact]
  [(symbol (str (.getGroupId artifact)
                "/"
                (.getArtifactId artifact)))
   (.getVersion artifact)])

(defn artifact<-vector
  [[blob version]]
  (let [name (.getName blob)
        nsp  (or (.getNamespace blob)
                 name)]
    (DefaultArtifact. (str nsp ":" name ":" version))))

(defn artifact->string
  [artifact]
  (str artifact))

(defn artifact<-string
  [s]
  (DefaultArtifact. s))

(defn artifact
  [rep]
  (vector? rep) (artifact->vector rep)
  (string? rep) (artifact->string rep)
  (instance? Artifact rep) artifact
  :else (throw (Exception. (str "Cannot convert " rep " to artifact."))))

(comment
  (str (artifact<-vector '[midje "1.6.3"]))

  )
