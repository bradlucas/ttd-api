(ns ttd-api.api.partner
  (:require [clj-http.client :as client]
            [ttd-api.api.api :as api]))


(defn get-partners []
  (-> (api/build-url "partner/query")
      (client/post (api/build-body))
      :body))

(defn get-partner [partner-id]
  (-> (str (api/build-url "partner/") partner-id)
      (client/get (api/headers))
      :body))




