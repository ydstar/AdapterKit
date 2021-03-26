# AdapterKit

<img src="https://github.com/ydstar/AdapterKit/blob/main/preview/show.gif" alt="动图演示效果" width="250px">

轻量级高扩展adapter

## 导入方式

仅支持`AndroidX`
```
dependencies {
     implementation 'com.android.ydkit:adapter-kit:1.0.0'
}
```

## 使用方法

```java
val adapter = IAdapter(this)

recycler_view.layoutManager = GridLayoutManager(this,2)
recycler_view.adapter=adapter

//数据
val mList:ArrayList<IDataItem<*, out RecyclerView.ViewHolder>> = ArrayList()
mList.add(TopTabDataItem(NewModel()))//顶部导航
mList.add(TopBanner(NewModel()))//顶部轮播图
mList.add(GridDataItem(NewModel()))//金刚区
mList.add(ActivityDataItem(NewModel()))//活动区域
mList.add(ItemTabDataItem(NewModel()))//商品tab栏

for (i in 0..9) {
    if (i % 2 == 0) {
       //feeds流的视频类型
       mList.add(VideoDataItem(1, NewModel()))
    } else {
       //feeds流的图片类型
       mList.add(ImageDataItem(1, NewModel()))
    }
}

//为adapter添加数据
adapter.addItems(mList, false)
```


## License
```text
Copyright [2021] [ydStar]

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```