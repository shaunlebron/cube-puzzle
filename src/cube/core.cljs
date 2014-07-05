(ns cube.core)

(enable-console-print!)

(def dir-names {[ 0  0  1] "forward"
                [ 0  0 -1] "back"
                [ 0  1  0] "up"
                [ 0 -1  0] "down"
                [ 1  0  0] "right"
                [-1  0  0] "left"})

(def initial-segs [2 2 2 2 1 1 1 2 2 1 1 2 1 2 1 1 2])
(def initial-pos [0 0 0])
(def initial-dir [0 0 1])
(def initial-cube #{initial-pos})

(def dirs [[ 0  0  1]
           [ 0  0 -1]
           [ 0  1  0]
           [ 0 -1  0]
           [ 1  0  0]
           [-1  0  0]])

(defn log [x] (js/console.log (pr-str x)))

(defn write-seg
  [[x y z] [dx dy dz] len cube]
  (let [new-pos [(+ x dx) (+ y dy) (+ z dz)]
        new-cube (conj cube new-pos)]
  (cond
    (zero? len) cube
    (contains? cube new-pos) nil
    :else (recur new-pos [dx dy dz] (dec len) new-cube))))

(defn dim-width
  [cube i]
  (let [xs (map #(nth % i) cube)]
    (js/Math.abs (- (apply min xs) (apply max xs)))))

(defn valid-cube?
  [cube]
  (and (<= (dim-width cube 0) 2)
       (<= (dim-width cube 1) 2)
       (<= (dim-width cube 2) 2)))

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

(defn get-valid-dirs
  [pos dirs cube]
  )

(defn search
  [pos dir cube segs]
  (if-not (seq segs)
    (do
      (log "done.")
      (log cube)
      (log (count cube))
      true)
    (if-let [new-cube (write-seg pos dir (first segs) cube)]
      (when (valid-cube? new-cube)
        (let [new-pos (mapv (fn [a da] (+ a (* da (first segs)))) pos dir)
              dirs (get-dir-options dir)
              search-next #(search new-pos % new-cube (rest segs))]
          (when-let [success-path (first (filter search-next dirs))]
            (log (get dir-names dir))
            true))))))

(log (search initial-pos
             initial-dir
             initial-cube
             initial-segs))
