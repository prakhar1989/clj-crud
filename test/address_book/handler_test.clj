(ns address-book.handler-test
  (:use midje.sweet)
  (:require [clojure.test :refer :all]
            ;;[ring.mock.request :as mock]
            [address-book.handler :refer :all]))

#_(facts "Example GET and POST tests"
  (fact "Test GET"
   (let [response (app (mock/request :get "/"))]
     (:status response) => 200
     (:body response) => (contains "Jarrod Taylor")))

  (fact "Test post"
    (let [response (app (mock/request :post "/post" {:name "Foo"}))]
      (:status response) => 200
      (:body response) => "Hello, Foo")))
