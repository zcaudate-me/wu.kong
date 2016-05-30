(ns wu.kong.system
  (:require [hara.object :as object])
  (:import [org.apache.maven.repository.internal MavenRepositorySystemUtils]
           [org.eclipse.aether.connector.basic BasicRepositoryConnectorFactory]
           [org.eclipse.aether.spi.connector RepositoryConnectorFactory]
           [org.eclipse.aether.spi.connector.transport TransporterFactory]
           [org.eclipse.aether.transport.file FileTransporterFactory]
           [org.eclipse.aether.transport.http HttpTransporterFactory]
           [org.eclipse.aether RepositorySystem]))

(object/map-like RepositorySystem {:tag "repo"})

(defn base-repo-system []
  (-> (doto (MavenRepositorySystemUtils/newServiceLocator)
        (.addService RepositoryConnectorFactory BasicRepositoryConnectorFactory)
        (.addService TransporterFactory FileTransporterFactory)
        (.addService TransporterFactory HttpTransporterFactory))
      (.getService RepositorySystem)))

(defrecord Aether [])

(defmethod print-method Aether
  [v w]
  (.write w (str "#aether" (into {} v))))

(defonce +defaults+
  {:local-repo (-> (System/getProperty "user.home")
                   (io/file  ".m2" "repository")
                   (.getAbsolutePath))
   :repositories {"central" {:name "central"
                             :url "http://central.maven.org/maven2/"}
                  "clojars" {:name "clojars"
                             :url "http://clojars.org/repo"}}})

(defn aether
  ([] (repo-system {}))
  ([config]
   (map->RepositorySystem (nested/merge-nil-nested config +defaults+))))
