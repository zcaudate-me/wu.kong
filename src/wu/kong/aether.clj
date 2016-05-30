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

(defn create-session [aether]
  (-> (system/repository-system)
      (session/session (select-keys aether [:local-repo]))) )

(comment
  (create-session
   ()))
