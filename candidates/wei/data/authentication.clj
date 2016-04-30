(ns wu.wei.data.authentication
  (:require [hara.object.access :refer :all]
            [hara.object.builder :as builder]
            [hara.object.base :as base]
            [hara.object :as object]
            [hara.protocol.map :as map]
            [hara.protocol.data :as data]
            [hara.common.string :refer [to-string]]
            [hara.string.case :as case]
            [hara.reflect :as reflect])
  (:import [org.eclipse.aether.util.repository
            AuthenticationBuilder
            ChainedAuthentication
            StringAuthentication]
           org.eclipse.aether.repository.Authentication))

(defmethod data/-meta-object Authentication
  [_]
  {:class Authentication
   :types #{java.util.Map}
   :from-data base/from-map
   :to-data map/-to-map})

(defmethod builder/build-template Authentication
  [_]
  {:init  (fn [m] (AuthenticationBuilder.))
   :final (fn [obj] (.build obj))
   :exclude []
   :prefix "add"
   :setters {:private-key (fn [obj private-key]
                            (let [{:keys [path passphrase]} private-key]
                              (.addPrivateKey obj path passphrase)))

             :secrets (fn [obj secrets]
                        (reduce-kv (fn [obj k v]
                                     (doto obj (.addSecret k v)))
                                   obj
                                   secrets))
             :ntlms (fn [obj ntlms]
                      (reduce-kv (fn [obj k v]
                                   (doto obj (.addNtlm k v)))
                                 obj
                                 ntlms))}})

(defmethod map/-from-map Authentication
  [m cls]
  (-> (builder/build-template cls)
      (builder/build m)))

(extend-protocol map/IMap
  Authentication
  (-to-map [obj]
    (let [t (type obj)
          map-fn (fn [out {:keys [value key] secret :secretHash}]
                   (if (#{"username"} key)
                     (assoc out (keyword key) value)
                     out))]
      (cond (= ChainedAuthentication t)
            (->> (reflect/delegate obj)
                 (into {})
                 :authentications
                 seq
                 (map #(into {} (reflect/delegate %)))
                 (reduce map-fn {}))
            (= StringAuthentication t)
            (->> (reflect/delegate obj)
                 (into {})
                 (map-fn {}))))))

(comment
  (map/-to-map (map/-from-map {:username "chris"} Authentication))

  => {:username "chris"}

  (reflect/query-hierarchy ((:init (builder/build-template Authentication)) {})
                           ["setUsername"])
  (object/from-data {:username "chris"} Authentication)
  (+ 1 1)

  )
