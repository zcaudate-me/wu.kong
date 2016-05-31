(ns wu.kong.aether.request
  (:require [wu.kong.aether.artifact :as artifact])
  (:import [org.eclipse.aether.collection CollectRequest]
           [org.eclipse.aether.resolution ArtifactRequest DependencyRequest]
           [org.eclipse.aether.repository
            RemoteRepository
            RemoteRepository$Builder]
           [org.eclipse.aether.artifact DefaultArtifact]
           [org.eclipse.aether.util.artifact JavaScopes]
           [org.eclipse.aether.graph Dependency DependencyNode]))

(defn collect-request [aether coords]
  (doto (CollectRequest.)
    (.setRoot (Dependency. (artifact/artifact coords) JavaScopes/COMPILE))
    ;;(.setRootArtifact (DefaultArtifact. coords))
    (.setRepositories
     [(-> (RemoteRepository$Builder.  "central" "default" "http://central.maven.org/maven2/")
          (.build))
      (-> (RemoteRepository$Builder.  "clojars" "default" "http://clojars.org/repo/")
          (.build))])))

(defn artifact-request [aether coords]
  (doto (ArtifactRequest.)
    (.setArtifact (DefaultArtifact. coords))
    (.setRepositories
     [(-> (RemoteRepository$Builder.  "central" "default" "http://central.maven.org/maven2/")
          (.build))
      (-> (RemoteRepository$Builder.  "clojars" "default" "http://clojars.org/repo/")
          (.build))])))

(defn dependency-request [aether coords]
  (doto (DependencyRequest.)
    (.setCollectRequest (collect-request aether coords))))
