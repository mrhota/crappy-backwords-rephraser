(ns mrhota.alphabetsuperset.b.translate
  "Google Translate API v2 wrapper"
  (:require [clojure.string :as string]
            [clojure.java.io :as io])
  (:import [com.google.cloud.translate TranslateOptions Translate$TranslateOption]
           [com.google.auth.oauth2 GoogleCredentials]))

(def google-credentials-config (GoogleCredentials/fromStream
                                (io/input-stream (io/resource "clientLibraryConfig-personal-aws-provider.json"))))

(def xlate (-> (doto (TranslateOptions/newBuilder)
                 (.setCredentials google-credentials-config)
                 (.setProjectId "alphabet-superset"))
               (.build)
               (.getService)))

(defn translate
  "Translate s using Google Translate API."
  [s src-lang dest-lang]
  (if (string/blank? s)
    ""
    (let [translation (.translate xlate
                                  s
                                  (into-array [(Translate$TranslateOption/sourceLanguage src-lang)
                                               (Translate$TranslateOption/targetLanguage dest-lang)]))]
      (.getTranslatedText translation))))
