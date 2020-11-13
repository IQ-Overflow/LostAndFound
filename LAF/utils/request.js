// import login from '../utils/login'

export default function request(option) {
  let cookie = wx.getStorageSync("sessionid") || ''
  return new Promise((resolve, reject) => {
    wx.request({
      url: "http://106.52.47.25:8080" + option.url,
      data: option.data || {},
      header: { 
        "Content-Type": "application/json",
        'Cookie': cookie
      },
      method: option.method || 'GET',
      success: async (res)=>{
        // if(res.data.status == 800 || res.data.data == 802) {
        //   try {
        //     wx.removeStorageSync('token')
        //   } catch (e) {
        //   }
        //   await login();
        //   resolve(115)
        // } else if (res.data.status == 801) {
        //   wx.navigateTo({
        //     url: '/pages/index/index'
        //   })
        //   wx.showToast({
        //     title: '请登录管理员',
        //     duration: 2000,
        //     icon: 'none'
        //   })
        //   resolve(res);
        // } else {
          resolve(res)
        // }
      },
      fail: reject,
    })
  })
}