(ns wu.wei-test
  (:use midje.sweet)
  (:require [wu.wei :refer :all]))

(fact "hello there"
  (+ 1 2) => 3)