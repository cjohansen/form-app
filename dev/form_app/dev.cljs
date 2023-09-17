(ns form-app.dev
  (:require [form-app.core :as app]))

(defonce store (atom {}))
(defonce element (js/document.getElementById "app"))

(app/start store element)
