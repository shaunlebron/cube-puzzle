(ns cube.core)

(enable-console-print!)

(def initial-segs [2 2 2 2 1 1 1 2 2 1 1 2 1 2 1 1 2])
(def initial-cube #{})

(def dirs [[ 0  0  1]
           [ 0  0 -1]
           [ 0  1  0]
           [ 0 -1  0]
           [ 1  0  0]
           [-1  0  0]])

(defn log [x] (js/console.log (pr-str x)))

(defn write-seg
  [[x y z] [dx dy dz] len cube]
  (if (zero? len)
    cube
    (let [new-pos [(+ x dx) (+ y dy) (+ z dz)]
          new-cube (conj cube new-pos)]
      (recur new-pos [dx dy dz] (dec len) new-cube))))

(defn dim-width
  [cube i]
  (let [xs (map #(nth % i) cube)]
    (js/Math.abs (- (apply min xs) (apply max xs)))))

(defn valid-cube?
  [cube]
  (and (<= (dim-width cube 0) 3)
       (<= (dim-width cube 1) 3)
       (<= (dim-width cube 2) 3)))

(defn get-dir-axis
  [dir]
  (->> dir
       (map-indexed vector)
       (remove #(zero? (second %)))
       (first)
       (first)))

(defn get-dir-options
  [dir]
  (let [i (get-dir-axis dir)]
    (filter #(zero? (nth % i)) dirs)))

(defn search
  [pos dir cube segs path]
  nil)

(log (->> initial-cube
          (write-seg [0 0 0] [0 0 1] 3)
          (write-seg [0 0 0] [0 0 -1] 3)))

(log (get-dir-options [0 1 0]))
