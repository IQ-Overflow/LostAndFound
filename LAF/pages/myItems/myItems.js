// pages/myItems/myItems.js
import request from '../../utils/request'
Page({

  /**
   * 页面的初始数据
   */
  data: {
    mode: 'normal',
    idCardList: [],
    normalCardList: [],
    type: 'found'     //当前页面类型，found表示我捡到的，lookfor表示我寻找的
  },

  changeMode(e) {
    let mode = e.target.dataset.mode
    this.setData({
      mode
    })
    if(this.data.mode == 'normal') {

    } else {
      
    }
  },


  getMyCardList() {
    if(this.data.type == 'found') {
      request({
        url: '/whatIFound'
      })
      .then(res => {
        if(res.statusCode == 200) {
          this.setData({
            idCardList: res.data[1],
            normalCardList: res.data[0]
          })
        }
      })
    } else if(this.data.type == 'lookfor') {
      request({
        url: '/myLostItems'
      })
      .then(res => {
        if(res.statusCode == 200) {
          this.setData({
            idCardList: res.data[1],
            normalCardList: res.data[0]
          })
        }
      })
    }
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    this.setData({
      type: options.type
    })
    this.getMyCardList()
  },

  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady: function () {

  },

  /**
   * 生命周期函数--监听页面显示
   */
  onShow: function () {

  },

  /**
   * 生命周期函数--监听页面隐藏
   */
  onHide: function () {

  },

  /**
   * 生命周期函数--监听页面卸载
   */
  onUnload: function () {

  },

  /**
   * 页面相关事件处理函数--监听用户下拉动作
   */
  onPullDownRefresh: function () {

  },

  /**
   * 页面上拉触底事件的处理函数
   */
  onReachBottom: function () {

  },

  /**
   * 用户点击右上角分享
   */
  onShareAppMessage: function () {

  }
})