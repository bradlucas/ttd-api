(ns ttd-api.core
  (:require [ttd-api.api.api :as api])
  (:gen-class))


(defn -main [& args]
  (println "ttd-api.core")
  (println (api/load-config))
  (println (api/root-url))
  (println (api/token)))
  

