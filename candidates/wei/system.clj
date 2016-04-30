(ns wu.wei.system
  (:require [clojure.java.io :as io]
            [hara.data.nested :as nested]
            [wu.wei.system.raw :as raw]))

(defrecord RepositorySystem [])

(defmethod print-method RepositorySystem
  [v w]
  (.write w (str "#system" (into {} v))))

(defonce +defaults+
  {:local-repo (-> (System/getProperty "user.home")
                   (io/file  ".m2" "repository")
                   (.getAbsolutePath))
   :repositories {"central" {:name "central"
                             :url "http://central.maven.org/maven2/"}
                  "clojars" {:name "clojars"
                             :url "http://clojars.org/repo"}}})

(defn repo-system
  ([] (repo-system {}))
  ([config]
   (map->RepositorySystem (nested/merge-nil-nested config +defaults+))))

(defn create- )

(defn collect-dependencies [system coordinate])

(defn resolve-dependencies [system coordinate])


