(ns ttd-api.core
  (:require [ttd-api.api.api :as api]
            [ttd-api.report :as report])
  (:gen-class))


(defn -main [& args]
  (println "ttd-api.core")
  (println (api/load-config))
  (println (api/root-url))
  (println (api/token)))
  

