(ns wu.kong.aether.request
  (:import [org.eclipse.aether.collection CollectRequest]
           [org.eclipse.aether.resolution ArtifactRequest DependencyRequest]
           [org.eclipse.aether.repository
            RemoteRepository
            RemoteRepository$Builder]
           [org.eclipse.aether.artifact DefaultArtifact]
           [org.eclipse.aether.util.artifact JavaScopes]
           [org.eclipse.aether.graph Dependency DependencyNode]))

(defn collect-request [coords]
  (doto (CollectRequest.)
    (.setRoot (Dependency. (DefaultArtifact. coords) JavaScopes/COMPILE))
    ;;(.setRootArtifact (DefaultArtifact. coords))
    (.setRepositories
     [(-> (RemoteRepository$Builder.  "central" "default" "http://central.maven.org/maven2/")
          (.build))
      (-> (RemoteRepository$Builder.  "clojars" "default" "http://clojars.org/repo/")
          (.build))])))

(defn artifact-request [coords]
  (doto (ArtifactRequest.)
    (.setArtifact (DefaultArtifact. coords))
    (.setRepositories
     [(-> (RemoteRepository$Builder.  "central" "default" "http://central.maven.org/maven2/")
          (.build))
      (-> (RemoteRepository$Builder.  "clojars" "default" "http://clojars.org/repo/")
          (.build))]
     )))

(defn dependency-request [coords]
  (doto (DependencyRequest.)
    (.setCollectRequest (collect-request coords))))



(comment
  (collect-request "midje:midje:1.6.3")
  (artifact-request "midje:midje:1.6.3")
  (dependency-request "midje:midje:1.6.3")
  )
