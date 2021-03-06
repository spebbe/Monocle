package monocle.function

import monocle.MonocleSuite

import scalaz.IMap
import scalaz.std.string._

class AtExample extends MonocleSuite {

  test("at creates a Lens from a Map, IMap to an optional value") {
    (Map("One" -> 2, "Two" -> 2) applyLens at("Two") get) shouldEqual Some(2)

    (Map("One" -> 1, "Two" -> 2) applyLens at("One") set Some(-1))  shouldEqual Map("One" -> -1, "Two" -> 2)

    (IMap("One" -> 2, "Two" -> 2) applyLens at("Two") get) shouldEqual Some(2)

    (IMap("One" -> 1, "Two" -> 2) applyLens at("One") set Some(-1))  shouldEqual IMap("One" -> -1, "Two" -> 2)


    // can delete a value
    (Map("One" -> 1, "Two" -> 2) applyLens at("Two") set None) shouldEqual Map("One" -> 1)
    (IMap("One" -> 1, "Two" -> 2) applyLens at("Two") set None) shouldEqual IMap("One" -> 1)

    // add a new value
    (Map("One" -> 1, "Two" -> 2) applyLens at("Three") set Some(3)) shouldEqual Map("One" -> 1, "Two" -> 2, "Three" -> 3)
    (IMap("One" -> 1, "Two" -> 2) applyLens at("Three") set Some(3)) shouldEqual IMap("One" -> 1, "Two" -> 2, "Three" -> 3)
  }

  test("at creates a Lens from a Set to an optional element of the Set") {
    (Set(1, 2, 3) applyLens at(2) get) shouldEqual Some(())
    (Set(1, 2, 3) applyLens at(4) get) shouldEqual None

    (Set(1, 2, 3) applyLens at(4) set Some(())) shouldEqual Set(1, 2, 3, 4)
    (Set(1, 2, 3) applyLens at(2) set None)     shouldEqual Set(1, 3)
  }

}
