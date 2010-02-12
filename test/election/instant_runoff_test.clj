(ns election.instant-runoff-test
  (:use election.instant-runoff
	clojure.contrib.test-is))

(deftest interpret-vote-test
  (is (= [] 
	 (interpret-vote ["jeff" "bill"] 
			 #{"harry" "sally"})))
  (is (= ["sally"]
	 (interpret-vote ["jeff" "bill" "sally"]
			 #{"harry" "sally"})))
  (is (= ["jeff"]
	 (interpret-vote ["jeff" "bill" "sally"]
			 #{"harry" "jeff" "bill"}))))

