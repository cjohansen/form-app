(ns form-app.scenes
  (:require [form-app.ui.button-scenes]
            [form-app.ui.date-form-scenes]
            [form-app.ui.date-input-scenes]
            [form-app.ui.date-range-input-scenes]
            [portfolio.ui :as ui]))

::form-app.ui.date-form-scenes/keep
::form-app.ui.button-scenes/keep
::form-app.ui.date-input-scenes/keep
::form-app.ui.date-range-input-scenes/keep

(def app
  (ui/start!
   {:config
    {:css-paths ["https://cdn.jsdelivr.net/npm/bulma@0.9.4/css/bulma.min.css"]}}))
