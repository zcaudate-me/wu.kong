(ns wu.kong.types.authentication
  (:require [hara.object :as object])
  (:import [org.eclipse.aether.util.repository
            AuthenticationBuilder
            ChainedAuthentication
            StringAuthentication]
           [org.eclipse.aether.repository
            Authentication]))
