(ns form-app.ui.date-form-scenes
  (:require [form-app.ui.date-form :refer [DateForm]]
            [portfolio.dumdom :as portfolio :refer-macros [defscene]]))

(portfolio/configure-scenes
 {:title "Date form scenes"})

(defscene form-layout
  (DateForm
   {:date-range {:from {:label "From"
                        :placeholder "YYYY-MM-DD"}
                 :to {:label "To"
                      :placeholder "YYYY-MM-DD"}}
    :date-field {:placeholder "YYYY-MM-DD"}
    :button {:text "Submit"}}))
