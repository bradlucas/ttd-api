(ns ttd-api.api.adgroup
    (:require [clj-http.client :as client]
              [ttd-api.api.api :as api]
              [ttd-api.api.campaign :as campaign]
              [cheshire.core :as c]))


(defn build-adgroup-body [campaign-id]
  (let [token (api/token)
        m {:headers {"TTD-AUTH" token}
           :body (str "{\"CampaignId\": \"" campaign-id "\"," "\"PageStartIndex\": \"0\", \"PageSize\": null}")
           :content-type :json
           :accept :json}]
    (clojure.pprint/pprint m)
    m))

(defn get-adgroups [campaign-id]
  (-> (api/build-url "adgroup/query/campaign")
      (client/post (build-adgroup-body campaign-id))
      :body))

(defn get-adgroup [adgroup-id]
  (-> (str (api/build-url "adgroup/") adgroup-id)
      (client/get (api/headers))
      :body))

(defn get-campaign-from-adgroup [adgroup-json]
  ;; pull out campaign-id
  (let [campaign-id (get-in (c/decode adgroup-json) ["CampaignId"])]
    (campaign/get-campaign campaign-id)))


