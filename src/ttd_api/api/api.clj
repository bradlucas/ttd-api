(ns ttd-api.api.api
  (:require [clojure.pprint]
            [cheshire.core :as c]
            [clojure.edn :as edn])
  )

;; Create a edn file called config.edn inside a directory called .ttd-api in your $HOME directory
;;
;; {:root-url "https://api.thetradedesk.com/v3/"
;;  :token "<token you created with the authentication method"
;;  }
;;
;; @see https://api.thetradedesk.com/v3/doc/api/post-authentication
;;
;; You can manually make a call using an app such as Postman (https://www.getpostman.com/)


(defn load-config []
  (let [filename (str (System/getProperty "user.home") "/.ttd-api/config.edn")]
    (edn/read-string (slurp filename))))

(defn root-url []
  (:root-url (load-config)))

(defn token []
  (:token (load-config)))

(defn headers []
  {:headers {"TTD-AUTH" (token)}})

(defn build-url [path]
  (let [s (str (root-url) path)]
    (println s)
    s))
  
(defn build-body []
  (let [m {:headers {"TTD-AUTH" (token)}
           :body "{\"PageStartIndex\": \"0\", \"PageSize\": null}"
           :content-type :json
           :accept :json}]
    (clojure.pprint/pprint m)
    m))

