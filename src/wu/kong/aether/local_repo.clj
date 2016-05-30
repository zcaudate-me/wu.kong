(ns wu.kong.aether.local-repo
  (:require [clojure.java.io :as io])
  (:import [org.eclipse.aether.repository LocalRepository]))

(def +default-local-repo+
  (-> (System/getProperty "user.home")
      (io/file ".m2" "repository")
      (.getAbsolutePath)))

(defn local-repo
  [path]
  (LocalRepository. path))
