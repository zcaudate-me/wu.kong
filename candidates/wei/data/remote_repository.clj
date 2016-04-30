(ns wu.wei.data.remote-repository
  (:require [hara.object.access :refer :all]
            [hara.object.builder :as builder]
            [hara.object :as object]
            [hara.protocol.map :as map]
            [hara.protocol.data :as data]
            [hara.common.string :refer [to-string]]
            [hara.string.case :as case]
            [hara.reflect :as reflect])
  (:import [org.eclipse.aether.repository
            LocalRepository
            RemoteRepository
            RemoteRepository$Builder]))

(defmethod data/-meta-object RemoteRepository
  [_]
  {:class RemoteRepository
   :types #{java.util.Map}
   :from-data map/-from-map
   :to-data map/-to-map})

(defmethod builder/build-template RemoteRepository
  [_]
  {:init  (fn [{:keys [id type url]}] (RemoteRepository$Builder. id type url))
   :final (fn [obj] (.build obj))
   :exclude [:id :type :url]
   :prefix "set"})

(defmethod map/-from-map RemoteRepository
  [m cls]
  (-> (builder/build-template cls)
      (builder/build m)))

(comment
  (map/-from-map  {:id "central"
                   :type "default"
                   :url "http://central.maven.org/maven2/"
                   :authentication {:username "chris"}}
                  RemoteRepository)
  (object/from-data {:id "central"
                     :type "default"
                     :url "http://central.maven.org/maven2/"
                     :authentication {:username "chris"}}
                    RemoteRepository)

  (binding [hara.object.base/*transform* {RemoteRepository {[:id] [:name]
                                                            [:authentication :account] [:user]
                                                            [:authentication :password] [:pass]}
                                          org.eclipse.aether.repository.Authentication {[:username] [:account]}}]
    (object/from-data
     {:name "central"
      :type "default"
      :url "http://central.maven.org/maven2/"
      :user "chris"
      :pass "hello"}

     RemoteRepository)

    )
  
  )
