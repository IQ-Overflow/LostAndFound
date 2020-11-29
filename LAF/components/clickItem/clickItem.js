// components/clickItem/clickItem.js
Component({
  /**
   * 组件的属性列表
   */
  properties: {

  },

  /**
   * 组件的初始数据
   */
  data: {
    arr:[
      {
        url: '/pages/myItems/myItems?type=found',
        img:'',
        content: '我捡到的'
      },
      {
        url: '/pages/myItems/myItems?type=lookfor',
        // img: 'http://qkjen8x7q.hn-bkt.clouddn.com/lost.png',
        content: '我丢失的'
      },
      {
        url:'/pages/apply/apply',
        img: '',
        content:'我申请的'
      },
      {
        url:'/pages/applied/applied',
        img: '',
        content:'向我提交的申请'
      }
    ]
  },

  /**
   * 组件的方法列表
   */
  methods: {
    toDetail(e){
      let url = e.currentTarget.dataset.url
      wx.navigateTo({
        url:url
      })
    }
  }
})
