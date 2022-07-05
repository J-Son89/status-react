(ns quo2.screens.tags
  (:require [quo.react-native :as rn]
            [quo.design-system.colors :as colors]
            [quo.previews.preview :as preview]
            [quo2.components.tags :as quo2.tags]
            [status-im.react-native.resources :as resources]
            [reagent.core :as reagent]))

(def descriptor [{:label   "Size:"
                  :key     :size
                  :type    :select
                  :options [{:key   32
                             :value "32"}
                            {:key   24
                             :value "24"}]}])

(defn cool-preview []
  (let [state  (reagent/atom {:size  32})]
    (fn []
      [rn/view {:margin-bottom 50
                :padding       16}
       [rn/view {:flex 1}
        [preview/customizer state descriptor]]
       [rn/view {:padding-vertical 60
                 :flex-direction   :row
                 :justify-content  :center}
        [quo2.tags/tags (merge @state
                               {:default-active :all
                                :data [{:id 1 :label "Crypto"   :resource (resources/reactions :angry)}
                                       {:id 2 :label "NFT"      :resource (resources/reactions :love)}
                                       {:id 4 :label "DeFi"     :resource (resources/reactions :laugh)}]
                                :on-change #(println "Active tab" %)})]]])))

(defn preview-tags []
  [rn/view {:background-color (:ui-background @colors/theme)
            :flex             1}
   [rn/flat-list {:flex                      1
                  :keyboardShouldPersistTaps :always
                  :header                    [cool-preview]
                  :key-fn                    str}]])