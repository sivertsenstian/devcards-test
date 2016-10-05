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


(def data (r/atom [{:id 1 :name "Robot1" :color "red" :ipaddress "123" :url "https://images-na.ssl-images-amazon.com/images/I/41IXITCtVwL._SY300_.jpg"}
                   {:id 2 :name "Robot2" :color "blue" :ipaddress "123" :url "https://images-na.ssl-images-amazon.com/images/I/41IXITCtVwL._SY300_.jpg"}
                   {:id 3 :name "Robot3" :color "green" :ipaddress "123" :url "https://images-na.ssl-images-amazon.com/images/I/41IXITCtVwL._SY300_.jpg"}]))

(defn create-robot-row [{:keys [id name color ipaddress url] :as robot}]
  ^{:key (str "robot-row-" id)}
  [:tr
    [:td id]
    [:td name]
    [:td 
     {:style {:color color
              :border (str "2px solid " color)}}
     color]
    [:td ipaddress]
    [:td.text-center.img-responsive [:img {:src url :height 40}]]])

(defn create-robot-table [data]
  [:table.table.table-bordered
       [:thead>tr
        [:th "ID"]
        [:th "Name"]
        [:th "Color"]
        [:th "IP"]
        [:th "Image"]]
      [:tbody
        (map create-robot-row data)]])

(defcard-rg robots-table
  (fn []
    [:div.row
      [:div.btn.btn-danger 
       {:on-click #(reset! data [{:id 1 :name "Robot1" :color "red" :ipaddress "123" :url "https://images-na.ssl-images-amazon.com/images/I/41IXITCtVwL._SY300_.jpg"}])}
       "Dont click!"]
      [:div.col-xs-12
        [:h1 "Robots here!"]
        [:hr]
        (create-robot-table @data)]]))
      