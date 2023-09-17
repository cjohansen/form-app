(ns form-app.form-test
  (:require [cljs.test :refer [deftest testing is]]
            [form-app.form :as sut]))

(deftest prepare-date-field-test
  (testing "Prepares field with placeholder"
    (is (= (sut/prepare-date-input {} :field)
           {:placeholder "YYYY-MM-DD"
            :value ""
            :input-actions [[:action/save [:field :value] :event/target.value]]})))

  (testing "Includes current value"
    (is (= (sut/prepare-date-input {:field {:value "2023"}} :field)
           {:placeholder "YYYY-MM-DD"
            :value "2023"
            :input-actions [[:action/save [:field :value] :event/target.value]]
            :blur-actions [[:action/save [:field :state] :validating]]})))

  (testing "Displays validation message when in validating state"
    (is (= (sut/prepare-date-input {:field {:value "2023"
                                            :state :validating}} :field)
           {:placeholder "YYYY-MM-DD"
            :value "2023"
            :input-actions [[:action/save [:field :value] :event/target.value]]
            :blur-actions [[:action/save [:field :state] :validating]]
            :message "Incorrect date format, please use YYYY-MM-DD"
            :error? true})))

  (testing "Stops validating when error is corrected"
    (is (= (sut/prepare-date-input {:field {:value "2023-01-01"
                                            :state :validating}} :field)
           {:placeholder "YYYY-MM-DD"
            :value "2023-01-01"
            :input-actions [[:action/save [:field :value] :event/target.value]
                            [:action/save [:field :state] nil]]})))

  (testing "Stops validating when value is blank"
    (is (= (sut/prepare-date-input {:field {:value ""
                                            :state :validating}} :field)
           {:placeholder "YYYY-MM-DD"
            :value ""
            :input-actions [[:action/save [:field :value] :event/target.value]
                            [:action/save [:field :state] nil]]})))

  (testing "Treats nil value as empty string"
    (is (= (sut/prepare-date-input {:field {:value nil}} :field)
           {:placeholder "YYYY-MM-DD"
            :value ""
            :input-actions [[:action/save [:field :value] :event/target.value]]}))))

