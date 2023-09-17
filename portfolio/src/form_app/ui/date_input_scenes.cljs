(ns form-app.ui.date-input-scenes
  (:require [form-app.ui.date-input :refer [DateInput]]
            [portfolio.dumdom :as portfolio :refer-macros [defscene]]))

(portfolio/configure-scenes
 {:title "Date input scenes"})

(defscene standard-input
  (DateInput {:placeholder "YYYY-MM-DD"}))

(defscene invalid-input
  (DateInput {:placeholder "YYYY-MM-DD"
              :value "31-31"
              :message "Incorrect input date format, use YYYY-MM-DD"
              :error? true}))

(defscene date-hint
  (DateInput {:placeholder "YYYY-MM-DD"
              :value "31"
              :message "You might want to reconsider that year"}))
