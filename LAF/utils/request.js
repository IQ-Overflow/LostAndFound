// import login from '../utils/login'

export default function request(option) {
  let cookie = wx.getStorageSync("cookie") || ''
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
        resolve(res)
      },
      fail: reject,
    })
  })
}