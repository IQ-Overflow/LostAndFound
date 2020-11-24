// pages/index/index.js
import request from '../../utils/request.js'
Page({

  /**
   * 页面的初始数据
   */
  data: {
    label:'lost',
    page:1, // 当前页码
    dataList:[]
  },
  changeTab(e){
    this.setData({
      label: e.currentTarget.dataset.label
    })
    // console.log(e)
  },
  add(){
    // 发布丢失或者捡到
    wx.navigateTo({
      url: '/pages/searchThing/searchThing',
    })
  },
  getDataList(){
    request({
      url:'/others/getOthersForPage',
      data:{
        pageNext:this.data.page,
        pageSize:10
      },
      method:'POST'
    }).then(res=>{
      this.setData({
        dataList:res.data
      })
      // console.log(res.data)
    })
  },
  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    this.getDataList()
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