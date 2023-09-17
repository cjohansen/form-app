(ns form-app.ui.button
  (:require [dumdom.core :as d]))

(d/defcomponent Button [{:keys [class text enabled? actions]}]
  [:button {:class (cond-> [:button :is-dark]
                     (keyword? class) (conj class)
                     (coll? class) (concat class))
            :disabled (false? enabled?)
            :on-click actions}
   text])
