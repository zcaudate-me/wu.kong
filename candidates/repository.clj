(ns wu.wei.repository
  (:require [clojure.java.io :as io])
  (:import [org.eclipse.aether.impl DefaultServiceLocator]
           [org.eclipse.aether.transport.file.FileTransporterFactory]
           [org.apache.maven.repository.internal MavenRepositorySystemUtils]
           [org.eclipse.aether.transport.wagon WagonProvider WagonTransporterFactory]
           [org.eclipse.aether.connector.basic BasicRepositoryConnectorFactory]
           [org.eclipse.aether.spi.connector RepositoryConnectorFactory]
           [org.eclipse.aether.spi.connector.transport TransporterFactory]
           [org.eclipse.aether.transport.file FileTransporterFactory]
           [org.eclipse.aether.transport.http HttpTransporterFactory]
           [org.eclipse.aether RepositorySystem]
           [org.eclipse.aether.util.graph.transformer ConflictResolver]
           [org.eclipse.aether.util.graph.manager DependencyManagerUtils]
           [org.eclipse.aether.collection CollectRequest]
           [org.eclipse.aether.artifact Artifact DefaultArtifact]
           [org.eclipse.aether.util.artifact JavaScopes]
           [org.eclipse.aether.graph Dependency]
           [org.eclipse.aether.repository RemoteRepository RemoteRepository$Builder
            LocalRepository]))

(defn )

(def +default-local-repo+
  (io/file (System/getProperty "user.home") ".m2" "repository"))

(defn base-repo-system []
  (-> (doto (MavenRepositorySystemUtils/newServiceLocator)
        (.addService RepositoryConnectorFactory BasicRepositoryConnectorFactory)
        (.addService TransporterFactory FileTransporterFactory)
        (.addService TransporterFactory HttpTransporterFactory))
      (.getService RepositorySystem)))

(defn local-repo
  ([] (local-repo +default-local-repo+))
  ([path]
   (LocalRepository. path)))





(comment
  (into {} (.& (DefaultArtifact. "org.apache.maven:maven-aether-provider:3.1.0")))
 

  (def repo-system
    (-> (doto (MavenRepositorySystemUtils/newServiceLocator)
          (.addService RepositoryConnectorFactory BasicRepositoryConnectorFactory)
          (.addService TransporterFactory FileTransporterFactory)
          (.addService TransporterFactory HttpTransporterFactory))
        (.getService RepositorySystem)))

  (def local-repo 
    (LocalRepository. default-local-repo))

  (.getId local-repo)

  (def session
    (doto (MavenRepositorySystemUtils/newSession)
      (.setConfigProperty ConflictResolver/CONFIG_PROP_VERBOSE true)
      (.setConfigProperty DependencyManagerUtils/CONFIG_PROP_VERBOSE true)))

  (def local-repo-manager
    (.newLocalRepositoryManager repo-system session local-repo))

  (.setLocalRepositoryManager session local-repo-manager)

  (-> (.& session) keys)
  
  (def ^{:private true} default-local-repo
    (io/file (System/getProperty "user.home") ".m2" "repository"))

  (def artifact
    (DefaultArtifact. "org.eclipse.aether:aether-impl:1.0.0.v20140518"))

  
  (def request
    (doto (CollectRequest.)
      (.setRoot (Dependency. artifact JavaScopes/COMPILE))
      (.setRepositories
       (-> (RemoteRepository$Builder.  "central" "default" "http://central.maven.org/maven2/")
           (.build))
       vector)))

  (def deps (.collectDependencies repo-system session request))

  (type deps)
  org.eclipse.aether.collection.CollectResult

  (.getDependency (.getRoot deps))

  (.getChildren (.getRoot deps))
  


  CollectRequest collectRequest = new CollectRequest();
  collectRequest.setRoot( new Dependency( artifact, JavaScopes.COMPILE ) );
  collectRequest.setRepositories( Booter.newRepositories( system, session ) );

  DependencyRequest dependencyRequest = new DependencyRequest( collectRequest, classpathFlter );

  List<ArtifactResult> artifactResults =
  system.resolveDependencies( session, dependencyRequest ).getArtifactResults();
)


