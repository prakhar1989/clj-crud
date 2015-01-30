(ns address-book.core.routes.address-book-routes
  (:require [compojure.core :refer [defroutes GET POST]]
            [ring.util.response :as response]
            [address-book.core.views.address-book-views :refer [common-layout
                                                                read-contact
                                                                add-contact-form
                                                                edit-contact-form]]
            [address-book.core.models.query-defs :as query]))

(defn add-contact [request]
  (let [name (get-in request [:params :name])
        phone (get-in request [:params :phone])
        email (get-in request [:params :email])]
    (query/insert-contact<! {:name name :phone phone :email email})
    (response/redirect "/")))

(defn display-contact [contact contact-id]
  (if (not= (and contact-id (Integer. contact-id)) (:id contact))
    (read-contact contact)
    (edit-contact-form contact)))

(defn show-contacts [request]
  (let [contact-id (get-in request [:params :contact-id])]
    (common-layout 
      (for [contact (query/all-contacts)]
        (display-contact contact contact-id))
      (add-contact-form))))

(defn update-contact [request]
  (let [contact-id (get-in request [:params :contact-id])
        name (get-in request [:params :name])
        phone (get-in request [:params :phone])
        email (get-in request [:params :email])]
    (query/update-contact<! {:name name :email email 
                             :phone phone :id (Integer. contact-id)})
    (response/redirect "/")))

(defn delete-contact [request]
  (let [contact-id (get-in request [:params :contact-id])]
    (query/delete-contact<! {:id (Integer. contact-id)})
    (response/redirect "/")))

(defroutes address-book-routes
  (GET "/"                   [] show-contacts)
  (POST "/post"              [] add-contact)
  (GET "/edit/:contact-id"   [] show-contacts)
  (POST "/edit/:contact-id"  [] update-contact)
  (POST "/delete/:contact-id" [] delete-contact))
