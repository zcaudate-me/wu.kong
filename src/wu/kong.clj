(ns wu.kong
  (:require [wu.kong.aether :as aether]
            [clojure.java.io :as io]))


(defn collect-dependencies
  [{:keys [local-repo repositories] :as aether} {:keys [] :as request}]
  ())
