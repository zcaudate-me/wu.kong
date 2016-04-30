(ns wu.wei.repository.session
  (:require [wu.wei.repository.system :as system]
            [clojure.java.io :as io]
            [hara.object :as object])
  (:import [org.apache.maven.repository.internal MavenRepositorySystemUtils]
           [org.eclipse.aether.util.graph.transformer ConflictResolver]
           [org.eclipse.aether.util.graph.manager DependencyManagerUtils]
           [org.eclipse.aether.repository LocalRepository]))

(object/extend-maplike LocalRepository {:tag "repo"})

(def +default-local+
  (io/file (System/getProperty "user.home") ".m2" "repository"))

(defn local-repo
  [path]
  (LocalRepository. path))

(defn session [system {:keys [local] :as opts}]
  (let [session (doto (MavenRepositorySystemUtils/newSession)
                  (.setConfigProperty ConflictResolver/CONFIG_PROP_VERBOSE true)
                  (.setConfigProperty DependencyManagerUtils/CONFIG_PROP_VERBOSE true))
        manager (.newLocalRepositoryManager system session
                                            (local-repo
                                             (or local +default-local+)))
        _ (.setLocalRepositoryManager session manager)]
    session))
