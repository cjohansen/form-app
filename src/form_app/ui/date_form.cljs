(ns form-app.ui.date-form
  (:require [form-app.ui.button :refer [Button]]
            [form-app.ui.date-input :refer [DateInput]]
            [form-app.ui.date-range-input :refer [DateRangeInput]]
            [dumdom.core :as d]))

(d/defcomponent DateForm [{:keys [date-field date-range button]}]
  [:div {:class [:container :my-6]}
   (DateInput (assoc date-field :class :my-3))
   (DateRangeInput (assoc date-range :class :my-6))
   (Button (assoc button :class :my-3))])
