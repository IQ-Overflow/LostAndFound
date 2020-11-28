// pages/searchThing/searchThing.js
import request from '../../utils/request.js'
Page({

  /**
   * 页面的初始数据
   */
  data: {
    array: ['捡到', '丢失'],
    index:0,
    title:'',
    content:'',
    contactMethod:'',
    contact:''
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {

  },
  bindPickerChange (e) {
    this.data.index = e.detail.value
    console.log(this.data.index)
    this.setData({
      index: this.data.index
    })
  },
  bindFormSubmit(e){
    this.data.title = e.detail.value.title
    this.data.content = e.detail.value.content
    this.data.contact = e.detail.value.contact
    // console.log(this.data.contact)
    request({
      url:'/others/publishOthers',
      data:{
        title:this.data.title,
        content: this.data.content,
        flag: this.data.index === 0 ? true: false,
        contact: this.data.contact
      },
      method:'POST'
    }).then(res=>{
      if (res.data.msg==='发布成功'){
        wx.showToast({
          title: '发布成功',
          duration:1000,
          icon:'success'
        })
      }
    })
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