(ns ttd-api.api.advertiser
    (:require [clj-http.client :as client]
              [ttd-api.api.api :as api]))


(defn build-ad-body [partner-id]
  (let [token (api/token)
        m {:headers {"TTD-AUTH" token}
           :body (str "{\"PartnerId\": \"" partner-id "\"," "\"PageStartIndex\": \"0\", \"PageSize\": null}")
           :content-type :json
           :accept :json}]
    m))

(defn get-advertisers [partner-id]
  (-> (api/build-url "advertiser/query/partner")
      (client/post (build-ad-body partner-id))
      :body))

(defn get-advertiser [advertiser-id]
  (-> (str (api/build-url "advertiser/") advertiser-id)
      (client/get (api/headers))
      :body))
