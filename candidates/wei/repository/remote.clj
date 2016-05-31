(ns wu.kong.repository.remote
  (:require [hara.object :as object])
  (:import [org.eclipse.aether.repository
            RemoteRepository
            RemoteRepository$Builder]))

(object/map-like
 RemoteRepository$Builder
 {:tag "build.remote"
  :read :reflect
  :write {:empty (fn [{:keys [id type url]}]
                   (RemoteRepository$Builder. id type url))}}

 RemoteRepository
 {:tag "remote"
  :write {:from-map (fn [m] (-> (object/from-data m RemoteRepository$Builder)
                                (.build)))}})

(comment
  (object/meta-write RemoteRepository$Builder))
