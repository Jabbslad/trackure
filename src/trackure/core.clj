(ns trackure.core
    (:require [clojure.java.jdbc :as j]
              [clojure.java.jdbc.sql :as s]))

(def db 
    {:classname "org.sqlite.JDBC"
     :subprotocol "sqlite"
     :subname "db/database.db"})

(defn create-database []
    (try (j/with-connection db
        (j/create-table :projects
            [:id :serial "PRIMARY KEY"]
            [:name :varchar "NOT NULL"]
            [:timespent :int "NOT NULL"]
            [:created_at :timestamp "NOT NULL" "DEFAULT CURRENT_TIMESTAMP"]))
    (catch Exception e (println e))))

(defn destroy-database []
    (try (j/with-connection db
        (j/drop-table :projects))
    (catch Exception e (println e))))

(defn insert-project [name timespent]
    (j/insert! db :projects
        {:name name :timespent timespent}))

(defn get-project [name]
    (j/query db 
        (s/select * :projects (s/where {:name name}))))
