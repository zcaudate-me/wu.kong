(ns wu.kong-test
  (:use midje.sweet)
  (:require [wu.kong :as aether]))

(aether/resolve-dependencies '[midje "1.6.3"])
{[midje/midje "1.6.3"]
 [[ordered/ordered "1.2.0"]
  [org.clojure/math.combinatorics "0.0.7"]
  [org.clojure/core.unify "0.5.2"]
  {[utilize/utilize "0.2.3"]
   [[org.clojure/tools.macro "0.1.1"]
    [joda-time/joda-time "2.0"]
    [ordered/ordered "1.0.0"]]}
  [colorize/colorize "0.1.1"]
  [org.clojure/tools.macro "0.1.5"]
  [dynapath/dynapath "0.2.0"]
  [swiss-arrows/swiss-arrows "1.0.0"]
  [org.clojure/tools.namespace "0.2.4"]
  [slingshot/slingshot "0.10.3"]
  [commons-codec/commons-codec "1.9"]
  {[gui-diff/gui-diff "0.5.0"]
   [{[org.clojars.trptcolin/sjacket "0.1.3"]
     [[net.cgrand/regex "1.1.0"]
      {[net.cgrand/parsley "0.9.1"]
       [[net.cgrand/regex "1.1.0"]]}]}
    [ordered/ordered "1.2.0"]]}
  {[clj-time/clj-time "0.6.0"]
   [[joda-time/joda-time "2.2"]]}]}

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
