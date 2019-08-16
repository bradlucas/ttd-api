(ns ttd-api.api.bidlist
  (:require [ttd-api.api.http :as http]
            [ttd-api.api.api :as api]
            [ttd-api.api.adgroup :as adgroup]
            [cheshire.core :as c]))


(defn get-bidlist [bidlist-id]
  (-> (str (api/build-url "bidlist/") bidlist-id)
      (http/get (api/headers))
      :body
      (c/parse-string true)))

(defn get-bidlists
  "run (c/parse-string (get-adgroup adgroup-id true) and pass here to get the bidlist ids"
  [adgroup]
  (map get-bidlist (adgroup/get-bidlist-ids adgroup)))

