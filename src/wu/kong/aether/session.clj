(ns wu.kong.aether.session
  (:require [wu.kong.aether.local-repo :as local])
  (:import [org.apache.maven.repository.internal MavenRepositorySystemUtils]
           [org.eclipse.aether.util.graph.transformer ConflictResolver]
           [org.eclipse.aether.util.graph.manager DependencyManagerUtils]
           [org.eclipse.aether.repository LocalRepository]))

(defn session [system {:keys [local-repo] :as opts}]
  (let [session (doto (MavenRepositorySystemUtils/newSession)
                  (.setConfigProperty ConflictResolver/CONFIG_PROP_VERBOSE true)
                  (.setConfigProperty DependencyManagerUtils/CONFIG_PROP_VERBOSE true))
        manager (.newLocalRepositoryManager system
                                            session
                                            (-> (or local-repo local/+default-local-repo+)
                                                (local/local-repo)))
        _ (.setLocalRepositoryManager session manager)]
    session))
