(ns wu.wei.repository.system
  (:require [hara.object :as object])
  (:import [org.apache.maven.repository.internal MavenRepositorySystemUtils]
           [org.eclipse.aether.connector.basic BasicRepositoryConnectorFactory]
           [org.eclipse.aether.spi.connector RepositoryConnectorFactory]
           [org.eclipse.aether.spi.connector.transport TransporterFactory]
           [org.eclipse.aether.transport.file FileTransporterFactory]
           [org.eclipse.aether.transport.http HttpTransporterFactory]
           [org.eclipse.aether RepositorySystem]))

(object/extend-maplike RepositorySystem {:tag "repo-system"})

(defn base-repo-system []
  (-> (doto (MavenRepositorySystemUtils/newServiceLocator)
        (.addService RepositoryConnectorFactory BasicRepositoryConnectorFactory)
        (.addService TransporterFactory FileTransporterFactory)
        (.addService TransporterFactory HttpTransporterFactory))
      (.getService RepositorySystem)))



