(ns trackure.core
	(:import (java.util Date)))

(defn new-project
	[name]
	{:name name :created (Date.)})

(defn add-project
	([project] (add-project {} project))
	([projects project] (assoc projects (:name project) project)))

(defn del-project
	[projects name] 
	(dissoc projects name))
