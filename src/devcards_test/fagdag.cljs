(ns devcards-test.fagdag
  (:require [reagent.core :as r])
  (:require-macros
   [devcards.core :as dc :refer [defcard defcard-rg deftest]]))

(enable-console-print!)

(defn enhance [count]
  (apply str (repeat count "!")))

(defcard-rg miles-card
  (let [times (r/atom 0)]
    (fn []
      [:div.row
       [:div.col-xs-12]
       [:h1 (str "Hello miles" (enhance @times))]
       [:div.btn.btn-success
        {:on-click #(reset! times (inc @times))}
        "+1"]])))
