(ns wu.kong-test
  (:use midje.sweet)
  (:require [wu.kong :as aether]))

(comment
  (aether/collect-dependencies repo request)

  (aether/resolve-dependencies repo request)

  ;; repo will create a RepositorySystemSession object and process request

  (.? org.eclipse.aether.RepositorySystem :name)
  ("collectDependencies" "deploy" "install" "newDeploymentRepository" "newLocalRepositoryManager" "newResolutionRepositories" "newSyncContext" "readArtifactDescriptor" "resolveArtifact" "resolveArtifacts" "resolveDependencies" "resolveMetadata" "resolveVersion" "resolveVersionRange")

  (execute
   (system {:local-repo
            :repositories {"central" ...
                           "clojars" ...}})
   {:type :collect-dependencies
    :args []})

  )
