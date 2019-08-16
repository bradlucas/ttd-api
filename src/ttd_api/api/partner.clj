(ns ttd-api.api.partner
  (:require [cheshire.core :as c]
            [ttd-api.api.http :as http]
            [ttd-api.api.api :as api]))


(defn get-partners []
  (-> (api/build-url "partner/query")
      (http/post (api/build-body))
      :body
      (c/parse-string true)
      :Result))

(defn get-partner [partner-id]
  (-> (str (api/build-url "partner/") partner-id)
      (http/get (api/headers))
      :body
      (c/parse-string true)))




