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
    message: '',
    alertMessage: false, // 是否出现提示性的话
    pageHeightArr: [],
    currentRenderIndex: 0, // 当前渲染的是哪一屏
    windowHeight:0,// 窗口高度
    isMoreNews: true, // 是否有更多信息
    computedCurrentIndex:0,
    list:[]
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
  onPullDownRefresh() {
    console.log('刷新')
  },
  loadmore: function() {
    if (this.data.isMoreNews) {
      this.data.page++;
    }
    this.getDataList()
    // console.log('到底了')
  },
  // 获取每一屏的高度
  setHeight() {
    let that = this
    let query = wx.createSelectorQuery();
    query.select(`#warp_${that.data.page-1}`).boundingClientRect()
    query.exec((res) => {
      that.data.pageHeightArr[`${that.data.page-1}`] = res[0] && res[0].height
      // console.log(that.data.pageHeightArr)
      that.setData({
        pageHeightArr: that.data.pageHeightArr
      })
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
      console.log(this.data.page, res.data)
      let list = `dataList[${this.data.dataList.length}]`
      if (res.data.length === 0) {
        // console.log(res.data)
        // 如果没有数据的时候
        this.setData({
          alertMessage: true,
          isMoreNews: false // 表明没有更多信息
        }, () => {})
      } else {
        let that = this
        this.setData({
          [list]: res.data,
          alertMessage: false
        }, () => {
          that.setHeight()
          console.log(this.data.dataList)
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
        pID: this.data.oID + '',
        tID: this.data.uID,
        message: this.data.message
      },
      method: "POST"
    }).then(res => {
      console.log(res)
    })
  },
  scroll(){

  },
  //监听当前在哪个屏幕
  // scroll(e) {
  //   // console.log(e)
  //   setTimeout(() => {
  //     // console.log(e.detail.scrollTop)
  //     let scrollTop = e.detail.scrollTop
  //     let tempScrollTop = 0
  //     for (let i = 0; i < this.data.pageHeightArr.length; i++) {
  //       tempScrollTop = tempScrollTop + this.data.pageHeightArr[i]
  //       if (tempScrollTop > scrollTop + this.data.windowHeight - 30) {
  //         // 说明此时位于第i页
  //         this.data.computedCurrentIndex = i
  //         // console.log(this.data.currentRenderIndex)
  //         break;
  //       }
  //     }
  //     // this.data.currentRenderIndex = this.data.computedCurrentIndex;
  //     if(this.data.currentRenderIndex!==this.data.computedCurrentIndex){
  //       let tempList = new Array(this.data.page).fill(0)
  //       // console.log(tempList)
  //       tempList.forEach((item,index)=>{
  //         if(this.data.computedCurrentIndex-1<=index&&index<=this.computedCurrentIndex+1){
  //           tempList[index] = this.data.dataList[index]
  //         }else{
  //           tempList[index] = {height:this.data.pageHeightArr[index]}
  //         }
  //       })
  //       this.data.currentRenderIndex = this.data.computedCurrentIndex;
  //       this.setData({
  //         list: tempList
  //       }, () => {
  //         console.log(this.data.list)
  //       })
  //     }
     
  //   }, 500)
  //   // console.log('index')
  // },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function(options) {
    this.setData({
      windowHeight: wx.getSystemInfoSync().windowHeight,
    })
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