(ns modern-cljs.login
  (:use [domina :only [by-id value]]))

;; define the function to be attached to the form submission evnt
(defn validate-form []
  ;; get email & password element from id attritube in HTML form
  (let [email (by-id "email")
        password (by-id "password")]
    (if (and (> (count (value email)) 0)
             (> (count (value password)) 0))
      true
      (do (js/alert "Please, complete the form!")
          false))))

;; define the fn to attach validate-form to onsubmit of the form
(defn ^:export init []
  ;; verify that js/document exists and that it has getElementById
  (if (and js/document
           (.-getElementById js/document))
    ;; get loginForm by element id and its onsubmit to validate
    (let [login-form (.getElementById js/document "loginForm")]
      (set! (.-onsubmit login-form) validate-form))))
