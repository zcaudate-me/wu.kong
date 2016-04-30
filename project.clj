(defproject im.chit/wu.kong "0.1.0-SNAPSHOT"
  :description "a wrapper for org.eclipse.aether"
  :url "https://github.com/zcaudate/wu.kong"
  :license {:name "The MIT License"
            :url "http://http://opensource.org/licenses/MIT"}
  :dependencies [[org.clojure/clojure "1.8.0"]
                 [org.eclipse.aether/aether-api "1.1.0"]
                 [org.eclipse.aether/aether-spi "1.1.0"]
                 [org.eclipse.aether/aether-util "1.1.0"]
                 [org.eclipse.aether/aether-impl "1.1.0"]
                 [org.eclipse.aether/aether-connector-basic "1.1.0"]
                 [org.eclipse.aether/aether-transport-wagon "1.1.0"]
                 [org.eclipse.aether/aether-transport-http "1.1.0"]
                 [org.eclipse.aether/aether-transport-file "1.1.0"]
                 [org.eclipse.aether/aether-transport-classpath "1.1.0"]
                 [org.apache.maven/maven-aether-provider "3.1.0"]
                 [im.chit/hara.reflect "2.3.3"]
                 [im.chit/hara.object "2.3.3"]
                 [im.chit/hara.data.nested "2.3.3"]]
  :profiles {:dev {:plugins [[lein-hydrox "0.1.16"]]
                   :dependencies [;[im.chit/lucid "0.9.7"]
                                  [midje "1.6.3"]
                                  ;;[helpshift/hydrox "0.1.16"]
                                  ]}})
