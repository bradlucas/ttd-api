(ns ttd-api.api.http
  (:require [clj-http.client :as client])
  (:use [slingshot.slingshot]))


(defn post-ex [url param]
  (try+
   (client/post url param)
   (catch Object e
     e)))


(defn post [url param]
  (let [r (post-ex url param)
        s (:status r)]
    (if (= 200 s)
      r
      (if (= 403 s)
        (do
          (prn r)
          (throw (ex-info "Expired token" r)))
        (do
          ;; (prn r)
          ;; (println "PAUSING FOR 2 SECONDS --------------------------------------------------")
          (Thread/sleep 2000)
          (recur url param))))))
        

(defn get-ex [url param]
  (try+
   (client/get url param)
   (catch Object e
     e)))


(defn get [url param]
  (let [r (get-ex url param)
        s (:status r)]
    (if (= 200 s)
      r
      (if (= 403 s)
        (do
          (prn r)
          (throw (ex-info "Expired token" r)))
        (do
          ;; (prn r)
          ;; (println "PAUSING FOR 2 SECONDS --------------------------------------------------")
          (Thread/sleep 2000)
          (recur url param))))))





  

