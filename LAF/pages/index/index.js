// pages/index/index.js
import request from '../../utils/request.js'
Page({

  /**
   * 页面的初始数据
   */
  data: {
    label: 'lost',
    page: 1, // 当前页码
    dataList: [],
    isToastShow: false,
    message: ''
  },
  changeTab(e) {
    this.setData({
      label: e.currentTarget.dataset.label
    })
    // console.log(e)
  },
  add() {
    // 发布丢失或者捡到
    wx.navigateTo({
      url: '/pages/searchThing/searchThing',
    })
  },
  getDataList() {
    request({
      url: '/others/getOthersForPage',
      data: {
        pageNext: this.data.page,
        pageSize: 10
      },
      method: 'POST'
    }).then(res => {
      // console.log(res.data)
      if (res.data) {
        this.setData({
          dataList: res.data
        })
      }
    })
  },
  applyContact(e) {
    // console.log(e.detail)
    // 获取申请理由
    let {
      oID,
      uID
    } = e.detail
    this.data.oID = oID
    this.data.uID = uID
    let that = this
    this.setData({
      isToastShow: true
    })
    // console.log(oID, uID)
  },
  // 点击了确认键
  clickSureBtn(e) {
    this.data.message = e.detail
    this.sendApplyRequest()
  },
  // 申请联系
  sendApplyRequest() {
    // console.log(this.data.uID)
    // console.log(this.data.oID)
    // console.log(this.data.message)

    request({
      url: '/reason/appliesForContact',
      data: {
        pID: this.data.oID+'',
        tID: this.data.uID,
        message: this.data.message
      },
      method:"POST"
    }).then(res => {
      console.log(res)
    })
  },
  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function(options) {
    // this.getDataList()
  },

  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady: function() {

  },

  /**
   * 生命周期函数--监听页面显示
   */
  onShow: function() {
    this.getDataList()
  },

  /**
   * 生命周期函数--监听页面隐藏
   */
  onHide: function() {

  },

  /**
   * 生命周期函数--监听页面卸载
   */
  onUnload: function() {

  },

  /**
   * 页面相关事件处理函数--监听用户下拉动作
   */
  onPullDownRefresh: function() {

  },

  /**
   * 页面上拉触底事件的处理函数
   */
  onReachBottom: function() {

  },

  /**
   * 用户点击右上角分享
   */
  onShareAppMessage: function() {

  }
})