(defproject address-book "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :min-lein-version "2.0.0"
  :dependencies [[org.clojure/clojure "1.6.0"]
                 [compojure "1.3.1"]
                 [ring/ring-defaults "0.1.2"]
                 [midje "1.6.3"]
                 [hiccup "1.0.5"]
                 [postgresql/postgresql "9.3-1102.jdbc41"]
                 [yesql "0.5.0-rc1"]
                 [environ "1.0.0"]]
  :plugins [[lein-ring "0.9.1"]
            [lein-environ "1.0.0"]]
  :ring {:handler address-book.handler/app
         :init address-book.handler/init}
  :profiles
  {:test-local {:dependencies [[midje "1.6.3"]
                               [javax.servlet/servlet-api "2.5"]
                               [ring-mock "0.1.5"]]
                :plugins      [[lein-midje "3.1.3"]]}
   
   :test-env-vars {}
   :dev-env-vars {}
   :test [:test-local :test-env-vars]
   :dev [:dev-env-vars]})
