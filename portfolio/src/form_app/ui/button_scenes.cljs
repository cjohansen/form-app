(ns form-app.ui.button-scenes
  (:require [form-app.ui.button :as button]
            [portfolio.dumdom :as portfolio :refer-macros [defscene]]))

(portfolio/configure-scenes
 {:title "Button scenes"})

(defscene standard-button
  (button/Button {:text "Click it"}))

(defscene disabled-button
  (button/Button {:text "Click it"
                  :enabled? false}))
