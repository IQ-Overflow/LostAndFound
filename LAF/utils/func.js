
export let throttle  = function () {
  let prev = Date.now()
  return function (delay) {
    let nowTime = Date.now()
    let delayTime = delay? delay : 1000 //默认是一秒
    if(nowTime-prev >= delayTime) {
      prev = Date.now()
      return true
    }
    false
  }
}

/*
* 截取日期时间
* @param String 时间字符串如: 2020-10-10 20:33:22 
*/
export let getDayTime = function (timeString) {
  let curDate = new Date();
  let curDay = curDate.getDate()
  let moodDay = timeString.slice(8,10)
  let moodTime = ''
  if(curDay == moodDay) {
    moodTime = timeString.slice(11,16)
  } else {
    moodTime = timeString.slice(5,10)
  } 
  
  return moodTime
}

export default {
  throttle
}