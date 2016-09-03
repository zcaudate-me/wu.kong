# wu.kong (悟空)

[![Build Status](https://travis-ci.org/tahto/wu.kong.png?branch=master)](https://travis-ci.org/tahto/wu.kong)
[![Clojars](https://img.shields.io/clojars/v/im.chit/wu.kong.svg)](https://clojars.org/im.chit/wu.kong)

![](https://raw.githubusercontent.com/tahto/wu.kong/master/monkey.jpg)

    “Nothing in this world is difficult, but thinking makes it
     seem so. Where there is true will, there is always a way.”

    ― Wu Cheng'en, Monkey: A Journey to the West

### Installation

Add to project.clj dependencies:

```clojure
[tahto/wu.kong "0.1.3"]
```

## Usage

```clojure
(require '[wu.kong :as aether])
```

## Documentation

Currently only resolve dependencies work (and only with clojars)

```clojure
(aether/resolve-dependencies '[midje "1.6.3"])
;;=> {[midje/midje "1.6.3"]
;     [[ordered/ordered "1.2.0"]
;       [org.clojure/math.combinatorics "0.0.7"]
;       [org.clojure/core.unify "0.5.2"]
;       {[utilize/utilize "0.2.3"]
;        [[org.clojure/tools.macro "0.1.1"]
;         [joda-time/joda-time "2.0"]
;         [ordered/ordered "1.0.0"]]}
;       [colorize/colorize "0.1.1"]
;       [org.clojure/tools.macro "0.1.5"]
;       [dynapath/dynapath "0.2.0"]
;       [swiss-arrows/swiss-arrows "1.0.0"]
;       [org.clojure/tools.namespace "0.2.4"]
;       [slingshot/slingshot "0.10.3"]
;       [commons-codec/commons-codec "1.9"]
;       {[gui-diff/gui-diff "0.5.0"]
;        [{[org.clojars.trptcolin/sjacket "0.1.3"]
;          [[net.cgrand/regex "1.1.0"]
;           {[net.cgrand/parsley "0.9.1"]
;            [[net.cgrand/regex "1.1.0"]]}]}
;         [ordered/ordered "1.2.0"]]}
;       {[clj-time/clj-time "0.6.0"]
;        [[joda-time/joda-time "2.2"]]}]}
```

## License

Copyright © 2016 Chris Zheng

Distributed under the MIT License
