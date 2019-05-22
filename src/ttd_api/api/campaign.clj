(ns ttd-api.api.campaign
    (:require [clj-http.client :as client]
              [ttd-api.api.api :as api]
              [ttd-api.api.advertiser :as advertiser]
              [cheshire.core :as c]))


(defn build-campaign-body [advertiser-id]
  (let [token (api/token)
        m {:headers {"TTD-AUTH" token}
           :body (str "{\"AdvertiserId\": \"" advertiser-id "\"," "\"PageStartIndex\": \"0\", \"PageSize\": null}")
           :content-type :json
           :accept :json}]
    (clojure.pprint/pprint m)
    m))

(defn get-campaigns [advertiser-id]
  (-> (api/build-url "campaign/query/advertiser")
      (client/post (build-campaign-body advertiser-id))
      :body))

(defn get-campaign [campaign-id]
  (-> (str (api/build-url "campaign/") campaign-id)
      (client/get (api/headers))
      :body))

(defn get-advertiser-from-campaign [campaign-json]
  ;; pull out advertiser-id
  (let [advertiser-id (get-in (c/decode campaign-json) ["AdvertiserId"])]
    (advertiser/get-advertiser advertiser-id)))
