(ns wu.kong.aether.artifact-test
  (:use hara.test)
  (:require [wu.kong.aether.artifact :refer :all])
  (:import [org.eclipse.aether.artifact Artifact  DefaultArtifact]))

^{:refer wu.kong.aether.artifact/artifact->vector :added "0.1"}
(fact "converts an artifact to the clojure vector notation"
  (-> (DefaultArtifact. "im.chit:hara.test:2.4.0")
      (artifact->vector))
  => '[im.chit/hara.test "2.4.0"])

^{:refer wu.kong.aether.artifact/artifact<-vector :added "0.1"}
(fact "converts an artifact to the clojure vector notation"
  (-> (artifact<-vector '[im.chit/hara.test "2.4.0"])
      (str))
  => "im.chit:hara.test:jar:2.4.0")