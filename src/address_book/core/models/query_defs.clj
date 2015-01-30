(ns address-book.core.models.query-defs
  (:require [environ.core :refer [env]]
            [yesql.core :refer [defqueries]]))

;; setup filename and read correct database URL from
;; the environment
(defqueries 
  "address_book/core/models/address_book_queries.sql"
  {:connection (env :database-url)})

