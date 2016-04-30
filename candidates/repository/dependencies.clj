(ns wu.wei.repository.dependencies
  (:require [wu.wei.repository.system :as system]
            [wu.wei.repository.session :as session]
            [hara.object :as object])
  (:import [org.eclipse.aether.repository
            RemoteRepository
            RemoteRepository$Builder
            LocalRepository]
           [org.eclipse.aether.collection CollectRequest CollectResult]
           [org.eclipse.aether.resolution ArtifactRequest ArtifactResult]
           [org.eclipse.aether.artifact Artifact DefaultArtifact]
           [org.eclipse.aether.util.artifact JavaScopes]
           [org.eclipse.aether.graph Dependency DependencyNode]))

(object/extend-maplike Dependency {:tag "dep"}
                       CollectRequest {:tag "result"})

(defn to-coordinates [artifact]
  [(symbol (str (.getGroupId artifact)
                "/"
                (.getArtifactId artifact)))
   (.getVersion artifact)])

(defmethod print-method Artifact
  [v w]
  (.write w (str (to-coordinates v))))

(defn to-summary [node]
  (let [artifact (.getArtifact node)
        children (filter (fn [c]
                           (let [ca (.getArtifact c)]
                             (not (= (first (to-coordinates ca))
                                     'org.clojure/clojure))))
                         (.getChildren node))
        coords (to-coordinates artifact)]
    (if (empty? children)
      coords
      [coords (mapv to-summary children)])))

(defmethod print-method DependencyNode
  [v w]
  (.write w (str (to-summary v))))

(defn artifact [str]
  (DefaultArtifact. str))

(defn request [{:keys [repositories artifact] :as opts}]
  (doto (CollectRequest.)
    (.setRoot (Dependency. artifact JavaScopes/COMPILE))
    (.setRepositories
     (mapv (fn [{:keys [id tag url] :as repo}]
             (-> (RemoteRepository$Builder.  id (or tag "default") url)
                 (.build)))
           repositories))))

(defn dependencies [repo-system session request]
  (-> (.collectDependencies repo-system session request)
      (.getRoot)))

(defn resolve-request [repo-system session {:keys [repositories artifact] :as opts}]
  (->> (doto (ArtifactRequest.)
         (.setArtifact (DefaultArtifact. artifact))
         (.setRepositories
          (mapv (fn [{:keys [id tag url] :as repo}]
                  (-> (RemoteRepository$Builder.  id (or tag "default") url)
                      (.build)))
                repositories)))
       (.resolveArtifact repo-system session)))

(comment


  (artifact "midje:midje:1.6.3")
  

  
  [[midje/midje "1.6.3"]
   [[ordered/ordered "1.2.0"]
    [org.clojure/math.combinatorics "0.0.7"]
    [org.clojure/core.unify "0.5.2"]
    [[utilize/utilize "0.2.3"]
     [[org.clojure/tools.macro "0.1.1"]
      [joda-time/joda-time "2.0"]
      [ordered/ordered "1.0.0"]]]
    [colorize/colorize "0.1.1"]
    [org.clojure/tools.macro "0.1.5"]
    [dynapath/dynapath "0.2.0"]
    [swiss-arrows/swiss-arrows "1.0.0"]
    [org.clojure/tools.namespace "0.2.4"]
    [slingshot/slingshot "0.10.3"]
    [commons-codec/commons-codec "1.9"]
    [[gui-diff/gui-diff "0.5.0"]
     [[[org.clojars.trptcolin/sjacket "0.1.3"]
       [[net.cgrand/regex "1.1.0"]
        [[net.cgrand/parsley "0.9.1"]
         [[net.cgrand/regex "1.1.0"]]]]]
      [ordered/ordered "1.2.0"]]]
    [[clj-time/clj-time "0.6.0"]
     [[joda-time/joda-time "2.2"]]]]]
  

  (keys (.& (.getRoot deps)))
  (:relocations :artifact :children :data :version :dependency :managedBits :versionConstraint :context :repositories :aliases)
  
  (to-summary (.getRoot deps))
  (.getRoot deps)

  org.eclipse.aether.graph.DefaultDependencyNode
  (-> (into {} (.& (.getRoot deps)))
      :children
      first
      .&)

  (let [sys (system/base-repo-system)
        sess (session/session sys {})]
    (resolve-request sys sess {:artifact "midje:midje:1.6.3"
                               :repositories
                               [{:id "clojars" :url "http://clojars.org/repo"}
                                {:id "central" :url "http://central.maven.org/maven2"}]}))
  

  (def deps
    (let [sys (system/base-repo-system)
          sess (session/session sys {})]
      (->> (request {:artifact (DefaultArtifact. "midje:midje:1.6.3")
                     :repositories
                     [{:id "clojars" :url "http://clojars.org/repo"}
                      {:id "central" :url "http://central.maven.org/maven2"}]})
           (dependencies sys sess))))

  (def deps
    (let [sys (system/base-repo-system)
          sess (session/session sys {})]
      (->> (request {:artifact (DefaultArtifact. "")
                     :repositories
                     [{:id "clojars" :url "http://clojars.org/repo"}
                      {:id "central" :url "http://central.maven.org/maven2"}]})
           (dependencies sys sess))))



  )
