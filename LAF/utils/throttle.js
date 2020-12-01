const throttle = (cb, delay = 1000) => {
  let previous = 0
  return (...args) => {
    const now = + new Date()
    if (now - previous > delay) {
      previous = now
      cb.apply(this, args)
    }
  }
}
export default throttle
