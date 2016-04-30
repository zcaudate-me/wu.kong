(ns wu.wei.repository.artifact
  (:require [wu.wei.repository.system :as system]
            [wu.wei.repository.session :as session]
            [hara.object :as object])
  (:import [org.eclipse.aether.collection CollectRequest]
           [org.eclipse.aether.artifact Artifact DefaultArtifact]
           [org.eclipse.aether.util.artifact JavaScopes]
           [org.eclipse.aether.graph Dependency]
           [org.eclipse.aether.repository RemoteRepository RemoteRepository$Builder]))

(object/extend-maplike Dependency {:tag "dep"}
                       Artifact {:tag "artifact"})

(defn collect-request [artifact]
  (doto (CollectRequest.)
    (.setRoot (Dependency. artifact JavaScopes/COMPILE))
    (.setRepositories
     (-> (RemoteRepository$Builder.  "central" "default" "http://central.maven.org/maven2/")
         (.build))
     vector)))
