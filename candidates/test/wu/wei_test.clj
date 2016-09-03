(ns wu.wei-test
  (:use hara.test)
  (:require [wu.wei :refer :all]))

(fact "hello there"
  (+ 1 2) => 3)