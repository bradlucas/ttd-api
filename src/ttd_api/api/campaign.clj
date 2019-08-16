(ns ttd-api.api.campaign
    (:require [cheshire.core :as c]
              [ttd-api.api.http :as http]
              [ttd-api.api.api :as api]
              [ttd-api.api.advertiser :as advertiser]))


(defn build-campaign-body [advertiser-id]
  (let [token (api/token)
        m {:headers {"TTD-AUTH" token}
           :body (str "{\"AdvertiserId\": \"" advertiser-id "\"," "\"PageStartIndex\": \"0\", \"PageSize\": null}")
           :content-type :json
           :accept :json}]
    m))

(defn get-campaigns [advertiser-id]
  (-> (api/build-url "campaign/query/advertiser")
      (http/post (build-campaign-body advertiser-id))
      :body
      (c/parse-string true)
      :Result))

(defn get-campaign [campaign-id]
  (-> (str (api/build-url "campaign/") campaign-id)
      (http/get (api/headers))
      :body
      (c/parse-string true)))

(defn get-advertiser-from-campaign [campaign]
  ;; pull out advertiser-id
  (let [advertiser-id (:AdvertiserId campaign)]
    (advertiser/get-advertiser advertiser-id)))
