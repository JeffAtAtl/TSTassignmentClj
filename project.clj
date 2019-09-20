(defproject TSTassignmentClj "0.1.0"
  :description "TST assignment Project using gorilla repl"
  :dependencies [[org.clojure/clojure "1.9.0"]]
  :target-path "target/%s"
  :plugins [[org.clojars.benfb/lein-gorilla "0.6.0"]]
  :profiles {:uberjar {:aot :all}})