package controllers

case class ElevatorState(
  open: Boolean = false,
  floor: Int = 0,
  up: Boolean = true,
  calls: List[Int] = Nil,
  dests: List[Int] = Nil) {

  def call(floor: Int, up: Boolean) =
    copy(calls = floor :: calls)

  def go(floor: Int) =
    copy(dests = floor :: dests)

  def enters =
    copy(calls = calls.diff(List(floor)))

  def exits =
    copy(dests = dests.diff(List(floor)))

  def all = dests ::: calls

  def next: (String, ElevatorState) = {
    if (all.contains(floor) && !open)
      ("OPEN", copy(open = true))
    else if (!all.contains(floor) && open)
      ("CLOSE", copy(open = false))
    else if (!open && all.exists(_ > floor))
      ("UP", this.copy(up = true, floor = floor + 1))
    else if (!open && all.exists(_ < floor))
      ("DOWN", this.copy(up = false, floor = floor - 1))
    else ("NOTHING", this)
  }
}