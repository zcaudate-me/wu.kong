(ns wu.kong.aether.artifact-test
  (:use midje.sweet)
  (:require [wu.kong.aether.artifact :refer :all])
  (:import [org.eclipse.aether.artifact Artifact  DefaultArtifact]))

^{:refer wu.kong.aether.artifact/artifact->vector :added "0.1"}
(fact "converts an artifact to the clojure vector notation"
  (-> (DefaultArtifact. "midje:midje:1.6.3")
      (artifact->vector))
  => '[midje/midje "1.6.3"])

^{:refer wu.kong.aether.artifact/artifact<-vector :added "0.1"}
(fact "converts an artifact to the clojure vector notation"
  (-> (artifact<-vector '[midje "1.6.3"])
      (str))
  => "midje:midje:jar:1.6.3")


