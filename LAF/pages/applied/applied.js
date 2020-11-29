// pages/applied/applied.js
import request from '../../utils/request.js'
Page({

  /**
   * 页面的初始数据
   */
  data: {
    dataList:[]
  },
  getDataList(){
    request({
      url:'/reason/receivedApplies'
    }).then(res=>{
      // console.log(res)
      this.setData({
        dataList:res.data
      })
    })
  },
  refuse(e){
    this.useApi(e, '/reason/refuseApplies','refuse')
  },
  agree(e){
    this.useApi(e, '/reason/agreeApplies','agree')
   
  },
  useApi(e,url,type){
    let { fid, pid } = e.detail
    let index = e.currentTarget.dataset.index
    // console.log(url)
    // console.log(fid,pid,index)
    request({
      url: url,
      data: {
        pID: pid,
        fID: fid
      },
      method: 'POST'
    }).then(res => {
      console.log(res)
      if (res.data.code === 1) {
        let that = this
        wx.showToast({
          title: res.data.msg,
          success: function () {
            // that.data.dataList.splice(index, 1)
            // that.setData({
            //   dataList: that.data.dataList
            // })
            let item = that.data.dataList[index].state
            // console.log(item)
            this.setData({
              [item]: type ==='refuse'?2:1
            })
          }
        })
      } else if (res.data.code === 0) {
        wx.showToast({
          title: res.data.msg,
        })
      }
    })
  },
  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {

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
    this.getDataList()
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