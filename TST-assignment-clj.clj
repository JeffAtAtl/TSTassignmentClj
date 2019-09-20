;; gorilla-repl.fileformat = 1

;; **
;;; # Gorilla REPL
;;; 
;;; Welcome to gorilla :-)
;;; 
;;; Shift + enter evaluates code. Hit alt+g twice in quick succession or click the menu icon (upper-right corner) for more commands ...
;;; 
;;; It's a good habit to run each worksheet in its own namespace: feel free to use the declaration we've provided below if you'd like.
;;; 
;; **

;; @@
(ns TST-assignment-clj
  (:use clojure.test))
;; @@
;; =>
;;; {"type":"html","content":"<span class='clj-nil'>nil</span>","value":"nil"}
;; <=

;; **
;;; ## Problem 1 
;;; 
;;; **Background:** The TST cruise application receives pricing and rate information from a third party data provider. We make two calls to this provider to receive a list of rates and a list of cabin prices. We can use this data to solve several problems for our customers. The problem we’ll be focusing on for this exercise will be finding the best price for a particular rate group.   
;;; 
;;; **Cabin Price:** The price for a specific cabin on a specific cruise. All cabin prices will have a single rate attached. 
;;; 
;;; **Rate:** A rate is a way to group related prices together. A rate is defined by its Rate Code and which Rate Group it belongs to. For example. (MilAB, Military) and (Sen123, Senior)  
;;; 
;;; **Rate Group:** Specific rates are grouped into a related rate group. There is a one-to-many relationship between rate groups and rates (A rate group is made up of many rates, but a rate can only belong to a single rate group) Some examples of rate groups are: Standard, Military, Senior, and Promotion
;; **

;; **
;;;  1. Write a function that will take a list of rates and a list of prices and returns the best price for each rate group. We’ve supplied the function and case class definitions below for you to use
;;;  
;;;  2. On startup, your program should run the following sample data through your function and output the sequence of BestGroupPrices. We included the expected output below: 
;; **

;; @@
(defn best-price-for-each-rate-group
  [rates prices]
  (->> (for [r rates
             p prices
           :when (= (:rateCode r) (:rateCode p))]
          (merge r p))                             ;; Join using list comprehension
     (sort-by (juxt :cabinCode :rateGroup :price)) ;; Sort so cabin codes together and rate groups together and lowest price first
     (partition-by (juxt :rateGroup :cabinCode))   ;; Group by rate group and cabin code
     (map first)                                   ;; get the lowest price in each group
  ))
;; @@
;; =>
;;; {"type":"html","content":"<span class='clj-var'>#&#x27;TST-assignment-clj/best-price-for-each-rate-group</span>","value":"#'TST-assignment-clj/best-price-for-each-rate-group"}
;; <=

;; @@
;; data
(def rates [{:rateCode "M1" :rateGroup "Military"}
            {:rateCode "M2" :rateGroup "Military"}
            {:rateCode "S1" :rateGroup "Senior"}
            {:rateCode "S2" :rateGroup "Senior"}])

(def prices [{:cabinCode "CA" :rateCode "M1" :price 200.00}
             {:cabinCode "CA" :rateCode "M2" :price 250.00}
             {:cabinCode "CA" :rateCode "S1" :price 225.00}
             {:cabinCode "CA" :rateCode "S2" :price 260.00}
             {:cabinCode "CB" :rateCode "M1" :price 230.00}
             {:cabinCode "CB" :rateCode "M2" :price 260.00}
             {:cabinCode "CB" :rateCode "S1" :price 245.00} 
             {:cabinCode "CB" :rateCode "S2" :price 270.00}])
;; @@
;; =>
;;; {"type":"html","content":"<span class='clj-var'>#&#x27;TST-assignment-clj/prices</span>","value":"#'TST-assignment-clj/prices"}
;; <=

;; @@
;; correct output
(def correct-output '({:rateCode "M1", :rateGroup "Military", :cabinCode "CA", :price 200.0} 
                      {:rateCode "S1", :rateGroup "Senior", :cabinCode "CA", :price 225.0} 
                      {:rateCode "M1", :rateGroup "Military", :cabinCode "CB", :price 230.0} 
                      {:rateCode "S1", :rateGroup "Senior", :cabinCode "CB", :price 245.0}))
;; @@
;; =>
;;; {"type":"html","content":"<span class='clj-var'>#&#x27;TST-assignment-clj/correct-output</span>","value":"#'TST-assignment-clj/correct-output"}
;; <=

;; @@
;; tests
(deftest test-best-price-for-each-rate-group
  (is (= correct-output (best-price-for-each-rate-group rates prices))))
;; @@
;; =>
;;; {"type":"html","content":"<span class='clj-var'>#&#x27;TST-assignment-clj/test-best-price-for-each-rate-group</span>","value":"#'TST-assignment-clj/test-best-price-for-each-rate-group"}
;; <=

;; @@
(run-tests)
;; @@
;; ->
;;; 
;;; Testing TST-assignment-clj
;;; 
;;; Ran 1 tests containing 1 assertions.
;;; 0 failures, 0 errors.
;;; 
;; <-
;; =>
;;; {"type":"list-like","open":"<span class='clj-map'>{</span>","close":"<span class='clj-map'>}</span>","separator":", ","items":[{"type":"list-like","open":"","close":"","separator":" ","items":[{"type":"html","content":"<span class='clj-keyword'>:test</span>","value":":test"},{"type":"html","content":"<span class='clj-long'>1</span>","value":"1"}],"value":"[:test 1]"},{"type":"list-like","open":"","close":"","separator":" ","items":[{"type":"html","content":"<span class='clj-keyword'>:pass</span>","value":":pass"},{"type":"html","content":"<span class='clj-long'>1</span>","value":"1"}],"value":"[:pass 1]"},{"type":"list-like","open":"","close":"","separator":" ","items":[{"type":"html","content":"<span class='clj-keyword'>:fail</span>","value":":fail"},{"type":"html","content":"<span class='clj-long'>0</span>","value":"0"}],"value":"[:fail 0]"},{"type":"list-like","open":"","close":"","separator":" ","items":[{"type":"html","content":"<span class='clj-keyword'>:error</span>","value":":error"},{"type":"html","content":"<span class='clj-long'>0</span>","value":"0"}],"value":"[:error 0]"},{"type":"list-like","open":"","close":"","separator":" ","items":[{"type":"html","content":"<span class='clj-keyword'>:type</span>","value":":type"},{"type":"html","content":"<span class='clj-keyword'>:summary</span>","value":":summary"}],"value":"[:type :summary]"}],"value":"{:test 1, :pass 1, :fail 0, :error 0, :type :summary}"}
;; <=

;; @@
;; experiments 
(->> (for [r rates
           p prices
           :when (= (:rateCode r) (:rateCode p))]
          (merge r p))                             ;; Join using list comprehension
     (sort-by (juxt :cabinCode :rateGroup :price)) ;; Sort so cabin codes together and rate groups together and lowest price first
     (partition-by (juxt :rateGroup :cabinCode))   ;; Group by rate group and cabin code
     (map first)                                   ;; get the lowest price in each group.
  )
;; @@
;; =>
;;; {"type":"list-like","open":"<span class='clj-lazy-seq'>(</span>","close":"<span class='clj-lazy-seq'>)</span>","separator":" ","items":[{"type":"list-like","open":"<span class='clj-map'>{</span>","close":"<span class='clj-map'>}</span>","separator":", ","items":[{"type":"list-like","open":"","close":"","separator":" ","items":[{"type":"html","content":"<span class='clj-keyword'>:rateCode</span>","value":":rateCode"},{"type":"html","content":"<span class='clj-string'>&quot;M1&quot;</span>","value":"\"M1\""}],"value":"[:rateCode \"M1\"]"},{"type":"list-like","open":"","close":"","separator":" ","items":[{"type":"html","content":"<span class='clj-keyword'>:rateGroup</span>","value":":rateGroup"},{"type":"html","content":"<span class='clj-string'>&quot;Military&quot;</span>","value":"\"Military\""}],"value":"[:rateGroup \"Military\"]"},{"type":"list-like","open":"","close":"","separator":" ","items":[{"type":"html","content":"<span class='clj-keyword'>:cabinCode</span>","value":":cabinCode"},{"type":"html","content":"<span class='clj-string'>&quot;CA&quot;</span>","value":"\"CA\""}],"value":"[:cabinCode \"CA\"]"},{"type":"list-like","open":"","close":"","separator":" ","items":[{"type":"html","content":"<span class='clj-keyword'>:price</span>","value":":price"},{"type":"html","content":"<span class='clj-double'>200.0</span>","value":"200.0"}],"value":"[:price 200.0]"}],"value":"{:rateCode \"M1\", :rateGroup \"Military\", :cabinCode \"CA\", :price 200.0}"},{"type":"list-like","open":"<span class='clj-map'>{</span>","close":"<span class='clj-map'>}</span>","separator":", ","items":[{"type":"list-like","open":"","close":"","separator":" ","items":[{"type":"html","content":"<span class='clj-keyword'>:rateCode</span>","value":":rateCode"},{"type":"html","content":"<span class='clj-string'>&quot;S1&quot;</span>","value":"\"S1\""}],"value":"[:rateCode \"S1\"]"},{"type":"list-like","open":"","close":"","separator":" ","items":[{"type":"html","content":"<span class='clj-keyword'>:rateGroup</span>","value":":rateGroup"},{"type":"html","content":"<span class='clj-string'>&quot;Senior&quot;</span>","value":"\"Senior\""}],"value":"[:rateGroup \"Senior\"]"},{"type":"list-like","open":"","close":"","separator":" ","items":[{"type":"html","content":"<span class='clj-keyword'>:cabinCode</span>","value":":cabinCode"},{"type":"html","content":"<span class='clj-string'>&quot;CA&quot;</span>","value":"\"CA\""}],"value":"[:cabinCode \"CA\"]"},{"type":"list-like","open":"","close":"","separator":" ","items":[{"type":"html","content":"<span class='clj-keyword'>:price</span>","value":":price"},{"type":"html","content":"<span class='clj-double'>225.0</span>","value":"225.0"}],"value":"[:price 225.0]"}],"value":"{:rateCode \"S1\", :rateGroup \"Senior\", :cabinCode \"CA\", :price 225.0}"},{"type":"list-like","open":"<span class='clj-map'>{</span>","close":"<span class='clj-map'>}</span>","separator":", ","items":[{"type":"list-like","open":"","close":"","separator":" ","items":[{"type":"html","content":"<span class='clj-keyword'>:rateCode</span>","value":":rateCode"},{"type":"html","content":"<span class='clj-string'>&quot;M1&quot;</span>","value":"\"M1\""}],"value":"[:rateCode \"M1\"]"},{"type":"list-like","open":"","close":"","separator":" ","items":[{"type":"html","content":"<span class='clj-keyword'>:rateGroup</span>","value":":rateGroup"},{"type":"html","content":"<span class='clj-string'>&quot;Military&quot;</span>","value":"\"Military\""}],"value":"[:rateGroup \"Military\"]"},{"type":"list-like","open":"","close":"","separator":" ","items":[{"type":"html","content":"<span class='clj-keyword'>:cabinCode</span>","value":":cabinCode"},{"type":"html","content":"<span class='clj-string'>&quot;CB&quot;</span>","value":"\"CB\""}],"value":"[:cabinCode \"CB\"]"},{"type":"list-like","open":"","close":"","separator":" ","items":[{"type":"html","content":"<span class='clj-keyword'>:price</span>","value":":price"},{"type":"html","content":"<span class='clj-double'>230.0</span>","value":"230.0"}],"value":"[:price 230.0]"}],"value":"{:rateCode \"M1\", :rateGroup \"Military\", :cabinCode \"CB\", :price 230.0}"},{"type":"list-like","open":"<span class='clj-map'>{</span>","close":"<span class='clj-map'>}</span>","separator":", ","items":[{"type":"list-like","open":"","close":"","separator":" ","items":[{"type":"html","content":"<span class='clj-keyword'>:rateCode</span>","value":":rateCode"},{"type":"html","content":"<span class='clj-string'>&quot;S1&quot;</span>","value":"\"S1\""}],"value":"[:rateCode \"S1\"]"},{"type":"list-like","open":"","close":"","separator":" ","items":[{"type":"html","content":"<span class='clj-keyword'>:rateGroup</span>","value":":rateGroup"},{"type":"html","content":"<span class='clj-string'>&quot;Senior&quot;</span>","value":"\"Senior\""}],"value":"[:rateGroup \"Senior\"]"},{"type":"list-like","open":"","close":"","separator":" ","items":[{"type":"html","content":"<span class='clj-keyword'>:cabinCode</span>","value":":cabinCode"},{"type":"html","content":"<span class='clj-string'>&quot;CB&quot;</span>","value":"\"CB\""}],"value":"[:cabinCode \"CB\"]"},{"type":"list-like","open":"","close":"","separator":" ","items":[{"type":"html","content":"<span class='clj-keyword'>:price</span>","value":":price"},{"type":"html","content":"<span class='clj-double'>245.0</span>","value":"245.0"}],"value":"[:price 245.0]"}],"value":"{:rateCode \"S1\", :rateGroup \"Senior\", :cabinCode \"CB\", :price 245.0}"}],"value":"({:rateCode \"M1\", :rateGroup \"Military\", :cabinCode \"CA\", :price 200.0} {:rateCode \"S1\", :rateGroup \"Senior\", :cabinCode \"CA\", :price 225.0} {:rateCode \"M1\", :rateGroup \"Military\", :cabinCode \"CB\", :price 230.0} {:rateCode \"S1\", :rateGroup \"Senior\", :cabinCode \"CB\", :price 245.0})"}
;; <=

;; @@
(partition-by (juxt :rateGroup :cabinCode) '({:rateCode "M1", :rateGroup "Military", :cabinCode "CA", :price 200.0} {:rateCode "M2", :rateGroup "Military", :cabinCode "CA", :price 250.0} {:rateCode "S1", :rateGroup "Senior", :cabinCode "CA", :price 225.0} {:rateCode "S2", :rateGroup "Senior", :cabinCode "CA", :price 260.0} {:rateCode "M1", :rateGroup "Military", :cabinCode "CB", :price 230.0} {:rateCode "M2", :rateGroup "Military", :cabinCode "CB", :price 260.0} {:rateCode "S1", :rateGroup "Senior", :cabinCode "CB", :price 245.0} {:rateCode "S2", :rateGroup "Senior", :cabinCode "CB", :price 270.0}))
;; @@
;; =>
;;; {"type":"list-like","open":"<span class='clj-lazy-seq'>(</span>","close":"<span class='clj-lazy-seq'>)</span>","separator":" ","items":[{"type":"list-like","open":"<span class='clj-list'>(</span>","close":"<span class='clj-list'>)</span>","separator":" ","items":[{"type":"list-like","open":"<span class='clj-map'>{</span>","close":"<span class='clj-map'>}</span>","separator":", ","items":[{"type":"list-like","open":"","close":"","separator":" ","items":[{"type":"html","content":"<span class='clj-keyword'>:rateCode</span>","value":":rateCode"},{"type":"html","content":"<span class='clj-string'>&quot;M1&quot;</span>","value":"\"M1\""}],"value":"[:rateCode \"M1\"]"},{"type":"list-like","open":"","close":"","separator":" ","items":[{"type":"html","content":"<span class='clj-keyword'>:rateGroup</span>","value":":rateGroup"},{"type":"html","content":"<span class='clj-string'>&quot;Military&quot;</span>","value":"\"Military\""}],"value":"[:rateGroup \"Military\"]"},{"type":"list-like","open":"","close":"","separator":" ","items":[{"type":"html","content":"<span class='clj-keyword'>:cabinCode</span>","value":":cabinCode"},{"type":"html","content":"<span class='clj-string'>&quot;CA&quot;</span>","value":"\"CA\""}],"value":"[:cabinCode \"CA\"]"},{"type":"list-like","open":"","close":"","separator":" ","items":[{"type":"html","content":"<span class='clj-keyword'>:price</span>","value":":price"},{"type":"html","content":"<span class='clj-double'>200.0</span>","value":"200.0"}],"value":"[:price 200.0]"}],"value":"{:rateCode \"M1\", :rateGroup \"Military\", :cabinCode \"CA\", :price 200.0}"},{"type":"list-like","open":"<span class='clj-map'>{</span>","close":"<span class='clj-map'>}</span>","separator":", ","items":[{"type":"list-like","open":"","close":"","separator":" ","items":[{"type":"html","content":"<span class='clj-keyword'>:rateCode</span>","value":":rateCode"},{"type":"html","content":"<span class='clj-string'>&quot;M2&quot;</span>","value":"\"M2\""}],"value":"[:rateCode \"M2\"]"},{"type":"list-like","open":"","close":"","separator":" ","items":[{"type":"html","content":"<span class='clj-keyword'>:rateGroup</span>","value":":rateGroup"},{"type":"html","content":"<span class='clj-string'>&quot;Military&quot;</span>","value":"\"Military\""}],"value":"[:rateGroup \"Military\"]"},{"type":"list-like","open":"","close":"","separator":" ","items":[{"type":"html","content":"<span class='clj-keyword'>:cabinCode</span>","value":":cabinCode"},{"type":"html","content":"<span class='clj-string'>&quot;CA&quot;</span>","value":"\"CA\""}],"value":"[:cabinCode \"CA\"]"},{"type":"list-like","open":"","close":"","separator":" ","items":[{"type":"html","content":"<span class='clj-keyword'>:price</span>","value":":price"},{"type":"html","content":"<span class='clj-double'>250.0</span>","value":"250.0"}],"value":"[:price 250.0]"}],"value":"{:rateCode \"M2\", :rateGroup \"Military\", :cabinCode \"CA\", :price 250.0}"}],"value":"({:rateCode \"M1\", :rateGroup \"Military\", :cabinCode \"CA\", :price 200.0} {:rateCode \"M2\", :rateGroup \"Military\", :cabinCode \"CA\", :price 250.0})"},{"type":"list-like","open":"<span class='clj-list'>(</span>","close":"<span class='clj-list'>)</span>","separator":" ","items":[{"type":"list-like","open":"<span class='clj-map'>{</span>","close":"<span class='clj-map'>}</span>","separator":", ","items":[{"type":"list-like","open":"","close":"","separator":" ","items":[{"type":"html","content":"<span class='clj-keyword'>:rateCode</span>","value":":rateCode"},{"type":"html","content":"<span class='clj-string'>&quot;S1&quot;</span>","value":"\"S1\""}],"value":"[:rateCode \"S1\"]"},{"type":"list-like","open":"","close":"","separator":" ","items":[{"type":"html","content":"<span class='clj-keyword'>:rateGroup</span>","value":":rateGroup"},{"type":"html","content":"<span class='clj-string'>&quot;Senior&quot;</span>","value":"\"Senior\""}],"value":"[:rateGroup \"Senior\"]"},{"type":"list-like","open":"","close":"","separator":" ","items":[{"type":"html","content":"<span class='clj-keyword'>:cabinCode</span>","value":":cabinCode"},{"type":"html","content":"<span class='clj-string'>&quot;CA&quot;</span>","value":"\"CA\""}],"value":"[:cabinCode \"CA\"]"},{"type":"list-like","open":"","close":"","separator":" ","items":[{"type":"html","content":"<span class='clj-keyword'>:price</span>","value":":price"},{"type":"html","content":"<span class='clj-double'>225.0</span>","value":"225.0"}],"value":"[:price 225.0]"}],"value":"{:rateCode \"S1\", :rateGroup \"Senior\", :cabinCode \"CA\", :price 225.0}"},{"type":"list-like","open":"<span class='clj-map'>{</span>","close":"<span class='clj-map'>}</span>","separator":", ","items":[{"type":"list-like","open":"","close":"","separator":" ","items":[{"type":"html","content":"<span class='clj-keyword'>:rateCode</span>","value":":rateCode"},{"type":"html","content":"<span class='clj-string'>&quot;S2&quot;</span>","value":"\"S2\""}],"value":"[:rateCode \"S2\"]"},{"type":"list-like","open":"","close":"","separator":" ","items":[{"type":"html","content":"<span class='clj-keyword'>:rateGroup</span>","value":":rateGroup"},{"type":"html","content":"<span class='clj-string'>&quot;Senior&quot;</span>","value":"\"Senior\""}],"value":"[:rateGroup \"Senior\"]"},{"type":"list-like","open":"","close":"","separator":" ","items":[{"type":"html","content":"<span class='clj-keyword'>:cabinCode</span>","value":":cabinCode"},{"type":"html","content":"<span class='clj-string'>&quot;CA&quot;</span>","value":"\"CA\""}],"value":"[:cabinCode \"CA\"]"},{"type":"list-like","open":"","close":"","separator":" ","items":[{"type":"html","content":"<span class='clj-keyword'>:price</span>","value":":price"},{"type":"html","content":"<span class='clj-double'>260.0</span>","value":"260.0"}],"value":"[:price 260.0]"}],"value":"{:rateCode \"S2\", :rateGroup \"Senior\", :cabinCode \"CA\", :price 260.0}"}],"value":"({:rateCode \"S1\", :rateGroup \"Senior\", :cabinCode \"CA\", :price 225.0} {:rateCode \"S2\", :rateGroup \"Senior\", :cabinCode \"CA\", :price 260.0})"},{"type":"list-like","open":"<span class='clj-list'>(</span>","close":"<span class='clj-list'>)</span>","separator":" ","items":[{"type":"list-like","open":"<span class='clj-map'>{</span>","close":"<span class='clj-map'>}</span>","separator":", ","items":[{"type":"list-like","open":"","close":"","separator":" ","items":[{"type":"html","content":"<span class='clj-keyword'>:rateCode</span>","value":":rateCode"},{"type":"html","content":"<span class='clj-string'>&quot;M1&quot;</span>","value":"\"M1\""}],"value":"[:rateCode \"M1\"]"},{"type":"list-like","open":"","close":"","separator":" ","items":[{"type":"html","content":"<span class='clj-keyword'>:rateGroup</span>","value":":rateGroup"},{"type":"html","content":"<span class='clj-string'>&quot;Military&quot;</span>","value":"\"Military\""}],"value":"[:rateGroup \"Military\"]"},{"type":"list-like","open":"","close":"","separator":" ","items":[{"type":"html","content":"<span class='clj-keyword'>:cabinCode</span>","value":":cabinCode"},{"type":"html","content":"<span class='clj-string'>&quot;CB&quot;</span>","value":"\"CB\""}],"value":"[:cabinCode \"CB\"]"},{"type":"list-like","open":"","close":"","separator":" ","items":[{"type":"html","content":"<span class='clj-keyword'>:price</span>","value":":price"},{"type":"html","content":"<span class='clj-double'>230.0</span>","value":"230.0"}],"value":"[:price 230.0]"}],"value":"{:rateCode \"M1\", :rateGroup \"Military\", :cabinCode \"CB\", :price 230.0}"},{"type":"list-like","open":"<span class='clj-map'>{</span>","close":"<span class='clj-map'>}</span>","separator":", ","items":[{"type":"list-like","open":"","close":"","separator":" ","items":[{"type":"html","content":"<span class='clj-keyword'>:rateCode</span>","value":":rateCode"},{"type":"html","content":"<span class='clj-string'>&quot;M2&quot;</span>","value":"\"M2\""}],"value":"[:rateCode \"M2\"]"},{"type":"list-like","open":"","close":"","separator":" ","items":[{"type":"html","content":"<span class='clj-keyword'>:rateGroup</span>","value":":rateGroup"},{"type":"html","content":"<span class='clj-string'>&quot;Military&quot;</span>","value":"\"Military\""}],"value":"[:rateGroup \"Military\"]"},{"type":"list-like","open":"","close":"","separator":" ","items":[{"type":"html","content":"<span class='clj-keyword'>:cabinCode</span>","value":":cabinCode"},{"type":"html","content":"<span class='clj-string'>&quot;CB&quot;</span>","value":"\"CB\""}],"value":"[:cabinCode \"CB\"]"},{"type":"list-like","open":"","close":"","separator":" ","items":[{"type":"html","content":"<span class='clj-keyword'>:price</span>","value":":price"},{"type":"html","content":"<span class='clj-double'>260.0</span>","value":"260.0"}],"value":"[:price 260.0]"}],"value":"{:rateCode \"M2\", :rateGroup \"Military\", :cabinCode \"CB\", :price 260.0}"}],"value":"({:rateCode \"M1\", :rateGroup \"Military\", :cabinCode \"CB\", :price 230.0} {:rateCode \"M2\", :rateGroup \"Military\", :cabinCode \"CB\", :price 260.0})"},{"type":"list-like","open":"<span class='clj-list'>(</span>","close":"<span class='clj-list'>)</span>","separator":" ","items":[{"type":"list-like","open":"<span class='clj-map'>{</span>","close":"<span class='clj-map'>}</span>","separator":", ","items":[{"type":"list-like","open":"","close":"","separator":" ","items":[{"type":"html","content":"<span class='clj-keyword'>:rateCode</span>","value":":rateCode"},{"type":"html","content":"<span class='clj-string'>&quot;S1&quot;</span>","value":"\"S1\""}],"value":"[:rateCode \"S1\"]"},{"type":"list-like","open":"","close":"","separator":" ","items":[{"type":"html","content":"<span class='clj-keyword'>:rateGroup</span>","value":":rateGroup"},{"type":"html","content":"<span class='clj-string'>&quot;Senior&quot;</span>","value":"\"Senior\""}],"value":"[:rateGroup \"Senior\"]"},{"type":"list-like","open":"","close":"","separator":" ","items":[{"type":"html","content":"<span class='clj-keyword'>:cabinCode</span>","value":":cabinCode"},{"type":"html","content":"<span class='clj-string'>&quot;CB&quot;</span>","value":"\"CB\""}],"value":"[:cabinCode \"CB\"]"},{"type":"list-like","open":"","close":"","separator":" ","items":[{"type":"html","content":"<span class='clj-keyword'>:price</span>","value":":price"},{"type":"html","content":"<span class='clj-double'>245.0</span>","value":"245.0"}],"value":"[:price 245.0]"}],"value":"{:rateCode \"S1\", :rateGroup \"Senior\", :cabinCode \"CB\", :price 245.0}"},{"type":"list-like","open":"<span class='clj-map'>{</span>","close":"<span class='clj-map'>}</span>","separator":", ","items":[{"type":"list-like","open":"","close":"","separator":" ","items":[{"type":"html","content":"<span class='clj-keyword'>:rateCode</span>","value":":rateCode"},{"type":"html","content":"<span class='clj-string'>&quot;S2&quot;</span>","value":"\"S2\""}],"value":"[:rateCode \"S2\"]"},{"type":"list-like","open":"","close":"","separator":" ","items":[{"type":"html","content":"<span class='clj-keyword'>:rateGroup</span>","value":":rateGroup"},{"type":"html","content":"<span class='clj-string'>&quot;Senior&quot;</span>","value":"\"Senior\""}],"value":"[:rateGroup \"Senior\"]"},{"type":"list-like","open":"","close":"","separator":" ","items":[{"type":"html","content":"<span class='clj-keyword'>:cabinCode</span>","value":":cabinCode"},{"type":"html","content":"<span class='clj-string'>&quot;CB&quot;</span>","value":"\"CB\""}],"value":"[:cabinCode \"CB\"]"},{"type":"list-like","open":"","close":"","separator":" ","items":[{"type":"html","content":"<span class='clj-keyword'>:price</span>","value":":price"},{"type":"html","content":"<span class='clj-double'>270.0</span>","value":"270.0"}],"value":"[:price 270.0]"}],"value":"{:rateCode \"S2\", :rateGroup \"Senior\", :cabinCode \"CB\", :price 270.0}"}],"value":"({:rateCode \"S1\", :rateGroup \"Senior\", :cabinCode \"CB\", :price 245.0} {:rateCode \"S2\", :rateGroup \"Senior\", :cabinCode \"CB\", :price 270.0})"}],"value":"(({:rateCode \"M1\", :rateGroup \"Military\", :cabinCode \"CA\", :price 200.0} {:rateCode \"M2\", :rateGroup \"Military\", :cabinCode \"CA\", :price 250.0}) ({:rateCode \"S1\", :rateGroup \"Senior\", :cabinCode \"CA\", :price 225.0} {:rateCode \"S2\", :rateGroup \"Senior\", :cabinCode \"CA\", :price 260.0}) ({:rateCode \"M1\", :rateGroup \"Military\", :cabinCode \"CB\", :price 230.0} {:rateCode \"M2\", :rateGroup \"Military\", :cabinCode \"CB\", :price 260.0}) ({:rateCode \"S1\", :rateGroup \"Senior\", :cabinCode \"CB\", :price 245.0} {:rateCode \"S2\", :rateGroup \"Senior\", :cabinCode \"CB\", :price 270.0}))"}
;; <=

;; **
;;; ##Problem 2 
;;; 
;;; **Background:** Cruise bookings can have one or more Promotions applied to them. But sometimes a Promotion cannot be combined with another Promotion. Our application has to find out all possible Promotion Combinations that can be applied together.   
;;; 
;;;  1. Implement a function to find all PromotionCombos with maximum number of combinable promotions in each. The function and case class definitions are supplied below to get you started.
;;;  
;;;  2. Implement a function to find all PromotionCombos for a given Promotion from given list of Promotions. The function definition is provided. 
;;;  
;;;  3. On startup your program should run through the following sample data and output the sequence of PromotionCombos. 
;; **

;; @@
(defn all-combinable-promotions
  [promotions]
  )

(defn combinable-promotions
  [code promotions]
  )
;; @@
;; =>
;;; {"type":"html","content":"<span class='clj-var'>#&#x27;TST-assignment-clj/combinable-promotions</span>","value":"#'TST-assignment-clj/combinable-promotions"}
;; <=

;; @@
(def promotions [{:code "P1" :not-combinable-with ["P3"]}
                 {:code "P2" :not-combinable-with ["P4" "P5"]}
                 {:code "P3" :not-combinable-with ["P1"]}
                 {:code "P4" :not-combinable-with ["P2"]}
                 {:code "P5" :not-combinable-with ["P2"]}])

(def expected-output-for-all-promotion-combinations [["P1" "P2"]
                                                     ["P1" "P4" "P5"]
                                                     ["P3" "P2"]
                                                     ["P3" "P4" "P5"]])
;; @@
;; =>
;;; {"type":"html","content":"<span class='clj-var'>#&#x27;TST-assignment-clj/expected-output-for-all-promotion-combinations</span>","value":"#'TST-assignment-clj/expected-output-for-all-promotion-combinations"}
;; <=

;; @@
;; experiments
(let [p-codes (map :code promotions)
      p-keys (map keyword p-codes)
      p-map (zipmap p-keys (map :not-combinable-with promotions))] 
     (remove (set ((first p-keys) p-map)) p-codes))

;; I know this needs to be recursive 

(defn remove-not-combinable-with
  [p])
;; @@
;; =>
;;; {"type":"list-like","open":"<span class='clj-lazy-seq'>(</span>","close":"<span class='clj-lazy-seq'>)</span>","separator":" ","items":[{"type":"html","content":"<span class='clj-string'>&quot;P1&quot;</span>","value":"\"P1\""},{"type":"html","content":"<span class='clj-string'>&quot;P2&quot;</span>","value":"\"P2\""},{"type":"html","content":"<span class='clj-string'>&quot;P4&quot;</span>","value":"\"P4\""},{"type":"html","content":"<span class='clj-string'>&quot;P5&quot;</span>","value":"\"P5\""}],"value":"(\"P1\" \"P2\" \"P4\" \"P5\")"}
;; <=

;; @@
(zipmap (map (comp keyword :code) promotions) (map :not-combinable-with promotions))
;; @@
;; =>
;;; {"type":"list-like","open":"<span class='clj-map'>{</span>","close":"<span class='clj-map'>}</span>","separator":", ","items":[{"type":"list-like","open":"","close":"","separator":" ","items":[{"type":"html","content":"<span class='clj-keyword'>:P1</span>","value":":P1"},{"type":"list-like","open":"<span class='clj-vector'>[</span>","close":"<span class='clj-vector'>]</span>","separator":" ","items":[{"type":"html","content":"<span class='clj-string'>&quot;P3&quot;</span>","value":"\"P3\""}],"value":"[\"P3\"]"}],"value":"[:P1 [\"P3\"]]"},{"type":"list-like","open":"","close":"","separator":" ","items":[{"type":"html","content":"<span class='clj-keyword'>:P2</span>","value":":P2"},{"type":"list-like","open":"<span class='clj-vector'>[</span>","close":"<span class='clj-vector'>]</span>","separator":" ","items":[{"type":"html","content":"<span class='clj-string'>&quot;P4&quot;</span>","value":"\"P4\""},{"type":"html","content":"<span class='clj-string'>&quot;P5&quot;</span>","value":"\"P5\""}],"value":"[\"P4\" \"P5\"]"}],"value":"[:P2 [\"P4\" \"P5\"]]"},{"type":"list-like","open":"","close":"","separator":" ","items":[{"type":"html","content":"<span class='clj-keyword'>:P3</span>","value":":P3"},{"type":"list-like","open":"<span class='clj-vector'>[</span>","close":"<span class='clj-vector'>]</span>","separator":" ","items":[{"type":"html","content":"<span class='clj-string'>&quot;P1&quot;</span>","value":"\"P1\""}],"value":"[\"P1\"]"}],"value":"[:P3 [\"P1\"]]"},{"type":"list-like","open":"","close":"","separator":" ","items":[{"type":"html","content":"<span class='clj-keyword'>:P4</span>","value":":P4"},{"type":"list-like","open":"<span class='clj-vector'>[</span>","close":"<span class='clj-vector'>]</span>","separator":" ","items":[{"type":"html","content":"<span class='clj-string'>&quot;P2&quot;</span>","value":"\"P2\""}],"value":"[\"P2\"]"}],"value":"[:P4 [\"P2\"]]"},{"type":"list-like","open":"","close":"","separator":" ","items":[{"type":"html","content":"<span class='clj-keyword'>:P5</span>","value":":P5"},{"type":"list-like","open":"<span class='clj-vector'>[</span>","close":"<span class='clj-vector'>]</span>","separator":" ","items":[{"type":"html","content":"<span class='clj-string'>&quot;P2&quot;</span>","value":"\"P2\""}],"value":"[\"P2\"]"}],"value":"[:P5 [\"P2\"]]"}],"value":"{:P1 [\"P3\"], :P2 [\"P4\" \"P5\"], :P3 [\"P1\"], :P4 [\"P2\"], :P5 [\"P2\"]}"}
;; <=

;; @@

;; @@
