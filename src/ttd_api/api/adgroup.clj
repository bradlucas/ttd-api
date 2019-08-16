(ns ttd-api.api.adgroup
    (:require [cheshire.core :as c]
              [ttd-api.api.http :as http]
              [ttd-api.api.api :as api]
              [ttd-api.api.campaign :as campaign]
              [cheshire.core :as c]))


(defn build-adgroup-body [campaign-id]
  (let [token (api/token)
        m {:headers {"TTD-AUTH" token}
           :body (str "{\"CampaignId\": \"" campaign-id "\"," "\"PageStartIndex\": \"0\", \"PageSize\": null}")
           :content-type :json
           :accept :json}]
    m))

(defn get-adgroups [campaign-id]
  (-> (api/build-url "adgroup/query/campaign")
      (http/post (build-adgroup-body campaign-id))
      :body
      (c/parse-string true)
      :Result))

(defn get-adgroup [adgroup-id]
  (-> (str (api/build-url "adgroup/") adgroup-id)
      (http/get (api/headers))
      :body
      (c/parse-string true)))

(defn get-campaign-from-adgroup [adgroup]
  ;; pull out campaign-id
  (let [campaign-id (:CampaignId  adgroup)]
    (campaign/get-campaign campaign-id)))

(defn get-bidlist-ids [adgroup]
  (map :BidListId (:AssociatedBidLists adgroup)))
