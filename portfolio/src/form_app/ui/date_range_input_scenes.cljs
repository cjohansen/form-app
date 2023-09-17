(ns form-app.ui.date-range-input-scenes
  (:require [form-app.ui.date-range-input :refer [DateRangeInput]]
            [portfolio.dumdom :as portfolio :refer-macros [defscene]]))

(portfolio/configure-scenes
 {:title "Date range input scenes"})

(defscene standard-input
  (DateRangeInput
   {:from {:label "From"
           :placeholder "YYYY-MM-DD"}
    :to {:label "To"
         :placeholder "YYYY-MM-DD"}}))

(defscene range-error
  (DateRangeInput
   {:from {:label "Start"
           :value "2023-09-17"}
    :to {:label "End"
         :value "2023-09-08"}
    :message "The start date should be earlier than the end date"
    :error? true}))
