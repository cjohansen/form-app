(ns form-app.ui.date-range-input
  (:require [dumdom.core :as d]
            [form-app.ui.date-input :refer [DateInput]]))

(d/defcomponent DateRangeInput [{:keys [class from to message error?]}]
  [:div {:class class}
   [:div {:class [:is-flex :is-align-items-center]}
    [:p {:class :pr-3} (:label from)]
    [:span {:class :pr-3}
     (DateInput from)]
    [:p {:class :pr-3} (:label to)]
    [:span {:class :pr-3}
     (DateInput to)]]
   (when message
     [:p {:class [:help (when error? :is-danger)]}
      message])])
