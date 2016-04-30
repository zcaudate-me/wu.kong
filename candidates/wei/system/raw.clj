(ns wu.wei.system.raw
  (:require [hara.object :as object])
  (:import [org.apache.maven.repository.internal MavenRepositorySystemUtils]
           [org.eclipse.aether.connector.basic BasicRepositoryConnectorFactory]
           [org.eclipse.aether.spi.connector RepositoryConnectorFactory]
           [org.eclipse.aether.spi.connector.transport TransporterFactory]
           [org.eclipse.aether.transport.file FileTransporterFactory]
           [org.eclipse.aether.transport.http HttpTransporterFactory]
           [org.eclipse.aether RepositorySystem]
           [org.eclipse.aether.util.graph.transformer ConflictResolver]
           [org.eclipse.aether.util.graph.manager DependencyManagerUtils]
           [org.eclipse.aether.repository LocalRepository RemoteRepository RemoteRepository$Builder]))

(defn system-base []
  (-> (doto (MavenRepositorySystemUtils/newServiceLocator)
        (.addService RepositoryConnectorFactory BasicRepositoryConnectorFactory)
        (.addService TransporterFactory FileTransporterFactory)
        (.addService TransporterFactory HttpTransporterFactory))
      (.getService RepositorySystem)))

(defn system-session [system {:keys [local-repo]}]
  (let [session (doto (MavenRepositorySystemUtils/newSession)
                  (.setConfigProperty ConflictResolver/CONFIG_PROP_VERBOSE true)
                  (.setConfigProperty DependencyManagerUtils/CONFIG_PROP_VERBOSE true))
        manager (.newLocalRepositoryManager system session (LocalRepository. local-repo))
        _ (.setLocalRepositoryManager session manager)]
    session))

(defn set-options [builder m])

(defn repository [{:keys []}]
  (cond-> (RemoteRepository$Builder)))


