//index.js
//获取应用实例
const app = getApp()
import request from '../../utils/request'

Page({
  data: {
    motto: '为了提供更好的服务，请点击授权登录❤',
    userInfo: {},
    hasUserInfo: false,
    canIUse: wx.canIUse('button.open-type.getUserInfo')
  },
  //事件处理函数
  bindViewTap: function() {
    wx.switchTab({
      url: '../logs/logs'
    })
  },
  onLoad: function () {
    if (app.globalData.userInfo) {
      this.setData({
        userInfo: app.globalData.userInfo,
        hasUserInfo: true
      })
      wx.switchTab({
        url: '/pages/index/index'
      })
    } else if (this.data.canIUse){
      // 由于 getUserInfo 是网络请求，可能会在 Page.onLoad 之后才返回
      // 所以此处加入 callback 以防止这种情况
      app.userInfoReadyCallback = res => {
        this.setData({
          userInfo: res.userInfo,
          hasUserInfo: true
        })
        wx.switchTab({
          url: '/pages/index/index'
        })
      }
    } else {
      // 在没有 open-type=getUserInfo 版本的兼容处理
      wx.getUserInfo({
        success: res => {
          console.log(res)
          app.globalData.userInfo = res.userInfo
          this.setData({
            userInfo: res.userInfo,
            hasUserInfo: true
          })
          wx.switchTab({
            url: '/pages/index/index'
          })
        }
      })
    }
  },
  getUserInfo: function(e) {
    wx.login({
      success: res => {
        // 发送 res.code 到后台换取 openId, sessionKey, unionId
        console.log(res)
        request({
          url: `/wxLogin?code=${res.code}`,
          method: 'POST',
          data: e.detail.userInfo
        })
        .then(res => {
          console.log(res)
          if(res.data.code == 200) {
            wx.setStorageSync('cookie', res.cookies[0]);
            wx.setStorageSync('userInfo', e.detail.rawData)
            wx.setStorageSync('openid', res.data.openid)
            app.globalData.userInfo = e.detail.userInfo
            this.setData({
              userInfo: e.detail.userInfo,
              hasUserInfo: true
            })
            wx.switchTab({
              url: '/pages/index/index'
            })
          }
        })
      }
    })
  }
})
