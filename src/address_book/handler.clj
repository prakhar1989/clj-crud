(ns address-book.handler
  (:require [compojure.core :refer :all]
            [compojure.route :as route]
            [ring.middleware.defaults :refer [wrap-defaults site-defaults]]
            [address-book.core.routes.address-book-routes :refer :all]
            [address-book.core.models.query-defs :as query]))

(defn init []
  (do 
   (println "Address book application has started")  
   (query/create-contacts-table-if-not-exists!)))

(defroutes app-routes
  (route/not-found "Not Found"))

(def app
  (-> (routes address-book-routes app-routes)
      (wrap-defaults (assoc-in site-defaults [:security :anti-forgery] false))))
