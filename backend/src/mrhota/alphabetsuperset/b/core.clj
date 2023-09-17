(ns mrhota.alphabetsuperset.b.core
  (:gen-class)
  (:require
   [mrhota.alphabetsuperset.b.translate :as t]
   [fierycod.holy-lambda.response :as hr]
   [fierycod.holy-lambda.agent :as agent]
   [fierycod.holy-lambda.core :as h]))

(defn ExampleLambda
  "I can run on Java, Babashka or Native runtime..."
  [{:keys [event]}]
  (let [text (get-in event [:queryStringParameters :text])
        translated-text (t/translate text "en" "la")
        backwords-text (t/translate translated-text "la" "en")]
    (println text)
    (println translated-text)
    (println backwords-text)
    ;; return a successful plain text response. See also, hr/json
    (hr/text backwords-text)))

;; Specify the Lambda's entry point as a static main function when generating a class file
(h/entrypoint [#'ExampleLambda])

;; Executes the body in a safe agent context for native configuration generation.
;; Useful when it's hard for agent payloads to cover all logic branches.
(agent/in-context
 (println "I will help in generation of native-configurations"))
