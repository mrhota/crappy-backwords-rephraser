(ns mrhota.alphabetsuperset.b)

(def endpoint "https://5ljlvnjrc5.execute-api.us-west-2.amazonaws.com/Prod/?text=")

(defn get-el [id]
  (.getElementById js/document id))

(defn form-val [id]
  (-> id
      get-el
      .-value))

(defn get-style [id]
  (-> js/document
      (.getElementById id)
      .-style))

(defn ^:export rephrase []
  (let [text (form-val "inputText")]
    (when (not-empty text)
      (let [url (str endpoint
                     (js/encodeURIComponent text))]
        (-> (js/fetch url)
            (.then #(.text %))
            (.then #(js/decodeURIComponent %))
            (.then #(-> "output"
                        get-el
                        .-innerText
                        (set! %)))
            (.catch #(js/console.error "Error fetching rephrased text: " %)))))))

(defn init []
  (set! (.-onclick (get-el "submitButton")) rephrase))

(.addEventListener js/window "load" init)