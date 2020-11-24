// import login from '../utils/login'

export default function request(option) {
  let cookie = wx.getStorageSync("cookie")
  console.log(cookie)
  if(!cookie && option.url.indexOf('/wxLogin') == -1) {
    //非登录接口
    wx.getSetting({
      success: res => {
        if (res.authSetting['scope.userInfo']) {
          // 已经授权，可以直接调用 getUserInfo 获取头像昵称，不会弹框
          wx.getUserInfo({
            success: res => {
              let userInfo = res.userInfo
              wx.login({
                success: res => {
                  // 发送 res.code 到后台换取 openId, sessionKey, unionId
                  request({
                    url: `/wxLogin?code=${res.code}`,
                    method: 'POST',
                    data: userInfo
                  })
                  .then(res => {
                    if(res.data.code == 200) {
                      wx.setStorageSync('cookie', res.cookies[0]);
                      wx.setStorageSync('userInfo', JSON.stringify(userInfo))
                      wx.setStorageSync('openid', res.data.openid)
                      request(res.cookies[0], option);
                    }
                  })
                }
              })
            }
          })
        } else {            //没有获得授权
          wx.navigateTo({
            url: '/pages/login/login'
          })
        }
        return;
      }
    })
  }
  return realRequest(cookie, option);
}

function realRequest(cookie, option) {
  let p = new Promise((resolve, reject) => {
    wx.request({
      url: "http://106.52.47.25:8080" + option.url,
      data: option.data || {},
      header: { 
        "Content-Type": "application/json",
        'Cookie': cookie
      },
      method: option.method || 'GET',
      success: async (res)=>{
        resolve(res)
      },
      fail: reject,
    })
  })
  return p;
}