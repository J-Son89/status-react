(ns quo.previews.reacts
  (:require [reagent.core :as reagent]
            [quo.core :as quo]
            [status-im.ui.components.icons.icons :as icons] 
            [quo.react-native :as rn]
            [quo.design-system.colors :as colors]
            [quo.previews.preview :as preview]))


(def all-props (preview/list-comp [clicks        [1122 212 0]
                                   dark?         [false true false]
                                   neutral?      [true false true]
                                   emoji         ["😛" "👍" "❤️"]]
                                  {:emoji emoji
                                   :clicks clicks
                                   :dark?   dark?
                                   :neutral? neutral?}))

(defn render-item
  "Add your emoji as a param here"
  [{:keys [emoji clicks dark? neutral?]}] 
  (let [text-color (if dark? "white" "black")
        clicks-positive? (pos-int? clicks)]
    [rn/view {:style {:display "flex"
                      :flex-direction "row"
                      :justify-content :space-evenly 
                      :padding-vertical 8
                      :padding-horizontal (if (not clicks-positive?)
                                            0
                                            8)
                      :background-color   (if dark?
                                            (if neutral? "#192438" "black")
                                            (if neutral? "#F0F2F5" "white"))
                      
                      :margin-top 25
                      :border-radius 10
                      :border-color (if dark? "white" "black")
                      :border-width 1}}
     [quo/text {:style {:color text-color}}
      (str emoji (if clicks-positive?
                   (str " " clicks)
                   ""))]]))

(defn preview-reacts []
  [rn/view {:background-color (:ui-background @colors/theme) 
            :display "flex" 
            :flex-direction "column"
            :align-items "center"}
   [rn/flat-list {:keyboardShouldPersistTaps :always 
                  :data                      all-props
                  :render-fn                 render-item
                  :key-fn                    str}]])


