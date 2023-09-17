(ns form-app.ui.date-input
  (:require [dumdom.core :as d]))

(d/defcomponent DateInput [{:keys [class message error? value placeholder input-actions blur-actions]}]
  [:div {:class class}
   [:input {:class [:input (when error? :is-danger)]
            :type "text"
            :value value
            :placeholder placeholder
            :on-input input-actions
            :on-blur blur-actions}]
   (when message
     [:p {:class [:help (when error? :is-danger)]}
      message])])
