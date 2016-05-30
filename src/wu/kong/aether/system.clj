(ns wu.kong.aether.system
  (:import [org.apache.maven.repository.internal MavenRepositorySystemUtils]
           [org.eclipse.aether.connector.basic BasicRepositoryConnectorFactory]
           [org.eclipse.aether.spi.connector RepositoryConnectorFactory]
           [org.eclipse.aether.spi.connector.transport TransporterFactory]
           [org.eclipse.aether.transport.file FileTransporterFactory]
           [org.eclipse.aether.transport.http HttpTransporterFactory]
           [org.eclipse.aether RepositorySystem]))

(defn repository-system []
  (-> (doto (MavenRepositorySystemUtils/newServiceLocator)
        (.addService RepositoryConnectorFactory BasicRepositoryConnectorFactory)
        (.addService TransporterFactory FileTransporterFactory)
        (.addService TransporterFactory HttpTransporterFactory))
      (.getService RepositorySystem)))
