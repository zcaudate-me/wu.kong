(ns wu.kong.aether
  (:require [wu.kong.aether
             [local-repo :as local]
             [session :as session]
             [system :as system]
             [request :as request]
             [response :as response]]))

(defonce +defaults+
  {:repositories {"central" {:name "central"
                             :url "http://central.maven.org/maven2/"}
                  "clojars" {:name "clojars"
                             :url "http://clojars.org/repo"}}})

(defrecord Aether [])

(defmethod print-method Aether
  [v w]
  (.write w (str "#aether" (into {} v))))

(defn aether
  ([] (map->Aether +defaults+))
  ([config] (map->Aether ())))

(defn create-session [system aether]
  (session/session system (select-keys aether [:local-repo])))

(comment
  (def result
    (let [system  (system/repository-system)
          request (request/collect-request "midje:midje:1.6.3")
          session (session/session system (select-keys (aether) [:local-repo]))]
      (.collectDependencies system session request)))

  (.& (.getRoot result))
  (.& (first (.getChildren (.getRoot result))))
  
  (def result
    (let [system  (system/repository-system)
          request (request/artifact-request "midje:midje:1.6.3")
          session (session/session system (select-keys (aether) [:local-repo]))]
      (.resolveArtifact system session request)))

  (.& result)

  (def result
    (let [system  (system/repository-system)
          request (request/dependency-request "midje:midje:1.6.3")
          session (session/session system (select-keys (aether) [:local-repo]))]
      (.resolveDependencies system session request)))

  (.& (.getRoot result))

  (defn to-coordinates [artifact]
    [(symbol (str (.getGroupId artifact)
                  "/"
                  (.getArtifactId artifact)))
     (.getVersion artifact)])

  (defn from-coordinates [artifact]
    [(symbol (str (.getGroupId artifact)
                  "/"
                  (.getArtifactId artifact)))
     (.getVersion artifact)])
  
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
  
  (defn dependencies [coords]
    (let [system  (system/repository-system)
          request (request/dependency-request "org.clojure:clojure:1.8.0")
          session (session/session system (select-keys (aether) [:local-repo]))]
      (-> (.resolveDependencies system session request)
          (.getRoot)
          (to-summary))))


  
  (to-summary (.getRoot result))
  '[[midje/midje "1.6.3"]
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

 
 
  
  
  
  
  
  
  
  (create-session
   (aether))

  )
