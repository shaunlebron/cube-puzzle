# Cube Puzzle

Found this puzzle and realized I was doing a depth first search trying to solve
it.  So I wrote a program in
[ClojureScript](https://github.com/shaunlebron/ClojureScript-Syntax-in-15-minutes)
to do it.

The puzzle is a chain of 27 cubes that when solved forms a 3x3x3 cube.  Each
cube freely rotates along the axes connecting it to its adjacent cubes.  We can
visualize the chain's structure by laying it out flat (shown below on the
left).  The solved form is on the right.

<img src="http://i.imgur.com/cUz0x75.jpg" width="310px">
<img src="http://i.imgur.com/vkbY9Xz.jpg" width="310px">

Open index.html in a browser and view the console to see the steps for solving.

__To build:__

1. Install [Leiningen](http://leiningen.org/).
1. In a terminal, run the auto-compiler from the project directory:

    ```
    lein cljsbuild auto
    ```

1. Open "index.html" in your browser.
1. Open the developer console to see the results.
