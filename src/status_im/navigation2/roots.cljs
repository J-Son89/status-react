(ns status-im.navigation2.roots
  (:require [quo.design-system.colors :as colors]
            [status-im.utils.platform :as platform]))

(defn status-bar-options []
  (if platform/android?
    {:navigationBar {:backgroundColor colors/white}
     :statusBar     {:backgroundColor colors/white
                     :style           (if (colors/dark?) :light :dark)}}
    {:statusBar {:style (if (colors/dark?) :light :dark)}}))

(defn roots []
  {:home-stack
   {:root
    {:stack {:id       :home-stack
             :children [{:component {:name    :chat-stack
                                     :id      :chat-stack
                                     :options (merge (status-bar-options)
                                                     {:topBar {:visible false}})}}]}}}})
