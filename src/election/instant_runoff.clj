(ns election.instant-runoff)

(def candidates #{"john" "jeff" "bill"})
(def votes [["john" "jeff" "bill"]
	    ["jeff" "bill"]
	    ["jeff" "john"]])

(defn interpret-vote [vote candidates]
  (let [elligible (filter #(contains? candidates %)
			  vote)]
    (if (empty? elligible)
      []
      [(first elligible)])))

; votelist is in form: ["bob" "bob" "jeff"]
(defn votelist-for-round [votes candidates]
  (apply concat 
	 (map #(interpret-vote % candidates)
	      votes)))

(defn votes-for [candidate votelist]
  (count (filter #(= candidate %) votelist)))

(defn smallest-number-of-votes [votelist candidates]
  (apply min
	 (map #(votes-for % votelist) 
	      candidates)))

(defn survivors-of-round [votelist candidates]
  (let [survival-threshold
	(smallest-number-of-votes votelist 
				  candidates)]
    (set (filter #(> (votes-for % votelist) 
		     survival-threshold)
		 candidates))))


(defn winner [votes candidates]
  (cond (empty? candidates) []
	(= 1 (count candidates)) [(first candidates)]
	:default (recur votes
			(survivors-of-round (votelist-for-round votes 
								candidates)
					    candidates))))



