# A minimal "Hello, world!" program with test and launcher

Roughly based on the [Scala.js tutorial](http://www.scala-js.org/doc/tutorial.html).

## Development

- Launch `sbt`.

- For continuous compilation of your changes type `~fastOptJS`.

- Open the `target/scala-2.11/classes/index-dev.html` in the browser of your choice.

- Open browser console.

## Production

- Launch `sbt` and type `fullOptJS`.

- Then extract the following files:
```
target/scala-2.11/
  +- hello-scalajs-2-opt.js
  +- hello-scalajs-2-launcher.js
  +- classes/
          +- index.html
```

- Finally open the `index.html` in the browser of your choice.