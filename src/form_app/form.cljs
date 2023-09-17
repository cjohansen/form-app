(ns form-app.form
  (:require [clojure.string :as str]))

(def DATE_FORMAT "YYYY-MM-DD")

(defn valid-date-format? [value]
  (when (string? value)
    (re-find #"^\d\d\d\d-\d\d-\d\d$" (str/trim value))))

(defn validate-date [value]
  (when (not-empty value)
    (when-not (valid-date-format? value)
      (str "Incorrect date format, please use " DATE_FORMAT))))

(defn prepare-date-input [state k]
  (let [{:keys [value validating?]} (k state)
        message (when validating?
                  (validate-date value))]
    (cond-> {:placeholder DATE_FORMAT
             :value (or value "")
             :input-actions
             (->> [[:action/save [k :value] :event/target.value]
                   (when (and validating? (or (empty? value) (not message)))
                     [:action/save [k :validating?] false])]
                  (remove nil?))}
      message
      (assoc :message message
             :error? (boolean message))

      (and (not validating?) value)
      (assoc :blur-actions [[:action/save [k :validating?] true]]))))

(defn prepare-date-field [state]
  (prepare-date-input state :fields/date))

(defn before? [from to]
  (< (js/Date. from) (js/Date. to)))

(defn valid-range? [from to]
  (and (valid-date-format? from)
       (valid-date-format? to)
       (before? from to)))

(defn validate-date-range [state]
  (let [from (get-in state [:fields/range-from :value])
        to (get-in state [:fields/range-to :value])]
    (when (and (valid-date-format? from)
               (valid-date-format? to)
               (not (before? from to)))
      "End date should be after start date")))

(defn prepare-date-range [state]
  (let [message (validate-date-range state)]
    {:from (-> (prepare-date-input state :fields/range-from)
               (assoc :label "From"))
     :to (-> (prepare-date-input state :fields/range-to)
             (assoc :label "To"))
     :message message
     :error? (boolean message)}))

(defn prepare-button [state]
  (let [ready? (boolean
                (and (valid-date-format? (get-in state [:fields/date :value]))
                     (valid-range? (get-in state [:fields/range-from :value])
                                   (get-in state [:fields/range-to :value]))))]
    {:text "Submit"
     :enabled? ready?
     :actions (when ready?
                [[:action/save [:fields/date] nil]
                 [:action/save [:fields/range-from] nil]
                 [:action/save [:fields/range-to] nil]])}))

(defn prepare-ui-data [state]
  {:date-field (prepare-date-field state)
   :date-range (prepare-date-range state)
   :button (prepare-button state)})
